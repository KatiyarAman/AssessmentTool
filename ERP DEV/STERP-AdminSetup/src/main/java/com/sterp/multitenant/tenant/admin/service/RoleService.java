package com.sterp.multitenant.tenant.admin.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.modules.entity.Roles;
import com.sterp.multitenant.tenant.settings.template.entity.ScreenFilter;

public interface RoleService {
	public Map<String, Object> addOrUpdateRole(HttpServletRequest request ,Roles roles, MultipartFile[] files);

	public Map<String, Object> getRoleList(int page, int limit, String search, String sort, String order,
			List<ScreenFilter> screenFilter, boolean filterRemove, int moduleId, String entryType);

	public Roles getRoleById(long id);

	public Map<String, Object> getActiveRolesDropdown();
}
