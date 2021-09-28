package com.sterp.multitenant.tenant.core.services;

import java.util.List;

import com.sterp.multitenant.tenant.settings.template.entity.ScreenFilter;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

public interface ScreenFilterService {
	public List<ScreenFilterDTO> getScreenFilter(long userId, long moduleId);

	public List<ScreenFilter> save(List<ScreenFilter> screenFilters);

	public List<ScreenFilter> deleteAll(List<ScreenFilter> screenFilters);
}
