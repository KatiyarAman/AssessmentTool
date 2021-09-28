package com.sterp.multitenant.tenant.department.dto;

import java.io.Serializable;

public class DepartmentResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8587045595528875404L;
	private long id;
	private String department;
	private String shortName;
	private String description;
	private Long status;

	public DepartmentResponseDTO(long id, String department, String shortName, String description, Long status) {
		this.id = id;
		this.department = department;
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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
		return "DepartmentResponseDTO [id=" + id + ", department=" + department + ", shortName=" + shortName
				+ ", description=" + description + ", status=" + status + "]";
	}
	
	

}
