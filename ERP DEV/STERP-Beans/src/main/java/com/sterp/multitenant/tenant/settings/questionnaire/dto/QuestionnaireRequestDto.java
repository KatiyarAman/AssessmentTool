package com.sterp.multitenant.tenant.settings.questionnaire.dto;

import java.io.Serializable;

public class QuestionnaireRequestDto implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5291983361000670296L;
	private Long id;
	private Long customFieldId;
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomFieldId() {
		return customFieldId;
	}

	public void setCustomFieldId(Long customFieldId) {
		this.customFieldId = customFieldId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "QuestionnaireRequestDto [id=" + id + ", customFieldId=" + customFieldId + ", value=" + value + "]";
	}

	

	

}
