package com.sterp.multitenant.tenant.employee.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.department.entity.Department;
import com.sterp.multitenant.tenant.designation.entity.Designation;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "employee")
public class Employee extends SuperBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4704249631640779514L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "designation_id")
	private Designation designation;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "reporting_manager")
	private Long reportingManager;

	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "joining_date")
	private LocalDate joiningDate;

	@Column(name = "reporting_branch")
	private Long reportingBranch;

	@Column(name = "erp_user")
	private Boolean erpUser;

	@Column(name = "emp_code")
	private String empCode;

	@Column(name = "gender")
	private String gender;

//	@Column(name = "status")
//	@Enumerated(EnumType.ORDINAL)
//	private StatusEnum status;
	
//	@Column(name="status")
//	private Long status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Long getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(Long reportingManager) {
		this.reportingManager = reportingManager;
	}

	

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Long getReportingBranch() {
		return reportingBranch;
	}

	public void setReportingBranch(Long reportingBranch) {
		this.reportingBranch = reportingBranch;
	}

	public Boolean getErpUser() {
		return erpUser;
	}

	public void setErpUser(Boolean erpUser) {
		this.erpUser = erpUser;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", department=" + department + ", designation=" + designation + ", firstName="
				+ firstName + ", lastName=" + lastName + ", middleName=" + middleName + ", fatherName=" + fatherName
				+ ", reportingManager=" + reportingManager + ", dob=" + dob + ", joiningDate=" + joiningDate
				+ ", reportingBranch=" + reportingBranch + ", erpUser=" + erpUser + ", empCode=" + empCode + ", gender="
				+ gender + "]";
	}

		
	

}
