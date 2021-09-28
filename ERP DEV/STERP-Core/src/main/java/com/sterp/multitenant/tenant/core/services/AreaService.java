package com.sterp.multitenant.tenant.core.services;

import java.util.Map;
import com.sterp.multitenant.tenant.dto.AreaRequestDTO;


public interface AreaService {

	Map<String, Object> addOrUpdateArea(AreaRequestDTO areaDTO);

	Map<String, Object> getAreaById(Long areaId, boolean iscacheable);

	Map<String, Object> getSelectionList(boolean iscacheable);

}
