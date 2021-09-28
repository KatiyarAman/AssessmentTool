package com.sterp.multitenant.tenant.modules.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_permissions")
public class RolePermissions implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4836271302146236364L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "module_action_id")
	private Long moduleActionId;

	@Column(name = "permission")
	private boolean permission;

	@Column(name = "role_id")
	private Long roleId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getModuleActionId() {
		return moduleActionId;
	}

	public void setModuleActionId(Long moduleActionId) {
		this.moduleActionId = moduleActionId;
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

}
