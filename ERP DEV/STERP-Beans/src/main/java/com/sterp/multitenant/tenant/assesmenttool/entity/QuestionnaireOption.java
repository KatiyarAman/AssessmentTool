package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaire_options")
public class QuestionnaireOption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "option_name", nullable = false)
	private String optionName;
	
	@Column(name = "status")
	private Long status;
	
	@Column(name = "correct_option", nullable = false)
	private boolean correctOption;
	
	@Column(name = "option_weight")
	private Integer optionWeight;
	
	@Column(name="questionnaire_id")
	private Long questionnaireId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public boolean isCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(boolean correctOption) {
		this.correctOption = correctOption;
	}
	public Integer getOptionWeight() {
		return optionWeight;
	}
	public void setOptionWeight(Integer optionWeight) {
		this.optionWeight = optionWeight;
	}
	public Long getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
}
