package com.sterp.multitenant.tenant.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "device_verification_otp")
public class DeviceVerificationOTP {
	private static final int EXPIRATION = 60 * 24;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String otp;

	@OneToOne(targetEntity = DeviceMetadata.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "device_id")
	private  DeviceMetadata deviceMetadata;

	@Column(name = "expiry_date")
	private Date expiryDate;

	public DeviceVerificationOTP() {
		// TODO Auto-generated constructor stub
	}
	

	public DeviceVerificationOTP(String otp, DeviceMetadata deviceMetadata) {
		this.otp = otp;
		this.deviceMetadata = deviceMetadata;
	}

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(cal.getTime().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}


	public void setOtp(String otp) {
		this.otp = otp;
	}


	public DeviceMetadata getDeviceMetadata() {
		return deviceMetadata;
	}


	public void setDeviceMetadata(DeviceMetadata deviceMetadata) {
		this.deviceMetadata = deviceMetadata;
	}


	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(int expiryTimeInMinutes) {
		this.expiryDate = this.calculateExpiryDate(EXPIRATION);
	}

}