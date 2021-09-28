package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;

@Embeddable
public class CandidateSkillExperience {

	private Long id;

	@Column(name = "mandatory_skill_id")
	private Long mandatorySkillId;

	@Column(name = "have_skill")
	private Boolean haveSkill;

	@Column(name = "experience")
	private Double experience;

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
