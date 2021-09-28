package com.sterp.multitenant.mastertenant.service.impl;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.mastertenant.repository.CountryRepository;
import com.sterp.multitenant.mastertenant.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryRepository countryRepository;

	@CacheEvict(value = "core-list-cache", key = "'core-list-cache'", condition = "#iscacheable ==null && #iscacheable", beforeInvocation = true)
	@Cacheable(value = "core-list-cache", key = "'core-list-cache'", condition = "#iscacheable !=null && #iscacheable")
	@Override
	public Map<String, Object> getActiveCountryListInCommonDropdown(boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		response.put("data", this.countryRepository.findActiveDataCommonDropDownList(1L));
		return response;
	}

}
