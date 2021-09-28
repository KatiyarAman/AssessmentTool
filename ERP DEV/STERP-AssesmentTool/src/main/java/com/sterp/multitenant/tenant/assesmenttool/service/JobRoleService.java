package com.sterp.multitenant.tenant.assesmenttool.service;

import java.util.Map;

import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobRoleDto;
import com.sterp.multitenant.tenant.entity.StatusEnum;

public interface JobRoleService {
	Map<String, Object> saveOrUpdate(JobRoleDto category);
	JobRoleDto getById(Long id);
	Map<String, Object> getJobRoleList(Map<String, Object> params);
	Map<String, Object> getSelectionList(StatusEnum status);
}
