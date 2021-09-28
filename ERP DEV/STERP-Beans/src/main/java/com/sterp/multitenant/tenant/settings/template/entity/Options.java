package com.sterp.multitenant.tenant.settings.template.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "options")
@JsonIgnoreProperties(value = { "customField" })
public class Options implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6073116990732372302L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String value;

	private String title;

	@Column(name = "default_value")
	private boolean defaultValue;

	@Column(name = "custom_field_id")
	private Integer customFieldId;

//	@ManyToOne(cascade = CascadeType.ALL)
//	private CustomField customField;

	private Long status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Integer getCustomFieldId() {
		return customFieldId;
	}

	public void setCustomFieldId(Integer customFieldId) {
		this.customFieldId = customFieldId;
	}

	@Override
	public String toString() {
		return "Options [id=" + id + ", value=" + value + ", title=" + title + ", defaultValue=" + defaultValue
				+ ", customFieldId=" + customFieldId + ", status=" + status + "]";
	}
}
