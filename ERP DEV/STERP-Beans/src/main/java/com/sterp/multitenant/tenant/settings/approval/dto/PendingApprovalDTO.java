package com.sterp.multitenant.tenant.settings.approval.dto;

import java.util.List;

public class PendingApprovalDTO {

	String level;
	
	List<String> userGroup;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<String> getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(List<String> userGroup) {
		this.userGroup = userGroup;
	}

	@Override
	public String toString() {
		return "PendingApprovalDTO [level=" + level + ", userGroup=" + userGroup + "]";
	}
	
	
}
