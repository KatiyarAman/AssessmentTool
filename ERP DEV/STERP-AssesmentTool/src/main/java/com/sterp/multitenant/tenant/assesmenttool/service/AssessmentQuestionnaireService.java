package com.sterp.multitenant.tenant.assesmenttool.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentQuestionnaireDto;

public interface AssessmentQuestionnaireService {
	Map<String, Object> saveOrUpdate(AssessmentQuestionnaireDto questionnaireDto);
	AssessmentQuestionnaireDto getQuestionnaireById(Long id);
	Map<String ,Object> getQuestionnaireList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException;
	Map<String, Object> getSelectionList();
	Map<String, Object> getQuestionnaireDetailsById(Long id);
	Map<String, Object> getQuestionnaireListByIds(List<Long> ids);
}
