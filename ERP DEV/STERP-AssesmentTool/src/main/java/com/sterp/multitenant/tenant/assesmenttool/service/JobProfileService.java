package com.sterp.multitenant.tenant.assesmenttool.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobProfileDto;

@Service
public interface JobProfileService {
	Page<JobProfile> getAllJobProfiles(Pageable pageable);
	JobProfileDto getJobProfileDtoById(long id);
	JobProfile getJobProfileById(long id);
	public Map<String, Object> getSelectionList();
	Map<String, Object> saveOrUpdateJobProfile(JobProfileDto jobProfile);
	Map<String, Object> removeJobProfile(long id);
	Map<String, Object> getJobProfilesList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException;
	//String getSharableJwtToken(Long id);
}
