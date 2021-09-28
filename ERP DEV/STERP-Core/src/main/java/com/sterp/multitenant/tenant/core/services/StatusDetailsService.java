package com.sterp.multitenant.tenant.core.services;

import java.util.List;
import java.util.Map;

import com.sterp.multitenant.tenant.model.StatusDetails;

public interface StatusDetailsService {
	public Map<String, Object> getStatusDetails();

	StatusDetails getStatusDetailsById(Long statusDetailId);
	
	List<StatusDetails> getStatus();

	public Map<String, Object> getSelectionList();
}
