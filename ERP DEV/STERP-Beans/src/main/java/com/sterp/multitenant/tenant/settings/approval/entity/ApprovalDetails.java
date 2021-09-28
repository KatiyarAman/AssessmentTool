package com.sterp.multitenant.tenant.settings.approval.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "approval_details")
public class ApprovalDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9144080193525906613L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "module_id")
	private long moduleId;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "ref_doc_id")
	private Long refDocId;

	@Column(name = "from_user_id")
	private long fromUserId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "approval_date")
	private Date approvalDate;

	@Column(name = "approval_status")
	private Long approvalStatus;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private Long status;

	@Transient
	private String labelName;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
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

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Long getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Long approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ApprovalDetails [id=" + id + ", moduleId=" + moduleId + ", parentId=" + parentId + ", refDocId="
				+ refDocId + ", fromUserId=" + fromUserId + ", userId=" + userId + ", approvalDate=" + approvalDate
				+ ", approvalStatus=" + approvalStatus + ", remarks=" + remarks + ", status=" + status + ", labelName="
				+ labelName + "]";
	}

	
	
	

}
