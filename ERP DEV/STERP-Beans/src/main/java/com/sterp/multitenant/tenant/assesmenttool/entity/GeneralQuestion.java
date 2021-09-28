package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.GeneralQuestionType;
import com.sterp.multitenant.tenant.model.Type;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "general_questions")
public class GeneralQuestion extends SuperBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String question;
	
	@Column(name = "answer_type")
	private String answerType;
	
	private Integer timeout;
	
	private String description;
	
	@Column(name = "max_attempt")
	private Integer maxAttempt;
	
	@Column(name = "think_time")
	private Integer thinkTime;
	
	@Column(name = "general_question_type")
	@Enumerated(EnumType.STRING)
	private GeneralQuestionType generalQuestionType;
	
	@ManyToOne
	@JoinColumn(name = "question_category_type_id")
	private Type questionCategoryType;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
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
	public Type getQuestionCategoryType() {
		return questionCategoryType;
	}
	public void setQuestionCategoryType(Type questionCategoryType) {
		this.questionCategoryType = questionCategoryType;
	}
}
