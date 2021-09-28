package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import java.util.List;

import com.sterp.multitenant.tenant.assesmenttool.entity.QuestionnaireOption;
import com.sterp.multitenant.tenant.assesmenttool.enumtype.QuestionDifficultyLevel;

public class AssessmentQuestionnaireDto {
	
	private Long id;
	private List<Long> skillIds;
	private QuestionDifficultyLevel difficultyLevel;
	private String fieldType;
	private String question;
	private String description;
	private Long status;
	private String answerExplanation;
	private Integer timeLimit;
	private Double negetiveMarkFactor;
	private Integer marks;
	private List<QuestionnaireOption> options;
	private Long questionCategoryTypeId;
	
	public AssessmentQuestionnaireDto() {
		super();
	}

	public AssessmentQuestionnaireDto(Long id,QuestionDifficultyLevel difficultyLevel, String fieldType,
			String question, String description, Long status, Integer timeLimit, Integer marks, Double negetiveMarkFactor) {
		super();
		this.id = id;
		this.difficultyLevel = difficultyLevel;
		this.fieldType = fieldType;
		this.question = question;
		this.description = description;
		this.status = status;
		this.timeLimit = timeLimit;
		this.marks = marks;
		this.negetiveMarkFactor = negetiveMarkFactor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		
	}

	public String getAnswerExplanation() {
		return answerExplanation;
	}

	public void setAnswerExplanation(String answerExplanation) {
		this.answerExplanation = answerExplanation;
	}

	public List<Long> getSkillIds() {
		return skillIds;
	}

	public void setSkillIds(List<Long> skillIds) {
		this.skillIds = skillIds;
	}

	public QuestionDifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(QuestionDifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Double getNegetiveMarkFactor() {
		return negetiveMarkFactor;
	}

	public void setNegetiveMarkFactor(Double negetiveMarkFactor) {
		this.negetiveMarkFactor = negetiveMarkFactor;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

	public List<QuestionnaireOption> getOptions() {
		return options;
	}

	public void setOptions(List<QuestionnaireOption> options) {
		this.options = options;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getQuestionCategoryTypeId() {
		return questionCategoryTypeId;
	}

	public void setQuestionCategoryTypeId(Long questionCategoryTypeId) {
		this.questionCategoryTypeId = questionCategoryTypeId;
	}
	
}
