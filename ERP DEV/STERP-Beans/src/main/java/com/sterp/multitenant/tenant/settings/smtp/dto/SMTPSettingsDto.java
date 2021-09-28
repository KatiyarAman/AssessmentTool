package com.sterp.multitenant.tenant.settings.smtp.dto;

import java.io.Serializable;

public class SMTPSettingsDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1880620833338471194L;
	private Integer id;
	private String host;
	private int port;
	private boolean auth;
	private int socketFactoryPort;
	private String socketFactoryClass;
	private String username;
	private String password;
	private boolean ssl;
	private boolean test;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public int getSocketFactoryPort() {
		return socketFactoryPort;
	}

	public void setSocketFactoryPort(int socketFactoryPort) {
		this.socketFactoryPort = socketFactoryPort;
	}

	public String getSocketFactoryClass() {
		return socketFactoryClass;
	}

	public void setSocketFactoryClass(String socketFactoryClass) {
		this.socketFactoryClass = socketFactoryClass;
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

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

}
