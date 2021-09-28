package com.sterp.multitenant.tenant.uploadutility.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "fixed_document_type")
public class FixedDocumentType extends SuperBean {

	private static final long serialVersionUID = 8911033681205124810L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "label_name")
	private String labelName;

	@Column(name = "upload_required")
	private boolean uploadRequired;

	@Column(name = "type_id")
	private long typeId;
//
//<<<<<<< .mine
////	private long status;
//||||||| .r9766
//	private long status;
//=======
//	//private long status;
//>>>>>>> .r9779

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public boolean isUploadRequired() {
		return uploadRequired;
	}

	public void setUploadRequired(boolean uploadRequired) {
		this.uploadRequired = uploadRequired;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
//
//<<<<<<< .mine
////	public long getStatus() {
////		return status;
////	}
//||||||| .r9766
//	public long getStatus() {
//		return status;
//	}
//=======
//	/*
//	 * public long getStatus() { return status; }
//	 * 
//	 * public void setStatus(long status) { this.status = status; }
//	 */
//>>>>>>> .r9779
//
//<<<<<<< .mine
////	public void setStatus(long status) {
////		this.status = status;
////	}
//
//||||||| .r9766
//	public void setStatus(long status) {
//		this.status = status;
//	}
//
//=======
//>>>>>>> .r9779
}
