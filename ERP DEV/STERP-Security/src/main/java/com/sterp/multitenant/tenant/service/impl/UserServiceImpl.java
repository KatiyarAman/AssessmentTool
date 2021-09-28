package com.sterp.multitenant.tenant.service.impl;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.dto.ChangePasswordRequestDTO;
import com.sterp.multitenant.tenant.dto.BranchUserRoleResponseDTO;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.dto.UserDTO;
import com.sterp.multitenant.tenant.dto.UserResponseDTO;
import com.sterp.multitenant.tenant.entity.Authority;
import com.sterp.multitenant.tenant.entity.AuthorityType;
import com.sterp.multitenant.tenant.entity.BranchUserRole;
import com.sterp.multitenant.tenant.entity.UserBranchAssign;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.enumtype.AuthProvider;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.repository.BranchUserRoleRepository;
import com.sterp.multitenant.tenant.repository.RoleRepository;
import com.sterp.multitenant.tenant.repository.UserBranchAssignRepository;
import com.sterp.multitenant.tenant.repository.UserRepository;
import com.sterp.multitenant.tenant.service.UserService;
import com.sterp.multitenant.tenant.settings.template.entity.ScreenFilter;
import com.sterp.multitenant.utils.CommonUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	CommonUtils utils;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	SecurityAppServiceImpl appService;

	@Autowired
	UserBranchAssignRepository userBranchAssignRepository;

	@Autowired
	BranchUserRoleRepository branchUserRoleRepository;

