package com.sterp.multitenant.tenant.company.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.sterp.multitenant.tenant.dto.DocumentRequestDTO;
import com.sterp.multitenant.tenant.enumtype.YesNoEnum;
import com.sterp.multitenant.tenant.model.BankDetail;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "company")
//@JsonIgnoreProperties(value = { "bankAccounts" })
public class Company extends SuperBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id", length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_code", length = 45)
	private String companyCode;

	@NotEmpty(message = "Field is mandatory")
	@Column(name = "company_name", unique = true, length = 45)
	private String companyName;

	@Lob
	@Column(name = "address")
	private String address;

	@Column(name = "city_id")
	private int cityId;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "city_id")
//	private City city;// `field_type` varchar(45) NOT NULL,

	@Transient
	private int stateId;
	@Transient
	private int countryId;

	@Email
	@Column(name = "email")
	private String companyEmail;

	@Column(name = "phone")
	private Long phone;

	// @Min(value = 6, message = "maximum allowed length is 6")
	@Column(name = "pin_code")
	private Long pinCode;

	// @Pattern(regexp = "[A-Z]{5}\\d{4}[A-z]{1}", message = "Pan No. Formate is
	// Invalid")
//	@Column(name = "pan_NO")
//	private String panNo;

	// @Pattern(regexp =
	// "[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9A-Za-z]{1}[Z]{1}[0-9a-zA-Z]{1}",
	// message = "gst no. formate is invalid")
//	@Column(name = "gst_No")
//	private String gstNo;

//	@Column(name = "status")
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name="status")
//	private Long status;

	@Column(name = "msme_Reg")
	@Enumerated(EnumType.STRING)
	private YesNoEnum msmeReg;

	@Column(name = "msme_reg_no")
	private String msmeRegNo;

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ref_id")
	@Where(clause="module_id=112")
	private Set<BankDetail> bankAccounts;
	
	
	@Transient
	private List<DocumentRequestDTO> documents;

	
	
	public List<DocumentRequestDTO> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentRequestDTO> documents) {
		this.documents = documents;
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

	public void setMsmeReg(YesNoEnum msmeReg) {
		this.msmeReg = msmeReg;
	}

	public String getMsmeRegNo() {
		return msmeRegNo;
	}

	public void setMsmeRegNo(String msmeRegNo) {
		this.msmeRegNo = msmeRegNo;
	}

	public Set<BankDetail> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(Set<BankDetail> bankAccounts) {
		for (BankDetail bankDetail : bankAccounts) {
			bankDetail.setModuleId(112L);
		}
		this.bankAccounts = bankAccounts;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyCode=" + companyCode + ", companyName=" + companyName + ", address="
				+ address + ", cityId=" + cityId + ", stateId=" + stateId + ", countryId=" + countryId
				+ ", companyEmail=" + companyEmail + ", phone=" + phone + ", pinCode=" + pinCode + ", msmeReg="
				+ msmeReg + ", msmeRegNo=" + msmeRegNo + ", bankAccounts=" + bankAccounts + "]";
	}

	
	

}