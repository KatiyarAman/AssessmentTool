package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;
import java.util.Date;

public class UserResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1520453800544260680L;
	private Long id;
	private String uuid;
	private String username;
	private String email;
	private Long mobile;
	private Integer passwordExpiry;
	private Long status;
	private Date createdAt;
	private Date modifiedAt;
	private String companyName;
	private String branchName;
	private String roleName;

	public UserResponseDTO(Long id, String uuid, String username, String email, Long mobile, Integer passwordExpiry,
			Long status, Date createdAt, Date modifiedAt, String companyName, String branchName,
			String roleName) {
		this.id = id;
		this.uuid = uuid;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.passwordExpiry = passwordExpiry;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.companyName = companyName;
		this.branchName = branchName;
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Integer getPasswordExpiry() {
		return passwordExpiry;
	}

	public void setPasswordExpiry(Integer passwordExpiry) {
		this.passwordExpiry = passwordExpiry;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
