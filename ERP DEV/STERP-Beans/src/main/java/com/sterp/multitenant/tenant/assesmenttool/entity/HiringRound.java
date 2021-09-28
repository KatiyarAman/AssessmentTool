package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.employee.entity.Employee;

@Entity
@Table(name="job_profiles_rounds_mapping")
public class HiringRound {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name = "round_name")
	private String roundName;
	
	@Column(name = "mode")
	private String mode;
	
	@Column(name="job_profile_id")
	private Long jobProfileId;
	
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee responsiblePerson;
	
	@Column(name = "round_order")
	private Integer order;
	
	public String getRoundName() {
		return roundName;
	}
	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public Employee getResponsiblePerson() {
		return responsiblePerson;
	}
	public void setResponsiblePerson(Employee responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Long getJobProfileId() {
		return jobProfileId;
	}
	public void setJobProfileId(Long jobProfileId) {
		this.jobProfileId = jobProfileId;
	}
	
	
	
}
