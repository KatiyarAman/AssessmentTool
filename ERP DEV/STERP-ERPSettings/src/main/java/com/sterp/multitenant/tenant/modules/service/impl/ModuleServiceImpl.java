package com.sterp.multitenant.tenant.modules.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.modules.dto.ActionMappingDto;
import com.sterp.multitenant.tenant.modules.dto.ModuleActionMappingResponseDTO;
import com.sterp.multitenant.tenant.modules.dto.ModuleDto;
import com.sterp.multitenant.tenant.modules.dto.ModuleMappedToActionResponseDTO;
import com.sterp.multitenant.tenant.modules.entity.Actions;
import com.sterp.multitenant.tenant.modules.entity.ModuleActionsMapping;
import com.sterp.multitenant.tenant.modules.entity.ModuleButtonMapping;
import com.sterp.multitenant.tenant.modules.entity.Modules;
import com.sterp.multitenant.tenant.modules.repository.ActionsRepository;
import com.sterp.multitenant.tenant.modules.repository.ModuleActionMappingRepository;
import com.sterp.multitenant.tenant.modules.repository.ModuleButtonMappingRepository;
import com.sterp.multitenant.tenant.modules.repository.ModulesRepository;
import com.sterp.multitenant.tenant.modules.response.dto.SearchModuleFilterDTO;
import com.sterp.multitenant.tenant.modules.service.ModuleService;
import com.sterp.multitenant.tenant.repository.BranchUserRoleRepository;
import com.sterp.multitenant.tenant.service.SecurityAppService;
import com.sterp.multitenant.tenant.service.impl.AppServiceImpl;
import com.sterp.multitenant.tenant.settings.template.entity.Buttons;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	ModulesRepository modulesRepository;

	@Autowired
	SecurityAppService securityAppService;

	@Autowired
	ModuleActionMappingRepository moduleActionMappingRepository;

	@Autowired
	private ModuleButtonMappingRepository moduleButtonMappingRepository;

	@Autowired
	BranchUserRoleRepository branchUserRoleRepository;

	@Autowired
	private ActionsRepository actionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	AppServiceImpl appServiceImpl;
	
	@Autowired
	CommonService commonService;

	public Long getCount() {
		return this.modulesRepository.count();
	}

	@CacheEvict(value = "menu-cache", key = "'menuCache'", condition = "#iscacheable ==null && #iscacheable", beforeInvocation = true)
	@Cacheable(value = "menu-cache", key = "'menuCache'", condition = "#iscacheable !=null && #iscacheable")
	@Override
	public Map<String, Object> getActiveParentModuleListWithRecursiveChildModules(boolean iscacheable,
			HttpServletRequest httpServletRequest) {
		if (httpServletRequest.getAttribute("defaultloginbranch") == null) {
			throw new ResourceNotFoundException("Please select logged in branch to continue using MINIERP");
		}
		int loggedInBranchId = (int) httpServletRequest.getAttribute("defaultloginbranch");
//		List<Long> Ids = this.branchUserRoleRepository.findAssignedModule(appServiceImpl.getCurrentUser().getId(),
//				loggedInBranchId, true);
		List<Long> Ids = this.branchUserRoleRepository.findAssignedModule(appServiceImpl.getCurrentUser().getId(), 1L);
		Map<String, Object> response = new HashMap<String, Object>();
//		System.out.println(this.modulesRepository.findAll());
		List<Modules> parentModules = this.modulesRepository.findCustom(1L);
		for (Modules module : parentModules) {
			if (module.getParentId() == null) {
				module.setSubModules(this.recursiveFetchSubmodules(module, Ids));
			}
		}
		List<Modules> responseMenuList = new ArrayList<Modules>();
		for (Modules modules : parentModules) {
			if (!modules.getSubModules().isEmpty()) {
				responseMenuList.add(modules);
			}
		}
		List<Modules> noParentChildModule = this.modulesRepository.findCustomChildModule(1L, Ids);
		for (Modules modules : noParentChildModule) {
//			if (!modules.getSubModules().isEmpty() && modules.getClassName() == "has-sub") {
			responseMenuList.add(modules);
//			}
		}
		response.put("data", responseMenuList);
		return response;
	}

	public List<Modules> recursiveFetchSubmodules(Modules parentModule, List<Long> ids) {
		List<Modules> submodules = this.modulesRepository.findCustomModules(parentModule.getId(), ids, "has-sub",
				1L);
		if (submodules.size() != 0) {
			for (Modules module : submodules) {
				module.setSubModules(this.recursiveFetchSubmodules(module, ids));
			}
		}
		return submodules;
	}

	@CacheEvict(value = "menu-cache", key = "'menuCache'+#title", condition = "#iscacheable ==null && #iscacheable", beforeInvocation = true)
	@Cacheable(value = "menu-cache", key = "'menuCache'+#title", condition = "#iscacheable !=null && #iscacheable")
	@Override
	public Map<String, Object> searchModuleByTitle(String title, boolean iscacheable) {
		List<SearchModuleFilterDTO> moduleFilterDTOs = this.modulesRepository.findCustomSearchByTitle(title,
				1L);
		HashMap<String, Object> response = new HashMap<String, Object>();
		if (moduleFilterDTOs.size() == 0) {
			response.put("message", "No record found!");
			return response;
		}
		response.put("message", moduleFilterDTOs.size() + " records found!");
		response.put("data", moduleFilterDTOs);
		return response;
	}

	@Override
	public Modules getModuleById(long id, boolean iscacheable) {
		Modules module = this.modulesRepository.findById(id).get() ;
		return module;
	}
	
	public Modules getModuleByTitle(String title, boolean iscacheable)
	{
		return this.modulesRepository.findByTitle(title);
	}

	@Override
	public ModuleDto getModuleDtoById(Long id) {
		Modules module = this.modulesRepository.findById(id).get();
		ModuleDto dto = this.modelMapper.map(module, ModuleDto.class);
		List<ModuleActionsMapping> moduleActionMappings = this.moduleActionMappingRepository
				.findByModuleId(dto.getId());

		List<ActionMappingDto> actionMappingDto = moduleActionMappings.stream().map(actionMapping -> {
			List<Long> buttonIds = this.moduleButtonMappingRepository.findByModuleActionId(actionMapping.getId())
					.stream().map(mbm -> mbm.getButtonId()).collect(Collectors.toList());
			return new ActionMappingDto(actionMapping.getActionId(), buttonIds);
		}).collect(Collectors.toList());
		dto.setActionMapping(actionMappingDto);
		return dto;
	}

	@Override
	public ModuleActionsMapping getModuleActionMappingByModuleIdAndActionId(long moduleId, long actionId) {
		return this.moduleActionMappingRepository.findByModuleIdAndActionId(moduleId, actionId);
	}

	@Override
	public Map<String, Object> getLastActiveChildModuleList() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> modules = modulesRepository
				.getLastActiveChildModuleList(1L);
		if (modules.size() == 0) {
			throw new ResourceNotFoundException("No Active Area found");
		}
		response.put("data", modules);
		return response;
	}

	@Override
	public Map<String, Object> getModuleActionForRoleCreation() {
		List<CommonDropdownResponseDataTravelObject> modules = modulesRepository
				.getLastActiveChildModuleList(1L);
		Map<String, Object> response = new HashMap<String, Object>();
		List<ModuleMappedToActionResponseDTO> returnList = new ArrayList<ModuleMappedToActionResponseDTO>();
		for (CommonDropdownResponseDataTravelObject commonDropdownResponseDataTravelObject : modules) {
			List<ModuleActionMappingResponseDTO> list = this.moduleActionMappingRepository
					.findCustomForRoleCreation(commonDropdownResponseDataTravelObject.getId());
			if (list.size() != 0) {
				ModuleMappedToActionResponseDTO actionResponseDTO = new ModuleMappedToActionResponseDTO();
				actionResponseDTO.setModuleId(commonDropdownResponseDataTravelObject.getId());
				actionResponseDTO.setTitle(commonDropdownResponseDataTravelObject.getName());
				actionResponseDTO.setMappingResponseDTOs(list);
				returnList.add(actionResponseDTO);
			}
		}
		if (returnList.size() == 0) {
			response.put("message",
					"No mapping of Module and Action found. Please try again later. If issue persist contact administration.");
			return response;
		}
		response.put("data", returnList);
		return response;
	}

	@Override
	public Map<String, Object> saveOrUpdate(HttpServletRequest request, ModuleDto object, MultipartFile[] files) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<ActionMappingDto> actionMappingDtoList = object.getActionMapping();
		List<Long> actionIds = actionMappingDtoList.stream().map(amd -> amd.getActionId()).collect(Collectors.toList());
		Set<Actions> actionsSet = new HashSet<Actions>();
		this.actionRepository.findAllById(actionIds).forEach(action -> actionsSet.add(action));

		// if this entity is for update
		if (object.getId() != 0) {

			/*
			 * Find out what action id's have been deleted and delete the button mappings
			 * corresponding to them.
			 */

			Modules module = this.modulesRepository.findById(object.getId()).get();
			Set<Long> currentActionsId = module.getActions().stream().map(action -> action.getId())
					.collect(Collectors.toSet());
			// remove all actions that we don't want to remove. this set now contains
			// actions that
			// we have to remove and also buttons mapped to it.
			currentActionsId.removeAll(actionIds);
			if (!currentActionsId.isEmpty()) {
				List<Long> actionMappings = this.moduleActionMappingRepository
						.findByModuleIdWhereActionIdIn(object.getId(), currentActionsId).stream()
						.map(mam -> mam.getId()).collect(Collectors.toList());
				this.moduleButtonMappingRepository.deleteModuleButtonMappingWhereModuleActionIdIn(actionMappings);
			}
		}

		// Save Module
		Modules module = this.modelMapper.map(object, Modules.class);
		module.setActions(actionsSet);
