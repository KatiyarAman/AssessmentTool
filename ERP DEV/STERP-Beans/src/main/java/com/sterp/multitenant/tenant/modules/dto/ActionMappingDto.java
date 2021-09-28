package com.sterp.multitenant.tenant.modules.dto;

import java.util.List;

public class ActionMappingDto {
	private long id;
	private long actionId;
	private List<Long> buttonId;
	
	
	
	public ActionMappingDto() {
		super();
	}
	public ActionMappingDto(long actionId, List<Long> buttonId) {
		super();
		this.actionId = actionId;
		this.buttonId = buttonId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getActionId() {
		return actionId;
	}
	public void setActionId(long actionId) {
		this.actionId = actionId;
	}
	public List<Long> getButtonId() {
		return buttonId;
	}
	public void setButtonId(List<Long> buttonId) {
		this.buttonId = buttonId;
	}
	
	
}
