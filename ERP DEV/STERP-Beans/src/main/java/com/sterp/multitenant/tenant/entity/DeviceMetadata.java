package com.sterp.multitenant.tenant.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device_metadata")
public class DeviceMetadata implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7746035383911327322L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "device_details")
	private String deviceDetails;
	@Column(name = "location")
	private String location;
	@Column(name = "last_logged_in")
	private Date lastLoggedIn;
	
	@Column(name = "system_ip")
	private String systemIp;
	
	@Column(name = "enabled")
	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getSystemIp() {
		return systemIp;
	}

	public void setSystemIp(String systemIp) {
		this.systemIp = systemIp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDeviceDetails() {
		return deviceDetails;
	}

	public void setDeviceDetails(String deviceDetails) {
		this.deviceDetails = deviceDetails;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}

	public void setLastLoggedIn(Date lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}
}
