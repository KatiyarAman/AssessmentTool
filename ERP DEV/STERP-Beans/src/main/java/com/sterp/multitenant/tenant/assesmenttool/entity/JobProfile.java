package com.sterp.multitenant.tenant.assesmenttool.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "job_profiles")
public class JobProfile extends SuperBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "job_title", nullable = false)
	private String jobTitle;
	
	@Column(name = "date_posted", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate postedDate;
	
	@Column(name = "applicable_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate applicableDate;
	
	@Column(name = "description", columnDefinition = "text")
	private String description;
	
	@Column(name = "min_experience")
	private Integer minExperience;
	
	@Column(name = "min_experience_in")
	@Enumerated(EnumType.STRING)
	private ExperienceCountUnit minExperienceUnit;
	
	@Column(name = "max_experience")
	private Integer maxExperience;
	
	@Column(name = "max_experience_in")
	@Enumerated(EnumType.STRING)
	private ExperienceCountUnit maxExperienceUnit;
	
	@Column(name = "min_salary")
	private String minSalary;
	
	@Column(name = "max_salary")
	private String maxSalary;
	
	@Column(name = "show_salary_to_candidates")
	private boolean showSalaryToCandidates;
	
	@Column(name = "no_of_openings")
	private Integer noOfOpenings;
	
	@Column(name = "schedule")
	private String schedule;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "job_type")
	private String jobType;
	
	@Column(name = "work_from_home")
	private String workFromHome;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "job_role_id", nullable = false)
	private JobRole jobRole;

	@ManyToOne(optional = false)
	@JoinColumn(name = "industry_type_id")
	private IndustryType industryType;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "job_profile_id")
	private List<HiringRound> jobHiringRounds = new ArrayList<HiringRound>();
	
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
//		if (this.location != null) {
//			return Arrays.asList(this.location.split(","));
//		}
//		return null;
//	}
//
//	public void setLocation(List<String> location) {
//		if (location != null) {
//			this.location = String.join(",", location);
//		}
//	}
	
	public String getJobType() {
		return jobType;
	}

	public String getLocation() {
		return location;
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

	

	public void setLocation(String location) {
		this.location = location;
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

	public List<HiringRound> getJobHiringRounds() {
		return jobHiringRounds;
	}

	public void setJobHiringRounds(List<HiringRound> jobHiringRounds) {
		this.jobHiringRounds = jobHiringRounds;
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