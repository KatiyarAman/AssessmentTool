package com.sterp.multitenant.tenant.company.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.company.entity.Company;
import com.sterp.multitenant.tenant.company.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@Autowired
	ObjectMapper objectMapper;

	@RequestMapping(value = { "/add", "/update" }, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE }, method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> addOrUpdate(HttpServletRequest request, @RequestPart Company object, @RequestPart (required=false, value = "files") MultipartFile[] files)
			throws IOException, ClassNotFoundException {
		FileOutputStream fileOutputStream = new FileOutputStream("yourfile.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
		objectOutputStream.close();
		
		FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//		Company p2 = (Company) objectInputStream.readObject();
		objectInputStream.close();
		return ResponseEntity.ok(this.companyService.addOrUpdateCompany(request ,object, files));
	}

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllActive(HttpServletRequest request) {
		return ResponseEntity.ok(null);
	}

	@GetMapping(value = "/getById/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById(HttpServletRequest request, @PathVariable(value = "Id", required = true) Long Id) {
		return ResponseEntity.ok(this.companyService.getCompnayById(Id));
	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getList(HttpServletRequest httpServletRequest,
			@RequestParam(required = false) Map<String, Object> params){
		params.put("module_id", Long.parseLong(httpServletRequest.getHeader("moduleId")));
		return ResponseEntity.ok(this.companyService.getCompanyList(params));

	}

}
