package com.sterp.multitenant.tenant.settings.approval.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalGroup;
import com.sterp.multitenant.tenant.settings.approval.service.ApprovalGroupService;

@RestController
@RequestMapping("/approvalGroup")
public class ApprovalGroupController {
	
	@Autowired
	ApprovalGroupService approvalGroupService;
	
	@Autowired
	CommonService commonService;
	

	@Autowired
	ObjectMapper objectMapper;

	@RequestMapping(value = { "/add", "/update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Map<String, Object>> addOrUpdate(HttpServletRequest request,
			@RequestPart ApprovalGroup object) {
		return new ResponseEntity<Map<String, Object>>(this.approvalGroupService.addOrUpdateApprovalGroup(object), HttpStatus.OK);

	}

	@GetMapping(value = "/getById/{approvalGroupId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getById(@PathVariable("approvalGroupId") Long approvalGroupId) {
		return ResponseEntity.ok(this.approvalGroupService.getApprovalGroupById(approvalGroupId, false));
	}

	/*
	 * Get Active Approval Group List
	 * 
	 */
	@GetMapping(value = "/getSelectionList", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getSelectionList() {
		return ResponseEntity.ok(this.approvalGroupService.getSelectionList(false, 1L));
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
//		return ResponseEntity.ok(this.approvalGroupService.getApprovarGroupList(page, limit, search, sort, order, screenFilterDTO,
//				filterRemove, moduleId));
		
		return ResponseEntity.ok(this.commonService.getCommonFilteredList(ApprovalGroup.class, params));
	}
}
