package com.sterp.multitenant.mastertenant.service;

import java.util.Map;

public interface CountryService {
	public Map<String, Object> getActiveCountryListInCommonDropdown(boolean iscacheable);
}
