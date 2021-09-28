package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;

public class ApplyMandatorySkillDto {
	
	private Long id;
	private Long skillId;
	private Integer experienceRequired;
	private ExperienceCountUnit experienceCountUnit;
	private Boolean mandatory;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	
	public Integer getExperienceRequired() {
		return experienceRequired;
	}
	public void setExperienceRequired(Integer experienceRequired) {
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
}
