package com.sterp.multitenant.tenant.settings.template.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sterp.multitenant.tenant.settings.template.entity.CustomField;
import com.sterp.multitenant.tenant.settings.template.entity.ScreenFilter;
import com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldByModuleAndFieldTypeRequestDTO;
import com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldRequestDto;

public interface CustomFieldService {
	public Map<String, Object> getActiveCustomFieldByModuleIdOrderByDisplayOrder(long moduleId, boolean iscacheable,
			List<Boolean> shortLinkEnabled, String type, HttpServletRequest httpServletRequest);

	public Map<String, Object> getAll();

	public Map<String, Object> saveOrUpdate(CustomFieldRequestDto customFieldRequestDto);

	public Map<String, Object> getActiveFieldByModuleIdAndFieldType(CustomFieldByModuleAndFieldTypeRequestDTO dto);

	public CustomField getCustomFieldById(int id);

	public Map<String, Object> getCustomFieldList(int page, int limit, String search, String sort, String order,
			List<ScreenFilter> screenFilter, boolean filterRemove, int moduleId, String entryType);

	public List<CustomField> getCustomFieldByModuleId(long moduleId);

}
