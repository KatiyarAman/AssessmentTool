package com.sterp.multitenant.tenant.settings.template.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sterp.multitenant.tenant.dto.DocumentRequestDTO;
import com.sterp.multitenant.tenant.model.StatusDetails;

@MappedSuperclass
@JsonIgnoreProperties(value = { "attachments" })
public class SuperBean implements Serializable {
	
	private static final long serialVersionUID = -6788643021371787575L;

	@Column(name = "created_by", insertable = true, updatable = false)
	private Long createdBy;

	@Column(name = "modified_by", insertable = true, updatable = true)
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", columnDefinition = "TIMESTAMP", insertable = true, updatable = false)
	private Date createdDate = new Date(System.currentTimeMillis());

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", columnDefinition = "TIMESTAMP", insertable = true, updatable = true)
	private Date modifiedDate = new Date(System.currentTimeMillis());

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "document_id")
	private List<CustomFieldData> customFieldDatas;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "branch_id", nullable = true)
	private Long branchId;
	
	@Column(name= "status")
	private Long status;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "refDocId")
//	@Where(clause = "status='Active'")
//	private List<Attachment> attachments;
	
//	@Transient
//	private boolean approval;
//	
//	@Transient
//	private List<ApprovalDetails> approvalDetails;
//	
//	@Transient
//	private List<PendingApproversDetails> pendingApproversDetails;
	
//	@Transient
//	private boolean edit;
	
	@Transient
	private Map<String,Object> infoApproval;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status", insertable = false, updatable = false)
	private StatusDetails statusDetail;
	
	@Transient
	private List<DocumentRequestDTO> documents;
	

	public List<CustomFieldData> getCustomFieldDatas() {
		return customFieldDatas;
	}

	public void setCustomFieldDatas(List<CustomFieldData> customFieldDatas) {
		this.customFieldDatas = customFieldDatas;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public StatusDetails getStatusDetail() {
		return statusDetail;
	}

	public void setStatusDetail(StatusDetails statusDetail) {
		this.statusDetail = statusDetail;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Map<String, Object> getInfoApproval() {
		return infoApproval;
	}

	public void setInfoApproval(Map<String, Object> infoApproval) {
		this.infoApproval = infoApproval;
	}

	@Override
	public String toString() {
		return "SuperBean [createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", customFieldDatas=" + customFieldDatas + ", companyId="
				+ companyId + ", branchId=" + branchId + ", status=" + status + ", infoApproval=" + infoApproval
				+ ", statusDetail=" + statusDetail + "]";
	}

	public List<DocumentRequestDTO> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentRequestDTO> documents) {
		this.documents = documents;
	}

	
	

	
	
}
