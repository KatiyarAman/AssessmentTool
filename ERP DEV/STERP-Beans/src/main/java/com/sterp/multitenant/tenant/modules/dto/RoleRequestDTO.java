package com.sterp.multitenant.tenant.modules.dto;

import java.io.Serializable;

public class RoleRequestDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3983098772309932301L;
	private Long id;
	private String roleCode;
	private String roleName;
	private String roleDescription;
	//@Enumerated(EnumType.STRING)
	private Long status;

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
		return "RoleRequestDTO [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + ", roleDescription="
				+ roleDescription + ", status=" + status + "]";
	}

	

}
