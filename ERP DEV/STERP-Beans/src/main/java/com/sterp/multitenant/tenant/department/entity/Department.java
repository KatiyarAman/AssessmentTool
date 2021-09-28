package com.sterp.multitenant.tenant.department.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "department")
public class Department extends SuperBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8204216398761061419L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "department")
	private String department;

	@Column(name = "short_name")
	private String shortName;

	@Column(name = "description")
	private String description;

//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
//	
//	@Column(name="status")
//	private Long status;

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

	@Override
	public String toString() {
		return "Department [id=" + id + ", department=" + department + ", shortName=" + shortName + ", description="
				+ description + "]";
	}

	
	
}
