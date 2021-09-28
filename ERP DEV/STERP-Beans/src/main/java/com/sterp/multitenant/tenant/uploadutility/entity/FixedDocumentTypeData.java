package com.sterp.multitenant.tenant.uploadutility.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "fixed_document_type_data")
public class FixedDocumentTypeData extends SuperBean implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -3485181882231159950L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "module_statutory_mapping_id")
	private long moduleStatutoryMappingId;
	
	private String value;
	
	@Column(name = "document_ref")
	private long documentRef;
	
	@Column(name = "effective_from_date")
	private Date effectiveFromDate;
	
	@Column(name = "efffective_to_date")
	private Date efffectiveToDate;
	
	@Column(name = "ref_doc")
	private long refDoc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getModuleStatutoryMappingId() {
		return moduleStatutoryMappingId;
	}

	public void setModuleStatutoryMappingId(long moduleStatutoryMappingId) {
		this.moduleStatutoryMappingId = moduleStatutoryMappingId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getDocumentRef() {
		return documentRef;
	}

	public void setDocumentRef(long documentRef) {
		this.documentRef = documentRef;
	}

	public Date getEffectiveFromDate() {
		return effectiveFromDate;
	}

	public void setEffectiveFromDate(Date effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	public Date getEfffectiveToDate() {
		return efffectiveToDate;
	}

	public void setEfffectiveToDate(Date efffectiveToDate) {
		this.efffectiveToDate = efffectiveToDate;
	}

	public long getRefDoc() {
		return refDoc;
	}

	public void setRefDoc(long refDoc) {
		this.refDoc = refDoc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
