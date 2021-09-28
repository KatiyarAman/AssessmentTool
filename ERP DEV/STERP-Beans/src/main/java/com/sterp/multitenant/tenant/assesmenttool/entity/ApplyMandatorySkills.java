package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;

//@Embeddable
@Entity
@Table(name = "job_posting_mandatory_skills_mapping")
public class ApplyMandatorySkills {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "skill_id")
	private Long skillId;
	
	@OneToOne
	@JoinColumn(name = "skill_id", updatable = false, insertable = false)
	private Skill skill;
	
	@Column(name = "experience_required")
	private Double experienceRequired;
	
	@Column(name = "experience_in")
	@Enumerated(EnumType.STRING)
	private ExperienceCountUnit experienceCountUnit;
	
	@Column(name = "mandatory")
	private Boolean mandatory;
	
	@Column(name = "job_posting_id")
	private Long jobPostingId;// ` bigint DEFAULT NULL,
	
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	public Double getExperienceRequired() {
		return experienceRequired;
	}
	public void setExperienceRequired(Double experienceRequired) {
		this.experienceRequired = experienceRequired;
	}
	public ExperienceCountUnit getExperienceCountUnit() {
		return experienceCountUnit;
	}
	public void setExperienceCountUnit(ExperienceCountUnit experienceCountUnit) {
		this.experienceCountUnit = experienceCountUnit;
	}
	public Boolean getMandatory() {
		return mandatory;
	}
	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getJobPostingId() {
		return jobPostingId;
	}
	public void setJobPostingId(Long jobPostingId) {
		this.jobPostingId = jobPostingId;
	}
		
}
