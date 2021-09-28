package com.sterp.multitenant.tenant.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "notifications")
public class Notifications implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8495645132457174099L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ref_type")
	private String refType;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "module_id")
	private long moduleId;

	@Column(name = "ref_doc_id")
	private Long refDocId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "notification_date")
	private Date notificationDate;

	@Column(name = "status")
	private Long status;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRefDocId() {
		return refDocId;
	}

	public void setRefDocId(Long refDocId) {
		this.refDocId = refDocId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public Long getId() {
		return id;
	}

}
