package com.sterp.multitenant.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import com.sterp.multitenant.constant.UserStatus;
import com.sterp.multitenant.dto.AuthResponse;
import com.sterp.multitenant.mastertenant.config.DBContextHolder;
import com.sterp.multitenant.mastertenant.entity.MasterTenant;
import com.sterp.multitenant.mastertenant.service.ClientageService;
import com.sterp.multitenant.mastertenant.service.MasterTenantService;
import com.sterp.multitenant.security.UserTenantInformation;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.ExceptionResponse;
import com.sterp.multitenant.tenant.model.request.UserLoginRequestModel;
import com.sterp.multitenant.tenant.service.DeviceService;
import com.sterp.multitenant.tenant.service.SecurityAppService;
import com.sterp.multitenant.tenant.service.UserService;
import com.sterp.multitenant.utils.JwtTokenUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController implements Serializable {
	private static final long serialVersionUID = 6831968981313281239L;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	private Map<String, String> mapValue = new HashMap<>();
	private Map<String, String> userDbMap = new HashMap<>();

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	MasterTenantService masterTenantService;

	@Autowired
	ClientageService clientageService;

	@Autowired
	UserService userService;

	@Autowired
	SecurityAppService securityAppService;

	@Autowired
	DeviceService deviceService;

	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> userLogin(HttpServletRequest httpServletRequest,
			@RequestBody @NotNull UserLoginRequestModel userLoginRequestModel) throws Exception {
		LOGGER.info("userLogin() method call...");

		String clientIp = this.securityAppService.getClientIPAddress(httpServletRequest);

		System.out.println("Client Ip: " + clientIp);
//		System.out.println("Mac Id: " + this.securityAppService.getClientMACAddress(clientIp));
//		System.out.println("System Name: " + this.securityAppService.getSystemName());

		this.securityAppService.printClientInfo(httpServletRequest);
		if (null == userLoginRequestModel.getUsername() || userLoginRequestModel.getUsername().isEmpty()) {
			return new ResponseEntity<>("User name is required", HttpStatus.BAD_REQUEST);
		}
		// set database parameter
		MasterTenant masterTenant = masterTenantService.findByClientId(userLoginRequestModel.getTenantOrClientId());
		if (null == masterTenant || masterTenant.getStatus().toUpperCase().equals(UserStatus.INACTIVE)) {
			throw new RuntimeException("Please contact service provider.");
		}
		// Entry Client Wise value dbName store into bean.
		loadCurrentDatabaseInstance(masterTenant.getDbName(), userLoginRequestModel.getUsername());
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestModel.getUsername(),
						userLoginRequestModel.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		UserEntity userEntity = this.userService.getUserByUsername(userDetails.getUsername());

		if (userEntity.getDefaultLoginBranch() == null) {
			throw new CustomException(
					"No default branch set. Please choose a default branch to continue using the erp.");
		}
		
		if(userLoginRequestModel.isResendOTP()) {
			this.deviceService.resendOTP(userEntity, httpServletRequest);
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("message", "OTP has been sent to your registered mobile number.");
			response.put("errorCode", "2001");
			return ResponseEntity.ok(response);
		}
		if (this.deviceService.verifyDevice(userEntity, httpServletRequest)&&userLoginRequestModel.getOTP()==null) {
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("status", "OTP Required");
			response.put("errorCode", "2001");
			return ResponseEntity.ok(response);
		}
		else if(userLoginRequestModel.getOTP()!=null) {
			this.deviceService.verifyOTP(userEntity,userLoginRequestModel.getOTP(),httpServletRequest);
		}
		
		
		final String token = jwtTokenUtil.generateToken(userDetails.getUsername(),
				String.valueOf(userLoginRequestModel.getTenantOrClientId()), userEntity.getDefaultLoginBranch());
		// Map the value into applicationScope bean
		setMetaDataAfterLogin();

		

		// final String token = JWTConstants.TOKEN_PREFIX + "" +
		// jwtTokenUtil.generateToken(userDetails, siteResponse);
		// return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(),
		// siteResponse));
		return ResponseEntity.ok(new AuthResponse(userDetails.getUsername(), token, userEntity.isFirstLogin()));
	}

	@RequestMapping(value = "/refreshToken/{token}", method = RequestMethod.POST)
	public ResponseEntity<?> refreshToken(@PathVariable String token, HttpServletResponse response,
			HttpServletRequest request) throws javax.security.sasl.AuthenticationException {
		LOGGER.info("refreshToken() method call...");
		if (token.isEmpty()) {
			return new ResponseEntity<>("Token is required", HttpStatus.BAD_REQUEST);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			UserDetails userDetails = null;

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				userDetails = (UserDetails) auth.getPrincipal();
			}
			final String refreshtoken = jwtTokenUtil.getRefreshToken(token, userDetails, 0L);
			return ResponseEntity.ok(new AuthResponse(userName, refreshtoken, false));
		} catch (BadCredentialsException e) {
			ExceptionResponse error = new ExceptionResponse();
			error.setStatus(404);
			error.setMessage("INVALID_TOKEN");
			error.setError(e.getMessage());
			error.setPath(request.getRequestURI());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/refreshToken/{token}/{branchId}")
	public ResponseEntity<?> refreshTokenWhenBranchChanged(HttpServletRequest request, @PathVariable String token,
			@PathVariable Long branchId)
			throws javax.security.sasl.AuthenticationException {
		LOGGER.info("refreshToken() method call...");
		if (token.isEmpty()) {
			return new ResponseEntity<>("Token is required", HttpStatus.BAD_REQUEST);
		}
		if (branchId == null) {
			return new ResponseEntity<>("Branch Id is required", HttpStatus.BAD_REQUEST);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			UserDetails userDetails = null;

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				userDetails = (UserDetails) auth.getPrincipal();
			}
			final String refreshtoken = jwtTokenUtil.getRefreshToken(token, userDetails, branchId);
			return ResponseEntity.ok(new AuthResponse(userName, refreshtoken, false));
		} catch (BadCredentialsException e) {
			ExceptionResponse error = new ExceptionResponse();
			error.setStatus(404);
			error.setMessage("INVALID_TOKEN");
			error.setError(e.getMessage());
			error.setPath(request.getRequestURI());
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
	}

	private void loadCurrentDatabaseInstance(String databaseName, String userName) {
		DBContextHolder.setCurrentDb(databaseName);
		mapValue.put(userName, databaseName);
	}

	@Bean(name = "userTenantInfo")
	@ApplicationScope
	public UserTenantInformation setMetaDataAfterLogin() {
		UserTenantInformation tenantInformation = new UserTenantInformation();
		if (mapValue.size() > 0) {
			for (String key : mapValue.keySet()) {
				if (null == userDbMap.get(key)) {
					// Here Assign putAll due to all time one come.
					userDbMap.putAll(mapValue);
				} else {
					userDbMap.put(key, mapValue.get(key));
				}
			}
			mapValue = new HashMap<>();
		}
		tenantInformation.setMap(userDbMap);
		return tenantInformation;
	}

	@GetMapping(value = "/tenantInfo/{subdomain}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTenantIdBySubDomain(
			@PathVariable(name = "subdomain", required = true) String subDomain) {
		return ResponseEntity.ok(this.clientageService.getTenantIdBySubDomain(subDomain));
	}
}
