package com.sterp.multitenant.tenant.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.admin.service.RoleService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.modules.dto.RoleResponse;
import com.sterp.multitenant.tenant.modules.entity.Roles;
import com.sterp.multitenant.tenant.repository.RoleRepository;
import com.sterp.multitenant.tenant.settings.template.entity.ScreenFilter;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	AppService appService;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired CommonService commonService;

	@Override
	public Map<String, Object> addOrUpdateRole(HttpServletRequest request ,Roles roles, MultipartFile[] files) {
		Map<String, Object> response = new HashMap<String, Object>();
//		Long id = roles.getId();
//		response.put("data", this.roleRepository.save(roles));
//		response.put("message", id == null ? "Created Successfully" : "Updated Successfully");
		response = this.commonService.addUpdate(request, roles, files);
		return response;
	}

	private Long getCount() {
		return this.roleRepository.count();
	}

	@Override
	public Map<String, Object> getRoleList(int page, int limit, String search, String sort, String order,
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
		Page<RoleResponse> roleListResponse = null;
		if (totalCount == 0) {
			throw new ResourceNotFoundException("No Record Found");
		}
		if ((search == null) || (search.isEmpty())) {
			roleListResponse = this.roleRepository
					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			roleListResponse = this.roleRepository.findCustomGlobalSearch(search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}

		response.put("totalCount", this.roleRepository.count());
		response.put("data", roleListResponse);
		return response;
	}

	@Override
	public Roles getRoleById(long id) {
		Optional<Roles> optional = this.roleRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Map<String, Object> getActiveRolesDropdown() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.roleRepository.getSelectionList(1L));
		return response;
	}

}
