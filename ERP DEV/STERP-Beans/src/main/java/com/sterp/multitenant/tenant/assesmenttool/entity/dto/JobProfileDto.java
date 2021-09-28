package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import java.time.LocalDate;
import java.util.List;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;

public class JobProfileDto {
	
	private Long id;
	private String jobTitle;
	private LocalDate postedDate;
	private LocalDate applicableDate;
	private String description;
	private Long status;
	private Long industryTypeId;
	private Long jobRoleId;
	private Integer noOfOpenings;
	private String minSalary;
	private String maxSalary;
	private Integer minExperience;
	private ExperienceCountUnit minExperienceUnit;
	private Integer maxExperience;
	private ExperienceCountUnit maxExperienceUnit;
	private boolean showSalaryToCandidates;
	private String schedule;
//	private List<String> location;
	private String location;
	private String jobType;
	private String workFromHome;
	private List<HiringRoundDto> hiringRounds;
	
	
	public JobProfileDto() {
		super();
	}
	
	public JobProfileDto(Long id, String jobTitle, LocalDate postedDate, LocalDate applicableDate, String description,
			Long status) {
		super();
		this.id = id;
		this.jobTitle = jobTitle;
		this.postedDate = postedDate;
		this.applicableDate = applicableDate;
		this.description = description;
		this.status = status;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

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
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(Long industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public Long getJobRoleId() {
		return jobRoleId;
	}

	public void setJobRoleId(Long jobRoleId) {
		this.jobRoleId = jobRoleId;
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
//	public List<String> getLocation() {
//		return this.location;
//	}
//	public void setLocation(List<String> location) {
//		this.location = location;
//	}
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

	public List<HiringRoundDto> getHiringRounds() {
		return hiringRounds;
	}

	public void setHiringRounds(List<HiringRoundDto> hiringRounds) {
		this.hiringRounds = hiringRounds;
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

	public Integer getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(Integer minExperience) {
		this.minExperience = minExperience;
	}

	public Integer getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(Integer maxExperience) {
		this.maxExperience = maxExperience;
	}
}