package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.io.IOException;
import java.math.BigInteger;
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
import com.sterp.multitenant.tenant.assesmenttool.entity.AssessementQuetsionTestMapping;
import com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentQuestionnaire;
import com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentTest;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile;
import com.sterp.multitenant.tenant.assesmenttool.entity.Skill;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentQuestionnaireDto;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentTestDto;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.SkillDto;
import com.sterp.multitenant.tenant.assesmenttool.repository.AssessmentQuestionnaireRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.AssessmentTestRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.JobProfileRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.SkillRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.AssessmentTestService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service	
public class AssessmentTestServiceImpl implements AssessmentTestService {

	@Autowired
	private AssessmentTestRepository assessmentTestRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private JobProfileRepository jobProfileRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AssessmentQuestionnaireRepository assessmentQuestionnaireRepository;
	
	@Override
	public Map<String, Object> saveOrUpdate(AssessmentTestDto object) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<AssessmentQuestionnaire> questions = new ArrayList<>();
		AssessmentTest assessmentTest = this.modelMapper.map(object, AssessmentTest.class);
		if (object.getId() == null)
			response.put("message", "Assessment Test Created Successfully");
		else
			response.put("message", "Assessment Test Updated Successfully");
		Optional<JobProfile> jobProfile = this.jobProfileRepository.findById(object.getJobProfileId());
		assessmentTest.setJobProfile(jobProfile.get());
		List<AssessementQuetsionTestMapping> mappings = new ArrayList<AssessementQuetsionTestMapping>();
		if(object.getId() == null) {
			if (!object.getQuestionnaireIds().isEmpty()) {
				for (Long  qIds : object.getQuestionnaireIds()) {
					AssessementQuetsionTestMapping testMapping = new AssessementQuetsionTestMapping();
					testMapping.setQuestionnaireId(qIds);
					mappings.add(testMapping);
				}
				
			}
			
			
		}else {
			AssessmentTest test = this.assessmentTestRepository.findById(object.getId()).get();
			mappings = test.getMapping();
			if (!object.getQuestionnaireIds().isEmpty()) {
				int flag = 0;
				for (Long  qIds : object.getQuestionnaireIds()) {
					flag= 0;
					for (AssessementQuetsionTestMapping mapping1 : mappings) {
						if(mapping1.getQuestionnaireId() == qIds) {
							flag++;
						}
					}
					if(flag == 0) {
						AssessementQuetsionTestMapping testMapping = new AssessementQuetsionTestMapping();
						testMapping.setQuestionnaireId(qIds);
						mappings.add(testMapping);
					}
					
				}
				
			}
		}
		
		if (!object.getQuestionnaireIds().isEmpty())
			questions = this.assessmentQuestionnaireRepository
		 		.getAllQuestionnairesById(object.getQuestionnaireIds());			
		assessmentTest.setQuestions(questions);
		
		assessmentTest = this.assessmentTestRepository.save(assessmentTest);
		object = this.modelMapper.map(assessmentTest, AssessmentTestDto.class);
		response.put("data", object);
		return response;
	}

	@Override
	@Transactional(value = "tenantTransactionManager", readOnly = true)
	public AssessmentTestDto getById(Long id) {
		AssessmentTest assessmentTest = this.assessmentTestRepository
			.findById(id).orElseThrow(() -> new RuntimeException());
		AssessmentTestDto dto = this.modelMapper.map(assessmentTest, AssessmentTestDto.class);
		dto.setJobProfileId(assessmentTest.getJobProfile().getId());
		dto.setQuestionnaireIds(assessmentTest.getQuestions().stream().map(q -> q.getId()).collect(Collectors.toList()));
		return dto;
	}

	@Override
	public Map<String, Object> getSkillDetailsByJobProfileId(Long jobProfileId) {
		Map<String, Object> response = new HashMap<String, Object>();
		JobProfile jobProfile = this.jobProfileRepository.findById(jobProfileId).get();
		List<Object[]> skills = this.skillRepository.getSkillDetails(jobProfile.getJobRole().getId());
		List<SkillDto> skillsDto = new ArrayList<SkillDto>();
		skills.forEach(e -> {
			skillsDto.add(new SkillDto(((BigInteger) e[0]).longValue(),(String) e[1],(String) e[2],((BigInteger) e[3]).intValue()));
		});
		response.put("data", skillsDto);
		return response;
	}
	
	@Override
	public Map<String, Object> getAssessmentTestList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
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
//		int totalCount = Integer.parseInt(Long.valueOf(assessmentTestRepository.count()).toString());
//		Page<AssessmentTestDto> assessmentTestList = null;
//		
//		if ((search == null) || (search.isEmpty())) {
//			assessmentTestList = this.assessmentTestRepository
//					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		} else {
//			assessmentTestList = this.assessmentTestRepository.findCustomSearch(search,
//					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		}
//		// customQuery.findCustom("ac");
//		response.put("totalCount", this.assessmentTestRepository.count());
//		response.put("data", assessmentTestList);
//		return response;
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(AssessmentTest.class, params));
		return response;
	}

	
	@Override
	public Map<String, Object> getQuestionnairesBySkillId(Long skillId) {
		Map<String, Object> response = new HashMap<String, Object>();
		Skill skill = this.skillRepository.findById(skillId).get();
		List<AssessmentQuestionnaireDto> list = this.assessmentQuestionnaireRepository.findQuestionnairesBySkill(skill);
		response.put("data", list);
		return response;
	}

	@Override
	public Map<String, Object> getSelectionList() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> list = this.assessmentTestRepository.getSelectionList(2L);
		response.put("data", list);
		return response;
	}
}
