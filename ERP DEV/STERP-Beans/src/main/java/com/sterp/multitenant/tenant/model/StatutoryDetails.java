package com.sterp.multitenant.tenant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "statutory_details")
public class StatutoryDetails extends SuperBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346651956989488062L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "label_name")
	private String labelName;

	@Column(name = "upload_required")
	private Boolean uploadRequired;

//	@Column(name = "status")
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name = "status")
//	private Long status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Boolean getUploadRequired() {
		return uploadRequired;
	}

	public void setUploadRequired(Boolean uploadRequired) {
		this.uploadRequired = uploadRequired;
	}

	@Override
	public String toString() {
		return "StatutoryDetails [id=" + id + ", labelName=" + labelName + ", uploadRequired=" + uploadRequired + "]";
	}
	
	

}
