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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.InterviewDto;
import com.sterp.multitenant.tenant.assesmenttool.service.InterviewService;

@RestController
@RequestMapping("/interview")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;

	@RequestMapping(path = { "/add", "/update" }, method = { RequestMethod.POST, RequestMethod.PUT }, consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestPart InterviewDto object) {
		return ResponseEntity.ok(this.interviewService.addOrUpdate(object));
	}
	
	@GetMapping("/getSelectionList")
	public ResponseEntity<Map<String, Object>> getSelectionList() {
		return ResponseEntity.ok(this.interviewService.getSelectionList());
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getInterviewList(HttpServletRequest request, @RequestParam Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {		
//		return ResponseEntity.ok(this.interviewService.getInterviewList(params));
		params.put("module_id", Long.parseLong(request.getHeader("moduleId")));
		return ResponseEntity.ok(this.interviewService.getInterviewList(params));
	}
	
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<InterviewDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(this.interviewService.getById(id));	
	}
}
