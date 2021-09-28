package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Column;
import javax.persistence.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;
@Entity
@Table(name = "candidate_skill_experience")
public class CandidateSkillSummary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "mandatory_skill_id")
	private Long mandatorySkillId;
	@Column(name = "candidate_application_id")
	private Long candidateApplicationId;
	@Column(name = "have_skill")
	private Boolean haveSkill;

	@Column(name = "experience")
	private Double experience;

	public Long getCandidateApplicationId() {
		return candidateApplicationId;
	}

	public void setCandidateApplicationId(Long candidateApplicationId) {
		this.candidateApplicationId = candidateApplicationId;
	}

	@Column(name = "experience_in")
	@Enumerated(EnumType.STRING)
	private ExperienceCountUnit experienceUnit;

	@Column(name = "comment")
	private String comment;

	@Column(name = "candidate_reg_id")
	private Long candidateRegId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getHaveSkill() {
		return haveSkill;
	}

	public void setHaveSkill(Boolean haveSkill) {
		this.haveSkill = haveSkill;
	}

	public Double getExperience() {
		return experience;
	}

	public void setExperience(Double experience) {
		this.experience = experience;
	}

	public ExperienceCountUnit getExperienceUnit() {
		return experienceUnit;
	}

	public void setExperienceUnit(ExperienceCountUnit experienceUnit) {
		this.experienceUnit = experienceUnit;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getMandatorySkillId() {
		return mandatorySkillId;
	}

	public void setMandatorySkillId(Long mandatorySkillId) {
		this.mandatorySkillId = mandatorySkillId;
	}

	public Long getCandidateRegId() {
		return candidateRegId;
	}

	public void setCandidateRegId(Long candidateRegId) {
		this.candidateRegId = candidateRegId;
	}

}
