package com.sterp.multitenant.tenant.department.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.department.dto.DepartmentRequestDTO;
import com.sterp.multitenant.tenant.department.entity.Department;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

public interface DepartmentService {

	Map<String, Object> addOrUpdateDepartment(HttpServletRequest request, DepartmentRequestDTO departmentDto,
			MultipartFile[] files);

	Department getDepartmentById(Long departmentId, boolean iscacheable);

	Department getDepartmentById(Long departmentId);

	Map<String, Object> getSelectionList(boolean iscacheable);

	public Map<String, Object> getDepartmentList(int page, int limit, String search, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, boolean filterRemove, int moduleId);

}
