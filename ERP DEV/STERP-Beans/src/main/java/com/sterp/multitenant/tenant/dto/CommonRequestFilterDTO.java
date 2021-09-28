package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;

public class CommonRequestFilterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566340975673849975L;
	private String field;
	private String value;

	public CommonRequestFilterDTO(String field, String value) {
		this.field = field;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CommonRequestFilterDTO [field=" + field + ", value=" + value + "]";
	}

}
