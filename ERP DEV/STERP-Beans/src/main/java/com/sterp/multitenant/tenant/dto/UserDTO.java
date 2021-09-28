package com.sterp.multitenant.tenant.dto;

import java.io.Serializable;
import java.util.Set;

import com.sterp.multitenant.tenant.entity.UserBranchAssign;

public class UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2233707723100152052L;
	private long id;
	private String uuid;
	private String username;
	private String password;
	private String email;
	private Long mobile;
	private Integer passwordExpiry;
	private Long status;
	private Set<UserBranchAssign> branchAssign;
	private Long roleId;
	private Long employeeId;

	public UserDTO(long id, String uuid, String username, String password, String email, Long mobile,
			Integer passwordExpiry, Long status, Long roleId, Long employeeId) {
		this.id = id;
		this.uuid = uuid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.passwordExpiry = passwordExpiry;
		this.status = status;
		this.roleId = roleId;
		this.employeeId = employeeId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<UserBranchAssign> getBranchAssign() {
		return branchAssign;
	}

	public void setBranchAssign(Set<UserBranchAssign> branchAssign) {
		this.branchAssign = branchAssign;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

}
