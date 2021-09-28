package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;

public class StatusDetailResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9027072362571751378L;
	private Integer id;
	private int value;
	private String description;
	private String classStyle;

	public StatusDetailResponse(Integer id, int value, String description, String classStyle) {
		this.id = id;
		this.value = value;
		this.description = description;
		this.classStyle = classStyle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

}
