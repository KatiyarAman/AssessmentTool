package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;

public class CommonDropdownResponseDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3408606952448397523L;
	private int id;
	private String name;

	public CommonDropdownResponseDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
