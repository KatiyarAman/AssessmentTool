package com.sterp.multitenant.tenant.uploadutility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fixed_documents_type_modules_mapping")
public class FixedDocumentsTypeModulesMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "module_id")
	private long moduleId;
	
	@Column(name = "fixed_document_type_id")
	private long fixedDocumentTypeId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public long getFixedDocumentTypeId() {
		return fixedDocumentTypeId;
	}

	public void setFixedDocumentTypeId(long fixedDocumentTypeId) {
		this.fixedDocumentTypeId = fixedDocumentTypeId;
	}
	
	
}
