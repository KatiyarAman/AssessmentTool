package com.sterp.multitenant.mastertenant.service;

import java.util.Map;

public interface StateService {
	public Map<String, Object> getActiveStateListByCountrtIdInCommonDropdown(Integer countryId, Integer currentCountryId, Integer permanentCountryId, boolean iscacheable);
}
