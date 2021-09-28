package com.sterp.multitenant.tenant.zone.controllor;

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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.zone.dto.ZoneRequestDTO;
import com.sterp.multitenant.tenant.zone.service.ZoneService;

@RestController
@RequestMapping("/zone")
public class ZoneController {

	@Autowired
	ZoneService zoneService;

	@Autowired
	ObjectMapper objectMapper;


	@RequestMapping(value = { "/add", "/update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<Map<String, Object>> addOrUpdate(HttpServletRequest request,
			@RequestPart ZoneRequestDTO object, @RequestPart(required = false) MultipartFile[] files) {
		//long moduleId = Long.parseLong(request.getHeader("moduleId"));
		return new ResponseEntity<Map<String, Object>>(this.zoneService.addOrUpdateZone(request, object, files),
				HttpStatus.OK);

	}

	@GetMapping(value = "/getById/{zoneId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getById(@PathVariable("zoneId") Long zoneId) {
		return ResponseEntity.ok(this.zoneService.getZoneById(zoneId, false));
	}

	@GetMapping(value = "/getSelectionList", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getSelectionList() {
		return ResponseEntity.ok(this.zoneService.getSelectionList(false));
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getList(HttpServletRequest httpServletRequest,
			@RequestParam(required = false) Map<String, Object> params){
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
		return ResponseEntity.ok(this.zoneService.getZoneList(params));
	}
}
