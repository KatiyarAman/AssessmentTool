package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.assesmenttool.entity.HiringRound;
import com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobPosting;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobRole;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobProfileDto;
import com.sterp.multitenant.tenant.assesmenttool.exception.ResourceNotFoundException;
import com.sterp.multitenant.tenant.assesmenttool.repository.CandidateRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.CandidateVerificationTokenRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.IndustryTypeRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.JobPostingRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.JobProfileRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.JobRoleRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.JobProfileService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.employee.entity.Employee;
import com.sterp.multitenant.tenant.employee.repository.EmployeeRepository;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;
import com.sterp.multitenant.utils.JwtTokenUtil;

@Service
public class JobProfileServiceImpl implements JobProfileService {

	private static final String RESUME_DIR = "/home/shivit/resume_dir/";

	@Autowired
	private JobProfileRepository jobProfileRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JobRoleRepository jobRoleRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private AppService appService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private IndustryTypeRepository industryTypeRepository;
	
	@Autowired
	private CandidateVerificationTokenRepository candidateVerificationTokenRepository;

	@Autowired
	private JobPostingRepository jobPostingRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	CommonService commonService;
	
	@Override
	public Page<JobProfile> getAllJobProfiles(Pageable pageable) {
		return jobProfileRepository.findAll(pageable);
	}

	@Override
	@Transactional(value = "tenantTransactionManager", readOnly = true)
	public JobProfileDto getJobProfileDtoById(long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		JobProfile jobProfile = jobProfileRepository.findById(id).get();
		JobProfileDto dto = this.mapper.map(jobProfile, JobProfileDto.class);
		dto.setIndustryTypeId(jobProfile.getIndustryType().getId());
		dto.setJobRoleId(jobProfile.getJobRole().getId());
		
//		jobProfile.getJobHiringRounds().forEach(round -> {
//			HiringRoundDto hrdto = this.mapper.map(round, HiringRoundDto.class);
//			dto.getHiringRounds().add(hrdto);
//		});
		
		return dto;
	}

	@Override
	@Transactional(value = "tenantTransactionManager")
	public Map<String, Object> saveOrUpdateJobProfile(JobProfileDto jobProfileDto) {
		Map<String, Object> response = new HashMap<>();
		JobRole jobRole = this.jobRoleRepository.findById(jobProfileDto.getJobRoleId()).get();
		IndustryType industryType = this.industryTypeRepository.findById(jobProfileDto.getIndustryTypeId()).get();
		JobProfile jobProfile = mapper.map(jobProfileDto, JobProfile.class);
		jobProfile.setJobHiringRounds(new ArrayList<HiringRound>());
		jobProfileDto.getHiringRounds().forEach(round -> {
			HiringRound hr = this.mapper.map(round, HiringRound.class);
			Employee employee = this.employeeRepository.findById(round.getResponsiblePersonId()).get();
			hr.setResponsiblePerson(employee);
			jobProfile.getJobHiringRounds().add(hr);
		});
		String message = null;
		if (jobProfile.getId() == null)
			message = "Job Profile Created"; 
		else
			message = "Job Profile Updated";

		jobProfile.setJobRole(jobRole);
		jobProfile.setIndustryType(industryType);
		jobProfile.setPostedDate(LocalDate.now());
		jobProfileRepository.save(jobProfile);
		
		jobProfileDto.setId(jobProfile.getId());
		response.put("data", jobProfileDto);
		response.put("message", message);
		return response;
	}

	@Override
	public Map<String, Object> removeJobProfile(long id) {
		Map<String, Object> response = new HashMap<>();
		jobProfileRepository.deleteById(id);
		response.put("message", "Job Profile Deleted Successfully");
		return response;
	}

	@Override
	public Map<String, Object> getSelectionList() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> industryTypes = jobProfileRepository
				.getSelectionList(2L);
		if (industryTypes.isEmpty()) {
			throw new ResourceNotFoundException("No Active Job Profile Found");
		}
		response.put("data", industryTypes);
		return response;
	}

	@Override
	public Map<String, Object> getJobProfilesList(Map<String, Object> params)
			throws JsonParseException, JsonMappingException, IOException {
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
//		int totalCount = Integer.parseInt(Long.valueOf(jobProfileRepository.count()).toString());
//		Page<JobProfileDto> zoneList = null;
//		
//		if ((search == null) || (search.isEmpty())) {
//			zoneList = this.jobProfileRepository
//					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		} else {
//			zoneList = this.jobProfileRepository.findCustomSearch(search,
//					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		}
//		// customQuery.findCustom("ac");
//		response.put("totalCount", this.jobProfileRepository.count());
//		response.put("data", zoneList);
//		return response;
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(JobProfile.class, params));
		return response;
	}
	
//	@Override
//	public String getSharableJwtToken(Long id) {
//		JobPosting jobPosting = this.jobPostingRepository.findById(id).get();
//		Date expiryDate = java.sql.Date.valueOf(jobPosting.getPostingEndDate());
//		// using common username
//		String token = jwtTokenUtil.generateTokenForJobPosting("ankit_1", "100" ,jobProfile.getId(), expiryDate);
////		return UriComponentsBuilder.fromUriString("http://locahost:8080/job-profiles/{title}")
////			.queryParam("token", token)
////			.build(jp.getDesignation()).toString();
////	
//		return token;
//	}

	@Override
	public JobProfile getJobProfileById(long id) {
		return this.jobProfileRepository.findById(id).get();
	}

}
