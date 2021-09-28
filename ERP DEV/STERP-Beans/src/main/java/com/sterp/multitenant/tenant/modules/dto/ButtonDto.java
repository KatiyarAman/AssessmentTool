package com.sterp.multitenant.tenant.modules.dto;

import java.util.List;

import com.sterp.multitenant.tenant.dto.DocumentRequestDTO;

public class ButtonDto {
	
	private long id;
	private String buttonName;
	private String buttonClass;
	private String icon;
	private String purpose;
	private Long status;
	private String type;
	private long actionId;
	private long statusDetailsId;
	private List<DocumentRequestDTO> documents;
	
	public ButtonDto() {
		super();
	}
	
	public ButtonDto(long id, String buttonName, String buttonClass, String icon, String purpose, Long status,
			String type) {
		super();
		this.id = id;
		this.buttonName = buttonName;
		this.buttonClass = buttonClass;
		this.icon = icon;
		this.purpose = purpose;
		this.status = status;
		this.type = type;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	public String getButtonClass() {
		return buttonClass;
	}
	public void setButtonClass(String buttonClass) {
		this.buttonClass = buttonClass;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	public long getStatusDetailsId() {
		return statusDetailsId;
	}

	public void setStatusDetailsId(long statusDetailsId) {
		this.statusDetailsId = statusDetailsId;
	}

	public List<DocumentRequestDTO> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentRequestDTO> documents) {
		this.documents = documents;
	}
}
