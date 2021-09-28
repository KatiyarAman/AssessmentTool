package com.sterp.multitenant.mastertenant.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sterp.multitenant.tenant.entity.Status;
import com.sterp.multitenant.tenant.enumtype.AuthProvider;
import com.sterp.multitenant.tenant.enumtype.BillingCycle;
import com.sterp.multitenant.tenant.enumtype.TrailUnit;

@Entity
@Table(name = "clientage")
public class Clientage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// ` int NOT NULL AUTO_INCREMENT,
	@Column(name = "client_code")
	private String clientCode;
	@Column(name = "company_name")
	private String companyName;// ` varchar(150) DEFAULT NULL,
	private String email;// varchar(100) DEFAULT NULL,

	@NotNull
	@Column(name = "sub_domain_name", unique = true)
	private String subDomainName;
	@Column(name = "company_address")
	private String companyAddress;// ` text,
	@Column(name = "contact_person")
	private String contactPerson;// ` varchar(100) DEFAULT NULL,
	@Column(name = "contact_number")
	private Long contactNumber;// ` bigint DEFAULT NULL,
//	@Column(name = "package_id")
//	private Long packageName;

	@Column(name = "created_date", updatable = false)
	private Timestamp createdDate;// datetime DEFAULT NULL,

	@Column(name = "verification_date", updatable = true)
	private Timestamp verificationDate;

	@Column(name = "expiry_date")
	private Timestamp expiryDate;
	@Column(name = "sub_type")
	private String subType;// ` int DEFAULT NULL,
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "trial_period")
	private int trialPeriod;
	@Column(name = "city_id")
	private int cityId;
	@Column(name = "pin_code")
	private int pinCode;
	@Column(name = "payment_date")
	private Timestamp paymentDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "billing_cycle")
	private BillingCycle billingCycle;

	@Column(name = "payable_amount")
	private double payableamount;

	@Enumerated(EnumType.STRING)
	@Column(name = "trial_unit")
	private TrailUnit trialUnit;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "max_user_allowed")
	private Integer maxUserAllowed;

	@Column(name = "provider")
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	@Column(name = "image_url")
	private String imageURL;

	@ManyToOne
	@JoinColumn(name = "package_id")
	private Package packageId;

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Clientage() {
		super();
		this.enabled = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

//	public Long getPackageName() {
//		return packageName;
//	}
//
//	public void setPackageName(Long packageName) {
//		this.packageName = packageName;
//	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getTrialPeriod() {
		return trialPeriod;
	}

	public void setTrialPeriod(int trialPeriod) {
		this.trialPeriod = trialPeriod;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Timestamp getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(Timestamp verificationDate) {
		this.verificationDate = verificationDate;
	}

	public Timestamp getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BillingCycle getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(BillingCycle billingCycle) {
		this.billingCycle = billingCycle;
	}

	public double getPayableamount() {
		return payableamount;
	}

	public void setPayableamount(double payableamount) {
		this.payableamount = payableamount;
	}

	public TrailUnit getTrialUnit() {
		return trialUnit;
	}

	public void setTrialUnit(TrailUnit trialUnit) {
		this.trialUnit = trialUnit;
	}

	public String getSubDomainName() {
		return subDomainName;
	}

	public void setSubDomainName(String subDomainName) {
		this.subDomainName = subDomainName;
	}

	public Integer getMaxUserAllowed() {
		return maxUserAllowed;
	}

	public void setMaxUserAllowed(Integer maxUserAllowed) {
		this.maxUserAllowed = maxUserAllowed;
	}

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}

	public Package getPackageId() {
		return packageId;
	}

	public void setPackageId(Package packageId) {
		this.packageId = packageId;
	}
}
