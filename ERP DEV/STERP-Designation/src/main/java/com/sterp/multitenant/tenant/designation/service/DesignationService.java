package com.sterp.multitenant.tenant.designation.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.designation.dto.DesignationRequestDTO;
import com.sterp.multitenant.tenant.designation.entity.Designation;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

public interface DesignationService {

	Map<String, Object> addOrUpdateDesignation(HttpServletRequest request, DesignationRequestDTO designationDTO,
			MultipartFile[] files);

	Designation getDesignationById(Long designationId, boolean iscacheable);

	Designation getDesignationById(Long designationId);

	Map<String, Object> getSelectionList(boolean iscacheable);

	public Map<String, Object> getDesignationList(int page, int limit, String search, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, boolean filterRemove, int moduleId);
}
