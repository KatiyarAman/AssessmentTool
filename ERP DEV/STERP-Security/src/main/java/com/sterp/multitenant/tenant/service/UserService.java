package com.sterp.multitenant.tenant.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sterp.multitenant.dto.ChangePasswordRequestDTO;
import com.sterp.multitenant.tenant.dto.BranchUserRoleResponseDTO;
import com.sterp.multitenant.tenant.dto.UserDTO;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.settings.template.entity.ScreenFilter;

public interface UserService extends UserDetailsService {

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	Map<String, Object> createUpdateUser(UserDTO userDTO);

	UserEntity getUserByUsername(String username);
	
	Map<String, Object> getSelectionList(boolean iscacheable, Long status);

	UserDTO getUserById(long userId);

	List<UserEntity> getAllUsers();

	Long getCount();

	public Map<String, Object> updateUser(ChangePasswordRequestDTO object) throws UsernameNotFoundException;

	public Map<String, Object> updateUserIsFirstLogin(String userName);

	public Map<String, Object> getbranchUserRoleForPermissionUpdate(long userId);

	public Object updateBranchUserRole(List<BranchUserRoleResponseDTO> dto);

	public Map<String, Object> getUserList(int page, int limit, String search, String sort, String order,
			List<ScreenFilter> screenFilter, boolean filterRemove, int moduleId, String entryType);
	
}
