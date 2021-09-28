package com.sterp.multitenant.tenant.settings.approval.dto;

import java.io.Serializable;

public class PendingApproversDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1918278571427495310L;
	private long userId;
	private String approverName;
	//private String approverGroup;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	@Override
	public String toString() {
		return "PendingApproversDetails [userId=" + userId + ", approverName=" + approverName + "]";
	}

	/*
	 * public String getApproverGroup() { return approverGroup; }
	 * 
	 * public void setApproverGroup(String approverGroup) { this.approverGroup =
	 * approverGroup; }
	 */
	
	
}
