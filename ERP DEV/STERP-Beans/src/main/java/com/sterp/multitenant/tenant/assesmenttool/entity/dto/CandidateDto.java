package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import java.time.LocalDate;

import com.sterp.multitenant.tenant.assesmenttool.entity.UploadedFileResource;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.AssessmentTestStatus;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;
import com.sterp.multitenant.tenant.model.StatusDetails;

public class CandidateDto {
	
	private Long id;
	private String name;
	private String email;
	private String contactNo;
	private AssessmentTestStatus assessmentTestResult;
	private Integer assessmentTestScore;
	private LocalDate appliedDate;
	private Long status;
	private StatusDetails statusDetail;
	private Double totalExperience;
	private ExperienceCountUnit totalExperienceUnit;
	private UploadedFileResource resume;
	private UploadedFileResource  introVideo;
	private Long candidateId;
	private Long jobPostingId;
	public CandidateDto() {
		super();
	}
	
	public CandidateDto(Long id, String name, String email, String contactNo, AssessmentTestStatus assessmentTestResult,
			Integer assessmentTestScore, LocalDate createdDate, Long status,Long candidateId,
			StatusDetails statusDetail, UploadedFileResource resume, UploadedFileResource introVideo,
			Double totalExperience, ExperienceCountUnit totalExperienceUnit) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.assessmentTestResult = assessmentTestResult;
		this.assessmentTestScore = assessmentTestScore;
		this.appliedDate = createdDate;
		this.status = status;
		this.candidateId=candidateId;
		this.statusDetail = statusDetail;
		this.resume = resume;
		this.introVideo = introVideo;
		this.totalExperience = totalExperience;
		this.totalExperienceUnit = totalExperienceUnit;
	}
	
	public CandidateDto(Long id,  String email, Long candidateId, Long jobPostingId) {
		super();
		this.id = id;
	
		this.email = email;
		this.candidateId = candidateId;
		this.jobPostingId = jobPostingId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public AssessmentTestStatus getAssessmentTestResult() {
		return assessmentTestResult;
	}
	public void setAssessmentTestResult(AssessmentTestStatus assessmentTestResult) {
		this.assessmentTestResult = assessmentTestResult;
	}
	public Integer getAssessmentTestScore() {
		return assessmentTestScore;
	}
	public void setAssessmentTestScore(Integer assessmentTestScore) {
		this.assessmentTestScore = assessmentTestScore;
	}
	public LocalDate getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}
	
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public StatusDetails getStatusDetail() {
		return statusDetail;
	}
	
	public void setStatusDetail(StatusDetails statusDetail) {
		this.statusDetail = statusDetail;
	}
	public UploadedFileResource getResume() {
		return resume;
	}
	public void setResume(UploadedFileResource resume) {
		this.resume = resume;
	}
	public UploadedFileResource getIntroVideo() {
		return introVideo;
	}
	public void setIntroVideo(UploadedFileResource introVideo) {
		this.introVideo = introVideo;
	}
	public Double getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(Double totalExperience) {
		this.totalExperience = totalExperience;
	}
	public ExperienceCountUnit getTotalExperienceUnit() {
		return totalExperienceUnit;
	}
	public void setTotalExperienceUnit(ExperienceCountUnit totalExperienceUnit) {
		this.totalExperienceUnit = totalExperienceUnit;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getJobPostingId() {
		return jobPostingId;
	}

	public void setJobPostingId(Long jobPostingId) {
		this.jobPostingId = jobPostingId;
	}
	
}
