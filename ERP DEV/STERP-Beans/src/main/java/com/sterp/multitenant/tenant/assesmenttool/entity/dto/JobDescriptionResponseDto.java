package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.sterp.multitenant.tenant.assesmenttool.entity.ApplyMandatorySkills;
import com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobRole;
import com.sterp.multitenant.tenant.assesmenttool.entity.Skill;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;

public class JobDescriptionResponseDto {
	
	private Long id;
	private String jobTitle;
	private LocalDate postedDate;
	private LocalDate applicableDate;
	private String description;
	private String minExperience;
	private ExperienceCountUnit minExperienceUnit;
	private String maxExperience;
	private ExperienceCountUnit maxExperienceUnit;
	private String minSalary;
	private String maxSalary;
	private boolean showSalaryToCandidates;
	private Integer noOfOpenings;
	private String schedule;
	private String location;
	private String jobType;
	private String workFromHome;
	private JobRole jobRole;
	private IndustryType industryType;
	private List<Skill> requiredSkills;
	private Collection<ApplyMandatorySkills> applyMandatorySkills;
	private JobPostingDto jobPostingDto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public LocalDate getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}
	public LocalDate getApplicableDate() {
		return applicableDate;
	}
	public void setApplicableDate(LocalDate applicableDate) {
		this.applicableDate = applicableDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMinExperience() {
		return minExperience;
	}
	public void setMinExperience(String minExperience) {
		this.minExperience = minExperience;
	}
	public String getMaxExperience() {
		return maxExperience;
	}
	public void setMaxExperience(String maxExperience) {
		this.maxExperience = maxExperience;
	}
	public String getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}
	public String getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(String maxSalary) {
		this.maxSalary = maxSalary;
	}
	public boolean isShowSalaryToCandidates() {
		return showSalaryToCandidates;
	}
	public void setShowSalaryToCandidates(boolean showSalaryToCandidates) {
		this.showSalaryToCandidates = showSalaryToCandidates;
	}
	public Integer getNoOfOpenings() {
		return noOfOpenings;
	}
	public void setNoOfOpenings(Integer noOfOpenings) {
		this.noOfOpenings = noOfOpenings;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getWorkFromHome() {
		return workFromHome;
	}
	public void setWorkFromHome(String workFromHome) {
		this.workFromHome = workFromHome;
	}
	public JobRole getJobRole() {
		return jobRole;
	}
	public void setJobRole(JobRole jobRole) {
		this.jobRole = jobRole;
	}
	public IndustryType getIndustryType() {
		return industryType;
	}
	public void setIndustryType(IndustryType industryType) {
		this.industryType = industryType;
	}
	public List<Skill> getRequiredSkills() {
		return requiredSkills;
	}
	public void setRequiredSkills(List<Skill> requiredSkills) {
		this.requiredSkills = requiredSkills;
	}
	public ExperienceCountUnit getMinExperienceUnit() {
		return minExperienceUnit;
	}
	public void setMinExperienceUnit(ExperienceCountUnit minExperienceUnit) {
		this.minExperienceUnit = minExperienceUnit;
	}
	public ExperienceCountUnit getMaxExperienceUnit() {
		return maxExperienceUnit;
	}
	public void setMaxExperienceUnit(ExperienceCountUnit maxExperienceUnit) {
		this.maxExperienceUnit = maxExperienceUnit;
	}
	public Collection<ApplyMandatorySkills> getApplyMandatorySkills() {
		return applyMandatorySkills;
	}
	public void setApplyMandatorySkills(Collection<ApplyMandatorySkills> applyMandatorySkills) {
		this.applyMandatorySkills = applyMandatorySkills;
	}
	public JobPostingDto getJobPostingDto() {
		return jobPostingDto;
	}
	public void setJobPostingDto(JobPostingDto jobPostingDto) {
		this.jobPostingDto = jobPostingDto;
	}
}
