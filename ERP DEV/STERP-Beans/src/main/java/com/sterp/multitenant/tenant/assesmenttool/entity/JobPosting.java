package com.sterp.multitenant.tenant.assesmenttool.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.InterviewType;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.JobApplyType;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name= "job_postings")
public class JobPosting extends SuperBean {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "job_apply_type")
	private JobApplyType jobApplyType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "interview_type")
	private InterviewType interviewType;
	
	@Column(name = "interview_start_time")
	@DateTimeFormat(pattern = "hh:mm")
	private LocalTime interviewStartTime;
	
	@Column(name = "interview_end_time")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalTime interviewEndTime;
	
	@Column(name = "interview_start_date")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate interviewStartDate;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Column(name = "interview_end_date")
	private LocalDate interviewEndDate;
	
	@Column(name = "venue")
	private String venue;
	
	@Column(name = "ask_general_questions")
	private boolean askGeneralQuestions;

	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "posting_end_date")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate postingEndDate;
	
	@Column(name = "shareable_token", columnDefinition = "text")
	private String shareableToken;
	
	@ManyToOne
	@JoinColumn(name = "job_profile_id")
	private JobProfile jobProfile;
	
	@ManyToOne
	@JoinColumn(name = "assessment_id")
	private AssessmentTest assessmentTest;
	
	@ManyToOne
	@JoinColumn(name = "interview_id")
	private Interview interview;
	
	@Column(name = "take_introduction_video")
	private boolean takeIntroductionVideo;
	
	@Column(name = "intro_video_duration")
	private Integer introVideoDuration;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "job_posting_id")
	private Collection<ApplyMandatorySkills> applyMandatorySkills;
	
//	@ElementCollection(fetch = FetchType.EAGER)
//	@JoinTable(name = "job_posting_mandatory_skills_mapping", joinColumns = @JoinColumn(name = "job_posting_id", nullable = false))
//	//@GenericGenerator(name = "increment-gen", strategy = "increment")
//	//@CollectionId(columns = @Column(name = "id"), generator = "increment-gen", type = @Type(type = "long"))
//	private List<ApplyMandatorySkills> jobApplyMandatorySkills = new ArrayList<>(); 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JobApplyType getJobApplyType() {
		return jobApplyType;
	}

	public void setJobApplyType(JobApplyType jobApplyType) {
		this.jobApplyType = jobApplyType;
	}

	public InterviewType getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(InterviewType interviewType) {
		this.interviewType = interviewType;
	}

	public LocalTime getInterviewStartTime() {
		return interviewStartTime;
	}

	public void setInterviewStartTime(LocalTime interviewStartTime) {
		this.interviewStartTime = interviewStartTime;
	}

	public LocalTime getInterviewEndTime() {
		return interviewEndTime;
	}

	public void setInterviewEndTime(LocalTime interviewEndTime) {
		this.interviewEndTime = interviewEndTime;
	}

	public LocalDate getInterviewStartDate() {
		return interviewStartDate;
	}

	public void setInterviewStartDate(LocalDate interviewStartDate) {
		this.interviewStartDate = interviewStartDate;
	}

	public LocalDate getInterviewEndDate() {
		return interviewEndDate;
	}

	public void setInterviewEndDate(LocalDate interviewEndDate) {
		this.interviewEndDate = interviewEndDate;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public boolean isAskGeneralQuestions() {
		return askGeneralQuestions;
	}

	public void setAskGeneralQuestions(boolean askGeneralQuestions) {
		this.askGeneralQuestions = askGeneralQuestions;
	}

	public JobProfile getJobProfile() {
		return jobProfile;
	}

	public void setJobProfile(JobProfile jobProfile) {
		this.jobProfile = jobProfile;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDate getPostingEndDate() {
		return postingEndDate;
	}

	public void setPostingEndDate(LocalDate postingEndDate) {
		this.postingEndDate = postingEndDate;
	}

	public String getShareableToken() {
		return shareableToken;
	}

	public void setShareableToken(String shareableToken) {
		this.shareableToken = shareableToken;
	}

	public AssessmentTest getAssessmentTest() {
		return assessmentTest;
	}

	public void setAssessmentTest(AssessmentTest assessmentTest) {
		this.assessmentTest = assessmentTest;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public Collection<ApplyMandatorySkills> getApplyMandatorySkills() {
		return applyMandatorySkills;
	}

	public void setApplyMandatorySkills(Collection<ApplyMandatorySkills> applyMandatorySkills) {
		this.applyMandatorySkills = applyMandatorySkills;
	}

	public boolean isTakeIntroductionVideo() {
		return takeIntroductionVideo;
	}

	public void setTakeIntroductionVideo(boolean takeIntroductionVideo) {
		this.takeIntroductionVideo = takeIntroductionVideo;
	}

	public Integer getIntroVideoDuration() {
		return introVideoDuration;
	}

	public void setIntroVideoDuration(Integer introVideoDuration) {
		this.introVideoDuration = introVideoDuration;
	}

}
