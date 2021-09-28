package com.sterp.multitenant.tenant.assesmenttool.entity.dto;

import java.util.List;

public class JobProfileQuestionnaireMappingDto {
	
	private Long jobProfileId;
	private List<QuestionnaireIdDto> questionnaireIds;
	
	public Long getJobProfileId() {
		return jobProfileId;
	}
	public void setJobProfileId(Long jobProfileId) {
		this.jobProfileId = jobProfileId;
	}
	public List<QuestionnaireIdDto> getQuestionnaireIds() {
		return questionnaireIds;
	}
	public void setQuestionnaireIds(List<QuestionnaireIdDto> questionnaireIds) {
		this.questionnaireIds = questionnaireIds;
	}
	
	
	
}
