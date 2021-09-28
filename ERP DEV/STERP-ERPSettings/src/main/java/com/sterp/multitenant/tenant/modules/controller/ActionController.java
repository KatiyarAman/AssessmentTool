package com.sterp.multitenant.tenant.modules.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sterp.multitenant.tenant.modules.service.ActionService;

@RestController
@RequestMapping("/actions")
public class ActionController {

	@Autowired
	private ActionService actionService;
	
	@GetMapping("/getSelectionList")
	public ResponseEntity<Map<String, Object>> getSelectionList() {
		return ResponseEntity.ok(actionService.getSelectionList());
	}
}
