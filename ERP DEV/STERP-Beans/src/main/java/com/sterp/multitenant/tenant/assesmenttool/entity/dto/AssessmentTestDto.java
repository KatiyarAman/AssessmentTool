package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import java.util.List;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.TimeDurationPolicy;

public class AssessmentTestDto {
	
	private Long id;
	private String title;
	private TimeDurationPolicy timeDurationPolicy;
	private Integer timeOutDuration;
	private boolean enableNegetiveMarking;
	private double testPassPercentage;
	private Integer totalQuestions;
	private Long status;
	private Long jobProfileId;
	private boolean enableWebCam;
	private boolean enableAntiCheating;
	private boolean takeIntroductionVideo;
	private List<Long> questionnaireIds;
	
	public AssessmentTestDto() {
		super();
	}
		public AssessmentTestDto(Long id, String title, TimeDurationPolicy timeDurationPolicy, Integer timeOutDuration,
			boolean enableNegetiveMarking, double testPassPercentage, Integer totalQuestions, Long status) {
		super();
		this.id = id;
		this.title = title;
		this.timeDurationPolicy = timeDurationPolicy;
		this.timeOutDuration = timeOutDuration;
		this.enableNegetiveMarking = enableNegetiveMarking;
		this.testPassPercentage = testPassPercentage;
		this.totalQuestions = totalQuestions;
		this.status = status;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public TimeDurationPolicy getTimeDurationPolicy() {
		return timeDurationPolicy;
	}
	public void setTimeDurationPolicy(TimeDurationPolicy timeDurationPolicy) {
		this.timeDurationPolicy = timeDurationPolicy;
	}
	public Integer getTimeOutDuration() {
		return timeOutDuration;
	}
	public void setTimeOutDuration(Integer timeOutDuration) {
		this.timeOutDuration = timeOutDuration;
	}
	public boolean isEnableNegetiveMarking() {
		return enableNegetiveMarking;
	}
	public void setEnableNegetiveMarking(boolean enableNegetiveMarking) {
		this.enableNegetiveMarking = enableNegetiveMarking;
	}
	public double getTestPassPercentage() {
		return testPassPercentage;
	}
	public void setTestPassPercentage(double testPassPercentage) {
		this.testPassPercentage = testPassPercentage;
	}
	public Integer getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public List<Long> getQuestionnaireIds() {
		return questionnaireIds;
	}
	public void setQuestionnaireIds(List<Long> questionnaireIds) {
		this.questionnaireIds = questionnaireIds;
	}
	public Long getJobProfileId() {
		return jobProfileId;
	}
	public void setJobProfileId(Long jobProfileId) {
		this.jobProfileId = jobProfileId;
	}
	public boolean isEnableWebCam() {
		return enableWebCam;
	}
	public void setEnableWebCam(boolean enableWebCam) {
		this.enableWebCam = enableWebCam;
	}
	public boolean isEnableAntiCheating() {
		return enableAntiCheating;
	}
	public void setEnableAntiCheating(boolean enableAntiCheating) {
		this.enableAntiCheating = enableAntiCheating;
	}
	public boolean isTakeIntroductionVideo() {
		return takeIntroductionVideo;
	}
	public void setTakeIntroductionVideo(boolean takeIntroductionVideo) {
		this.takeIntroductionVideo = takeIntroductionVideo;
	}
}
