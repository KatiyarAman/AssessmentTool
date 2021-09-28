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
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentTestDto;
import com.sterp.multitenant.tenant.assesmenttool.service.AssessmentQuestionnaireService;
import com.sterp.multitenant.tenant.assesmenttool.service.AssessmentTestService;

@RestController
@RequestMapping("/assessment-tests")
public class AssessmentTestController {

	@Autowired
	private AssessmentTestService assessmentTestService;
	
	@Autowired
	private AssessmentQuestionnaireService assessmentQuestionnaireService;
	
	@RequestMapping(
			path = {"/add", "/update"},
			method = {RequestMethod.POST, RequestMethod.PUT},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<Map<String, Object>> saveOrUpdate(@RequestPart AssessmentTestDto object) {
		return ResponseEntity.ok(this.assessmentTestService.saveOrUpdate(object));
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<AssessmentTestDto> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.assessmentTestService.getById(id));
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getAssessmentTestList(HttpServletRequest request, @RequestParam Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
		params.put("module_id", Long.parseLong(request.getHeader("moduleId")));
		return ResponseEntity.ok(this.assessmentTestService.getAssessmentTestList(params));
	}
	
	@GetMapping("/getSkillDetails/{jobProfileId}")
	public ResponseEntity<Map<String, Object>> getSkillDetailsByJobProfile(@PathVariable() Long jobProfileId) {
		return ResponseEntity.ok(this.assessmentTestService.getSkillDetailsByJobProfileId(jobProfileId));
	}
	
	@GetMapping("/getQuestionnairesBySkillId/{skillId}")
	public ResponseEntity<Map<String, Object>> getQuestionnaireBySkillsId(@PathVariable("skillId") Long skillId) {
		return ResponseEntity.ok(this.assessmentTestService.getQuestionnairesBySkillId(skillId));
	}
	
	@GetMapping("/getQuestionnaireDetailsById/{id}")
	public ResponseEntity<Map<String, Object>> getQuestionniareDetialsById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.assessmentQuestionnaireService.getQuestionnaireDetailsById(id));
	}
	
	@GetMapping("/getSelectionList")
	public ResponseEntity<Map<String, Object>> getSelectionList() {
		return ResponseEntity.ok(this.assessmentTestService.getSelectionList());
	}
}
