package com.sterp.multitenant.tenant.company.dto;

import java.io.Serializable;
import java.util.Date;

import com.sterp.multitenant.tenant.enumtype.YesNoEnum;

public class CompanyDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3934893486106636307L;
	private Long id;
	private String companyCode;
	private String companyName;
	private String address;
	private int cityId;
	private String companyEmail;
	private Long phone;
	private Long pinCode;
//	private String panNo;
//	private String gstNo;
	private Long status;
	private YesNoEnum msmeReg;
	private String msmeRegNo;
	private Long createdBy;
	private Long modifiedBy;
	private Date createdDate;
	private Date modifiedDate;

	public CompanyDTO(Long id, String companyCode, String companyName, String address, int cityId, String companyEmail,
			Long phone, Long pinCode, Long status, YesNoEnum msmeReg, String msmeRegNo, Long createdBy,
			Long modifiedBy, Date createdDate, Date modifiedDate) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.address = address;
		this.cityId = cityId;
		this.companyEmail = companyEmail;
		this.phone = phone;
		this.pinCode = pinCode;
//		this.panNo = panNo;
//		this.gstNo = gstNo;
		this.status = status;
		this.msmeReg = msmeReg;
		this.msmeRegNo = msmeRegNo;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

//	public String getPanNo() {
//		return panNo;
//	}
//
//	public void setPanNo(String panNo) {
//		this.panNo = panNo;
//	}
//
//	public String getGstNo() {
//		return gstNo;
//	}
//
//	public void setGstNo(String gstNo) {
//		this.gstNo = gstNo;
//	}

	

	public YesNoEnum getMsmeReg() {
		return msmeReg;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public void setMsmeReg(YesNoEnum msmeReg) {
		this.msmeReg = msmeReg;
	}

	public String getMsmeRegNo() {
		return msmeRegNo;
	}

	public void setMsmeRegNo(String msmeRegNo) {
		this.msmeRegNo = msmeRegNo;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "CompanyDTO [id=" + id + ", companyCode=" + companyCode + ", companyName=" + companyName + ", address="
				+ address + ", cityId=" + cityId + ", companyEmail=" + companyEmail + ", phone=" + phone + ", pinCode="
				+ pinCode + ", status=" + status + ", msmeReg=" + msmeReg + ", msmeRegNo=" + msmeRegNo + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + "]";
	}

	

}
