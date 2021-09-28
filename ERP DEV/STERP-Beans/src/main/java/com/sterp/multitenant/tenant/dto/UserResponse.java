package com.sterp.multitenant.tenant.dto;

public class UserResponse {
	private long userId;
	private String userName;

	public UserResponse() {
		// TODO Auto-generated constructor stub
	}

	public UserResponse(long userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}