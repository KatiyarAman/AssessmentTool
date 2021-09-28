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
import com.sterp.multitenant.tenant.core.services.StatutoryDetailsService;
import com.sterp.multitenant.tenant.dto.StatutoryDetailsRequestDTO;

@RestController
@RequestMapping("/statutoryDetails")
public class StatutoryDetailsControllor {
	
	@Autowired
	StatutoryDetailsService statutoryDetailsService;
	
	@RequestMapping(value = { "/add", "/update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> addOrUpdate(HttpServletRequest request,
			@RequestBody StatutoryDetailsRequestDTO statutoryDetailsDTO) {
		return ResponseEntity.ok(this.statutoryDetailsService.addOrUpdateStatutoryDetails(statutoryDetailsDTO));

	}

	@GetMapping(value = "/get/{statutoryDetailsId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getById(@PathVariable("statutoryDetailsId") Long statutoryDetailsId) {
		return ResponseEntity.ok(this.statutoryDetailsService.getAreaById(statutoryDetailsId, false));
	}
	
	
	@GetMapping(value = "/getSelectionList", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getSelectionList() {
		return ResponseEntity.ok(this.statutoryDetailsService.getSelectionList(false));
	}

}
