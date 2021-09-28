package com.sterp.multitenant.tenant.settings.template.entity.dto;

import java.io.Serializable;

public class FieldValidationsResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2473283833328426910L;
	private Integer id;
	private Integer customFieldId;
	private String validationName;
	private String errorMessage;
	private Long status;
	private String matchCase;

	public FieldValidationsResponse(Integer id, Integer customFieldId, String validationName, String errorMessage,
			Long status, String matchCase) {
		this.id = id;
		this.customFieldId = customFieldId;
		this.validationName = validationName;
		this.errorMessage = errorMessage;
		this.status = status;
		this.matchCase = matchCase;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomFieldId() {
		return customFieldId;
	}

	public void setCustomFieldId(Integer customFieldId) {
		this.customFieldId = customFieldId;
	}

	public String getValidationName() {
		return validationName;
	}

	public void setValidationName(String validationName) {
		this.validationName = validationName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getMatchCase() {
		return matchCase;
	}

	public void setMatchCase(String matchCase) {
		this.matchCase = matchCase;
	}

}
