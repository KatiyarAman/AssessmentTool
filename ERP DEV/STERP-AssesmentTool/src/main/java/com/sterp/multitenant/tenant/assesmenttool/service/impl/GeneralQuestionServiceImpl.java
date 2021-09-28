package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentQuestionnaire;
import com.sterp.multitenant.tenant.assesmenttool.entity.GeneralQuestion;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.GeneralQuestionDto;
import com.sterp.multitenant.tenant.assesmenttool.repository.GeneralQuestionRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.GeneralQuestionService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.repository.TypeRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.model.Type;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public class GeneralQuestionServiceImpl implements GeneralQuestionService {
	
	@Autowired
	private GeneralQuestionRepository generalQuestionRepository;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private CommonService commonService;
	
	public Map<String, Object> addOrUpdate(GeneralQuestionDto generalQuestionDto) {
		Map<String, Object> response = new HashMap<>();
		GeneralQuestion generalQuestion = this.modelMapper.map(generalQuestionDto,GeneralQuestion.class);
		Type questionCategoryType = this.typeRepository.findById(generalQuestionDto.getQuestionCategoryTypeId()).get();
		generalQuestion.setQuestionCategoryType(questionCategoryType);
		if (generalQuestion.getId() == null)
			response.put("message", "General Question Added Successfully");
		else
			response.put("message", "General Question Added Successfully");
		generalQuestion = this.generalQuestionRepository.save(generalQuestion);
		generalQuestionDto.setId(generalQuestion.getId());
		response.put("data", generalQuestionDto);
		return response;
	}

	@Override
	public GeneralQuestionDto getGeneralQuestionById(Long id) {
		GeneralQuestion gq = this.generalQuestionRepository.findById(id).get();
		GeneralQuestionDto dto = this.modelMapper.map(gq,GeneralQuestionDto.class);
		dto.setQuestionCategoryTypeId(gq.getQuestionCategoryType().getId());
		return dto;
	}

	@Override
	public Map<String, Object> getGeneralQuestionList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
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
//		int totalCount = Integer.parseInt(Long.valueOf(generalQuestionRepository.count()).toString());
//		Page<GeneralQuestionDto> skillList = null;
//		if ((search == null) || (search.isEmpty())) {
//			skillList = this.generalQuestionRepository
//					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		} else {
//			skillList = this.generalQuestionRepository.findCustomSearch(search,
//					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		}
//		// customQuery.findCustom("ac");
//		response.put("totalCount", this.generalQuestionRepository.count());
//		response.put("data", skillList);
//		return response;
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(GeneralQuestion.class, params));
		return response;
	}

	@Override
	public Map<String, Object> getSelectionList() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> list = this.generalQuestionRepository.getSelectionList();
		response.put("data", list);
		return response;
	}
	
	public GeneralQuestion getById(Long id) {
		return this.generalQuestionRepository.getOne(id);
	}
}
