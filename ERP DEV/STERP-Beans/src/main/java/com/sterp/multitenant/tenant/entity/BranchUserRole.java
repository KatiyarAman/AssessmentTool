package com.sterp.multitenant.tenant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "branch_user_role")
public class BranchUserRole extends SuperBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1045385632792723275L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "module_action_id")
	private long moduleActionId;

	@Column(name = "branch_user_assign_id")
	private long branchUserAssignId;

	private boolean permission;

	@Column(name = "role_id")
	private long roleId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getModuleActionId() {
		return moduleActionId;
	}

	public void setModuleActionId(long moduleActionId) {
		this.moduleActionId = moduleActionId;
	}

	public long getBranchUserAssignId() {
		return branchUserAssignId;
	}

	public void setBranchUserAssignId(long branchUserAssignId) {
		this.branchUserAssignId = branchUserAssignId;
	}

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
