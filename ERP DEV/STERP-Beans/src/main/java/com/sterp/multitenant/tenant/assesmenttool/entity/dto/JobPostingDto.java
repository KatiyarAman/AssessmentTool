package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import com.sterp.multitenant.tenant.assesmenttool.entity.ApplyMandatorySkills;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.InterviewType;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.JobApplyType;

public class JobPostingDto {
	
	private Long id;
	private JobApplyType jobApplyType;
	private InterviewType interviewType;
	private LocalTime interviewStartTime;
	private LocalTime interviewEndTime;
	private LocalDate interviewStartDate;
	private LocalDate interviewEndDate;
	private String venue;
	private boolean askGeneralQuestions;
	private Long jobProfileId;
	private String remarks;
	private String shareableToken;
	private LocalDate postingEndDate;
	private Long status;
	private Long assessmentId;
	private Long interviewId;
	private boolean takeIntroductionVideo;
	private Integer introVideoDuration;
	private Collection<ApplyMandatorySkills> applyMandatorySkills;
	
	
	public JobPostingDto() {
		super();
	}
	
	public JobPostingDto(Long id, JobApplyType jobApplyType, InterviewType interviewType, LocalTime interviewStartTime,
			LocalTime interviewEndTime, LocalDate interviewStartDate, LocalDate interviewEndDate, String venue,
			boolean askGeneralQuestions, String remarks, String shareableToken) {
		
		super();
		this.id = id;
		this.jobApplyType = jobApplyType;
		this.interviewType = interviewType;
		this.interviewStartTime = interviewStartTime;
		this.interviewEndTime = interviewEndTime;
		this.interviewStartDate = interviewStartDate;
		this.interviewEndDate = interviewEndDate;
		this.venue = venue;
		this.askGeneralQuestions = askGeneralQuestions;
		this.remarks = remarks;
		this.shareableToken = shareableToken;
	}
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
	public Long getJobProfileId() {
		return jobProfileId;
	}
	public void setJobProfileId(Long jobProfileId) {
		this.jobProfileId = jobProfileId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getShareableToken() {
		return shareableToken;
	}

	public void setShareableToken(String shareableToken) {
		this.shareableToken = shareableToken;
	}

	public LocalDate getPostingEndDate() {
		return postingEndDate;
	}

	public void setPostingEndDate(LocalDate postingEndDate) {
		this.postingEndDate = postingEndDate;
	}

	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Long interviewId) {
		this.interviewId = interviewId;
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
