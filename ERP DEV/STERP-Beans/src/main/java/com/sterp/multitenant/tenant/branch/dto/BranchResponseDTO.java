package com.sterp.multitenant.tenant.branch.dto;

import java.io.Serializable;

public class BranchResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1332445627595250953L;
	private long id;
	private String type;
	private String branchName;
	private String branchCode;
	private String shortName;
	private String zoneId;
	private Long cityId;
	private String address;
	private String email;
	private String reportingBranch;
	private String description;
	private String contactPerson;
	private Long phone;
	private Long mobile;
	private Long pincode;
	private String website;
	private Long status;

	public BranchResponseDTO(long id, String type, String branchName, String branchCode, String shortName,
			String zoneId, Long cityId, String address, String email, String reportingBranch, String description,
			String contactPerson, Long phone, Long mobile, Long pincode, String website, Long status) {
		this.id = id;
		this.type = type;
		this.branchName = branchName;
		this.branchCode = branchCode;
		this.shortName = shortName;
		this.zoneId = zoneId;
		this.cityId = cityId;
		this.address = address;
		this.email = email;
		this.reportingBranch = reportingBranch;
		this.description = description;
		this.contactPerson = contactPerson;
		this.phone = phone;
		this.mobile = mobile;
		this.pincode = pincode;
		this.website = website;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReportingBranch() {
		return reportingBranch;
	}

	public void setReportingBranch(String reportingBranch) {
		this.reportingBranch = reportingBranch;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	
	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BranchResponseDTO [id=" + id + ", type=" + type + ", branchName=" + branchName + ", branchCode="
				+ branchCode + ", shortName=" + shortName + ", zoneId=" + zoneId + ", cityId=" + cityId + ", address="
				+ address + ", email=" + email + ", reportingBranch=" + reportingBranch + ", description=" + description
				+ ", contactPerson=" + contactPerson + ", phone=" + phone + ", mobile=" + mobile + ", pincode="
				+ pincode + ", website=" + website + ", status=" + status + "]";
	}

	

}
