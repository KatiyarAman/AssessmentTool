package com.sterp.multitenant.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sterp.multitenant.mastertenant.service.ClientageService;
import com.sterp.multitenant.tenant.service.SecurityAppService;

@RestController
public class TenantInfoController {

	@Autowired
	ClientageService clientageService;

	@Autowired
	SecurityAppService securityAppService;

	@GetMapping(value = "/getTenantInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTenantInfo(HttpServletRequest httpServletRequest) {
		return ResponseEntity
				.ok(this.clientageService.getClientageByTenantId(securityAppService.getTenantId(httpServletRequest)));
	}
}
