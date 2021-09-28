package com.sterp.multitenant.tenant.uploadutility.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "documents")
public class Documents extends SuperBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3365556811451078509L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "document_folder_id")
	private long documentFolderId;
	@Column(name = "type_id")
	private long typeId;
	@Column(name = "document_name")
	private String documentName;
	@Column(name = "ref_doc")
	private long refDoc;
	@Column(name = "shown_in_module")
	private boolean shownInModule;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "document_id")
	Set<DocumentVersion> documentVersions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getDocumentFolderId() {
		return documentFolderId;
	}

	public void setDocumentFolderId(long documentFolderId) {
		this.documentFolderId = documentFolderId;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public long getRefDoc() {
		return refDoc;
	}

	public void setRefDoc(long refDoc) {
		this.refDoc = refDoc;
	}
	public boolean isShownInModule() {
		return shownInModule;
	}

	public void setShownInModule(boolean shownInModule) {
		this.shownInModule = shownInModule;
	}

	public Set<DocumentVersion> getDocumentVersions() {
		return documentVersions;
	}

	public void setDocumentVersions(Set<DocumentVersion> documentVersions) {
		this.documentVersions = documentVersions;
	}

	

}
