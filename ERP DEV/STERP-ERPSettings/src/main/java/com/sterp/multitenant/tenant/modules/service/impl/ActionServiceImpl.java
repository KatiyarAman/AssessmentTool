package com.sterp.multitenant.tenant.modules.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.modules.repository.ActionsRepository;
import com.sterp.multitenant.tenant.modules.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionsRepository actionsRepository;
	
	@Override
	public Map<String, Object> getSelectionList() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> data = this.actionsRepository.getSelectionList(1L);
		if (data.isEmpty())
			throw new ResourceNotFoundException("No Active Action Found");
		response.put("data", data);
		return response;
	}

}
