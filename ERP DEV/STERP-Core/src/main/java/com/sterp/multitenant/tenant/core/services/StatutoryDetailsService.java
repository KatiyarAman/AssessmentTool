package com.sterp.multitenant.tenant.core.services;

import java.util.Map;

import com.sterp.multitenant.tenant.dto.StatutoryDetailsRequestDTO;

public interface StatutoryDetailsService {

	Map<String, Object> addOrUpdateStatutoryDetails(StatutoryDetailsRequestDTO statutoryDetailsDTO);

	Map<String, Object> getAreaById(Long statutoryDetailsId, boolean iscacheable);

	Map<String, Object> getSelectionList(boolean iscacheable);

}
