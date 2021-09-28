package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import java.util.List;

import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateSkillExperience;
import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateSkillSummary;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;

public class CandidateRegisterationDto {
	
	private Long id;
	private String name;
	private String email;
	private String contactNo;
	private String message;
	private Long jobPostingId;
	private String applySource;
	
	private Double totalExperience;
	private ExperienceCountUnit totalExperienceUnit;
	private Double relavantExperience;
	private ExperienceCountUnit relavantExperienceUnit;
	
	private String permanentAddress;
	private Integer permanentCityId;
	private Integer permanentStateId;
	private Integer permanentCountryId;
	private Integer permanentPincode;
	
	private Boolean sameAsPermanentAddress;
	
	private String currentAddress;
	private Integer currentCityId;
	private Integer currentStateId;
	private Integer currentCountryId;
	private Integer currentPincode;
	
	private String coverLetter;
	
	private List<CandidateSkillExperience> skillExperience;
	private List<CandidateSkillSummary> skillSummary;
	
	private boolean throughSocialLogin;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getJobPostingId() {
		return jobPostingId;
	}
	public void setJobPostingId(Long jobPostingId) {
		this.jobPostingId = jobPostingId;
	}
	public List<CandidateSkillExperience> getSkillExperience() {
		return skillExperience;
	}
	public void setSkillExperience(List<CandidateSkillExperience> skillExperience) {
		this.skillExperience = skillExperience;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApplySource() {
		return applySource;
	}
	public void setApplySource(String applySource) {
		this.applySource = applySource;
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
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public Integer getPermanentCityId() {
		return permanentCityId;
	}
	public void setPermanentCityId(Integer permanentCityId) {
		this.permanentCityId = permanentCityId;
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
	public Boolean getSameAsPermanentAddress() {
		return sameAsPermanentAddress;
	}
	public void setSameAsPermanentAddress(Boolean sameAsPermanentAddress) {
		this.sameAsPermanentAddress = sameAsPermanentAddress;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public Integer getCurrentCityId() {
		return currentCityId;
	}
	public void setCurrentCityId(Integer currentCityId) {
		this.currentCityId = currentCityId;
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
	public String getCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	public boolean isThroughSocialLogin() {
		return throughSocialLogin;
	}
	public void setThroughSocialLogin(boolean throughSocialLogin) {
		this.throughSocialLogin = throughSocialLogin;
	}
	public List<CandidateSkillSummary> getSkillSummary() {
		return skillSummary;
	}
	public void setSkillSummary(List<CandidateSkillSummary> skillSummary) {
		this.skillSummary = skillSummary;
	}
}
