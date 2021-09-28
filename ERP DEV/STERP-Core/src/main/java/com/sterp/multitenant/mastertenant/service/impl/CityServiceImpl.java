package com.sterp.multitenant.mastertenant.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.mastertenant.entity.City;
import com.sterp.multitenant.mastertenant.repository.CityRepository;
import com.sterp.multitenant.mastertenant.service.CityService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;

	@CacheEvict(value = "core-list-cache", key = "'coreListCacheCity'+#stateId", condition = "#iscacheable ==null && #iscacheable", beforeInvocation = true)
	@Cacheable(value = "core-list-cache", key = "'coreListCacheCity'+#stateId", condition = "#iscacheable !=null && #iscacheable")
	@Override
	public Map<String, Object> getActiveCityListByStateIdInCommonDropdown(Integer stateId,Integer currentStateId,Integer permanentStateId, boolean iscacheable) {
		List<CommonDropdownResponseDto> cities = (stateId == null)
				? (currentStateId == null) ? (permanentStateId == null)?this.cityRepository.findActiveDataCommonDropDownList(1L)
				: this.cityRepository.findActiveDataCommonDropDownListByStateId(permanentStateId,1L)
				: this.cityRepository.findActiveDataCommonDropDownListByStateId(currentStateId,1L)
				:this.cityRepository.findActiveDataCommonDropDownListByStateId(stateId, 1L); 
				
				
		if (cities.size() == 0) {
			throw new ResourceNotFoundException("states not found for state id: " + stateId);
		}
		Map<String, Object> response = new HashedMap<String, Object>();
		response.put("data", cities);
		return response;
	}

	@CacheEvict(value = "core-list-cache", key = "'coreListCacheCity'+#cityId", condition = "#iscacheable ==null && #iscacheable", beforeInvocation = true)
	@Cacheable(value = "core-list-cache", key = "'coreListCacheCity'+#cityId", condition = "#iscacheable !=null && #iscacheable")
	@Override
	public City getCityById(int cityId, boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		response.put("data", this.cityRepository.findById(cityId));
		
		
		return this.cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("No city found"));
	}

}
