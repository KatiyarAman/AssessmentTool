package com.sterp.multitenant.tenant.settings.financialyear.service;

import java.util.Map;

import com.sterp.multitenant.tenant.settings.questionnaire.entity.QuestionnaireSettings;

public interface FinancialYearService {
	
	public Map<String, Object> add(QuestionnaireSettings setting);
	
	public Map<String, Object> update(QuestionnaireSettings setting);
}
