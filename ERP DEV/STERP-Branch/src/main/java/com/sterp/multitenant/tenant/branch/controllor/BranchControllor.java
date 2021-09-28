package com.sterp.multitenant.tenant.branch.controllor;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.branch.dto.BranchRequestDto;
import com.sterp.multitenant.tenant.branch.entity.Branch;
import com.sterp.multitenant.tenant.branch.service.BranchService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.company.entity.Company;

@RestController
@RequestMapping("/branch")
public class BranchControllor {

	@Autowired
	BranchService branchService;

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	CommonService commonService;

	@RequestMapping(value = { "/add", "/update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> addOrUpdate(HttpServletRequest request, @RequestPart Branch object, @RequestPart (required=false, value = "files") MultipartFile[] files)
			throws IOException, ClassNotFoundException {
		//long moduleId = Long.parseLong(request.getHeader("moduleId"));
		Map<String, Object> response = this.branchService.addOrUpdateBranch(request ,object, files);
		return ResponseEntity.ok(response);

	}

	@GetMapping(value = "/getById/{branchId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getById(@PathVariable("branchId") Long branchId) {
		return ResponseEntity.ok(this.branchService.getBranchById(branchId, false));
	}

	@GetMapping(value = "/getSelectionList", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getSelectionList() {
		return ResponseEntity.ok(this.branchService.getSelectionList(false));
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
//		int moduleId = 0;
//		return ResponseEntity.ok(this.branchService.getBranchList(page, limit, search, sort, order, screenFilterDTO,
//				filterRemove, moduleId));
		Map<String, Object> response = new HashedMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(Branch.class, params));
		return ResponseEntity.ok(response );
	}

}
