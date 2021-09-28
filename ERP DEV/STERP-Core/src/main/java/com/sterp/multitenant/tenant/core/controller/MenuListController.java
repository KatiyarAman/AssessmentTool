package com.sterp.multitenant.tenant.core.controller;
//package com.sterp.mutitenant.tenant.controller;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sterp.multitenant.tenant.model.request.ModuleRequestModel;
//import com.sterp.mutitenant.tenant.services.IModuleService;
//
//@RestController
//public class MenuListController {
//
//	@Autowired
//	IModuleService moduleService;
//
//	@PostMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Map<String, Object>> createMenu(@RequestBody ModuleRequestModel moduleRequestModel) {
//		return new ResponseEntity<Map<String, Object>>(this.moduleService.createMenu(moduleRequestModel),
//				HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Map<String, Object>> getMenuList(@RequestParam("iscacheable") boolean iscacheable) {
//		return new ResponseEntity<Map<String, Object>>(this.moduleService.getMenuList(iscacheable), HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/menu/{moduleId}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> getModuleById(@PathVariable("moduleId") Integer moduleId) {
//		return ResponseEntity.ok(this.moduleService.getModuleById(moduleId));
//	}
//
//	@GetMapping(value = "/screen/{parentModuleId}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Map<String, Object>> getScreen(@PathVariable("parentModuleId") Integer parentModuleId) {
//		return new ResponseEntity<Map<String, Object>>(this.moduleService.getScreens(parentModuleId), HttpStatus.OK);
//	}
//}
