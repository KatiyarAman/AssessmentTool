package com.sterp.multitenant.tenant.assesmenttool.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.xray.model.Http;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobPostingDto;
import com.sterp.multitenant.tenant.assesmenttool.service.JobPostingService;

@RestController
@RequestMapping("/jobPosting")
public class JobPostingController {

	@Autowired
	private JobPostingService jobPostingService;
	
	@GetMapping("/share-token/{id}")
	public ResponseEntity<String> getSharableToken(@PathVariable Long id) {
		return ResponseEntity.ok(this.jobPostingService.getShareableToken(id));
	}
	
	@RequestMapping(
			path="/add",
			method = {RequestMethod.POST, RequestMethod.PUT},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestPart JobPostingDto object, HttpServletRequest request) {
		return ResponseEntity.ok(this.jobPostingService.addOrUpdate(object, request));
	}
	
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		return ResponseEntity.ok(this.jobPostingService.getById(id));
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getJobPostingList(HttpServletRequest request, Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
		params.put("module_id", Long.parseLong(request.getHeader("moduleId")));
		return ResponseEntity.ok(this.jobPostingService.getJobPostingList(params));
//		return ResponseEntity.ok(this.jobPostingService.getJobPostingList(params));
	}
	
	@GetMapping("/getJDInfo/{postingId}")
	public ResponseEntity<Map<String , Object>> getJDInfoByJobPostingId(@PathVariable Long postingId) {
		return ResponseEntity.ok(this.jobPostingService.getJDInfo(postingId));
	}
	
	@GetMapping("/getSelectionList")
	public ResponseEntity<Map<String, Object>> getSelectionList() {
		return ResponseEntity.ok(this.jobPostingService.getSelectionList());
	}
}
