package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="questionnaire_skills_mapping")
public class QuestionnaireSkills {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;
	
	@Column(name="questionnaire_id")
	private Long questionnaireId;
	
	@Column(name="skill_id")
	private Long skillId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnareId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	
	

}
