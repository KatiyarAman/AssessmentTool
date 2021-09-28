package com.sterp.multitenant.tenant.settings.template.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.settings.template.entity.CustomField;
import com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldByModuleAndFieldTypeRequestDTO;
import com.sterp.multitenant.tenant.settings.template.entity.dto.CustomFieldRequestDto;
import com.sterp.multitenant.tenant.settings.template.service.CustomFieldService;

@RestController
@RequestMapping("/customfield")
public class CustomFieldController {

	@Autowired
	CustomFieldService customFieldService;

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	CommonService commonService;

	@PostMapping(value = { "/add", "/update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addCustomField(HttpServletRequest httpServletRequest,
			@RequestPart CustomFieldRequestDto object, @RequestPart(required = false) MultipartFile[] docs) {
		return ResponseEntity.ok(this.customFieldService.saveOrUpdate(object));
	}

	@GetMapping(value = "/getById/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCustomFieldById(HttpServletRequest httpServletRequest,
			@PathVariable(required = true, name = "Id") Integer Id) {
		return ResponseEntity.ok(this.customFieldService.getCustomFieldById(Id));
	}

	@GetMapping(value = { "/get/{moduleId}" })
	public ResponseEntity<?> getCustomField(HttpServletRequest httpServletRequest,
			@PathVariable("moduleId") long moduleId) {
		List<Boolean> shortLinkEnabled = new ArrayList<Boolean>(2);
		shortLinkEnabled.add(true);
		shortLinkEnabled.add(false);
		return ResponseEntity.ok(this.customFieldService.getActiveCustomFieldByModuleIdOrderByDisplayOrder(moduleId,
				false, shortLinkEnabled, "general", httpServletRequest));
	}

	@GetMapping(value = { "/getAll" })
	public ResponseEntity<?> getAll() {
		List<Boolean> shortLinkEnabled = new ArrayList<Boolean>(2);
		shortLinkEnabled.add(true);
		shortLinkEnabled.add(false);
		return ResponseEntity.ok(this.customFieldService.getAll());
	}

	@GetMapping(value = { "/getShortLinkForm/{moduleId}" })
	public ResponseEntity<?> getShortLinkForm(HttpServletRequest httpServletRequest,
			@PathVariable("moduleId") long moduleId) {
		List<Boolean> shortLinkEnabled = new ArrayList<Boolean>(1);
		shortLinkEnabled.add(true);
		return ResponseEntity.ok(this.customFieldService.getActiveCustomFieldByModuleIdOrderByDisplayOrder(moduleId,
				false, shortLinkEnabled, "shortLink", httpServletRequest));
	}

//@RequestBody CustomFieldByModuleAndFieldTypeRequestDTO requestDTO
	@GetMapping(value = "/getSelectionList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getActiveFieldByModuleIdAndFieldType(HttpServletRequest httpServletRequest,
			@RequestParam(required = false) Long moduleId, @RequestParam(required = false) List<Integer> fieldTypeIds) {
		CustomFieldByModuleAndFieldTypeRequestDTO requestDTO = new CustomFieldByModuleAndFieldTypeRequestDTO(moduleId,
				fieldTypeIds);
		return ResponseEntity.ok(this.customFieldService.getActiveFieldByModuleIdAndFieldType(requestDTO));
	}

//	@GetMapping(value = "/getSelectionList", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> getLastActiveChildModules(HttpServletRequest httpServletRequest,
//			@RequestBody CustomFieldByModuleAndFieldTypeRequestDTO requestDTO) {
//		return ResponseEntity.ok(this.customFieldService.getActiveFieldByModuleIdAndFieldType(requestDTO));
//	}

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
//			} else if (((String) entry.getKey()).contains("_entryType")) {
//				entryType = (String) entry.getValue();
//			}
//		}
//		if (entryType == null || entryType.isEmpty()) {
//			throw new CustomException("Please define entry type to fetch Custom Field");
//		}
//		int moduleId = 0;
//		return ResponseEntity.ok(this.customFieldService.getCustomFieldList(page, limit, search, sort, order,
//				screenFilter, filterRemove, moduleId, entryType));
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data",this.commonService.getCommonFilteredList(CustomField.class, params));
		return ResponseEntity.ok(response);
	}
}
