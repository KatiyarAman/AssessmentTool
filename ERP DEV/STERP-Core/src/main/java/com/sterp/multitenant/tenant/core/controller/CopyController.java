package com.sterp.multitenant.tenant.core.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public class CopyController {
	@RequestMapping(value = { "/add", "update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> addOrUpdate(HttpServletRequest httpServletRequest) {
		return ResponseEntity.ok(null);
	}

	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllActive(HttpServletRequest httpServletRequest) {
		return ResponseEntity.ok(null);
	}

	@GetMapping(value = "/get/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById(@PathVariable(value = "Id", required = true) Integer Id,
			HttpServletRequest httpServletRequest) {
		return ResponseEntity.ok(null);
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getList(@RequestParam Map<String, Object> params, HttpServletRequest httpServletRequest) {
//		int page = 1;
//		int limit = 10;
//		String search = null;
//		String filter = null;
//		String sort = null;
//		String order = null;
//		for (Map.Entry<String, Object> entry : params.entrySet()) {
//			if (((String) entry.getKey()).contains("_page")) {
//				page = Integer.parseInt(entry.getValue().toString());
//			} else if (((String) entry.getKey()).contains("_limit")) {
//				limit = Integer.parseInt(entry.getValue().toString());
//			} else if (((String) entry.getKey()).contains("_order")) {
//				order = (String) entry.getValue();
//			} else if (((String) entry.getKey()).contains("_sort")) {
//				sort = (String) entry.getValue();
//			} else if (((String) entry.getKey()).contains("_search")) {
//				search = (String) entry.getValue();
//			} else if (((String) entry.getKey()).contains("_like")) {
//				search = (String) entry.getValue();
//			}
//		}
		return ResponseEntity.ok(null);
	}
}
