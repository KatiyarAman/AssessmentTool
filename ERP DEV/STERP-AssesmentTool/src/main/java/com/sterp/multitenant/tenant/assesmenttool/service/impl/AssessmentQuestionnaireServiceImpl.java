package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentQuestionnaire;
import com.sterp.multitenant.tenant.assesmenttool.entity.QuestionnaireOption;
import com.sterp.multitenant.tenant.assesmenttool.entity.QuestionnaireSkills;
import com.sterp.multitenant.tenant.assesmenttool.entity.Skill;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentQuestionnaireDto;
import com.sterp.multitenant.tenant.assesmenttool.repository.AssessmentQuestionnaireRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.SkillRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.AssessmentQuestionnaireService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.company.entity.Company;
import com.sterp.multitenant.tenant.core.repository.TypeRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.model.Type;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public class AssessmentQuestionnaireServiceImpl implements AssessmentQuestionnaireService {

	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private AssessmentQuestionnaireRepository assessmentQuestionnaireRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private CommonService commonService;
	
	
	@Override
	@Transactional(readOnly = true, value = "tenantTransactionManager")
	public AssessmentQuestionnaireDto getQuestionnaireById(Long id) {
		AssessmentQuestionnaire questionnaire = this.assessmentQuestionnaireRepository.findById(id).get();
		AssessmentQuestionnaireDto dto = this.modelMapper.map(questionnaire, AssessmentQuestionnaireDto.class);
		List<Long> skillIds = questionnaire.getSkills().stream().map(s -> s.getSkillId()).collect(Collectors.toList());
		dto.setSkillIds(skillIds);
		dto.setQuestionCategoryTypeId(questionnaire.getQuestionCategoryType().getId());
		return dto;
	}

	@Override
	public Map<String, Object> getQuestionnaireList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
//		Map<String, Object> response = new HashMap<String, Object>();
//
//		int page = 1;
//		int limit = 10;
//		String search = null;
//		String sort = null;
//		String order = null;
//		String filter = null;
//		boolean filterRemove = false;
//		List<ScreenFilterDTO> screenFilterDTO = Collections.emptyList();
//		for (Map.Entry<String, Object> entry : params.entrySet()) {
//			if (((String) entry.getKey()).contains("_page")) {
//				page = Integer.parseInt(entry.getValue().toString());
//			} else if (((String) entry.getKey()).contains("_limit")) {
//				limit = Integer.parseInt(entry.getValue().toString());
//			} else if (((String) entry.getKey()).contains("_order")) {
//				order = (String) entry.getValue();
//			} else if (((String) entry.getKey()).contains("_sort")) {
//				sort = (String) entry.getValue();
//			} else if (((String) entry.getKey()).contains("_search")) {
//				search = (String) entry.getValue();
//			} else if (((String) entry.getKey()).contains("_like")) {
//				search = (String) entry.getValue();
//			} else if (((String) entry.getKey()).contains("screen_filter")) {
//				filter = (String) entry.getValue();
//				screenFilterDTO = objectMapper.readValue(filter, new TypeReference<List<ScreenFilterDTO>>() {
//				});
//			} else if (((String) entry.getKey()).contains("filter_remove")) {
//				filterRemove = Boolean.parseBoolean((String) entry.getValue());
//			}
//		}
//		int moduleId = 0;// Integer.parseInt(httpServletRequest.getHeader("screenId"));
//
//		if ((sort == null) || (sort.isEmpty())) {
//			sort = "id";
//		}
//		if ((order == null) || (order.isEmpty())) {
//			order = Sort.Direction.ASC.toString();
//		}
//		int totalCount = Integer.parseInt(Long.valueOf(assessmentQuestionnaireRepository.count()).toString());
//		Page<AssessmentQuestionnaireDto> categoryList = null;
//		if ((search == null) || (search.isEmpty())) {
//			categoryList = this.assessmentQuestionnaireRepository
//					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		} else {
//			categoryList = this.assessmentQuestionnaireRepository.findCustomSearch(search,
//					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		}
//		// customQuery.findCustom("ac");
//		response.put("totalCount", this.assessmentQuestionnaireRepository.count());
//		response.put("data", categoryList);
//		return response;
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(AssessmentQuestionnaire.class, params));
		return response;
	}

	@Override
	public Map<String, Object> getSelectionList() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> list = this.assessmentQuestionnaireRepository.getSelectionList(2L);
		response.put("data", list);
		return response;
	}

	@Override
	public Map<String, Object> saveOrUpdate(AssessmentQuestionnaireDto questionnaireDto) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<QuestionnaireSkills> skills = new ArrayList<QuestionnaireSkills>();
		System.out.println("Options:"+questionnaireDto.getOptions());
		if (questionnaireDto.getId() == null)
			response.put("message", "Questionnaire Inserted Successfully");
		else {
			AssessmentQuestionnaire questionnaires = this.assessmentQuestionnaireRepository.findById(questionnaireDto.getId()).get();
			skills = questionnaires.getSkills();
			response.put("message", "Questionnaire Updated Successfully");
		}
		
		AssessmentQuestionnaire questionnaire = this.modelMapper.map(questionnaireDto, AssessmentQuestionnaire.class);
		
		
		
		if (!questionnaireDto.getSkillIds().isEmpty()) {
			int flag = 0;
			for (Long skill : questionnaireDto.getSkillIds()) {
				
				flag = 0;
				if(questionnaireDto.getId() == null) {
					QuestionnaireSkills  qskill = new QuestionnaireSkills();
					qskill.setSkillId(skill);
					skills.add(qskill);
				}else {
					for (QuestionnaireSkills questionnaireSkills : skills) {
						if(questionnaireSkills.getSkillId() == skill) {
							flag++;
						}
					}
					if(flag == 0 ) {
						QuestionnaireSkills  qskill = new QuestionnaireSkills();
						qskill.setSkillId(skill);
						skills.add(qskill);
						
					}
				}
				
			}
			
			
		} 
		
		questionnaire.setSkills(skills);
		Optional<Type> typeOptional = this.typeRepository.findById(questionnaireDto.getQuestionCategoryTypeId());
		if (typeOptional.isPresent()) {
			questionnaire.setQuestionCategoryType(typeOptional.get());
		}
		questionnaire = this.assessmentQuestionnaireRepository.save(questionnaire);
		questionnaireDto.setId(questionnaire.getId());
		response.put("data", questionnaireDto);
		return response;
	}

	@Override
	@Transactional(readOnly = true, value="tenantTransactionManager")
	public Map<String, Object> getQuestionnaireDetailsById(Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		AssessmentQuestionnaire aq = this.assessmentQuestionnaireRepository.findById(id).get();
		AssessmentQuestionnaireDto dto = this.modelMapper.map(aq, AssessmentQuestionnaireDto.class);
		List<Long> skillIds = aq.getSkills().stream().map(s -> s.getId()).collect(Collectors.toList());
		dto.setSkillIds(skillIds);
		dto.setQuestionCategoryTypeId(aq.getQuestionCategoryType().getId());
		response.put("data", dto);
		return response;
	}

	@Override
	public Map<String, Object> getQuestionnaireListByIds(List<Long> ids) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<AssessmentQuestionnaire> allQuestionnairesById = this.assessmentQuestionnaireRepository.getAllQuestionnairesById(ids);
		response.put("data", allQuestionnairesById);
		return response;
	}
}
