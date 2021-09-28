package com.sterp.multitenant.tenant.assesmenttool.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentTestDto;

@Service
public interface AssessmentTestService {

	Map<String, Object> saveOrUpdate(AssessmentTestDto object);
	AssessmentTestDto getById(Long id);
	Map<String, Object> getAssessmentTestList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException;
	Map<String, Object> getSkillDetailsByJobProfileId(Long jobProfileId);
	Map<String, Object> getQuestionnairesBySkillId(Long skillId);
	Map<String, Object> getSelectionList();
}
