package com.sterp.multitenant.tenant.designation.dto;

import java.io.Serializable;

public class DesignationResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8508695238881893027L;
	private long id;
	private String designation;
	private String shortName;
	private String description;
	private Long status;

	public DesignationResponseDTO(long id, String designation, String shortName, String description,
			Long status) {
		this.id = id;
		this.designation = designation;
		this.shortName = shortName;
		this.description = description;
		this.status = status;
	}

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

	@Override
	public String toString() {
		return "DesignationResponseDTO [id=" + id + ", designation=" + designation + ", shortName=" + shortName
				+ ", description=" + description + ", status=" + status + "]";
	}

	

}
