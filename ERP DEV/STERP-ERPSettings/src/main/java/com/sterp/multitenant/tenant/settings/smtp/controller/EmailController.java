package com.sterp.multitenant.tenant.settings.smtp.controller;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.settings.smtp.dto.MailBody;
import com.sterp.multitenant.tenant.settings.smtp.dto.SMTPSettingsDto;
import com.sterp.multitenant.tenant.settings.smtp.service.EmailService;

@RestController
@RequestMapping("/mail")
public class EmailController {

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	EmailService emailService;

	@PostMapping(value = "/send", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> sendMail(@RequestPart MailBody object, @RequestPart MultipartFile[] attachments)
			throws MessagingException, IOException {
		this.emailService.composeMail(object, attachments);
		return null;
	}

	@PostMapping(value = { "/add" }, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> configEmail(@RequestPart SMTPSettingsDto object,
			@RequestPart(required = false) MultipartFile[] multipartfile) {
		return ResponseEntity.ok(this.emailService.sendConfigurationTestMail(object));
	}

	@GetMapping(value = "/list", produces = "application/json")
	public ResponseEntity<?> getClientLists(@RequestParam Map<String, Object> params,
			HttpServletRequest httpServletRequest) throws JsonParseException, JsonMappingException, IOException {
		int page = 1;
		int limit = 10;
		String search = null;
		String sort = null;
		String order = null;
		boolean filterRemove = false;
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (((String) entry.getKey()).contains("_page")) {
				page = Integer.parseInt(entry.getValue().toString());
			} else if (((String) entry.getKey()).contains("_limit")) {
				limit = Integer.parseInt(entry.getValue().toString());
			} else if (((String) entry.getKey()).contains("_order")) {
				order = (String) entry.getValue();
			} else if (((String) entry.getKey()).contains("_sort")) {
				sort = (String) entry.getValue();
			} else if (((String) entry.getKey()).contains("_search")) {
				search = (String) entry.getValue();
			} else if (((String) entry.getKey()).contains("_like")) {
				search = (String) entry.getValue();
			}
		}
		int screenId = Integer.parseInt(httpServletRequest.getHeader("screenId"));
		Map<String, Object> response = this.emailService.getSMTPConfigList(page, limit, search, sort, order,
				filterRemove, screenId);
		return ResponseEntity.ok(response);
	}
}
