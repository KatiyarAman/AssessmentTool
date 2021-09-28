package com.sterp.multitenant.tenant.modules.dto;

import java.io.Serializable;

public class RoleResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5734018477626102726L;

	private Long id;

	private String roleCode;

	private String roleName;

	private String roleDescription;

	private Long status;

	public RoleResponse(Long id, String roleCode, String roleName, String roleDescription, Long status) {
		this.id = id;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RoleResponse [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + ", roleDescription="
				+ roleDescription + ", status=" + status + "]";
	}

	

}
