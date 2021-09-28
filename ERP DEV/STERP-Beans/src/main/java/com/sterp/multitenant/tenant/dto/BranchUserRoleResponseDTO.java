package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;

public class BranchUserRoleResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8342839625377297786L;
	private Long id;
	private Long moduleActionId;
	private Long branchUserAssignId;
	private boolean permission;
	private Long roleId;
	private Long createdBy;
	private Long modifiedBy;
	private Long companyId;
	private Long branchId;
	private String branchName;
	private String title;
	private String actions;

	public BranchUserRoleResponseDTO(Long id, Long moduleActionId, Long branchUserAssignId, boolean permission,
			Long roleId, Long createdBy, Long modifiedBy, Long companyId, Long branchId, String branchName,
			String title, String actions) {
		this.id = id;
		this.moduleActionId = moduleActionId;
		this.branchUserAssignId = branchUserAssignId;
		this.permission = permission;
		this.roleId = roleId;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.companyId = companyId;
		this.branchId = branchId;
		this.branchName = branchName;
		this.title = title;
		this.actions = actions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModuleActionId() {
		return moduleActionId;
	}

	public void setModuleActionId(Long moduleActionId) {
		this.moduleActionId = moduleActionId;
	}

	public Long getBranchUserAssignId() {
		return branchUserAssignId;
	}

	public void setBranchUserAssignId(Long branchUserAssignId) {
		this.branchUserAssignId = branchUserAssignId;
	}

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}
}
