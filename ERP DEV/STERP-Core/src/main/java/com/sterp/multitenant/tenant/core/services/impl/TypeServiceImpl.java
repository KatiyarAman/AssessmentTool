package com.sterp.multitenant.tenant.core.services.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.core.repository.StatusDetailsRepository;
import com.sterp.multitenant.tenant.core.repository.TypeRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.core.services.TypeService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.dto.TypeRequestDTO;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.model.Type;
import com.sterp.multitenant.tenant.modules.entity.Modules;
import com.sterp.multitenant.tenant.repository.ModuleMainRepository;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AppService appService;

	@Autowired
	TypeRepository typeRepository;

	@Autowired
	private ModuleMainRepository modulesRepository;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private StatusDetailsRepository statusRepo;

	@Override
	public Map<String, Object> saveOrUpdateType(TypeRequestDTO typeDTO) {
		UserEntity currentUser = appService.getCurrentUser();
		Type type = modelMapper.map(typeDTO, Type.class);
		type.setCreatedBy(currentUser.getId());
		type.setModifiedBy(currentUser.getId());
		Map<String, Object> response = new HashMap<String, Object>();

		if (!typeDTO.getModulesId().isEmpty()) {
			Set<Modules> modules = this.modulesRepository.getAllModulesById(typeDTO.getModulesId());
			type.setModules(modules);
		}
		this.typeRepository.save(type);
		typeDTO.setId(type.getId());
		response.put("data", typeDTO);
		response.put("message", typeDTO.getId() == 0 ? "Type Added Successfully" : "Type Updated Successfully");
		return response;
	}

	@Override
	@Transactional(value = "tenantTransactionManager")
	public TypeRequestDTO getTypeById(Long typeId, boolean iscacheable) {
		Optional<Type> optional = this.typeRepository.findById(typeId);
		Type type = optional.get();
		List<Long> moduleIds = type.getModules().stream().map(m -> m.getId()).collect(Collectors.toList());
		TypeRequestDTO dto = this.modelMapper.map(type, TypeRequestDTO.class);
		dto.setModulesId(moduleIds);
		return dto;

	}

	@Override
	public Map<String, Object> getSelectionList(boolean iscacheable, Long moduleId) {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> type = (moduleId == null)
				? this.typeRepository.getSelectionList(2L)
				: this.typeRepository.getSelectionListByModuleId(2L, moduleId);
		if (type.size() == 0) {
			throw new ResourceNotFoundException("No Active Area found");
		}
		response.put("data", type);
		return response;
	}

	@Override
	public Map<String, Object> getTypeList(Map<String, Object> params)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		int page = 1;
		int limit = 10;
		String search = null;
		String sort = null;
		String order = null;
		String filter = null;
		boolean filterRemove = false;
		List<ScreenFilterDTO> screenFilterDTO = Collections.emptyList();
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
			} else if (((String) entry.getKey()).contains("_like")) {
				search = (String) entry.getValue();
			} else if (((String) entry.getKey()).contains("screen_filter")) {
				filter = (String) entry.getValue();
				screenFilterDTO = objectMapper.readValue(filter, new TypeReference<List<ScreenFilterDTO>>() {
				});
			} else if (((String) entry.getKey()).contains("filter_remove")) {
				filterRemove = Boolean.parseBoolean((String) entry.getValue());
			}
		}
		int moduleId = 0;// Integer.parseInt(httpServletRequest.getHeader("screenId"));

		if ((sort == null) || (sort.isEmpty())) {
			sort = "id";
		}
		if ((order == null) || (order.isEmpty())) {
			order = Sort.Direction.ASC.toString();
		}
		int totalCount = Integer.parseInt(Long.valueOf(typeRepository.count()).toString());
		Page<TypeRequestDTO> typeList = null;
		if ((search == null) || (search.isEmpty())) {
			typeList = this.typeRepository
					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			typeList = this.typeRepository.findCustomSearch(search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}
		// customQuery.findCustom("ac");
		for (TypeRequestDTO typeRequestDTO : typeList) {
			typeRequestDTO.setStatusDetail(this.statusRepo.findById(typeRequestDTO.getStatus()).get());
		}
		response.put("totalCount", this.typeRepository.count());
		response.put("data", typeList);
		return response;
	}

}
