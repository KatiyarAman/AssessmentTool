package com.sterp.multitenant.tenant.assesmenttool.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.assesmenttool.entity.Skill;
import com.sterp.multitenant.tenant.assesmenttool.service.SkillService;

@RestController
@RequestMapping("/skills")
public class SkillController {

	@Autowired
	private SkillService skillService;
	
	@Autowired
	private ObjectMapper objectMapper;


	@RequestMapping(
			path = {"/add", "/update"},
			method = {RequestMethod.PUT, RequestMethod.POST},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestPart Skill object) {
		return ResponseEntity.ok(this.skillService.addOrUpdate(object));
		
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Skill> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.skillService.getSkillById(id));
	}
	
//	@GetMapping("/list")
//	public ResponseEntity<Map<String, Object>> getSelectionList(@RequestParam Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
//		return ResponseEntity.ok(this.skillService.getSkillsList(params));
//	}
	

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getList(HttpServletRequest httpServletRequest,
			@RequestParam(required = false) Map<String, Object> params){
		//long moduleId = Long.parseLong(httpServletRequest.getHeader("moduleId"));
		params.put("module_id" ,Long.parseLong(httpServletRequest.getHeader("moduleId")));
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
//		
//		int start = (page - 1) * limit;
		return ResponseEntity.ok(this.skillService.getSkillList(params));
	}
	
	@GetMapping("/getSelectionList")
	public ResponseEntity<Map<String, Object>> getSelectionList() {
		return ResponseEntity.ok(this.skillService.getSelectionList());
	}
	
}

