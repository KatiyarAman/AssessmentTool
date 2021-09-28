package com.sterp.multitenant.mastertenant.service;

import java.util.Map;

import com.sterp.multitenant.mastertenant.entity.City;

public interface CityService {
	public Map<String, Object> getActiveCityListByStateIdInCommonDropdown(Integer stateId,Integer currentStateId,Integer permanentStateId, boolean iscacheable);

	public City getCityById(int cityId, boolean iscacheable);
}
