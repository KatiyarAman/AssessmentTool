package com.sterp.multitenant.tenant.assesmenttool.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;
@Entity
@Table(name = "job_roles")
public class JobRole extends SuperBean {
	private static final long serialVersionUID = 8889588146653691340L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "role")
	private String role;

	@Column(name = "description", columnDefinition = "text")
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "job_role_skills_mapping",joinColumns = {@JoinColumn(name = "job_role_id")}, inverseJoinColumns = {@JoinColumn(name = "skill_id")})
	private List<Skill> skillSet = new ArrayList<Skill>();
	
	@ManyToOne
	@JoinColumn(name = "industry_type_id", nullable = false)
	private IndustryType industryType;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Skill> getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(List<Skill> skillSet) {
		this.skillSet = skillSet;
	}

	public IndustryType getIndustryType() {
		return industryType;
	}

	public void setIndustryType(IndustryType industryType) {
		this.industryType = industryType;
	}
}
