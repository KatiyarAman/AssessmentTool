package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sterp.multitenant.tenant.entity.Status;
import com.sterp.multitenant.tenant.model.StatusDetails;
import com.sterp.multitenant.tenant.settings.template.entity.CustomFieldData;

public class TypeRequestDTO implements Serializable {
	private static final long serialVersionUID = 4736855236543512898L;
	private Long id;
	private String type;
	private List<Long> modulesId = new ArrayList<>();
	private Long status;
	private List<CustomFieldData> customFieldDatas = new ArrayList<CustomFieldData>();
	private StatusDetails statusDetail;
	
	
	public Long getId() {
		return id;
	}

	public TypeRequestDTO() {
		super();
	}

	public TypeRequestDTO(Long id, String type, Long status) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public List<Long> getModulesId() {
		return modulesId;
	}

	public void setModulesId(List<Long> modulesId) {
		this.modulesId = modulesId;
	}

	public List<CustomFieldData> getCustomFieldDatas() {
		return customFieldDatas;
	}

	public void setCustomFieldDatas(List<CustomFieldData> customFieldDatas) {
		this.customFieldDatas = customFieldDatas;
	}

	public StatusDetails getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(StatusDetails statusDetails) {
		this.statusDetail = statusDetails;
	}

	
}
