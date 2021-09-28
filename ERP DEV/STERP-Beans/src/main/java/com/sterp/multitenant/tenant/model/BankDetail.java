package com.sterp.multitenant.tenant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "bank_details")
public class BankDetail extends SuperBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5585122899706008047L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// bigint NOT NULL AUTO_INCREMENT,
	
	@Column(name = "bank_name")
	private String bankName;// ` varchar(180) NOT NULL,

	@Lob
	@Column(name = "address")
	private String address;// ` longtext,

	@Column(name = "city_id")
	private int cityId;// ` bigint DEFAULT NULL,

	@Column(name = "pincode")
	private int pincode;// ` int DEFAULT NULL,

	@Column(name = "ifsc_code")
	private String ifscCode;// ` varchar(45) DEFAULT NULL,

	@Column(name = "swift_code")
	private String swift_code;// ` varchar(45) DEFAULT NULL,

	@Column(name = "ac_no")
	private String acNo;// ` varchar(45) DEFAULT NULL,

	@Column(name = "module_id")
	private Long moduleId;// ` bigint DEFAULT NULL,
    
	
	@Column(name = "ref_id")
	private Integer refId;// ` bigint DEFAULT NULL,

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getSwift_code() {
		return swift_code;
	}

	public void setSwift_code(String swift_code) {
		this.swift_code = swift_code;
	}

	public String getAcNo() {
		return acNo;
	}

	public void setAcNo(String acNo) {
		this.acNo = acNo;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	@Override
	public String toString() {
		return "BankDetail [id=" + id + ", bankName=" + bankName + ", address=" + address + ", cityId=" + cityId
				+ ", pincode=" + pincode + ", ifscCode=" + ifscCode + ", swift_code=" + swift_code + ", acNo=" + acNo
				+ ", moduleId=" + moduleId + ", refId=" + refId + "]";
	}

}
