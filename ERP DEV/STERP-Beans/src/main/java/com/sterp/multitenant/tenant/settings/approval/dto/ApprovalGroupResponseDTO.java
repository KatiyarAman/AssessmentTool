package com.sterp.multitenant.tenant.settings.approval.dto;

import java.io.Serializable;

public class ApprovalGroupResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4118629113843449860L;
	
	private long id;
	private String approvalGroupName;
	private Long status;
	
	public ApprovalGroupResponseDTO(long id, String approvalGroupName, Long status) {
		this.id = id;
		this.approvalGroupName = approvalGroupName;
		this.status = status;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getApprovalGroupName() {
		return approvalGroupName;
	}
	public void setApprovalGroupName(String approvalGroupName) {
		this.approvalGroupName = approvalGroupName;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ApprovalGroupResponseDTO [id=" + id + ", approvalGroupName=" + approvalGroupName + ", status=" + status
				+ "]";
	}
	
	
	
	
}
