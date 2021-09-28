package com.sterp.multitenant.tenant.assesmenttool.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobPosting;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobPostingDto;

public interface JobPostingService {

	Map<String, Object> addOrUpdate(JobPostingDto object, HttpServletRequest request);

	Map<String, Object> getJobPostingList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException;

	JobPostingDto getById(Long id);

	String getShareableToken(Long token);

	Map<String, Object> getJDInfo(Long postingId);

	JobPosting getJobPostingById(Long jobPostingId);

	Map<String, Object> getSelectionList();
	
}
