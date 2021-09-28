package com.sterp.multitenant.tenant.settings.approval.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.settings.approval.dto.PendingApprovalDTO;
import com.sterp.multitenant.tenant.settings.approval.dto.PendingApproversDetails;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalDetails;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalSettings;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

public interface ApprovalSettingsService {

	public void deleteApprovalAsign(long approvalAsignId);

	public ApprovalSettings getApprovalSettingById(Integer approvalSettingId);

	public Long getApprovalSetting(boolean approvalLine, long moduleId, Long docId, String transactionNo, float value,
			Date time, int volume, Long createrId, Long layerId, Long status);

	public Long updateApproval(boolean approvalLine, long moduleId, String refType, Long docId, Long userId,
			String transactionNo, float value, Date time, int volume, Long status, Long createrId, String remarks, Long docStatus);

	List<ApprovalDetails> getApprovalList(long moduleId, Long docId, Long status);

	public ApprovalSettings saveApproval(ApprovalSettings approval);

	public List<UserEntity> getUserList();

	public int getApprovalSetting(long moduleId);
	
	public boolean getApprovalSettings(long moduleId);

	public List<Object> getApprovers(long moduleId);

	public List<Long> getApproverSettingIds();

	public String getApproverAssign(long moduleId, Long docId, Long userId);

	public List<PendingApproversDetails> getApprovers(long documentId, long moduleId);
	
	public Map<String, Object> getApprovarSettingsList(int page, int limit, String search, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, boolean filterRemove, int moduleId);

	public List<PendingApprovalDTO> getPendingApprovalLevelWise(long moduleId, Long docId);
}
