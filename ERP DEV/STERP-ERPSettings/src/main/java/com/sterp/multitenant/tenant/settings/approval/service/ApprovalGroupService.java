package com.sterp.multitenant.tenant.settings.approval.service;

import java.util.List;
import java.util.Map;

import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalGroup;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

public interface ApprovalGroupService {
	Map<String, Object> addOrUpdateApprovalGroup(ApprovalGroup aprovalGroup);

	ApprovalGroup getApprovalGroupById(Long approvalGroupId , boolean iscacheable);

	Map<String, Object> getSelectionList(boolean iscacheable, Long status);

	public Map<String, Object> getApprovarGroupList(int page, int limit, String search, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, boolean filterRemove, int moduleId);

}
