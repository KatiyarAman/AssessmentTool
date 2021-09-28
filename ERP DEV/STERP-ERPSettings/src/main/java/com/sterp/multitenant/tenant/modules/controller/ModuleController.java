package com.sterp.multitenant.tenant.modules.controller;

import java.io.IOException;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.modules.dto.ModuleDto;
import com.sterp.multitenant.tenant.modules.service.ModuleService;
import com.sterp.multitenant.utils.JwtTokenUtil;

@RestController
@RequestMapping("/module")
public class ModuleController {
	
	@Autowired
	ModuleService moduleService;

	@GetMapping(value = "/getAllActive", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getActiveParentModules(HttpServletRequest httpServletRequest) {
		return ResponseEntity
				.ok(this.moduleService.getActiveParentModuleListWithRecursiveChildModules(false, httpServletRequest));
	}

	@GetMapping(value = "/search/{title}")
	public ResponseEntity<?> searchModule(HttpServletRequest httpServletRequest, @PathVariable("title") String title) {
		return ResponseEntity.ok(this.moduleService.searchModuleByTitle(title, true));
	}

	@GetMapping(value = "/getSelectionList")
	public ResponseEntity<?> getLastActiveChildModules(HttpServletRequest httpServletRequest) {
		return ResponseEntity.ok(this.moduleService.getLastActiveChildModuleList());
	}

	@GetMapping(value = "/activemoduleaction")
	public ResponseEntity<?> getActiveModuleForRoleCreation() {
		return ResponseEntity.ok(this.moduleService.getModuleActionForRoleCreation());
	}
	
	@GetMapping(value = "/getById/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getCityById(@PathVariable Long id) {
		//return ResponseEntity.ok(this.moduleService.getModuleById(id, false));
		return ResponseEntity.ok(this.moduleService.getModuleDtoById(id));
	
	}
	
	
//	@GetMapping(path="/list", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Map<String, Object>> getModuleList(@RequestParam Map<String, Object> params) {
//		return ResponseEntity.ok(new HashMap<>());
//	}
	
	@RequestMapping(
			path = {"/add", "/update"},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE},
			method = {RequestMethod.POST, RequestMethod.PUT}
	)	
	public ResponseEntity<?> addOrUpdate(HttpServletRequest request, @RequestPart("object") ModuleDto object, @RequestPart(required = false) MultipartFile[] files) {
		return ResponseEntity.ok(this.moduleService.saveOrUpdate(request,object,files));
	}
	
	
	
	@GetMapping(path = "/list", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> getModuleList(HttpServletRequest request, @RequestParam Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
		params.put("module_id", Long.parseLong(request.getHeader("moduleId")));
		return ResponseEntity.ok(this.moduleService.getModuleList(params));
	}
	
	/*
	 * TEST Method
	 * */
	
	@GetMapping("/test")
	public ResponseEntity<String> testLink() {
		JwtTokenUtil util = new JwtTokenUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("moduleId", 123);
		map.put("rfqid", 232);
		String link = "http://www.google.com?name=sachin";
		return ResponseEntity.ok(util.getTokenLink(map, link));
	}
	
}
