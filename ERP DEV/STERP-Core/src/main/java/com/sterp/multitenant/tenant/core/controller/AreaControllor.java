package com.sterp.multitenant.tenant.core.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sterp.multitenant.tenant.core.services.AreaService;
import com.sterp.multitenant.tenant.dto.AreaRequestDTO;


@RestController
@RequestMapping("/area")
public class AreaControllor {
	
	@Autowired
	AreaService areaService;
	
	@RequestMapping(value = { "/add", "/update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> addOrUpdate(HttpServletRequest request,
			@RequestBody AreaRequestDTO areaDTO) {
		return ResponseEntity.ok(this.areaService.addOrUpdateArea(areaDTO));

	}

	@GetMapping(value = "/get/{areaId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getById(@PathVariable("areaId") Long areaId) {
		return ResponseEntity.ok(this.areaService.getAreaById(areaId, false));
	}
	
	
	@GetMapping(value = "/getSelectionList", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getSelectionList() {
		return ResponseEntity.ok(this.areaService.getSelectionList(false));
	}
	
}
