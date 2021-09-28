package com.sterp.multitenant.tenant.assesmenttool.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobProfileDto;
import com.sterp.multitenant.tenant.assesmenttool.service.JobProfileService;

@RestController
@RequestMapping("/job-profiles")
public class JobProfileController {

	@Autowired
	private JobProfileService jobProfileService;

	
//	@GetMapping("/{id}/getShareableToken")
//	public ResponseEntity<String> getToken(@PathVariable("id") Long id) {
//		return ResponseEntity.ok(this.jobProfileService.getSharableJwtToken(id));
//	}

	@RequestMapping(path = { "/add", "/update" }, method = { RequestMethod.PUT, RequestMethod.POST }, consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Object>> addOrUpdate(HttpServletRequest req, @RequestPart JobProfileDto object) {
		return ResponseEntity.ok(jobProfileService.saveOrUpdateJobProfile(object));
	}

	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getList(HttpServletRequest request, @RequestParam Map<String, Object> params)
			throws JsonParseException, JsonMappingException, IOException {
		params.put("module_id", Long.parseLong(request.getHeader("moduleId")));
		return ResponseEntity.ok(jobProfileService.getJobProfilesList(params));
	}

	@GetMapping
	public ResponseEntity<Page<JobProfile>> getAllJobProfiles(Pageable pageable) {
		return new ResponseEntity<>(jobProfileService.getAllJobProfiles(pageable), HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getJobProfileById(@PathVariable("id") long id) {
		return new ResponseEntity<>(jobProfileService.getJobProfileDtoById(id), HttpStatus.OK);
	}

	@GetMapping("/getSelectionList")
	public ResponseEntity<Map<String, Object>> getSelectionList() {
		return ResponseEntity.ok(jobProfileService.getSelectionList());
	}

	/**
	 * Retrieve questionnaires by page for a specific job profile.
	 * 
	 * @param id       - job profile id
	 * @param pageable - for spring boot pagination
	 * @return page of questionnaires
	 */
//	@GetMapping("/{id}/questionnaires")
//	public ResponseEntity<Page<AssesmentToolQuestionnaire>> getQuestionnaireByJobProfile(@PathVariable("id") int id, Pageable pageable) {
//		return new ResponseEntity<Page<AssesmentToolQuestionnaire>>(questoinnaireService.getQuestionnaireByJobProfile(id, pageable), HttpStatus.OK);
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> removeJobProfile(@PathVariable("id") int id) {
		return new ResponseEntity<Map<String, Object>>(jobProfileService.removeJobProfile(id), HttpStatus.OK);
	}

}
