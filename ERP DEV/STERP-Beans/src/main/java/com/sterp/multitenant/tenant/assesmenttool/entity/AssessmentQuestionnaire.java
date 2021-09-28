package com.sterp.multitenant.tenant.assesmenttool.entity;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.QuestionDifficultyLevel;
import com.sterp.multitenant.tenant.model.Type;
import com.sterp.multitenant.tenant.settings.template.entity.SuperBean;

@Entity
@Table(name = "questionnaires")
public class AssessmentQuestionnaire extends SuperBean {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "difficulty_level", nullable = false)
	private QuestionDifficultyLevel difficultyLevel;
	
	@Column(name = "question_type", nullable = false)
	private String fieldType;
	
	@Column(name = "question", nullable = false)
	private String question;
	
	@Column(name = "description", columnDefinition = "MEDIUMTEXT")
	private String description;
	
	@Column(name = "answer_explanation", columnDefinition = "text")
	private String answerExplanation;
	
	@ManyToOne
	@JoinColumn(name = "question_category_type_id")
	private Type questionCategoryType;
	
	/*
	 * Time limit of answering this question in seconds
	 * */
	@Column(name = "time_limit")
	private Integer timeLimit;

	@Column(name = "negetive_mark_factor")
	private Double negetiveMarkFactor;
	
	@Column(name = "marks")
	private Integer marks;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "questionnaire_id")
	private List<QuestionnaireSkills> skills = new ArrayList<QuestionnaireSkills>();
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "questionnaire_id")
	private List<QuestionnaireOption> options = new ArrayList<QuestionnaireOption>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public List<QuestionnaireSkills> getSkills() {
		return skills;
	}

	public void setSkills(List<QuestionnaireSkills> skills) {
		this.skills = skills;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getAnswerExplanation() {
		return answerExplanation;
	}

	public void setAnswerExplanation(String answerExplanation) {
		this.answerExplanation = answerExplanation;
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

	public Type getQuestionCategoryType() {
		return questionCategoryType;
	}

	public void setQuestionCategoryType(Type questionCategoryType) {
		this.questionCategoryType = questionCategoryType;
	}
}