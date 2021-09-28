package com.sterp.multitenant.tenant.employee.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.employee.dto.EmployeeDTO;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

public interface EmployeeService {

	Map<String, Object> addOrUpdateEmployee(HttpServletRequest request ,EmployeeDTO employeeDTO, MultipartFile[] files);

	EmployeeDTO getEmployeeById(Long employeeId, boolean iscacheable);

	Map<String, Object> getSelectionList(boolean iscacheable, List<Boolean> isERPUser);

	public Map<String, Object> getEmployeeList(int page, int limit, String search, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, boolean filterRemove, int moduleId);

}
