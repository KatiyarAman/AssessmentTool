package com.sterp.multitenant.tenant.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.admin.service.RoleService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.modules.entity.Roles;

@RestController
@RequestMapping("/role")
public class RoleSetupController {

	@Autowired
	RoleService roleService;

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	CommonService commonService;

	@RequestMapping(value = { "/add", "/update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> addUpdateRole(HttpServletRequest request ,@RequestPart Roles object, @RequestPart(required = false) MultipartFile[] files) {
		return ResponseEntity.ok(this.roleService.addOrUpdateRole(request, object,files));
	}

	@GetMapping(value = "/getById/{Id}")
	public ResponseEntity<?> getRoleById(@PathVariable(name = "Id", required = true) long Id) {
		return ResponseEntity.ok(this.roleService.getRoleById(Id));
	}

	@GetMapping(value = "/getSelectionList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getActiveFieldByModuleIdAndFieldType(HttpServletRequest httpServletRequest) {
		return ResponseEntity.ok(this.roleService.getActiveRolesDropdown());
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getList(@RequestParam Map<String, Object> params, HttpServletRequest httpServletRequest){
		params.put("module_id", Long.parseLong(httpServletRequest.getHeader("moduleId")));
//		int page = 1;
//		int limit = 10;
//		String search = null;
//		String sort = null;
//		String order = null;
//		String filter = null;
//		boolean filterRemove = false;
//		String entryType = null;
//		List<ScreenFilter> screenFilter = Collections.emptyList();
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
//				screenFilter = objectMapper.readValue(filter, new TypeReference<List<ScreenFilter>>() {
//				});
//			} else if (((String) entry.getKey()).contains("filter_remove")) {
//				filterRemove = Boolean.parseBoolean((String) entry.getValue());
//			}
//		}
//		int moduleId = 0;// Integer.parseInt(httpServletRequest.getHeader("screenId"));
//		return ResponseEntity.ok(this.roleService.getRoleList(page, limit, search, sort, order, screenFilter,
//				filterRemove, moduleId, entryType));
		Map<String, Object> response = new HashedMap<String, Object>();
		response.put("data",this.commonService.getCommonFilteredList(Roles.class, params));
		return ResponseEntity.ok(response);
	}
}
