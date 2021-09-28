package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.sterp.multitenant.tenant.assesmenttool.entity.GeneralQuestion;
import com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType;
import com.sterp.multitenant.tenant.assesmenttool.entity.Interview;
import com.sterp.multitenant.tenant.assesmenttool.entity.InterviewGeneralQuestionMappingBean;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.InterviewDto;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.InterviewGeneralQuestionMappingBeanDto;
import com.sterp.multitenant.tenant.assesmenttool.repository.InterviewRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.GeneralQuestionService;
import com.sterp.multitenant.tenant.assesmenttool.service.InterviewService;
import com.sterp.multitenant.tenant.assesmenttool.service.JobProfileService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;


@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepository interviewRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JobProfileService JobProfileService;

	@Autowired
	private GeneralQuestionService generalQuestionService;

	@Autowired
	private AppService appService;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	CommonService commonService;
	
	@Override
	@Transactional(value = "tenantTransactionManager")
	public Map<String, Object> addOrUpdate(InterviewDto object) {
		Map<String, Object> response = new HashMap<String, Object>();
		Interview interview = this.modelMapper.map(object, Interview.class);
		JobProfile jobProfile = this.JobProfileService.getJobProfileById(object.getJobProfileId());
		interview.setJobProfile(jobProfile);
		
//		object.getQuestions().forEach(question -> {
//			InterviewGeneralQuestionMappingBean bean = this.modelMapper.map(question, InterviewGeneralQuestionMappingBean.class);
//			GeneralQuestion generalQuestion = this.generalQuestionService.getById(question.getQuestionId());
////			bean.setGeneralQuestion(generalQuestion);
//			interview.getMappedQuestions().add(bean);
//		});
		
		if (interview.getId() == null) {
			response.put("message", "Interview Created!");
		} else {
			response.put("message", "Interview Updated!");
		}
		this.interviewRepository.save(interview);
		System.out.println(object);
		return response;
	}

	@Override
	@Transactional(value = "tenantTransactionManager", readOnly = true)
	public InterviewDto getById(Long id) {
		Optional<Interview> interview = this.interviewRepository.findById(id);
		if (interview.isPresent()) {
			Interview invw = interview.get();
			InterviewDto dto = this.modelMapper.map(invw,InterviewDto.class);
			dto.setQuestions(new ArrayList<>()); 
//			invw.getMappedQuestions().forEach(question -> {
//				InterviewGeneralQuestionMappingBeanDto questionDto = this.modelMapper.map(question, InterviewGeneralQuestionMappingBeanDto.class);
//				questionDto.setQuestionId(question.getGeneralQuestion().getId());
//				dto.getQuestions().add(questionDto);
//			});
			return dto;
		}
		return null;
	}

	@Override
	public Map<String, Object> getInterviewList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
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
//		int totalCount = Integer.parseInt(Long.valueOf(interviewRepository.count()).toString());
//		Page<InterviewDto> intetviewList = null;
//		if ((search == null) || (search.isEmpty())) {
//			intetviewList = this.interviewRepository
//					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		} else {
//			intetviewList = this.interviewRepository.findCustomSearch(search,
//					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		}
//		// customQuery.findCustom("ac");
//		response.put("totalCount", this.interviewRepository.count());
//		response.put("data", intetviewList);
//		return response;
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(Interview.class, params));
		return response;
	}

	@Override
	public Map<String, Object> getSelectionList() {
		Map<String, Object> response = new HashMap<>();
		List<CommonDropdownResponseDataTravelObject> list = this.interviewRepository.getSelectionList();
		response.put("data", list);
		return response;
	}

}
