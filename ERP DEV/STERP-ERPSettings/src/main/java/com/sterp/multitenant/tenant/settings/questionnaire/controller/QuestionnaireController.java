package com.sterp.multitenant.tenant.settings.questionnaire.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.sterp.multitenant.tenant.dto.CommonRequestFilterDTO;
import com.sterp.multitenant.tenant.settings.questionnaire.dto.QuestionnaireRequestDto;
import com.sterp.multitenant.tenant.settings.questionnaire.service.QuestionnaireService;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
	
	@Autowired
	QuestionnaireService questionnaireService;
	
	@PostMapping(value = {"/add", "/update"}, consumes = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> addQuestionnaire(HttpServletRequest request, 
			@RequestBody QuestionnaireRequestDto object){
		return ResponseEntity.ok(this.questionnaireService.saveOrUpdate(object, request));
	}
	
	@GetMapping(value="/getById/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getQuestionnaireById(HttpServletRequest httpServletRequest,
			@PathVariable(required = true , name = "Id") Long Id){
		return ResponseEntity.ok(this.questionnaireService.getQuestionnaireById(Id));
	}
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(this.questionnaireService.getAll());
	}
	
	@PostMapping(value = "/getSelectionList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSelectionList(HttpServletRequest httpServletRequest,
			@RequestPart List<CommonRequestFilterDTO> filterList){
		return ResponseEntity.ok(this.questionnaireService
				.getSelectionList("QuestionnaireSettings", "id", "value", filterList));
	}

}
