package com.sterp.multitenant.tenant.assesmenttool.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "candidate_application")
public class CandidateApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "candidate_id")
	private Long candidateId;
	
	@Column(name = "applied_date")
	private LocalDate appliedDate;
	@Column(name = "status")
	private Long status;
	@ElementCollection
	@JoinTable(name = "candidate_skill_experience", joinColumns = @JoinColumn(name = "candidate_application_id"))
	private List<CandidateSkillExperience> skillExperience = new ArrayList<>();
	
	@ManyToOne(optional = false) 
	@JoinColumn(name = "job_posting_id", nullable = false) 
	private JobPosting jobPosting;
	
	@Column(name = "applied_through")
	private String appliedThrough;

	@AttributeOverrides({
		@AttributeOverride(name = "filename", column= @Column(name = "resume_file_name")),
		@AttributeOverride(name = "contentType", column= @Column(name = "resume_file_content_type")),
		@AttributeOverride(name = "filesize", column= @Column(name = "resume_file_size")),
	})
	private UploadedFileResource resume;
	
	@AttributeOverrides({
		@AttributeOverride(name = "filename", column= @Column(name = "intro_video_file_name")),
		@AttributeOverride(name = "contentType", column= @Column(name = "intro_video_file_content_type")),
		@AttributeOverride(name = "filesize", column= @Column(name = "intro_video_file_size")),
	})
	private UploadedFileResource introVideo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
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

	public String getAppliedThrough() {
		return appliedThrough;
	}

	public void setAppliedThrough(String appliedThrough) {
		this.appliedThrough = appliedThrough;
	}

	public List<CandidateSkillExperience> getSkillExperience() {
		return skillExperience;
	}

	public void setSkillExperience(List<CandidateSkillExperience> skillExperience) {
		this.skillExperience = skillExperience;
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

	public JobPosting getJobPosting() {
		return jobPosting;
	}

	public void setJobPosting(JobPosting jobPosting) {
		this.jobPosting = jobPosting;
	}	
	
}
