package com.sterp.multitenant.tenant.assesmenttool.service;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.InterviewDto;

public interface InterviewService {

	Map<String, Object> addOrUpdate(InterviewDto object);

	InterviewDto getById(Long id);

	Map<String, Object> getInterviewList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException;

	Map<String, Object> getSelectionList();

//	Object getQuestionnaireList(Map<String, Object> params);

}
