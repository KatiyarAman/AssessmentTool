package com.sterp.multitenant.tenant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status_details")
public class StatusDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2513038260880016088L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// ` bigint NOT NULL AUTO_INCREMENT,
	@Column(name = "value")
	private int value;// ` int DEFAULT NULL,
	@Column(name = "description")
	private String description;// ` varchar(100) DEFAULT NULL,
	@Column(name = "class_style")
	private String classStyle;// varchar(180) DEFAULT NULL,

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

}
