package com.sterp.multitenant.tenant.modules.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sterp.multitenant.tenant.modules.dto.ModuleDto;
import com.sterp.multitenant.tenant.modules.entity.ModuleActionsMapping;
import com.sterp.multitenant.tenant.modules.entity.Modules;

public interface ModuleService {

	public Map<String, Object> getActiveParentModuleListWithRecursiveChildModules(boolean iscacheable,
			HttpServletRequest httpServletRequest);

	public Modules getModuleById(long moduleId, boolean iscacheable);
	public Modules getModuleByTitle(String title, boolean iscacheable);

	public Map<String, Object> searchModuleByTitle(String title, boolean iscacheable);

	public ModuleActionsMapping getModuleActionMappingByModuleIdAndActionId(long moduleId, long actionId);

	public Map<String, Object> getLastActiveChildModuleList();

	public Map<String, Object> getModuleActionForRoleCreation();

	public Map<String, Object> saveOrUpdate(HttpServletRequest request, ModuleDto object, MultipartFile[] files);
	
	public Map<String , Object> getModuleList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException;

	public ModuleDto getModuleDtoById(Long id); 
}
