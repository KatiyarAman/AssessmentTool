package com.sterp.multitenant.tenant.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "common_reminders")
public class CommonReminder extends SuperBean {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "module_id")
	private Long moduleId;
	
	@Column(name = "doc_ref_id")
	private Long docRefId;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "posted_timestamp")
	private LocalDateTime postedTimestamp;
	
	@Column(name = "reminder_timestamp")
	private LocalDateTime reminderTimestamp;
	
	@Transient
	private String username;
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getDocRefId() {
		return docRefId;
	}
	public void setDocRefId(Long docRefId) {
		this.docRefId = docRefId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}
