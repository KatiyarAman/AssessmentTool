package com.sterp.multitenant.tenant.assesmenttool.service;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.GeneralQuestion;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.GeneralQuestionDto;

public interface GeneralQuestionService {

	Map<String, Object> addOrUpdate(GeneralQuestionDto object);

	GeneralQuestionDto getGeneralQuestionById(Long id);

	Map<String, Object> getGeneralQuestionList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException;

	Map<String, Object> getSelectionList();
	
	GeneralQuestion getById(Long id);
}
