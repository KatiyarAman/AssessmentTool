package com.sterp.multitenant.tenant.zone.dto;

import java.io.Serializable;

public class ZoneResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5996899359679643380L;
	private Long id;
	private String zoneCode;
	private String zoneName;
	private String description;
	private Long status;

	public ZoneResponseDTO(Long id, String zoneCode, String zoneName, String description, Long status) {
		this.id = id;
		this.zoneCode = zoneCode;
		this.zoneName = zoneName;
		this.description = description;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "ZoneResponseDTO [id=" + id + ", zoneCode=" + zoneCode + ", zoneName=" + zoneName + ", description="
				+ description + ", status=" + status + "]";
	}

	

}
