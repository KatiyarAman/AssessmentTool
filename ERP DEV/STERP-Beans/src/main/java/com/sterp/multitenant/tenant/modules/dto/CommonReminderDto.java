package com.sterp.multitenant.tenant.modules.dto;

import java.time.LocalDateTime;

public class CommonReminderDto {
	
	private Long id;
	
	private Long moduleId;
	
	private Long docRefId;
	
	private String remarks;
	
	private LocalDateTime postedTimestamp;
	
	private LocalDateTime reminderTimestamp;
	
	private String username;
	
	public CommonReminderDto() {
		super();
	}

	public CommonReminderDto(Long id, Long moduleId, Long docRefId, String remarks, LocalDateTime postedTimestamp,
			LocalDateTime reminderTimestamp, String username) {
		super();
		this.id = id;
		this.moduleId = moduleId;
		this.docRefId = docRefId;
		this.remarks = remarks;
		this.postedTimestamp = postedTimestamp;
		this.reminderTimestamp = reminderTimestamp;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getDocRefId() {
		return docRefId;
	}

	public void setDocRefId(Long docRefId) {
		this.docRefId = docRefId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDateTime getPostedTimestamp() {
		return postedTimestamp;
	}

	public void setPostedTimestamp(LocalDateTime postedTimestamp) {
		this.postedTimestamp = postedTimestamp;
	}

	public LocalDateTime getReminderTimestamp() {
		return reminderTimestamp;
	}

	public void setReminderTimestamp(LocalDateTime reminderTimestamp) {
		this.reminderTimestamp = reminderTimestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
