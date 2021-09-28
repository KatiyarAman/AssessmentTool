package com.sterp.multitenant.tenant.assesmenttool.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.cfg.context.Cascadable;
import org.springframework.boot.autoconfigure.cache.CacheType;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.AssessmentTestStatus;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.CandidateStatus;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;
import com.sterp.multitenant.tenant.model.StatusDetails;

@Entity
@Table(name="candidates")
public class Candidates {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="candidate_email")
	private String candidateEmail;
	
	/*
	 * @Column(name="candidate_id") private Long candiateId;
	 */
	
	@Column(name="contact_no")
	private String contactNo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status", insertable = false, updatable = false)
	private StatusDetails statusDetail;
	
	@Column(name = "candidate_status")
	@Enumerated(EnumType.STRING)
	private CandidateStatus candidateStatus;
	
	@Column(name="verification_token")
    private String verificationToken;
	@Column(name="otp")
	private Long otp;
	@Column(name="expiry_date")
	private LocalDate expiryDate;
	@Column(name="company_id")
	private Long companyId;
	@Column(name="created_date")
	private LocalDate createdDate;
	@Column(name="login_type")
	private String loginType;
	@ElementCollection
	@JoinTable(name = "candidate_skill_experience", joinColumns = @JoinColumn(name = "candidate_id"))
	private List<CandidateSkillExperience> skillExperience = new ArrayList<>();
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="candidate_id")
	private List<CandidateProfile> candidateProfile;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="candiateApplication_id")
	private CandidateApplication candiateApplication;
	
	public List<CandidateSkillExperience> getSkillExperience() {
		return skillExperience;
	}
	public void setSkillExperience(List<CandidateSkillExperience> skillExperience) {
		this.skillExperience = skillExperience;
	}
	
	
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
	public CandidateApplication getCandiateApplication() {
		return candiateApplication;
	}
	public void setCandiateApplication(CandidateApplication candiateApplication) {
		this.candiateApplication = candiateApplication;
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
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public String getCandidateEmail() {
		return candidateEmail;
	}
	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	/*
	 * public Long getCandiateId() { return candiateId; } public void
	 * setCandiateId(Long candiateId) { this.candiateId = candiateId; }
	 */
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public StatusDetails getStatusDetail() {
		return statusDetail;
	}
	public void setStatusDetail(StatusDetails statusDetail) {
		this.statusDetail = statusDetail;
	}
	public String getVerificationToken() {
		return verificationToken;
	}
	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}
	public Long getOtp() {
		return otp;
	}
	public void setOtp(Long otp) {
		this.otp = otp;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public LocalDate getCreeatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate creeatedDate) {
		this.createdDate = creeatedDate;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public CandidateStatus getCandidateStatus() {
		return candidateStatus;
	}
	public void setCandidateStatus(CandidateStatus candidateStatus) {
		this.candidateStatus = candidateStatus;
	}
	public List<CandidateProfile> getCandidateProfile() {
		return candidateProfile;
	}
	public void setCandidateProfile(List<CandidateProfile> candidateProfile) {
		this.candidateProfile = candidateProfile;
	}
	
	
}
