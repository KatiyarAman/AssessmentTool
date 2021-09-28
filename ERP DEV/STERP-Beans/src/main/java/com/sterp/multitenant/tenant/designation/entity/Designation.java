package com.sterp.multitenant.tenant.designation.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "designation")
public class Designation extends SuperBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2572152995969645976L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String designation;
	
	@Column(name = "short_name")
	private String shortName;
	
	private String description;

	/*
	 * @Enumerated(EnumType.ORDINAL) private StatusEnum status;
	 */
	
//	@Column(name="status")
//	private Long status;
	
	

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

	@Override
	public String toString() {
		return "Designation [id=" + id + ", designation=" + designation + ", shortName=" + shortName + ", description="
				+ description + "]";
	}


	

	
}
