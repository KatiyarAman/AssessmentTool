package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class InterviewDto {
	
	private Long id;
	private String interviewName;
	private Long jobProfileId;
	private Boolean webcamPermission;
	private String questionSetName;
	private Integer totalQuestions;
	private Boolean createQuestionSet;
	private Long status;
	private List<InterviewGeneralQuestionMappingBeanDto> questions = new ArrayList<>();
	
	public InterviewDto() {
		super();
	}
	
	public InterviewDto(Long id, String interviewName, Long jobProfileId, Integer totalQuestions) {
		super();
		this.id = id;
		this.interviewName = interviewName;
		this.jobProfileId = jobProfileId;
		this.totalQuestions = totalQuestions;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInterviewName() {
		return interviewName;
	}
	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}
	public Long getJobProfileId() {
		return jobProfileId;
	}
	public void setJobProfileId(Long jobProfileId) {
		this.jobProfileId = jobProfileId;
	}
	public Boolean getWebcamPermission() {
		return webcamPermission;
	}
	public void setWebcamPermission(Boolean webcamPermission) {
		this.webcamPermission = webcamPermission;
	}
	public String getQuestionSetName() {
		return questionSetName;
	}
	public void setQuestionSetName(String questionSetName) {
		this.questionSetName = questionSetName;
	}
	public Integer getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	public List<InterviewGeneralQuestionMappingBeanDto> getQuestions() {
		return questions;
	}
	public void setQuestions(List<InterviewGeneralQuestionMappingBeanDto> questions) {
		this.questions = questions;
	}
	
	public Boolean getCreateQuestionSet() {
		return createQuestionSet;
	}
	public void setCreateQuestionSet(Boolean createQuestionSet) {
		this.createQuestionSet = createQuestionSet;
	}
	@Override
	public String toString() {
		return "InterviewDto [id=" + id + ", interviewName=" + interviewName + ", jobProfileId=" + jobProfileId
				+ ", webcamPermission=" + webcamPermission + ", questionSetName=" + questionSetName
				+ ", totalQuestions=" + totalQuestions + ", createQuestionSet=" + createQuestionSet + ", questions="
				+ questions + "]";
	}
	
	
	
}
