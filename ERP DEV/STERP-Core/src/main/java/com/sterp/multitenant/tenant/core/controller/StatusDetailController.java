package com.sterp.multitenant.tenant.core.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sterp.multitenant.tenant.core.services.StatusDetailsService;

@RestController
@RequestMapping("/statusDetails")
public class StatusDetailController {

	@Autowired
	StatusDetailsService statusDetailsService;

	@GetMapping(value = "/get", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getStatus() {
		return ResponseEntity.ok(this.statusDetailsService.getStatusDetails());
	}

	@GetMapping("/getSelectionList")
	public ResponseEntity<Map<String, Object>> getSelectionList() {
		return ResponseEntity.ok(this.statusDetailsService.getSelectionList());
	}

}
