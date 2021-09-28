package com.sterp.multitenant.tenant.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.dto.ChangePasswordRequestDTO;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.dto.BranchUserRoleResponseDTO;
import com.sterp.multitenant.tenant.dto.UserDTO;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	CommonService commonService;
		

	@GetMapping
	public String getUser() {
		return "get user was called";
	}

	@RequestMapping(value = { "/add", "/update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> createUser(HttpServletRequest httpServletRequest, @RequestPart UserDTO object) {
		return ResponseEntity.ok(this.userService.createUpdateUser(object));
	}

//	@PutMapping(value = "/{userName}/{oldPassword}/{newPassword}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> updateUser(@PathVariable("userName") String userName,
//			@PathVariable("oldPassword") String oldPassword, @PathVariable("newPassword") String newPassword) {
//		return ResponseEntity.ok(this.userService.updateUser(userName, newPassword, oldPassword));
//	}

	@PutMapping(value = "/changepassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(HttpServletRequest httpServletRequest,
			@RequestBody ChangePasswordRequestDTO object) {
		return ResponseEntity.ok(this.userService.updateUser(object));
	}

	@GetMapping(value = "/getbur/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getbranchUserRoleForPermissionUpdate(HttpServletRequest httpServletRequest,
			@PathVariable(name = "Id", required = true) long userId) {
		return ResponseEntity.ok(this.userService.getbranchUserRoleForPermissionUpdate(userId));
	}

	@PostMapping(value = "/updatebur")
	public ResponseEntity<?> updatebranchwiseuserrolepermission(HttpServletRequest httpServletRequest,
			@RequestBody List<BranchUserRoleResponseDTO> object) {
		return ResponseEntity.ok(this.userService.updateBranchUserRole(object));
	}

	@GetMapping(value = "/getById/{Id}")
	public ResponseEntity<?> getUserByUserId(HttpServletRequest httpServletRequest,
			@PathVariable(name = "Id", required = true) long userId) {
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

//	@PutMapping(value = "/defaultpassword/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> updateUserIsFirstLogin(@PathVariable("userName") String userName) {
//		return ResponseEntity.ok(this.userService.updateUserIsFirstLogin(userName));
//	}
	
	@GetMapping(value = "/getSelectionList", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getSelectionList() {
		return ResponseEntity.ok(this.userService.getSelectionList(false, 1L));
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
//		return ResponseEntity.ok(this.userService.getUserList(page, limit, search, sort, order, screenFilter,
//				filterRemove, moduleId, entryType));
		Map<String,Object> response=new HashedMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(UserEntity.class, params));
		return ResponseEntity.ok(response);
	}
}