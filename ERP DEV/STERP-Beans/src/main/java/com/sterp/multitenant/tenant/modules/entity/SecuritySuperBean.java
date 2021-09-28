package com.sterp.multitenant.tenant.modules.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class SecuritySuperBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2319116100233115069L;

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

	@Column(name = "company_id")
	private long companyId;

	@Column(name = "branch_id")
	private long branchId;

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

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

}
