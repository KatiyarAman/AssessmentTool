package com.sterp.multitenant.tenant.assesmenttool.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentQuestionnaireDto;
import com.sterp.multitenant.tenant.assesmenttool.service.AssessmentQuestionnaireService;

@RestController
@RequestMapping("/assessment-questionnaires")
public class AssessmentQuestionnaireController {

	@Autowired
	private AssessmentQuestionnaireService questionnaireService;

	@RequestMapping(path={"/add","/update"},
			method = {RequestMethod.PUT, RequestMethod.POST},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> saveOrUpdate(@RequestPart AssessmentQuestionnaireDto object, @RequestPart (required=false, value = "files") MultipartFile[] files) {
		return ResponseEntity.ok(this.questionnaireService.saveOrUpdate(object));
	}
	@RequestMapping(path={"/uploadVid"},
			method = {RequestMethod.PUT, RequestMethod.POST},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> uploads(@RequestPart (required=false, value = "files") MultipartFile[] files) throws IllegalStateException, IOException {
		files[0].transferTo(new File("D:\\Projects\\Uploads\\" + files[0].getOriginalFilename()+".mp4"));
		Map<String, String> response = new HashedMap<String, String>(); 
		response.put("url","http://127.0.0.1:8887/" + files[0].getOriginalFilename()+".mp4");
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<AssessmentQuestionnaireDto> getAssessmentQuestionnaireById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.questionnaireService.getQuestionnaireById(id));
	}

	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getAssessmentQuestionnaireList(HttpServletRequest request, @RequestParam Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
		params.put("module_id", Long.parseLong(request.getHeader("moduleId")));
		return ResponseEntity.ok(this.questionnaireService.getQuestionnaireList(params));
	}

	
	@GetMapping("/getSelectionList")
	public ResponseEntity<Map<String, Object>> getAssessmentQuestionnaireSelectionList() {
		return ResponseEntity.ok(this.questionnaireService.getSelectionList());
	}
	
	
	@GetMapping("/getQuestionniareListById")
	public ResponseEntity<Map<String, Object>> getQuestionnaireListById(@RequestParam List<Long> ids) {
		return ResponseEntity.ok(this.questionnaireService.getQuestionnaireListByIds(ids));
	}
}
