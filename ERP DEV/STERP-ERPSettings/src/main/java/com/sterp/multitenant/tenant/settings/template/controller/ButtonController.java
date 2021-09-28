package com.sterp.multitenant.tenant.settings.template.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.modules.dto.ButtonDto;
import com.sterp.multitenant.tenant.settings.template.service.ButtonsService;


@RestController
@RequestMapping("/buttons")
public class ButtonController {
	
	@Autowired
	private ButtonsService buttonsService;	
	
	@GetMapping("/getSelectionList")
	public ResponseEntity<Map<String, Object>> getSelectionList() {
		return ResponseEntity.ok(buttonsService.getSelectionList());
	}
	
	
	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getButtonList(HttpServletRequest httpServletRequest,
			@RequestParam(required = false) Map<String, Object> params){
		params.put("module_id", Long.parseLong(httpServletRequest.getHeader("moduleId")));
		return ResponseEntity.ok(this.buttonsService.getButtonList(params));
	}
	
	
	@RequestMapping(
			path = {"/add","/update"},
			method = {RequestMethod.PUT, RequestMethod.POST},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE}
	)
	public ResponseEntity<Map<String, Object>> saveOrUpdate(HttpServletRequest request,@RequestPart ButtonDto object, @RequestPart(required = false) MultipartFile[] files) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream("yourfile.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
		objectOutputStream.close();
		
		FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		objectInputStream.close();
		return ResponseEntity.ok(this.buttonsService.saveOrUpdate(request, object, files));
	}
	
}
