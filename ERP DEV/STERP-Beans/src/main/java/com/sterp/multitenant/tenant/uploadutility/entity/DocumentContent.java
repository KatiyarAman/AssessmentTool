package com.sterp.multitenant.tenant.uploadutility.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "document_content")
public class DocumentContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "document_version_id")
	private long documentVersionId;

	@Lob
	@Column(name = "document_content")
	private Blob documentContent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDocumentVersionId() {
		return documentVersionId;
	}

	public void setDocumentVersionId(long documentVersionId) {
		this.documentVersionId = documentVersionId;
	}

	public Blob getDocumentContent() {
		return documentContent;
	}

	public void setDocumentContent(Blob documentContent) {
		this.documentContent = documentContent;
	}

}
