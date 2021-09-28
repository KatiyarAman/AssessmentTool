package com.sterp.multitenant.tenant.model.request;

public class ModuleActionRequestModel {
	private String actionType;
	private Long status;

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

}
