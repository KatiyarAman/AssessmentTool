package com.sterp.multitenant.tenant.settings.template.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "field_type")
public class FieldType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8561039024792542565L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// `id` bigint NOT NULL AUTO_INCREMENT,

	private String type;// `type` varchar(100) DEFAULT NULL,

	@Column(name = "backend_type")
	private String backendType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBackendType() {
		return backendType;
	}

	public void setBackendType(String backendType) {
		this.backendType = backendType;
	}

	@Override
	public String toString() {
		return "FieldType [id=" + id + ", type=" + type + ", backendType=" + backendType + "]";
	}

}
