package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.assesmenttool.entity.QuestionnaireOption;
import com.sterp.multitenant.tenant.assesmenttool.repository.QuestionnaireOptionRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.QuestionnaireOptionService;

@Service
public class QuestionnaireOptionServiceImpl implements QuestionnaireOptionService{

	@Autowired
	private QuestionnaireOptionRepository questionnaireOptionRepository;
	
	@Override
	public Map<String, Object> saveOrUpdateQuestionnaireOption(QuestionnaireOption questionnaireOption) {
		Map<String, Object> response = new HashMap<>();
		if (questionnaireOption.getId() == 0)
			response.put("message", "Option added successfully");
		else
			response.put("message", "Option updated successfully");
		questionnaireOption = questionnaireOptionRepository.save(questionnaireOption);
	
		response.put("entity", questionnaireOption);
		return response;
	}

	@Override
	public Map<String, Object> removeQuestionnaireOption(long id) {
		Map<String, Object> response = new HashMap<>();
		questionnaireOptionRepository.deleteById(id);
		response.put("message", "Questionnaire Option Deleted Successfully");
		return response;
	}

}
