package com.sterp.multitenant.tenant.settings.template.entity.dto;

import java.io.Serializable;

public class OptionsDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2215445594129628104L;
	private Integer id;
	private String value;
	private String title;
	private Integer customFieldId;
	private boolean defaultValue;
	private Long status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Integer getCustomFieldId() {
		return customFieldId;
	}

	public void setCustomFieldId(Integer customFieldId) {
		this.customFieldId = customFieldId;
	}

}
