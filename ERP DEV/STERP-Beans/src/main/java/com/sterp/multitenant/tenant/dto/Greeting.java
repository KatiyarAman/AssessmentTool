package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;

public class Greeting implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -905632039801324222L;
	private String content;

	public Greeting() {
	}

	public Greeting(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}