package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;

public class UserProfileDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8338334299823949606L;
	private int id;
	private String type;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
