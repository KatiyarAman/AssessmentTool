package com.sterp.multitenant.tenant.zone.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sterp.multitenant.tenant.settings.template.entity.CustomFieldData;

public class ZoneRequestDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7102894484448851571L;
	private long id;
	private String zoneCode;
	private String zoneName;
	private String description;
	private Long status;
	private List<CustomFieldData> customFieldDatas = new ArrayList<CustomFieldData>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
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
		return "ZoneRequestDTO [id=" + id + ", zoneCode=" + zoneCode + ", zoneName=" + zoneName + ", description="
				+ description + ", status=" + status + ", customFieldDatas=" + customFieldDatas + "]";
	}
	
	

}
