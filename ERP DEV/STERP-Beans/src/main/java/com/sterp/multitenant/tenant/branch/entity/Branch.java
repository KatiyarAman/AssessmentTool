package com.sterp.multitenant.tenant.branch.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.sterp.multitenant.tenant.dto.DocumentRequestDTO;
import com.sterp.multitenant.tenant.model.BankDetail;
import com.sterp.multitenant.tenant.model.StatusDetails;
import com.sterp.multitenant.tenant.model.Type;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "branch")
public class Branch extends SuperBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "type")
	private Long type;

	@Column(name = "branch_name")
	private String branchName;

	@Column(name = "branch_code")
	private String branchCode;

	@Column(name = "short_name")
	private String shortName;

	@Column(name = "zone_id")
	private Long zoneId;

	@Column(name = "city_id")
	private Long cityId;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "reporting_branch")
	private Long reportingBranch;

	@Column(name = "description")
	private String description;

	@Column(name = "contact_person")
	private String contactPerson;

	@Column(name = "phone")
	private Long phone;

	@Column(name = "mobile")
	private Long mobile;

	@Column(name = "pincode")
	private Long pincode;

	@Column(name = "website")
	private String website;

//	@Column(name = "status")
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name= "status")
//	private Long status;
	
	@Column(name = "parent_company_id")
	private Long parentCompanyId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type", insertable = false, updatable = false)
	private Type typeDetail;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="ref_id")
	@Where(clause="module_id=124")
	private List<BankDetail> bankAccounts;
	@Transient
	private List<DocumentRequestDTO> documents;

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

	public Long getParentCompanyId() {
		return parentCompanyId;
	}

	public void setParentCompanyId(Long parentCompanyId) {
		this.parentCompanyId = parentCompanyId;
	}
	
	

	public Type getTypeDetail() {
		return typeDetail;
	}

	public void setTypeDetail(Type typeDetail) {
		this.typeDetail = typeDetail;
	}
	
	

	public List<DocumentRequestDTO> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentRequestDTO> documents) {
		this.documents = documents;
	}

	public List<BankDetail> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<BankDetail> bankAccounts) {
		for (BankDetail bankDetail : bankAccounts) {
			bankDetail.setModuleId(124L);
		}
		this.bankAccounts = bankAccounts;
	}

	@Override
	public String toString() {
		return "Branch [id=" + id + ", type=" + type + ", branchName=" + branchName + ", branchCode=" + branchCode
				+ ", shortName=" + shortName + ", zoneId=" + zoneId + ", cityId=" + cityId + ", address=" + address
				+ ", email=" + email + ", reportingBranch=" + reportingBranch + ", description=" + description
				+ ", contactPerson=" + contactPerson + ", phone=" + phone + ", mobile=" + mobile + ", pincode="
				+ pincode + ", website=" + website + ", parentCompanyId=" + parentCompanyId + ", typeDetail="
				+ typeDetail + ", bankAccounts=" + bankAccounts + ", documents=" + documents + "]";
	}

}
