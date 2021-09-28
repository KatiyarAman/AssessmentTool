package com.sterp.multitenant.tenant.modules.dto;

import java.io.Serializable;

public class ModuleActionMappingResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7327735723036389358L;
	private long id;
	private String actionName;

	public ModuleActionMappingResponseDTO(long id, String actionName) {
		this.id = id;
		this.actionName = actionName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Override
	public String toString() {
		return "ModuleActionMappingResponseDTO [id=" + id + ", actionName=" + actionName + "]";
	}

}
