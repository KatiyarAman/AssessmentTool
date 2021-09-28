package com.sterp.multitenant.mastertenant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "package")
public class Package implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5059963617443759517L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "package")
	private String packageName;
	@Column(name = "role_id")
	private Long roleId;
	@Column(name = "script_url")
	private String scriptUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getScriptUrl() {
		return scriptUrl;
	}

	public void setScriptUrl(String scriptUrl) {
		this.scriptUrl = scriptUrl;
	}
}
