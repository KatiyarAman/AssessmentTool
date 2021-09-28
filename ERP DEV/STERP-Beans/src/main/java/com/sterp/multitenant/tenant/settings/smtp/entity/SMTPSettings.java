package com.sterp.multitenant.tenant.settings.smtp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smtp_settings")
public class SMTPSettings implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6492150035207870420L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String host;
	private int port;
	@Column(name = "is_auth")
	private boolean auth;
	@Column(name = "socket_factory_port")
	private int socketFactoryPort;
	@Column(name = "socket_factory_class")
	private String socketFactoryClass;
	private String username;
	private String password;
	@Column(name = "is_SSL")
	private boolean ssl;

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

}
