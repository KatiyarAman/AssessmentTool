package com.sterp.multitenant.tenant.assesmenttool.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;

@Entity
@Table(name = "candidate_profile")
public class CandidateProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "candidate_id")
	private Long candidateId;
	@Column(name = "permanent_address")
	private String permanentAddress;
	@Column(name="permanent_state_id")
	private Integer permanentStateId;
	@Column(name="permanent_country_id")
	private Integer permanentCountryId;
	@Column(name="permanent_pincode")
	private Integer permanentPincode;
	@Column(name = "city_id")
	private Integer cityId;
	@Column(name="current_city_id")
	private Integer currentCityId;
	@Column(name = "current_address")
	private String currentAddress;
	@Column(name="current_state_id")
	private Integer currentStateId;
	@Column(name="current_country_id")
	private Integer currentCountryId;
	@Column(name="current_pincode")
	private Integer currentPincode;
	@Column(name = "is_same_to_permanent")
	private Boolean isSameTOPermanent;
	@Column(name = "father_name")
	private String fatherName;
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	@Column(name = "total_experience")
	private Double totalExperience;
	
	@Column(name = "total_experience_unit")
	@Enumerated(EnumType.STRING)
	private ExperienceCountUnit totalExperienceUnit;
	
	@Column(name = "relavant_experience")
	private Double relavantExperience;
	
	@Column(name = "relavant_experience_unit")
	@Enumerated(EnumType.STRING)
	private ExperienceCountUnit relavantExperienceUnit;
	
	
	@Column(name="current_ctc")
	private Double currentCtc;
	@Column(name = "expected_ctc")
	private Double expectedCtc;
	@Column(name = "marital_status")
	private String maritalStatus;
	@Column(name = "profile_title")
	private String profileTitle;
	@Column(name = "allow_sms")
	private Boolean allowSms;
	@Column(name = "allow_email")
	private Boolean allowEmail;
	@Column(name = "gender")
	private String gender;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public Boolean getIsSameTOPermanent() {
		return isSameTOPermanent;
	}

	public void setIsSameTOPermanent(Boolean isSameTOPermanent) {
		this.isSameTOPermanent = isSameTOPermanent;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public Double getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(Double totalExperience) {
		this.totalExperience = totalExperience;
	}

	public ExperienceCountUnit getTotalExperienceUnit() {
		return totalExperienceUnit;
	}

	public void setTotalExperienceUnit(ExperienceCountUnit totalExperienceUnit) {
		this.totalExperienceUnit = totalExperienceUnit;
	}

	public Double getRelavantExperience() {
		return relavantExperience;
	}

	public void setRelavantExperience(Double relavantExperience) {
		this.relavantExperience = relavantExperience;
	}

	public ExperienceCountUnit getRelavantExperienceUnit() {
		return relavantExperienceUnit;
	}

	public void setRelavantExperienceUnit(ExperienceCountUnit relavantExperienceUnit) {
		this.relavantExperienceUnit = relavantExperienceUnit;
	}

	public Double getCurrentCtc() {
		return currentCtc;
	}

	public void setCurrentCtc(Double currentCtc) {
		this.currentCtc = currentCtc;
	}

	public Double getExpectedCtc() {
		return expectedCtc;
	}

	public void setExpectedCtc(Double expectedCtc) {
		this.expectedCtc = expectedCtc;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getProfileTitle() {
		return profileTitle;
	}

	public void setProfileTitle(String profileTitle) {
		this.profileTitle = profileTitle;
	}

	public Boolean getAllowSms() {
		return allowSms;
	}

	public void setAllowSms(Boolean allowSms) {
		this.allowSms = allowSms;
	}

	public Boolean getAllowEmail() {
		return allowEmail;
	}

	public void setAllowEmail(Boolean allowEmail) {
		this.allowEmail = allowEmail;
	}

	public String getGender() {
		return gender;
	}

	public Integer getCurrentCityId() {
		return currentCityId;
	}

	public void setCurrentCityId(Integer currentCityId) {
		this.currentCityId = currentCityId;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getPermanentStateId() {
		return permanentStateId;
	}

	public void setPermanentStateId(Integer permanentStateId) {
		this.permanentStateId = permanentStateId;
	}

	public Integer getPermanentCountryId() {
		return permanentCountryId;
	}

	public void setPermanentCountryId(Integer permanentCountryId) {
		this.permanentCountryId = permanentCountryId;
	}

	public Integer getPermanentPincode() {
		return permanentPincode;
	}

	public void setPermanentPincode(Integer permanentPincode) {
		this.permanentPincode = permanentPincode;
	}

	public Integer getCurrentStateId() {
		return currentStateId;
	}

	public void setCurrentStateId(Integer currentStateId) {
		this.currentStateId = currentStateId;
	}

	public Integer getCurrentCountryId() {
		return currentCountryId;
	}

	public void setCurrentCountryId(Integer currentCountryId) {
		this.currentCountryId = currentCountryId;
	}

	public Integer getCurrentPincode() {
		return currentPincode;
	}

	public void setCurrentPincode(Integer currentPincode) {
		this.currentPincode = currentPincode;
	}

	@Override
	public String toString() {
		return "CandidateProfile [id=" + id + ", candidateId=" + candidateId + ", permanentAddress=" + permanentAddress
				+ ", cityId=" + cityId + ", currentAddress=" + currentAddress + ", isSameTOPermanent="
				+ isSameTOPermanent + ", fatherName=" + fatherName + ", dateOfBirth=" + dateOfBirth
				+ ", totalExperience=" + totalExperience + ", totalExperienceUnit=" + totalExperienceUnit
				+ ", relavantExperience=" + relavantExperience + ", relavantExperienceUnit=" + relavantExperienceUnit
				+ ", currentCtc=" + currentCtc + ", expectedCtc=" + expectedCtc + ", maritalStatus=" + maritalStatus
				+ ", profileTitle=" + profileTitle + ", allowSms=" + allowSms + ", allowEmail=" + allowEmail
				+ ", gender=" + gender + ", getId()=" + getId() + ", getCandidateId()=" + getCandidateId()
				+ ", getPermanentAddress()=" + getPermanentAddress() + ", getCityId()=" + getCityId()
				+ ", getCurrentAddress()=" + getCurrentAddress() + ", getIsSameTOPermanent()=" + getIsSameTOPermanent()
				+ ", getFatherName()=" + getFatherName() + ", getDateOfBirth()=" + getDateOfBirth()
				+ ", getTotalExperience()=" + getTotalExperience() + ", getTotalExperienceUnit()="
				+ getTotalExperienceUnit() + ", getRelavantExperience()=" + getRelavantExperience()
				+ ", getRelavantExperienceUnit()=" + getRelavantExperienceUnit() + ", getCurrentCtc()="
				+ getCurrentCtc() + ", getExpectedCtc()=" + getExpectedCtc() + ", getMaritalStatus()="
				+ getMaritalStatus() + ", getProfileTitle()=" + getProfileTitle() + ", getAllowSms()=" + getAllowSms()
				+ ", getAllowEmail()=" + getAllowEmail() + ", getGender()=" + getGender() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	

}