//	@Override
//	public Map<String, Object> createUpdateUser(UserDTO user) {
//		Set<UserBranchAssign> storedAssignsBranch = new HashSet<UserBranchAssign>();
//		if (user.getId() == 0) {
//			if (userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()) != null)
//				throw new CustomException("User Already Exist!");
//		} else {
//			storedAssignsBranch = this.userRepository.findById(user.getId()).get().getBranchAssign();
//		}
//		UserEntity storeUser = modelMapper.map(user, UserEntity.class);
//		storeUser.setRoles(this.roleRepository.findById(user.getRoleId()).get());
//		Set<Authority> authorities = new HashSet<Authority>();
//		Authority authority = null;
//		Date date = new Date();
//		long timemili = date.getTime();
//		Timestamp tstamp = new Timestamp(timemili);
//		String publicUserId = utils.generateRandomString(30);
//		storeUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		storeUser.setUuid(publicUserId);
//		storeUser.setAuthorities(authorities);
//		// userEntity.setRoles(user.getRole());
//		
//		
//		if (user.getId() != 0) {
//			Optional<UserEntity> optional = this.userRepository.findById(user.getId());
//			if (optional.isPresent()) {
//				UserEntity userStored = optional.get();
//				authorities = userStored.getAuthorities();
//			}
//		} else {
//			storeUser.setCreatedAt(tstamp);
//			storeUser.setCreatedBy(appService.getCurrentUser().getId());
//			authority = new Authority(AuthorityType.ROLE_USER);
//			authorities.add(authority);
//		}
//		storeUser.setCompanyId(appService.getCurrentUser().getCompanyId());
//		storeUser.setBranchId(appService.getCurrentUser().getBranchId());
//		storeUser.setModifiedAt(tstamp);
//		storeUser.setModifiedBy(appService.getCurrentUser().getId());
//		storeUser.setFirstLogin(true);
//		storeUser.setProvider(AuthProvider.local);
//
//		Set<UserBranchAssign> assignsBranchNew = new HashSet<UserBranchAssign>();
//		Set<UserBranchAssign> assignsBranchExisting = new HashSet<UserBranchAssign>();
//		if (user.getBranchAssignDto().size() == 0) {
//			this.unAssignBranchFromUser(storedAssignsBranch);
//		} else {
//			for (UserBranchAssign userBranch : user.getBranchAssignDto()) {
//				UserBranchAssign assign = new UserBranchAssign();
//				if (userBranch.getId() != 0) {
//					assign.setId(userBranch.getId());
//					assign.setBranchId(userBranch.getBranchId());
//					assign.setUserId(userBranch.getUserId());
//					assignsBranchExisting.add(assign);
//				}
//
//			}
//		}
//		if (assignsBranchExisting.size() != 0) {
//			storedAssignsBranch = storedAssignsBranch.stream().filter(e -> !assignsBranchExisting.contains(e))
//					.collect(Collectors.toSet());
//		}
//		if (storedAssignsBranch.size() != 0) {
//			this.unAssignBranchFromUser(storedAssignsBranch);
//		}
//
//		storeUser.setBranchAssign(assignsBranchExisting);
//		UserEntity storedUserDetails = userRepository.saveAndFlush(storeUser);
//
//		for (UserBranchAssign userBranch : user.getBranchAssignDto()) {
//			UserBranchAssign assign = new UserBranchAssign();
//			if (userBranch.getId() == 0) {
//				assign.setId(userBranch.getId());
//				assign.setBranchId(userBranch.getBranchId());
//				assign.setUserId(storedUserDetails.getId());
//				assignsBranchNew.add(assign);
//			}
//		}
//		storeUser.setBranchAssign(assignsBranchNew);
//		this.userBranchAssignRepository.saveAll(assignsBranchNew);
//		Map<String, Object> response = new HashMap<String, Object>();
//		response.put("message",
//				user.getId() == 0 ? "user created successfully with username: " + storedUserDetails.getUsername()
//						: "details for username :" + storedUserDetails.getUsername() + " updated successfully");
//		return response;
//	}

	@Override
	public Map<String, Object> createUpdateUser(UserDTO user) {
		if (user.getId() == 0) {
			if (userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()) != null)
				throw new DuplicateKeyException("User Already Exist!");
		} else {
			UserEntity userNameDuplicasyCheck = this.userRepository.findById(user.getId()).get();
			if (!userNameDuplicasyCheck.getUsername().equalsIgnoreCase(user.getUsername())) {
				if (this.userRepository.findByUsername(user.getUsername()) != null) {
					throw new DataIntegrityViolationException("Username Already Exist!");
				}
			}
		}
		UserEntity storeUser = modelMapper.map(user, UserEntity.class);
		storeUser.setRoles(this.roleRepository.findById(user.getRoleId()).get());
		Set<Authority> authorities = new HashSet<Authority>();
		Authority authority = null;
		Date date = new Date();
		long timemili = date.getTime();
		Timestamp tstamp = new Timestamp(timemili);
		String publicUserId = utils.generateRandomString(30);
		storeUser.setUuid(publicUserId);
		storeUser.setAuthorities(authorities);
		long defaultBranchId = 0;
		for (UserBranchAssign uba : user.getBranchAssign()) {
			defaultBranchId = uba.getBranchId();
			break;
		}
		storeUser.setDefaultLoginBranch(defaultBranchId);
		if (user.getId() != 0) {
			storeUser.setPassword(this.userRepository.findById(user.getId()).get().getPassword());
			storeUser.setFirstLogin(false);
//			storeUser.setPassword(user.getPassword());
			Optional<UserEntity> optional = this.userRepository.findById(user.getId());
			if (optional.isPresent()) {
				UserEntity userStored = optional.get();
				authorities = userStored.getAuthorities();
			}
		} else {
			storeUser.setFirstLogin(true);
			storeUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			storeUser.setCreatedAt(tstamp);
			storeUser.setCreatedBy(appService.getCurrentUser().getId());
			authority = new Authority(AuthorityType.ROLE_USER);
			authorities.add(authority);
		}
		storeUser.setCompanyId(appService.getCurrentUser().getCompanyId());
		storeUser.setBranchId(appService.getCurrentUser().getBranchId());
		storeUser.setModifiedAt(tstamp);
		storeUser.setModifiedBy(appService.getCurrentUser().getId());
		storeUser.setProvider(AuthProvider.local);
		Map<String, Object> response = new HashMap<String, Object>();
		UserEntity storedUserDetails = this.userRepository.save(storeUser);
		response.put("message",
				user.getId() == 0 ? "user created successfully with username: " + storedUserDetails.getUsername()
						: "details for username :" + storedUserDetails.getUsername() + " updated successfully");
		return response;
	}

	public String unAssignBranchFromUser(Set<UserBranchAssign> userBranchAssignId) {
		this.userBranchAssignRepository.deleteAll(userBranchAssignId);
		return "DELETED";
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = this.userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
			for (Authority grantedAuthority : user.getAuthorities()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(grantedAuthority.getName().toString()));
			}
			return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		}
	}

	@Override
	public UserEntity getUserByUsername(String username) {
		UserEntity userEntity = userRepository.findByUsername(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException(username);
		}
//		UserDTO returnValue = new UserDTO();
//		BeanUtils.copyProperties(userEntity, returnValue);
		return userEntity;
	}

	@Override
	public UserDTO getUserById(long userId) {
		UserDTO userDTO = this.userRepository.findCustomUserResponseId(userId);
		userDTO.setBranchAssign(this.userBranchAssignRepository.findByUserId(userId));
		return userDTO;
	}

	@Override
	public Map<String, Object> updateUser(ChangePasswordRequestDTO object) throws UsernameNotFoundException {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			if (!object.getOldPassword().isEmpty()) {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(object.getUsername(), object.getOldPassword()));
			}
			UserEntity existingUser = this.userRepository.findByUsername(object.getUsername());
			existingUser.setModifiedAt(new Timestamp(new Date().getTime()));
			existingUser.setPassword(bCryptPasswordEncoder.encode(object.getNewPassword()));
			existingUser.setFirstLogin(existingUser.isFirstLogin() == true ? false : false);
			this.userRepository.save(existingUser);
			response.put("message", "Password Updated Successfully");
			return response;
		} catch (BadCredentialsException e) {
			throw new CustomException("Old Password Must Match.");
		}

	}

	@Override
	public Map<String, Object> updateUserIsFirstLogin(String userName) {
		Map<String, Object> response = new HashMap<String, Object>();
		UserEntity existingUser = this.userRepository.findByUsername(userName);
		existingUser.setFirstLogin(false);
		this.userRepository.save(existingUser);
		response.put("message", "Continue User Login Successfully");
		return response;
	}

	@Override
	public Map<String, Object> getbranchUserRoleForPermissionUpdate(long userId) {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.branchUserRoleRepository.findbranchUserRoleForPermissionUpdate(userId));
		return response;
	}

	@Override
	public Object updateBranchUserRole(List<BranchUserRoleResponseDTO> dto) {
		Type burList = new TypeToken<List<BranchUserRole>>() {
		}.getType();
		return this.branchUserRoleRepository.saveAll(this.modelMapper.map(dto, burList));
	}

	@Override
	public Map<String, Object> getUserList(int page, int limit, String search, String sort, String order,
			List<ScreenFilter> screenFilter, boolean filterRemove, int moduleId, String entryType) {
		UserEntity currentUser = this.appService.getCurrentUser();
		if (currentUser == null)
			throw new CustomException("Session has been timed out!");

		Map<String, Object> response = new HashMap<String, Object>();

		if ((sort == null) || (sort.isEmpty())) {
			sort = "id";
		}
		if ((order == null) || (order.isEmpty())) {
			order = Sort.Direction.ASC.toString();
		}
		int totalCount = Integer.parseInt(Long.valueOf(this.getCount()).toString());
		Page<UserResponseDTO> userListResponse = null;
		if (totalCount == 0) {
			throw new ResourceNotFoundException("No Record Found");
		}
		if ((search == null) || (search.isEmpty())) {
			userListResponse = this.userRepository.findCustom(
					this.appService.createPageRequestSortedSingleColumn((page - 1), limit, order.toUpperCase(), sort));
		} else {
			// userListResponse = this.userRepository.findCustomGlobalSearch(search,
			// this.appService.createPageRequestSortedSingleColumn((page - 1), limit,
			// order.toUpperCase(), sort));
		}

		response.put("totalCount", this.userRepository.count());
		response.put("data", userListResponse);
		return response;
	}

	@Override
	public Long getCount() {
		return this.userRepository.count();
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public Map<String, Object> getSelectionList(boolean iscacheable, Long status) {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> users = this.userRepository.getSelectionList(status);
		if (users.size() == 0) {
			throw new ResourceNotFoundException("No Active Employee found to be a User");
		}
		response.put("data", users);
		return response;
	}

}
