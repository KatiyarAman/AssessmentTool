package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sterp.multitenant.tenant.settings.template.entity.CustomFieldData;

public class StatutoryDetailsRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3244073384304846927L;
	private Long id;
	private String labelName;
	private Boolean uploadRequired;
	private Long status;
	private List<CustomFieldData> customFieldDatas = new ArrayList<CustomFieldData>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Boolean getUploadRequired() {
		return uploadRequired;
	}

	public void setUploadRequired(Boolean uploadRequired) {
		this.uploadRequired = uploadRequired;
	}

	public List<CustomFieldData> getCustomFieldDatas() {
		return customFieldDatas;
	}

	public void setCustomFieldDatas(List<CustomFieldData> customFieldDatas) {
		this.customFieldDatas = customFieldDatas;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatutoryDetailsRequestDTO [id=" + id + ", labelName=" + labelName + ", uploadRequired="
				+ uploadRequired + ", status=" + status + ", customFieldDatas=" + customFieldDatas + "]";
	}

	

}
