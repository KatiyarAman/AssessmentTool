package com.sterp.multitenant.dto;

import java.io.Serializable;

/**
 * @author Navinder Singh
 */
public class UserLoginDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5097393202716501351L;
	private String userName;
	private String password;
	private Integer tenantOrClientId;

	public UserLoginDTO() {
	}

	public UserLoginDTO(String userName, String password, Integer tenantOrClientId) {
		this.userName = userName;
		this.password = password;
		this.tenantOrClientId = tenantOrClientId;
	}

	public String getUserName() {
		return userName;
	}

	public UserLoginDTO setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserLoginDTO setPassword(String password) {
		this.password = password;
		return this;
	}

	public Integer getTenantOrClientId() {
		return tenantOrClientId;
	}

	public UserLoginDTO setTenantOrClientId(Integer tenantOrClientId) {
		this.tenantOrClientId = tenantOrClientId;
		return this;
	}
}
