package com.sterp.multitenant.tenant.assesmenttool.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.assesmenttool.entity.QuestionnaireOption;


@Service
public interface QuestionnaireOptionService {
	
	Map<String, Object> saveOrUpdateQuestionnaireOption(QuestionnaireOption questionnaireOption);
	Map<String, Object> removeQuestionnaireOption(long id);
}
