package com.sterp.multitenant.tenant.uploadutility.dto;

public class IfDefaultLoggedInData{
	private long companyId;
	private long branchId;
	private String companyName;
	private String branchName;
	private long createdById;
	private long modifiedBy;
	
	public IfDefaultLoggedInData() {
		// TODO Auto-generated constructor stub
	}
	
	public IfDefaultLoggedInData(long companyId, long branchId, String companyName, String branchName, long createdById,
			long modifiedBy) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.companyName = companyName;
		this.branchName = branchName;
		this.createdById = createdById;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public long getCreatedById() {
		return createdById;
	}

	public void setCreatedById(long createdById) {
		this.createdById = createdById;
	}

	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
