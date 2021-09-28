package com.sterp.multitenant.tenant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "attachment")
public class Attachment implements Serializable {
	private static final long serialVersionUID = -7557021232327799207L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "doc_type_id")
	private int docTypeId; // Document Type Id //example - Pan Card/GST Info/TIN Certificate/Adhaar/etc

	@Column(name = "file_name")
	private String fileName;

	@Lob
	@Column(name = "file_url")
	private String fileUrl;

	@Column(name = "ref_module_id")
	private long refModuleId;

	@Column(name = "ref_doc_id")
	private long refDocId;

	@Column(name = "document_description")

	private String documentDescription;

//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
	@Column(name = "status")
	private Long status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDocTypeId() {
		return docTypeId;
	}

	public void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public long getRefModuleId() {
		return refModuleId;
	}

	public void setRefModuleId(long refModuleId) {
		this.refModuleId = refModuleId;
	}

	public long getRefDocId() {
		return refDocId;
	}

	public void setRefDocId(long refDocId) {
		this.refDocId = refDocId;
	}

	public String getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	

//	public Integer getStatus() {
//		return status.getVal();
//	}
//
//	public void setStatus(Integer status) {
//		for (StatusEnum enumstatus : StatusEnum.values()) {
//			if (enumstatus.getVal() == status) {
//				this.status = enumstatus;
//			}
//		}
//	}

}
