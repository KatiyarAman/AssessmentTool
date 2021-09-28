package com.sterp.multitenant.tenant.core.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sterp.multitenant.tenant.core.services.CommonReminderService;
import com.sterp.multitenant.tenant.model.CommonReminder;

@RestController
@RequestMapping("/common_reminders")
public class CommonReminderController {
	
	@Autowired
	private CommonReminderService commonReminderService;
	
	@RequestMapping(path = {"/add", "/path"}, method = {RequestMethod.POST, RequestMethod.PUT})
	public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody CommonReminder commonReminder, @RequestHeader("moduleId") Long moduleId) {
		return ResponseEntity.ok(this.commonReminderService.addOrUpdate(commonReminder, moduleId));
	}
	
	
	@GetMapping("/list/{docRefId}")
	public ResponseEntity<Map<String, Object>> list(@PathVariable Long docRefId, @RequestHeader("moduleId") Long moduleId) {
		return ResponseEntity.ok(this.commonReminderService.getList(docRefId, moduleId));
	}
	
	
}
