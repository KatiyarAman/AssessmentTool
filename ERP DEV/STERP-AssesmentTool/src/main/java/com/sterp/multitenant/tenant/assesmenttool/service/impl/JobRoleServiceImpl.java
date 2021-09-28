package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobRole;
import com.sterp.multitenant.tenant.assesmenttool.entity.Skill;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobRoleDto;
import com.sterp.multitenant.tenant.assesmenttool.repository.IndustryTypeRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.JobRoleRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.SkillRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.JobRoleService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.entity.StatusEnum;

@Service
public class JobRoleServiceImpl implements JobRoleService {

	@Autowired
	private JobRoleRepository jobRoleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private IndustryTypeRepository industryTypeRepository;

	@Autowired
	private CommonService commonService;
	
	@Override
	@Transactional(value = "tenantTransactionManager")
	public Map<String, Object> saveOrUpdate(JobRoleDto jobRoleDto) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (jobRoleDto.getId() == null)
			response.put("message", "Category Inserted Successfully");
		else
			response.put("message", "Category Updated Successfully");

		JobRole jobRole = this.modelMapper.map(jobRoleDto, JobRole.class);
		
		List<Skill> skills = this.skillRepository.getAllSkillByIds(jobRoleDto.getSkillIds());
		IndustryType industryType = this.industryTypeRepository.findById(jobRoleDto.getIndustryTypeId()).get();
		jobRole.setSkillSet(skills);
		jobRole.setIndustryType(industryType);
		
		jobRole = this.jobRoleRepository.save(jobRole);
		response.put("data", jobRole);
		return response;
	}

	@Override
	@Transactional(readOnly = true, value = "tenantTransactionManager")
	public JobRoleDto getById(Long id) {
		JobRole jobRole = this.jobRoleRepository.findById(id).orElseThrow(() -> new RuntimeException());
		JobRoleDto dto = this.modelMapper.map(jobRole, JobRoleDto.class);
		List<Long> skillIds = jobRole.getSkillSet().stream().map(s -> s.getId()).collect(Collectors.toList());
		dto.setIndustryTypeId(jobRole.getIndustryType().getId());
		dto.setSkillIds(skillIds);
		return dto;
	}

//	@Override
//	public Map<String, Object> getJobRoleList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
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
//		int totalCount = Integer.parseInt(Long.valueOf(jobRoleRepository.count()).toString());
//		Page<JobRoleDto> categoryList = null;
//		if ((search == null) || (search.isEmpty())) {
//			categoryList = this.jobRoleRepository
//					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		} else {
//			categoryList = this.jobRoleRepository.findCustomSearch(search,
//					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		}
//		// customQuery.findCustom("ac");
//		response.put("totalCount", this.jobRoleRepository.count());
//		response.put("data", categoryList);
//		return response;
//	}
//
	@Override
	public Map<String, Object> getSelectionList(StatusEnum status) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> list = this.jobRoleRepository.getSelectionList(2L);
		response.put("data", list);
		return response;
	}

	@Override
	public Map<String, Object> getJobRoleList(Map<String, Object> params){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(JobRole.class, params));
		return response;
	}

}
