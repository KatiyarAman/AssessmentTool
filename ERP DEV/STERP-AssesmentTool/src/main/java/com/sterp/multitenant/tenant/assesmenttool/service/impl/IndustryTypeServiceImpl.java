package com.sterp.multitenant.tenant.assesmenttool.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.IndustryTypeDto;
import com.sterp.multitenant.tenant.assesmenttool.exception.ResourceNotFoundException;
import com.sterp.multitenant.tenant.assesmenttool.repository.IndustryTypeRepository;
import com.sterp.multitenant.tenant.assesmenttool.service.IndustryTypeService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

@Service
public class IndustryTypeServiceImpl implements IndustryTypeService {
	
	@Autowired
	private IndustryTypeRepository industryTypeRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private AppService appService;

	@Autowired
	private CommonService commonService;
	
	@Override
	public Page<IndustryType> getAllIndustryTypes(Pageable pageable) {
		return industryTypeRepository.findAll(pageable);
	}

	
	
//	@Override
//	public IndustryTypeDto getIndustryTypeById(long id) {
//		return industryTypeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Industry Type with id " + id + " is not found"));
//	}

	@Override
	public Map<String, Object> saveOrUpdateIndustryType(IndustryTypeDto industryType) {
		Map<String, Object> response = new HashMap<>();
		IndustryType it = mapper.map(industryType, IndustryType.class);
		String message = null;
		if (it.getId() == 0)
			message = "Industry Type Created";
		else
			message = "Industry Type Updated";
		
		it = industryTypeRepository.save(it);
		
		response.put("data", it);
		response.put("message", message);
		return response;
	}
	
	@Override
	public Map<String, Object> removeIndustryType(long id) {
		Map<String, Object> response = new HashMap<>();
		industryTypeRepository.deleteById(id);
		response.put("message", "Industry Type Deleted Successfully");
		return response;
	}

//	
//	@Override
//	public Map<String, Object> getIndustryTypeList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException {
//		Map<String, Object> response = new HashMap<String, Object>();
//		
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
//		int moduleId = 0;// Integer.parseInt(httpServletRequest.getHeader("screenId"));
//		
//		
//		if ((sort == null) || (sort.isEmpty())) {
//			sort = "id";
//		}
//		if ((order == null) || (order.isEmpty())) {
//			order = Sort.Direction.ASC.toString();
//		}
//		int totalCount = Integer.parseInt(Long.valueOf(industryTypeRepository.count()).toString());
//		Page<IndustryTypeDto> zoneList = null;
//		if ((search == null) || (search.isEmpty())) {
//			zoneList = this.industryTypeRepository
//					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		} else {
//			zoneList = this.industryTypeRepository.findCustomSearch(search,
//					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
//		}
//		// customQuery.findCustom("ac");
//		response.put("totalCount", this.industryTypeRepository.count());
//		response.put("data", zoneList);
//		return response;
//	}
//	
	
	@Override
	public Map<String, Object> getSelectionList(boolean isCacheble) {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> industryTypes = industryTypeRepository.getSelectionList(2L);
		if (industryTypes.isEmpty()) {
			throw new ResourceNotFoundException("No Active Industry Type Found");
		}
		response.put("data", industryTypes);
		return response;
	}

	@Override
	public IndustryTypeDto getIndustryTypeById(long id) {
		IndustryType industryType = industryTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Industry Type with id " + id + " is not found"));
		IndustryTypeDto dto = this.mapper.map(industryType, IndustryTypeDto.class);
		return dto;
	}

	@Override
	public Map<String, Object> getIndustryTypeList(Map<String, Object> params){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(IndustryType.class, params));
		return response;
	}
}
