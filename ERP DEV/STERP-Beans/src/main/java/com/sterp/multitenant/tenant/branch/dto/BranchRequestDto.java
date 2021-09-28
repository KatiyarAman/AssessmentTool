package com.sterp.multitenant.tenant.branch.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sterp.multitenant.tenant.settings.template.entity.CustomFieldData;

public class BranchRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5114381231008050767L;
	private long id;
	private Long type;
	private String branchName;
	private String branchCode;
	private String shortName;
	private Long zoneId;
	private Long cityId;
	private String address;
	private String email;
	private Long reportingBranch;
	private String description;
	private String contact_person;
	private String phone;
	private String mobile;
	private Long pincode;
	private String website;
	private Long status;
	private Long parentCompanyId;
	private List<CustomFieldData> customFieldDatas = new ArrayList<CustomFieldData>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
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

	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(Long zoneId) {
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

	public Long getReportingBranch() {
		return reportingBranch;
	}

	public void setReportingBranch(Long reportingBranch) {
		this.reportingBranch = reportingBranch;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
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

	public List<CustomFieldData> getCustomFieldDatas() {
		return customFieldDatas;
	}

	public void setCustomFieldDatas(List<CustomFieldData> customFieldDatas) {
		this.customFieldDatas = customFieldDatas;
	}

	public Long getParentCompanyId() {
		return parentCompanyId;
	}

	public void setParentCompanyId(Long parentCompanyId) {
		this.parentCompanyId = parentCompanyId;
	}

	@Override
	public String toString() {
		return "BranchRequestDto [id=" + id + ", type=" + type + ", branchName=" + branchName + ", branchCode="
				+ branchCode + ", shortName=" + shortName + ", zoneId=" + zoneId + ", cityId=" + cityId + ", address="
				+ address + ", email=" + email + ", reportingBranch=" + reportingBranch + ", description=" + description
				+ ", contact_person=" + contact_person + ", phone=" + phone + ", mobile=" + mobile + ", pincode="
				+ pincode + ", website=" + website + ", status=" + status + ", parentCompanyId=" + parentCompanyId
				+ ", customFieldDatas=" + customFieldDatas + "]";
	}

	
	

}
