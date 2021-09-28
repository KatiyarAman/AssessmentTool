package com.sterp.multitenant.tenant.core.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.core.services.CMSUploadAsExcelService;

@RestController
@RequestMapping("/restrictedZoneToDeveloper")
public class DeveloperZone {
	
	@Autowired
	CMSUploadAsExcelService cMSUploadAsExcelService;

	@PostMapping(value = "/uploadCMSAsExcel", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> uploadCMSAsExcel(@RequestPart MultipartFile cmsFile) throws IOException {
		return ResponseEntity.ok(this.cMSUploadAsExcelService.uploadCMSAsExcel(cmsFile));
	}
}
