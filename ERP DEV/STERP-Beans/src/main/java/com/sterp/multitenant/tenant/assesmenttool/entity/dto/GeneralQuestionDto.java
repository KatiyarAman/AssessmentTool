package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.GeneralQuestionType;

public class GeneralQuestionDto {
	
	private Long id;	
	private String question;
	private String answerType;
	private Integer timeout;
	private String description;
	private Integer maxAttempt;
	private Integer thinkTime;
	private GeneralQuestionType generalQuestionType;
	private Long questionCategoryTypeId;
	private Long status;
	
	public GeneralQuestionDto() {
		super();
	}
	
	public GeneralQuestionDto(Long id, String question, String answerType, Integer timeout, String description,
			Integer maxAttempt, Integer thinkTime, GeneralQuestionType generalQuestionType,
			Long questionCategoryTypeId) {
		super();
		this.id = id;
		this.question = question;
		this.answerType = answerType;
		this.timeout = timeout;
		this.description = description;
		this.maxAttempt = maxAttempt;
		this.thinkTime = thinkTime;
		this.generalQuestionType = generalQuestionType;
		this.questionCategoryTypeId = questionCategoryTypeId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getMaxAttempt() {
		return maxAttempt;
	}
	public void setMaxAttempt(Integer maxAttempt) {
		this.maxAttempt = maxAttempt;
	}
	public Integer getThinkTime() {
		return thinkTime;
	}
	public void setThinkTime(Integer thinkTime) {
		this.thinkTime = thinkTime;
	}
	public GeneralQuestionType getGeneralQuestionType() {
		return generalQuestionType;
	}
	public void setGeneralQuestionType(GeneralQuestionType generalQuestionType) {
		this.generalQuestionType = generalQuestionType;
	}
	public Long getQuestionCategoryTypeId() {
		return questionCategoryTypeId;
	}
	public void setQuestionCategoryTypeId(Long questionCategoryTypeId) {
		this.questionCategoryTypeId = questionCategoryTypeId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
}
