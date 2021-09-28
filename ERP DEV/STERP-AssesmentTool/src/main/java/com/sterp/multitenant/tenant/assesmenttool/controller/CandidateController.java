package com.sterp.multitenant.tenant.assesmenttool.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateRegisterationDto;
import com.sterp.multitenant.tenant.assesmenttool.service.CandidateService;
import com.sterp.multitenant.tenant.settings.template.entity.dto.DefaultSortDto;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;
import com.sterp.multitenant.tenant.uploadutility.service.impl.FileStorageUploadServiceImpl;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private ServletContext servletContext;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private FileStorageUploadServiceImpl fileStorage;
	
	@RequestMapping(path = "/add", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Map<String, Object>> applyOnJob(@RequestPart CandidateRegisterationDto object,@RequestPart(required = true) String hostName, @RequestPart(required = false) MultipartFile files[], @RequestPart(required = false) MultipartFile introVideoFile)
			throws IllegalStateException, IOException, MessagingException {
//		String hostName="https://hiretool.shivit.co.in";l
		return ResponseEntity.ok(this.candidateService.applyOnJob(object, files, introVideoFile,hostName));
	}
	
	@PostMapping("/verifyEmail")
	public ResponseEntity<Map<String, Object>> verifyCandidateEmail(@RequestParam("verificationToken") String receivedToken,HttpServletRequest request) {
		return ResponseEntity.ok(this.candidateService.verifyCandidate(receivedToken,request));
	}
	
	@GetMapping("/getById/{candidateId}")
	public ResponseEntity<?> getById(@PathVariable Long candidateId, HttpServletRequest request) {
		return ResponseEntity.ok(this.candidateService.getById(candidateId,request));
	}
	
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getList(HttpServletRequest httpServletRequest,
			@RequestParam(required = false) Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException{
		params.put("module_id", Long.parseLong(httpServletRequest.getHeader("moduleId")));
		int page = 1;
		int limit = 10;
		String search = null;
		String sort = null;
		String order = null;
		String filter = null;
		boolean filterRemove = false;
		List<ScreenFilterDTO> screenFilterDTO = Collections.emptyList();
		DefaultSortDto defaultSort = null;
		Map<String, Object> advanceSearch = new HashedMap<String, Object>();
		Map<String, Object> advanceSearchAnd = new HashedMap<String, Object>();
		Map<String, Object> advanceSort = new HashedMap<String, Object>();
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
			} else if (((String) entry.getKey()).equals("screen_filter")) {
				filter = (String) entry.getValue();
				screenFilterDTO = objectMapper.readValue(filter, new TypeReference<List<ScreenFilterDTO>>() {
				});
			} else if (((String) entry.getKey()).equals("advanceSearch")) {
				advanceSearch = objectMapper.readValue((String) entry.getValue(), Map.class);
			} else if (((String) entry.getKey()).equals("advanceSearchAnd")) {
				advanceSearchAnd = objectMapper.readValue((String) entry.getValue(), Map.class);
			} else if (((String) entry.getKey()).equals("advanceSort")) {
				advanceSort = objectMapper.readValue((String) entry.getValue(), Map.class);
			} else if (((String) entry.getKey()).contains("filter_remove")) {
				filterRemove = Boolean.parseBoolean((String) entry.getValue());
			} else if (((String) entry.getKey()).equals("defaultSort")) {
				defaultSort = objectMapper.readValue((String) entry.getValue(), DefaultSortDto.class);
			}

		}
		System.out.println(this.servletContext.getRealPath("/resume"));
		int start = (page - 1) * limit;
		return ResponseEntity.ok(this.candidateService.getCandidateList(page, limit, search, sort, order, screenFilterDTO,
				filterRemove, Integer.parseInt(httpServletRequest.getHeader("moduleId"))));
	}
	
	
	@GetMapping("/resumes/{filename}")
	public ResponseEntity<InputStreamResource> downloadResume(@PathVariable(required = true) String filename) throws IOException {
		File file = this.fileStorage.getRealPath("/resumes", filename);
		byte[] bytes = Files.readAllBytes(file.toPath());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Expose-Headers","Content-Disposition");
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(new ByteArrayInputStream(bytes)));
	}

	
	/**
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/introVideos/{filename}")
	public ResponseEntity<InputStreamResource> getIntroVideo(@PathVariable(required = true) String filename) throws IOException {
		File file = this.fileStorage.getRealPath("/introductionVideos", filename); 
		byte[] bytes = Files.readAllBytes(file.toPath());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Expose-Headers","Content-Disposition");
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(new ByteArrayInputStream(bytes)));
	}
}
