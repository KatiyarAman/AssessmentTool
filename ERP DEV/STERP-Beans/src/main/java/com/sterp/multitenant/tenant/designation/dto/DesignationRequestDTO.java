package com.sterp.multitenant.tenant.designation.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sterp.multitenant.tenant.settings.template.entity.CustomFieldData;

public class DesignationRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6616927704312982039L;
	private long id;
	private String designation;
	private String shortName;
	private String description;
	private Long status;
	private List<CustomFieldData> customFieldDatas = new ArrayList<CustomFieldData>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public List<CustomFieldData> getCustomFieldDatas() {
		return customFieldDatas;
	}

	public void setCustomFieldDatas(List<CustomFieldData> customFieldDatas) {
		this.customFieldDatas = customFieldDatas;
	}

	@Override
	public String toString() {
		return "DesignationRequestDTO [id=" + id + ", designation=" + designation + ", shortName=" + shortName
				+ ", description=" + description + ", status=" + status + ", customFieldDatas=" + customFieldDatas
				+ "]";
	}

	

}
