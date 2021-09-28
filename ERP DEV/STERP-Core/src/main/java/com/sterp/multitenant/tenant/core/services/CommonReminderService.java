package com.sterp.multitenant.tenant.core.services;

import java.util.Map;

import com.sterp.multitenant.tenant.model.CommonReminder;

public interface CommonReminderService {

	Map<String, Object> addOrUpdate(CommonReminder commonReminder, Long moduleId);
	Map<String, Object> getList(Long docRefId, Long moduleId);

}
