package com.sterp.multitenant.tenant.settings.questionnaire.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.dto.CommonRequestFilterDTO;
import com.sterp.multitenant.tenant.settings.questionnaire.dto.QuestionnaireRequestDto;
import com.sterp.multitenant.tenant.settings.questionnaire.entity.QuestionnaireSettings;

public interface QuestionnaireService {

	Map<String, Object> saveOrUpdate(QuestionnaireRequestDto object, HttpServletRequest request);

	QuestionnaireSettings getQuestionnaireById(Long id);

	List<QuestionnaireSettings> getAll();
	
	public List<CommonDropdownResponseDataTravelObject> getSelectionList(String T, String id, String name,
			List<CommonRequestFilterDTO> filters);

}
