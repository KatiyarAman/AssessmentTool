package com.sterp.multitenant.tenant.employee.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeResponseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5366870337589252897L;
	private long id;
	private String department;
	private String designation;
	private String firstName;
	private String lastName;
	private String middleName;
	private String fatherName;
	private String reportingManager;
	private LocalDate dob;
	private LocalDate joiningDate;
	private String reportingBranch;
	private Boolean erpUser;
	private String empCode;
	private String gender;
	private Long status;

	public EmployeeResponseDTO(long id, String department, String designation, String firstName, String middleName,
			String lastName, String fatherName, String reportingManager, LocalDate dob, LocalDate joiningDate,
			String reportingBranch, Boolean erpUser, String empCode, String gender, Long status) {
		this.id = id;
		this.department = department;
		this.designation = designation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.fatherName = fatherName;
		this.reportingManager = reportingManager;
		this.dob = dob;
		this.joiningDate = joiningDate;
		this.reportingBranch = reportingBranch;
		this.erpUser = erpUser;
		this.empCode = empCode;
		this.gender = gender;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
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

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
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

	public String getReportingBranch() {
		return reportingBranch;
	}

	public void setReportingBranch(String reportingBranch) {
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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmployeeResponseDTO [id=" + id + ", department=" + department + ", designation=" + designation
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + ", fatherName="
				+ fatherName + ", reportingManager=" + reportingManager + ", dob=" + dob + ", joiningDate="
				+ joiningDate + ", reportingBranch=" + reportingBranch + ", erpUser=" + erpUser + ", empCode=" + empCode
				+ ", gender=" + gender + ", status=" + status + "]";
	}
	
	
}
