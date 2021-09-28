package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.assesmenttool.entity.Skill;
import com.sterp.multitenant.tenant.assesmenttool.repository.SkillRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.SkillService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private AppService appService;

	@Autowired
	private CommonService commonService;
	
	@Override
	public Map<String, Object> addOrUpdate(Skill skill) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (skill.getId() == null)
			response.put("mesage", "Skill Inserted Successfully");
		else
			response.put("message", "Skill Updated Successfully");
		skill = this.skillRepository.save(skill);
		response.put("data", skill);
		return response;
	}

	@Override
	public Skill getSkillById(Long id) {
		return this.skillRepository.findById(id).get();
	}

	@Override
	public Map<String, Object> getSkillsList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();

		int page = 1;
		int limit = 10;
		String search = null;
		String sort = null;
		String order = null;
		String filter = null;
		boolean filterRemove = false;
		List<ScreenFilterDTO> screenFilterDTO = Collections.emptyList();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (((String) entry.getKey()).contains("_page")) {
				page = Integer.parseInt(entry.getValue().toString());
			} else if (((String) entry.getKey()).contains("_limit")) {
				limit = Integer.parseInt(entry.getValue().toString());
			} else if (((String) entry.getKey()).contains("_order")) {
				order = (String) entry.getValue();
			} else if (((String) entry.getKey()).contains("_sort")) {
				sort = (String) entry.getValue();
			} else if (((String) entry.getKey()).contains("_search")) {
				search = (String) entry.getValue();
			} else if (((String) entry.getKey()).contains("_like")) {
				search = (String) entry.getValue();
			} else if (((String) entry.getKey()).contains("screen_filter")) {
				filter = (String) entry.getValue();
				screenFilterDTO = objectMapper.readValue(filter, new TypeReference<List<ScreenFilterDTO>>() {
				});
			} else if (((String) entry.getKey()).contains("filter_remove")) {
				filterRemove = Boolean.parseBoolean((String) entry.getValue());
			}
		}
		int moduleId = 0;// Integer.parseInt(httpServletRequest.getHeader("screenId"));

		if ((sort == null) || (sort.isEmpty())) {
			sort = "id";
		}
		if ((order == null) || (order.isEmpty())) {
			order = Sort.Direction.ASC.toString();
		}
		int totalCount = Integer.parseInt(Long.valueOf(skillRepository.count()).toString());
		Page<Skill> skillList = null;
		if ((search == null) || (search.isEmpty())) {
			skillList = this.skillRepository
					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			skillList = this.skillRepository.findCustomSearch(search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}
		// customQuery.findCustom("ac");
		response.put("totalCount", this.skillRepository.count());
		response.put("data", skillList);
		return response;
	}

	@Override
	public Map<String, Object> getSelectionList() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> list = this.skillRepository.getSelectionList(2L);
		response.put("data", list);
		return response;
	}

	@Override
	public Map<String, Object> getSkillList(Map<String, Object> params){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(Skill.class, params));
		return response;
	}

}
