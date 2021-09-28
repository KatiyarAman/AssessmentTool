package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.rds.model.Timezone;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.assesmenttool.entity.ApplyMandatorySkills;
import com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentQuestionnaire;
import com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentTest;
import com.sterp.multitenant.tenant.assesmenttool.entity.Interview;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobPosting;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile;
import com.sterp.multitenant.tenant.assesmenttool.entity.Skill;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobDescriptionResponseDto;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobPostingDto;
import com.sterp.multitenant.tenant.assesmenttool.repository.AssessmentTestRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.InterviewRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.JobPostingRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.JobProfileRepository;
import com.sterp.multitenant.tenant.assesmenttool.repository.SkillRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.JobPostingService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.repository.StatusDetailsRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;
import com.sterp.multitenant.utils.JwtTokenUtil;

@Service
public class JobPostingServiceImpl implements JobPostingService {
	
	@Autowired
	private JobPostingRepository jobPostingRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JobProfileRepository jobProfileRepository;
	
	@Autowired
	private AppService appService;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AssessmentTestRepository assessmentTestRepository;
	
	@Autowired
	private InterviewRepository interviewRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private StatusDetailsRepository statusDetailsRepository;
	
	@Autowired
	CommonService commonService;
	
	@Override
	@Transactional(value = "tenantTransactionManager")
	public Map<String, Object> addOrUpdate(JobPostingDto jobPostingDto, HttpServletRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (jobPostingDto.getId() == null) {
			response.put("message", "Job Posting Created");
		}
		else {
			response.put("message", "Job Posting Updated");
		}
		
		JobPosting jobPosting = this.modelMapper.map(jobPostingDto, JobPosting.class);
//		jobPosting.setJobApplyMandatorySkills(new ArrayList<ApplyMandatorySkills>());
		
//		jobPostingDto.getApplyMandatorySkills().forEach(mandatorySkill -> {
//			ApplyMandatorySkills ams = this.modelMapper.map(mandatorySkill, ApplyMandatorySkills.class);
//			Skill skill = this.skillRepository.findById(mandatorySkill.getSkillId()).get();
//			ams.setSkill(skill);
//			jobPosting.getJobApplyMandatorySkills().add(ams);
//		});
//		StatusDetails statusDetails = this.statusDetailsRepository.findById(jobPostingDto.getStatus()).get();
//		jobPosting.setStatusDetail(statusDetails);
		Optional<JobProfile> jobProfile = this.jobProfileRepository.findById(jobPostingDto.getJobProfileId());
		
		if (jobProfile.isPresent())
			jobPosting.setJobProfile(jobProfile.get());
		this.jobPostingRepository.save(jobPosting);
		
		if (jobPostingDto.getAssessmentId() != null) {
			AssessmentTest at = this.assessmentTestRepository.findById(jobPostingDto.getAssessmentId()).get();
			jobPosting.setAssessmentTest(at);
		}
		
		if (jobPostingDto.getInterviewId() != null) {
			Interview interview = this.interviewRepository.findById(jobPostingDto.getInterviewId()).get();
			jobPosting.setInterview(interview);
		}
		
		//this.appService.getCurrentUser(;
		Date expiryDate = java.sql.Date.valueOf(jobPosting.getPostingEndDate());
		UserEntity user = this.appService.getCurrentUser();
		String tenantId = (String) request.getAttribute("audience");
		String token = this.jwtTokenUtil.generateTokenForJobPosting(user.getUsername(), tenantId ,jobPosting.getId() , expiryDate);
		jobPosting.setShareableToken(token);
		this.jobPostingRepository.save(jobPosting); // save after generating token
		return response;
	}

	@Override
	public Map<String, Object> getJobPostingList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
//		Map<String, Object> response = new HashMap<String, Object>();
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
//		
//		int totalCount = Integer.parseInt(Long.valueOf(this.jobPostingRepository.count()).toString());
//		Page<JobPostingDto> jobPostingList = null;
//		if ((search == null) || (search.isEmpty())) {
//			jobPostingList = this.jobPostingRepository
//					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		} else {
//			jobPostingList = this.jobPostingRepository.findCustomSearch(search,
//					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		}
//		// customQuery.findCustom("ac");
//		response.put("totalCount", this.jobPostingRepository.count());
//		response.put("data", jobPostingList);
//		return response;
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(JobPosting.class, params));
		return response;
	}

	@Override
	@Transactional(value = "tenantTransactionManager", readOnly = true)
	public JobPostingDto getById(Long id) {
		JobPosting jobPosting = this.jobPostingRepository.findById(id).get();
		JobPostingDto jobPostingDto = this.modelMapper.map(jobPosting,JobPostingDto.class);
		jobPostingDto.setJobProfileId(jobPosting.getJobProfile().getId());
		if (jobPosting.getAssessmentTest() != null) {
			jobPostingDto.setAssessmentId(jobPosting.getAssessmentTest().getId());
		}
		if (jobPosting.getInterview() != null) {
			jobPostingDto.setInterviewId(jobPosting.getInterview().getId());
		}	
		return jobPostingDto;
	}

	
	@Override
	@Transactional(value = "tenantTransactionManager")
	public String getShareableToken(Long id) {
		JobPosting jobPosting = this.jobPostingRepository.findById(id).get();
		return jobPosting.getShareableToken();
	}

	@Override
	@Transactional(value = "tenantTransactionManager", readOnly = true)
	public Map<String, Object> getJDInfo(Long postingId) {
		Map<String, Object> response = new HashMap<String, Object>();
		JobPosting jobPosting = this.jobPostingRepository.findById(postingId).get();
		JobProfile jobProfile = jobPosting.getJobProfile();
		List<Skill> skills = jobProfile.getJobRole().getSkillSet();
		Collection<ApplyMandatorySkills> mandatorySkills = jobPosting.getApplyMandatorySkills();
		JobDescriptionResponseDto responseDto = this.modelMapper.map(jobProfile,JobDescriptionResponseDto.class);
		responseDto.setPostedDate(jobPosting.getCreatedDate().toInstant().atZone(ZoneId.of(TimeZone.getTimeZone("Asia/Kolkata").getID())).toLocalDate());
		responseDto.setRequiredSkills(skills);
		responseDto.setApplyMandatorySkills(mandatorySkills);
		responseDto.setJobPostingDto(this.modelMapper.map(jobPosting, JobPostingDto.class));
		response.put("jobDescription", responseDto);
		return response;
	}
	
	@Override
	public JobPosting getJobPostingById(Long jobPostingId) {
		return this.jobPostingRepository.findById(jobPostingId).get();
	}
	
	@Override
	public Map<String, Object> getSelectionList() {
		 Map<String, Object> response = new HashMap<String, Object>();
		 response.put("data", this.jobPostingRepository.getSelectionList(2L));
		 return response;
	}
}
