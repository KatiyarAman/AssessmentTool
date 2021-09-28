package com.sterp.multitenant.tenant.assesmenttool.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.IndustryTypeDto;


@Service
public interface IndustryTypeService {
	
	Page<IndustryType> getAllIndustryTypes(Pageable pageable);
	IndustryTypeDto getIndustryTypeById(long id);
	Map<String, Object> saveOrUpdateIndustryType(IndustryTypeDto industryType);
	Map<String, Object> removeIndustryType(long id);
	//Map<String, Object> getIndustryTypeList(Map<String, Object> params) throws JsonParseException, JsonMappingException, IOException;
	Map<String, Object> getSelectionList(boolean isCacheble);
	
	Map<String, Object> getIndustryTypeList(Map<String, Object> params);
}
