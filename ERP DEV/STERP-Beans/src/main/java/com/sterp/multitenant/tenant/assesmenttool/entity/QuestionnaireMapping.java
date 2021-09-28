package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sterp.multitenant.tenant.assesmenttool.enumtype.QuestionDifficultyLevel;

@Entity
@Table(name = "questionnaire_mappings")
public class QuestionnaireMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "question_quantity")
	private int questionQuantity;
	
	@Column(name = "difficulty_level")
	private QuestionDifficultyLevel difficultyLevel;
	
	@Column(name = "time_out_duration")
	private int timeOutDuration;
	
	@Column(name = "weight_per_question")
	private int weightPerQuestion;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	private Skill skill;

	@Transient
	private Long skillId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuestionQuantity() {
		return questionQuantity;
	}

	public void setQuestionQuantity(int questionQuantity) {
		this.questionQuantity = questionQuantity;
	}

	public QuestionDifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(QuestionDifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public int getTimeOutDuration() {
		return timeOutDuration;
	}

	public void setTimeOutDuration(int timeOutDuration) {
		this.timeOutDuration = timeOutDuration;
	}

	public int getWeightPerQuestion() {
		return weightPerQuestion;
	}

	public void setWeightPerQuestion(int weightPerQuestion) {
		this.weightPerQuestion = weightPerQuestion;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	
}
