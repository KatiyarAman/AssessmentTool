package com.sterp.multitenant.tenant.core.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.sterp.multitenant.tenant.service.impl.SecurityAppServiceImpl;
import com.sterp.multitenant.tenant.utils.SerializationAutoSaveUtil;

@RestController
public class SerializationController {

	@Autowired
	SerializationAutoSaveUtil serializationAutoSaveUtil;

	@Autowired
	SecurityAppServiceImpl securityAppServiceImpl;

	@RequestMapping(value = { "/autosave" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST, RequestMethod.PUT })
	public void autoSave(HttpServletRequest httpServletRequest, @RequestPart Object object) {
		this.serializationAutoSaveUtil.autosaveASynchronous(object,
				Integer.toString(this.securityAppServiceImpl.getTenantId(httpServletRequest)),
				Long.parseLong(httpServletRequest.getHeader("moduleId")));
	}

	@GetMapping("/getserialize")
	public ResponseEntity<?> getSerializedObject(HttpServletRequest httpServletRequest) throws IOException {
		return ResponseEntity.ok(this.serializationAutoSaveUtil.getSerialize(
				Integer.toString(this.securityAppServiceImpl.getTenantId(httpServletRequest)),
				Long.parseLong(httpServletRequest.getHeader("moduleId"))));
	}
}
