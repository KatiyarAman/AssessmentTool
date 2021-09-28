package com.sterp.multitenant.tenant.settings.approval.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalSettings;
import com.sterp.multitenant.tenant.settings.approval.service.ApprovalSettingsService;

@RestController("/approvalconfig")
public class ApprovalConfigController {

	@Autowired
	ApprovalSettingsService approvalSettingsService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	CommonService commonService;

	@PostMapping(value = { "/add", "/update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addCustomField(HttpServletRequest httpServletRequest,
			@RequestBody ApprovalSettings object) {
		return ResponseEntity.ok(this.approvalSettingsService.saveApproval(object));
	}

	@GetMapping(value = "/getById/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCustomFieldById(HttpServletRequest httpServletRequest,
			@PathVariable(required = true, name = "Id") Integer Id) {
		return new ResponseEntity<Object>(this.approvalSettingsService.getApprovalSettingById(Id), HttpStatus.OK);
	}

	@GetMapping(value = { "/approvers/{moduleId}" }, produces = "application/json")
	public ResponseEntity<List<Object>> getApproversList(@PathVariable("moduleId") int submoduleId) {
		return new ResponseEntity<List<Object>>(this.approvalSettingsService.getApprovers(submoduleId), HttpStatus.OK);
	}

	@GetMapping(value = "/approverAssign/{userId}/{submoduleCode}/{docId}", produces = "application/json")
	public ResponseEntity<String> getNotification(HttpServletRequest request, @PathVariable("userId") long userId,
			@PathVariable("submoduleCode") String submoduleCode, @PathVariable("docId") long docId) {
		return new ResponseEntity<String>(this.approvalSettingsService
				.getApproverAssign(Long.parseLong(request.getHeader("moduleId")), docId, userId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getList(HttpServletRequest httpServletRequest, @RequestParam Map<String, Object> params){
		params.put("module_id", Long.parseLong(httpServletRequest.getHeader("moduleId")));
//		int page = 1;
//		int limit = 10;
//		String search = null;
//		String sort = null;
//		String order = null;
//		String filter = null;
//		boolean filterRemove = false;
//		List<ScreenFilterDTO> screenFilterDTO = Collections.emptyList();
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
//			} else if (((String) entry.getKey()).contains("screen_filter")) {
//				filter = (String) entry.getValue();
//				screenFilterDTO = objectMapper.readValue(filter, new TypeReference<List<ScreenFilterDTO>>() {
//				});
//			} else if (((String) entry.getKey()).contains("filter_remove")) {
//				filterRemove = Boolean.parseBoolean((String) entry.getValue());
//			}
//		}
//
//		int moduleId = 0;// Integer.parseInt(httpServletRequest.getHeader("screenId"));
//		return ResponseEntity.ok(this.approvalSettingsService.getApprovarSettingsList(page, limit, search, sort, order, screenFilterDTO,
//				filterRemove, moduleId));
		
		return ResponseEntity.ok(this.commonService.getCommonFilteredList(ApprovalSettings.class, params));
	}
}
