package com.sterp.multitenant.tenant.core.services.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.core.repository.CommonReminderRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.core.services.CommonReminderService;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.model.CommonReminder;
import com.sterp.multitenant.tenant.modules.dto.CommonReminderDto;

@Service
public class CommonReminderServiceImpl implements CommonReminderService {
	
	@Autowired
	private CommonReminderRepository commonReminderRepository;

	@Autowired
	private AppService appService;
	
	@Override
	public Map<String, Object> addOrUpdate(CommonReminder commonReminder, Long moduleId) {
		Map<String, Object> response = new HashMap<>();
		if (commonReminder.getId() == null) {
			response.put("message", "Reminder Created!");
			commonReminder.setCreatedDate(new Date());
			UserEntity ue = appService.getCurrentUser();
			commonReminder.setCreatedBy(ue.getId());
		}
		else {
			response.put("message", "Reminder Updated!");
			commonReminder.setModifiedDate(new Date());
			UserEntity ue = appService.getCurrentUser();
			commonReminder.setModifiedBy(ue.getId());
		}
		commonReminder.setPostedTimestamp(LocalDateTime.now());
		commonReminder.setModuleId(moduleId);
		CommonReminder savedReminder = this.commonReminderRepository.save(commonReminder);
		response.put("reminder", savedReminder);
		return response;
	}

	@Override
	public Map<String, Object> getList(Long docRefId, Long moduleId) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonReminderDto> reminders = this.commonReminderRepository.findAllByModuleIdAndDocRefIdOrderByPostedTimestampDesc(moduleId, docRefId);
		response.put("data", reminders);
		return response;
	}
	
	
}
