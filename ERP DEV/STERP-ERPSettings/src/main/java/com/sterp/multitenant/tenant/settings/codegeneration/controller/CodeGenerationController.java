package com.sterp.multitenant.tenant.settings.codegeneration.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sterp.multitenant.tenant.settings.codegeneration.dto.CodeGenerationRequestDto;
import com.sterp.multitenant.tenant.settings.codegeneration.service.CodeGenerationService;

@RestController
@RequestMapping("/codegeneration")
public class CodeGenerationController {
	
	@Autowired
	CodeGenerationService codeGenerationService;
	
	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> addCodeGeneration(HttpServletRequest httpServletRequest,
			@RequestBody CodeGenerationRequestDto codeDto){
		//return ResponseEntity.ok(codeGenerationService.addCodeGeneration(codeDto));
		return new ResponseEntity<Map<String, Object>>(codeGenerationService.addCodeGeneration(codeDto, httpServletRequest), HttpStatus.OK);
	}
	
	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> updateCodeGeneration(HttpServletRequest httpServletRequest,
			@RequestBody CodeGenerationRequestDto codeDto){
		return ResponseEntity.ok(codeGenerationService.updateCodeGeneration(codeDto, httpServletRequest));
		
	}
	
	@GetMapping(value= "/getCode/{moduleId}",  produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getGeneratedCode(HttpServletRequest httpServletRequest
			, @PathVariable("moduleId") Long moduleId){
		//return ResponseEntity.ok(codeGenerationService.generateCode("BCH001/20-21/PO/A004", moduleId, httpServletRequest));
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
