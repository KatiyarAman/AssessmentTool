package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import java.util.List;

public class JobRoleDto {
	private Long id;
	private String role;
	private String description;
	private Long status;
	private List<Long> skillIds;
	private Long industryTypeId;
	
	public JobRoleDto() {
		super();
	}
	public JobRoleDto(Long id, String role, String description,Long industryTypeId, Long status) {
		super();
		this.id = id;
		this.role = role;
		this.description = description;
		this.status = status;
		this.industryTypeId = industryTypeId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Long> getSkillIds() {
		return skillIds;
	}
	public void setSkillIds(List<Long> skillIds) {
		this.skillIds = skillIds;
	}
	public Long getIndustryTypeId() {
		return industryTypeId;
	}
	public void setIndustryTypeId(Long industryTypeId) {
		this.industryTypeId = industryTypeId;
	}
}
