package com.sterp.multitenant.tenant.core.services.impl;
//package com.sterp.mutitenant.tenant.services.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import com.sterp.multitenant.exceptionhandler.CustomException;
//import com.sterp.multitenant.tenant.model.request.ModuleRequestModel;
//import com.sterp.mutitenant.tenant.entity.ModuleAction;
//import com.sterp.mutitenant.tenant.entity.Modules;
//import com.sterp.mutitenant.tenant.entity.UserEntity;
//import com.sterp.mutitenant.tenant.repository.IModuleActionRepository;
//import com.sterp.mutitenant.tenant.repository.IModulesRepository;
//import com.sterp.mutitenant.tenant.services.AppService;
//import com.sterp.mutitenant.tenant.services.IModuleService;
//
//@Service
//public class ModuleServiceImpl implements IModuleService {
//
//	@Autowired
//	AppService appService;
//
//	@Autowired
//	IModulesRepository modulesRepository;
//
//	@Autowired
//	IModuleActionRepository moduleActionRepository;
//
//	@Autowired
//	ModelMapper modelMapper;
//
//	@Override
//	public Long getCount() {
//		return this.modulesRepository.count();
//	}
//
//	@CacheEvict(value = "menu-cache", condition = "#iscacheable ==null && #iscacheable", beforeInvocation = true)
//	@Cacheable(value = "menu-cache", condition = "#iscacheable !=null && #iscacheable")
//	@Override
//	public Map<String, Object> getMenuList(boolean iscacheable) {
//		UserEntity user = this.appService.getCurrentUser();
//		if (user == null)
//			throw new CustomException("Session timed out. Try login again.");
//		Map<String, Object> response = new HashMap<String, Object>();
//		List<Modules> modules = this.modulesRepository.findByParentModuleIdAndMenuDisplayAndStatus(0, 1, 1,
//				this.appService.createPageRequest(0, this.getCount().intValue(), "ASC", "displayOrder"));
//		if (modules.size() != 0) {
//			for (Modules module : modules) {
//				module.setSubModules(this.recursiveSubModule(module));
//			}
//			response.put("menu", modules);
//		} else {
//			response.put("message", "Menu list not found");
//		}
//		return response;
//	}
//
//	@Override
//	public Map<String, Object> getScreens(int parentModuleId) {
//		UserEntity user = this.appService.getCurrentUser();
//		if (user == null)
//			throw new CustomException("Session timed out. Try login again.");
//		Map<String, Object> response = new HashMap<String, Object>();
//		response.put("screenPermission", this.appService.getPermission(parentModuleId));
//		response.put("screen", this.modulesRepository.findByParentModuleIdAndMenuDisplayAndStatus(parentModuleId, 0, 1,
//				this.appService.createPageRequest(0, this.getCount().intValue(), "ASC", "displayOrder")));
//
//		return response;
//	}
//
//	@Override
//	public Map<String, Object> createMenu(ModuleRequestModel moduleRequestModel) {
//		UserEntity user = this.appService.getCurrentUser();
//		if (user == null)
//			throw new CustomException("Session timed out. Try login again.");
//		Map<String, Object> response = new HashMap<String, Object>();
//		Optional<ModuleAction> moduleAction = this.moduleActionRepository.findById(moduleRequestModel.getActionId());
//		if (!moduleAction.isPresent()) {
//			throw new CustomException("Module Action with Id: " + moduleRequestModel.getActionId() + " is not present");
//		}
//		Modules module = this.modelMapper.map(moduleRequestModel, Modules.class);
//		module.setId(null);
//		module.setModuleAction(moduleAction.get());
//		response.put("data", this.modulesRepository.save(module));
//		return response;
//	}
//
//	public List<Modules> recursiveSubModule(Modules parentModule) {
//		List<Modules> subModules = this.modulesRepository.findByParentModuleIdAndMenuDisplayAndStatus(
//				parentModule.getId(), 1, 1,
//				this.appService.createPageRequest(0, this.getCount().intValue(), "ASC", "id"));
//		if (subModules.size() != 0) {
//			for (Modules module : subModules) {
//				module.setSubModules(this.recursiveSubModule(module));
//			}
//		}
//		return subModules;
//	}
//
//	@Override
//	public Modules getModuleById(Integer moduleId) {
//		Optional<Modules> module = this.modulesRepository.findById(moduleId);
//		if (module.isPresent()) {
//			return module.get();
//		}
//		return null;
//	}
//
//}
