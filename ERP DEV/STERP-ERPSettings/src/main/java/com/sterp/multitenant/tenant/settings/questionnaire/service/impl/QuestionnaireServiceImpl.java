package com.sterp.multitenant.tenant.settings.questionnaire.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.branch.entity.Branch;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.dto.CommonRequestFilterDTO;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.settings.financialyear.service.FinancialYearService;
import com.sterp.multitenant.tenant.settings.questionnaire.dto.QuestionnaireRequestDto;
import com.sterp.multitenant.tenant.settings.questionnaire.entity.QuestionnaireSettings;
import com.sterp.multitenant.tenant.settings.questionnaire.repository.QuestionnaireRepository;
import com.sterp.multitenant.tenant.settings.questionnaire.service.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
	
	@Autowired
	QuestionnaireRepository questionnaireRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	AppService appService;
	
	@Autowired
	FinancialYearService fyService;

	@Override
	public Map<String, Object> saveOrUpdate(QuestionnaireRequestDto object, HttpServletRequest request) {
		UserEntity currentUser = appService.getCurrentUser();
		Map<String, Object> response = new HashedMap<String, Object>();
		QuestionnaireSettings questionnaire = mapper.map(object, QuestionnaireSettings.class);
		
		Long defaultLoginBranch  = Long.valueOf((Integer) request.getAttribute("defaultloginbranch"));
		Long defaultCompanyId =  commonService.getById(Branch.class, defaultLoginBranch).getCompanyId();
		
		questionnaire.setCreatedBy(currentUser.getId());
		questionnaire.setModifiedBy(currentUser.getId());
		questionnaire.setCompanyId(defaultCompanyId);
		questionnaire.setBranchId(defaultLoginBranch);
		
		QuestionnaireSettings responseObject = this.questionnaireRepository.save(questionnaire);
		response.put("data", responseObject);
		response.put("message", object.getId() == null ? "QuestionnaireSettings Added Successfully" : "QuestionnaireSettings Updated Successfully");
		
		/* Add/Update Company Year(financial/fiscal year) */
		if(responseObject.getField().getFieldName().equals("fiscal_year") || responseObject.getField().getFieldName().equals("financial_month")) {
			response.put("fyear", object.getId() == 0 ? this.fyService.add(responseObject) : this.fyService.update(responseObject));
		}
		return response;
	}

	@Override
	public QuestionnaireSettings getQuestionnaireById(Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Optional<QuestionnaireSettings> optional = this.questionnaireRepository.findById(id);
		if(optional.isPresent()) {
			response.put("data", optional.get());
			return optional.get();
		}
		response.put("message", "QuestionnaireSettings with given Id: " + id + " not found!");
		return null;
	}

	@Override
	public List<QuestionnaireSettings> getAll() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<QuestionnaireSettings> settings = this.questionnaireRepository.findAll();
		if(settings.size() > 0) {
			response.put("data", settings);
			return settings;
		}
		response.put("message", "No QuestionnaireSettings found:");
		return null;
	}

	@Override
	public List<CommonDropdownResponseDataTravelObject> getSelectionList(String T, String id, String name,
			List<CommonRequestFilterDTO> filters) {
		return this.commonService.getSelectionList(T, id, name, filters);
	}

}