//		module = this.modulesRepository.save(module);
		response = commonService.addUpdate(request, object, files);
		module = (Modules) response.get("data");
		
		// get module id from saved module
		final Long moduleId = module.getId();
		List<ModuleActionsMapping> actionMappingsSaved = this.moduleActionMappingRepository.findByModuleId(moduleId);

		// actionMappingsSet.addAll(d);
//		actionMappingsSet.addAll(actionMappingDtoList.stream()
//				.map(action -> new ModuleActionsMapping(moduleId, action.getActionId()))
//				.collect(Collectors.toList()));actionsSet
//		
//		// create list of action id and module id mapping and save.
//		List<ModuleActionsMapping> actionMappingsSaved = this.moduleActionMappingRepository
//				.saveAll(actionMappingsSet);
		Set<ModuleButtonMapping> moduleButtonMapping = new HashSet<ModuleButtonMapping>();

		actionMappingsSaved.forEach(actionMapping -> {
			long actionId = actionMapping.getActionId();
			actionMappingDtoList.forEach(actionMappingDto -> {
				if (actionMappingDto.getActionId() == actionId) {
					moduleButtonMapping
							.addAll(this.moduleButtonMappingRepository.findByModuleActionId(actionMapping.getId()));
					actionMappingDto.getButtonId().forEach(buttonId -> {
						moduleButtonMapping.add(new ModuleButtonMapping(actionMapping.getId(), buttonId));
					});
				}
			});
		});

		this.moduleButtonMappingRepository.saveAll(moduleButtonMapping);
		response.put("data", module);
		return response;
	}

	@Override
	public Map<String, Object> getModuleList(Map<String, Object> params)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(Modules.class, params));
		return response;
	}

	private Pageable createPageRequest(int firstResult, int maxResults, String sortType, String sortField) {
		if (sortType.equalsIgnoreCase("ASC")) {
			return PageRequest.of(firstResult, maxResults, Sort.by(new String[] { sortField }).ascending());
		}
		if (sortType.equalsIgnoreCase("DESC")) {
			return PageRequest.of(firstResult, maxResults, Sort.by(new String[] { sortField }).descending());
		}
		return PageRequest.of(firstResult, maxResults);
	}
}
