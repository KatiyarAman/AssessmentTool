package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

public class SkillDto {
	private Long id;
	private String skill;
	private String description;
	private Integer totalQuestions;
	
	
	
	public SkillDto() {
		super();
	}
	public SkillDto(Long id, String skill, String description, Integer totalQuestions) {
		super();
		this.id = id;
		this.skill = skill;
		this.description = description;
		this.totalQuestions = totalQuestions;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	
}
