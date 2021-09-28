package com.sterp.multitenant.tenant.assesmenttool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="assessment_test_questionnaire_mapping")
public class AssessementQuetsionTestMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="assessment_test_id")
	private Long assessmentTestId;
	
	@Column(name="questionnaire_id")
	private Long questionnaireId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssessmentTestId() {
		return assessmentTestId;
	}

	public void setAssessmentTestId(Long assessmentTestId) {
		this.assessmentTestId = assessmentTestId;
	}

	public Long getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	
	

}
