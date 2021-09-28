package com.sterp.multitenant.tenant.modules.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "roles")
public class Roles extends SuperBean implements Serializable {
	private static final long serialVersionUID = 5697823470128204029L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_code")
	private String roleCode;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_description")
	private String roleDescription;

//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name = "status")
//	private Long status;

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Set<RolePermissions> permissions = new HashSet<RolePermissions>();

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

	public Set<RolePermissions> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<RolePermissions> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Roles [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + ", roleDescription="
				+ roleDescription + ", permissions=" + permissions + "]";
	}

	
	
}