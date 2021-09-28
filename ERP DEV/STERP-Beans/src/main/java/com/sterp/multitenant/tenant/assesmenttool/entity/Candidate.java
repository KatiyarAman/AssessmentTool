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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.AssessmentTestStatus;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.CandidateStatus;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.ExperienceCountUnit;
import com.sterp.multitenant.tenant.model.StatusDetails;

@Entity
@Table(name = "candidate")
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
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
	
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
   //@JoinTable(name="candidate_profile",joinColumns =@JoinColumn(name="candidate_id"))
	private CandidateProfile candidateProfile;
	
	//@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="candidate_id")
	private List<CandidateApplication> candiateApplication;
	
	@Column(name = "status") 
	private Long status;
	
	 @Column(name = "assessment_test_status")
	 @Enumerated(EnumType.STRING) 
	 private AssessmentTestStatus assessmentTestResult;
	  
	  @Column(name = "assessment_test_score")
	  private Integer assessmentTestScore;
	
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
	
	public List<CandidateApplication> getCandiateApplication() {
		return candiateApplication;
	}
	public void setCandiateApplication(List<CandidateApplication> candiateApplication) {
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
	
	public CandidateProfile getCandidateProfile() {
		return candidateProfile;
	}
	public void setCandidateProfile(CandidateProfile candidateProfile) {
		this.candidateProfile = candidateProfile;
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
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	 * 
	 * @Column(name = "name") private String name;
	 * 
	 * @Column(name = "email") private String email;
	 * 
	 * @Column(name = "contact_no") private String contactNo;
	 * 
	 * @AttributeOverrides({
	 * 
	 * @AttributeOverride(name = "filename", column= @Column(name =
	 * "resume_file_name")),
	 * 
	 * @AttributeOverride(name = "contentType", column= @Column(name =
	 * "resume_file_content_type")),
	 * 
	 * @AttributeOverride(name = "filesize", column= @Column(name =
	 * "resume_file_size")), }) private UploadedFileResource resume;
	 * 
	 * @AttributeOverrides({
	 * 
	 * @AttributeOverride(name = "filename", column= @Column(name =
	 * "intro_video_file_name")),
	 * 
	 * @AttributeOverride(name = "contentType", column= @Column(name =
	 * "intro_video_file_content_type")),
	 * 
	 * @AttributeOverride(name = "filesize", column= @Column(name =
	 * "intro_video_file_size")), }) private UploadedFileResource introVideo;
	 * 
	 * @Column(name = "assessment_test_status")
	 * 
	 * @Enumerated(EnumType.STRING) private AssessmentTestStatus
	 * assessmentTestResult;
	 * 
	 * @Column(name = "assessment_test_score") private Integer assessmentTestScore;
	 * 
	 * @Column(name = "applied_date") private LocalDate appliedDate;
	 * 
	 * @Column(name = "apply_source") private String applySource;
	 * 
	 * @Column(name = "status") private Long status;
	 * 
	 * @ManyToOne(optional = false)
	 * 
	 * @JoinColumn(name = "job_posting_id", nullable = false) private JobPosting
	 * jobPosting;
	 * 
	 * @ElementCollection
	 * 
	 * @JoinTable(name = "candidate_skill_experience", joinColumns
	 * = @JoinColumn(name = "candidate_id")) private List<CandidateSkillExperience>
	 * skillExperience = new ArrayList<>();
	 * 
	 * @Column(name = "candidate_status")
	 * 
	 * @Enumerated(EnumType.STRING) private CandidateStatus candidateStatus;
	 * 
	 * @Column(name = "total_experience") private Double totalExperience;
	 * 
	 * @Column(name = "total_experience_unit")
	 * 
	 * @Enumerated(EnumType.STRING) private ExperienceCountUnit totalExperienceUnit;
	 * 
	 * @Column(name = "relavant_experience") private Double relavantExperience;
	 * 
	 * @Column(name = "relavant_experience_unit")
	 * 
	 * @Enumerated(EnumType.STRING) private ExperienceCountUnit
	 * relavantExperienceUnit;
	 * 
	 * @Column(name = "permanent_address") private String permanentAddress;
	 * 
	 * @Column(name = "permanent_city_id") private Integer permanentCityId;
	 * 
	 * @Column(name = "permanent_pincode") private Integer permanentPincode;
	 * 
	 * @Column(name = "same_as_permanent_address") private Boolean
	 * sameAsPermanentAddress;
	 * 
	 * @Column(name = "current_address") private String currentAddress;
	 * 
	 * @Column(name = "current_city_id") private Integer currentCityId;
	 * 
	 * @Column(name = "current_pincode") private Integer currentPincode;
	 * 
	 * @Column(name = "cover_letter") private String coverLetter;
	 * 
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "status", insertable = false, updatable = false) private
	 * StatusDetails statusDetail;
	 */
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", email=" + email + ", contactNo=" + contactNo
				+ ", statusDetail=" + statusDetail + ", candidateStatus=" + candidateStatus + ", verificationToken="
				+ verificationToken + ", otp=" + otp + ", expiryDate=" + expiryDate + ", companyId=" + companyId
				+ ", createdDate=" + createdDate + ", loginType=" + loginType 
				+ ", candidateProfile=" + candidateProfile + ", candiateApplication=" + candiateApplication
				+ ", status=" + status + ", resume=" + resume + ", introVideo=" + introVideo + "]";
	}
	
	
}
