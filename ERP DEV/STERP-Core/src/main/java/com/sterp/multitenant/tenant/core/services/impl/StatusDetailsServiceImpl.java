package com.sterp.multitenant.tenant.core.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.core.repository.StatusDetailsRepository;
import com.sterp.multitenant.tenant.core.services.StatusDetailsService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.model.StatusDetails;

@Service
public class StatusDetailsServiceImpl implements StatusDetailsService {

	@Autowired
	StatusDetailsRepository statusDetailsRepository;

	@Override
	public Map<String, Object> getStatusDetails() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.statusDetailsRepository.findAll());
		return response;
	}

	@Override
	public StatusDetails getStatusDetailsById(Long statusDetailId) {
		return this.statusDetailsRepository.findById(statusDetailId)
				.orElseThrow(() -> new ResourceNotFoundException("Status not found"));
	}

	@Override
	public List<StatusDetails> getStatus() {
		return this.statusDetailsRepository.findAll();
	}

	@Override
	public Map<String, Object> getSelectionList() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> list = this.statusDetailsRepository.getSelectionList();
		response.put("data", list);
		return response;
	}

}
