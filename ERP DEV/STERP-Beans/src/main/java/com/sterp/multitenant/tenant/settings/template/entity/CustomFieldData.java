package com.sterp.multitenant.tenant.settings.template.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "custom_field_data")
public class CustomFieldData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 932287213250523342L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// bigint NOT NULL AUTO_INCREMENT,

	// @ManyToOne(cascade = CascadeType.ALL)

	@Column(name = "custom_field_id")
	private int customFieldId;// `custom_field_id` bigint DEFAULT NULL,

//	@Column(name = "document_id")
//	private int documentId;// `document_id` bigint DEFAULT NULL,

	@Column(name = "field_data")
	private String fieldData;// `field_data` longtext,

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCustomFieldId() {
		return customFieldId;
	}

	public void setCustomFieldId(int customFieldId) {
		this.customFieldId = customFieldId;
	}

//	public int getDocumentId() {
//		return documentId;
//	}
//
//	public void setDocumentId(int documentId) {
//		this.documentId = documentId;
//	}

	public String getFieldData() {
		return fieldData;
	}

	public void setFieldData(String fieldData) {
		this.fieldData = fieldData;
	}

	@Override
	public String toString() {
		return "CustomFieldData [id=" + id + ", customFieldId=" + customFieldId + ", fieldData=" + fieldData + "]";
	}
}
