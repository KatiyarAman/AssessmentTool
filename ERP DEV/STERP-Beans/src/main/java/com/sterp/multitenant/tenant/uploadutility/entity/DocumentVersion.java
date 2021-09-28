package com.sterp.multitenant.tenant.uploadutility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "document_version")
public class DocumentVersion extends SuperBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2514803052309960554L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "document_id")
	private Long documentId;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "original_file_name")
	private String originalFileName;
	
	@Column(name = "hash_key")
	private String hashKey;
	
	@Column(name = "revision_number")
	private long revisionNumber;
	

//	private long status;
	
	@Column(name = "reason_for_failure")
	private String reasonForFailure;

	@Transient
	@JsonIgnore(value = true)
	@JsonIgnoreProperties(ignoreUnknown = true)
	private DocumentContent documentContent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public long getRevisionNumber() {
		return revisionNumber;
	}

	public void setRevisionNumber(long revisionNumber) {
		this.revisionNumber = revisionNumber;
	}
	public String getReasonForFailure() {
		return reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		this.reasonForFailure = reasonForFailure;
	}

	public DocumentContent getDocumentContent() {
		return documentContent;
	}

	public void setDocumentContent(DocumentContent documentContent) {
		this.documentContent = documentContent;
	}

	

}
