package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

public class InterviewGeneralQuestionMappingBeanDto {
	
	private Long id;
	private Integer timeout;
	private Integer thinkTime;
	private Integer attempts;
	private Long questionId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public Integer getThinkTime() {
		return thinkTime;
	}
	public void setThinkTime(Integer thinkTime) {
		this.thinkTime = thinkTime;
	}
	public Integer getAttempts() {
		return attempts;
	}
	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	@Override
	public String toString() {
		return "InterviewGeneralQuestionMappingBeanDto [id=" + id + ", timeout=" + timeout + ", thinkTime=" + thinkTime
				+ ", attempts=" + attempts + ", questionId=" + questionId + "]";
	}
	
	
	
}
