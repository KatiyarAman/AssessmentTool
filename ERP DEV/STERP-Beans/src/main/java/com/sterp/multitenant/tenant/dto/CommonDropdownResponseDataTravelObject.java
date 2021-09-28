package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;
public class CommonDropdownResponseDataTravelObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2900668510577039036L;
	private long id;
	private String name;
   
	public CommonDropdownResponseDataTravelObject(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}
