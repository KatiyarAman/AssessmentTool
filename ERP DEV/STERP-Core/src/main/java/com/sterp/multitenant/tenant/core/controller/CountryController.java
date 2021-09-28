package com.sterp.multitenant.tenant.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sterp.multitenant.mastertenant.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping(value = "/getSelectionList")
	public ResponseEntity<?> getCountries() {
		return ResponseEntity.ok(this.countryService.getActiveCountryListInCommonDropdown(true));
	}
}
