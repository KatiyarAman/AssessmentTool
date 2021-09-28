package com.sterp.multitenant.tenant.assesmenttool.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.IndustryTypeDto;
import com.sterp.multitenant.tenant.assesmenttool.service.IndustryTypeService;


@RestController
@RequestMapping("/industry-types")
public class IndustryTypeController {

	@Autowired
	private IndustryTypeService industryTypeService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@RequestMapping(
			path = {"/add", "/update"},
			method= {RequestMethod.PUT, RequestMethod.POST},
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<Map<String, Object>> addOrUpdate(HttpServletRequest req, @RequestPart IndustryTypeDto object) {
		return ResponseEntity.ok(industryTypeService.saveOrUpdateIndustryType(object));
	}
	
	
//	@GetMapping(path="/list", produces = {MediaType.APPLICATION_JSON_VALUE})
//	public ResponseEntity<?> getList(HttpServletRequest request, @RequestParam Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
//		return ResponseEntity.ok(industryTypeService.getIndustryTypeList(params));
//	}

	
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getList(HttpServletRequest httpServletRequest,
			@RequestParam(required = false) Map<String, Object> params)
			throws JsonParseException, JsonMappingException, IOException, NoSuchFieldException, SecurityException {
		//long moduleId = Long.parseLong(httpServletRequest.getHeader("moduleId"));
		params.put("module_id", Long.parseLong(httpServletRequest.getHeader("moduleId")));
//		int page = 1;
//		int limit = 10;
//		String search = null;
//		String sort = null;
//		String order = null;
//		String filter = null;
//		boolean filterRemove = false;
//		List<ScreenFilterDTO> screenFilterDTO = Collections.emptyList();
//		DefaultSortDto defaultSort = null;
//		Map<String, Object> advanceSearch = new HashedMap<String, Object>();
//		Map<String, Object> advanceSearchAnd = new HashedMap<String, Object>();
//		Map<String, Object> advanceSort = new HashedMap<String, Object>();
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
//			} else if (((String) entry.getKey()).equals("screen_filter")) {
//				filter = (String) entry.getValue();
//				screenFilterDTO = objectMapper.readValue(filter, new TypeReference<List<ScreenFilterDTO>>() {
//				});
//			} else if (((String) entry.getKey()).equals("advanceSearch")) {
//				advanceSearch = objectMapper.readValue((String) entry.getValue(), Map.class);
//			} else if (((String) entry.getKey()).equals("advanceSearchAnd")) {
//				advanceSearchAnd = objectMapper.readValue((String) entry.getValue(), Map.class);
//			} else if (((String) entry.getKey()).equals("advanceSort")) {
//				advanceSort = objectMapper.readValue((String) entry.getValue(), Map.class);
//			} else if (((String) entry.getKey()).contains("filter_remove")) {
//				filterRemove = Boolean.parseBoolean((String) entry.getValue());
//			} else if (((String) entry.getKey()).equals("defaultSort")) {
//				defaultSort = objectMapper.readValue((String) entry.getValue(), DefaultSortDto.class);
//			}
//
//		}
//		int start = (page - 1) * limit;
		return ResponseEntity.ok(this.industryTypeService.getIndustryTypeList(params));
	}

	
	@GetMapping(path = "/getSelectionList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getSelectionList() {
		return ResponseEntity.ok(industryTypeService.getSelectionList(false));
	}
	
	
	@GetMapping
	public ResponseEntity<Page<IndustryType>> getAllIndustryTypes(Pageable pageable) {
		return new ResponseEntity<Page<IndustryType>>(industryTypeService.getAllIndustryTypes(pageable), HttpStatus.OK);
	}
	
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getIndustryTypeById(@PathVariable("id") int id) {
		return new ResponseEntity<>(industryTypeService.getIndustryTypeById(id), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeIndustryType(@PathVariable("id") int id) {
		return new ResponseEntity<>(industryTypeService.removeIndustryType(id), HttpStatus.OK);
	}
}

