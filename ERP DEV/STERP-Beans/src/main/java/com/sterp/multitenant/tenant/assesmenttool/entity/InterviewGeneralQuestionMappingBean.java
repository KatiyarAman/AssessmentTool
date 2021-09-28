package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="interview_questions_mapping")
public class InterviewGeneralQuestionMappingBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name = "attempts")
	private Integer attempts;
	
	@Column(name = "think_time")
	private Integer thinkTime;
	
	private Integer timeout;
	
	@Column(name="interview_id")
	private Long interviewId;

	
	@Column(name = "general_question_id")
	private Long generalQuestionId;
	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	

	public Integer getThinkTime() {
		return thinkTime;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public void setThinkTime(Integer thinkTime) {
		this.thinkTime = thinkTime;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Long interviewId) {
		this.interviewId = interviewId;
	}

	public Long getGeneralQuestionId() {
		return generalQuestionId;
	}

	public void setGeneralQuestionId(Long generalQuestionId) {
		this.generalQuestionId = generalQuestionId;
	}

	
	
	
}
