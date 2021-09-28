package com.sterp.multitenant.security.oauth2;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sterp.multitenant.mastertenant.config.DBContextHolder;
import com.sterp.multitenant.mastertenant.entity.Clientage;
import com.sterp.multitenant.mastertenant.entity.MasterTenant;
import com.sterp.multitenant.mastertenant.service.ClientageService;
import com.sterp.multitenant.mastertenant.service.MasterTenantService;
import com.sterp.multitenant.security.UserPrincipal;
import com.sterp.multitenant.security.oauth2.users.OAuth2UserInfo;
import com.sterp.multitenant.security.oauth2.users.OAuth2UserInfoFactory;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.enumtype.AuthProvider;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.OAuth2AuthenticationProcessingException;
import com.sterp.multitenant.tenant.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	MasterTenantService masterTenantService;

	@Autowired
	ClientageService clientageService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	HttpSession session;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			// Throwing an instance of AuthenticationException will trigger the
			// OAuth2AuthenticationFailureHandler
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
				oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}

		String email = oAuth2UserInfo.getEmail();
		Clientage clientage = this.clientageService.getClientageByEmail(email);
		if (clientage == null) {
			throw new BadCredentialsException("Invalid tenant and user.");
		}

		MasterTenant masterTenant = this.masterTenantService.loadMasterTenantByClientage(clientage);// masterTenantService.findByClientId(48);
		if (null == masterTenant) {
			// logger.error("An error during getting tenant name");
			throw new BadCredentialsException("Invalid tenant and user.");
		}
		DBContextHolder.setCurrentDb(masterTenant.getDbName());
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);
		UserEntity user;
		if (userOptional.isPresent()) {
			user = userOptional.get();
			if (!user.getProvider()
					.equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
				throw new OAuth2AuthenticationProcessingException(
						"Looks like you're signed up with " + user.getProvider() + " account. Please use your "
								+ user.getProvider() + " account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			throw new CustomException("Please subscribe for the product before you can continue using social login!");
			// user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
		}
		session.setAttribute("tenantOrCientId", masterTenant.getTenantClientId());
		session.setAttribute("username", user.getUsername());
		return UserPrincipal.create(user, oAuth2User.getAttributes());
	}

//	private UserEntity registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
//		UserEntity user = new UserEntity();
//		user.setId(2L);
//		user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
//		user.setPassword(encoder.encode("admin123"));
//		user.setUsername(oAuth2UserInfo.getEmail());
//		user.setEmail(oAuth2UserInfo.getEmail());
//		user.setMobile(7827230454L);
//		return userRepository.save(user);
//	}

	private UserEntity updateExistingUser(UserEntity existingUser, OAuth2UserInfo oAuth2UserInfo) {
		// existingUser.setName(oAuth2UserInfo.getName());
		// existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
		return userRepository.save(existingUser);
	}

}
