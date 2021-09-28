package com.sterp.multitenant.tenant.settings.template.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.modules.dto.ButtonDto;
import com.sterp.multitenant.tenant.modules.entity.Actions;
import com.sterp.multitenant.tenant.modules.repository.ActionsRepository;
import com.sterp.multitenant.tenant.settings.template.entity.Buttons;
import com.sterp.multitenant.tenant.settings.template.repository.ButtonsRepository;
import com.sterp.multitenant.tenant.settings.template.service.ButtonsService;

@Service
public class ButtonsServiceImpl implements ButtonsService {

	@Autowired
	private ButtonsRepository buttonsRepository;
	
	@Autowired
	private ActionsRepository actionsRepository;

	@Autowired
	CommonService commonService;
	
	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	public Map<String, Object> getSelectionList() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> data = this.buttonsRepository.getSelectionList();
		if (data.isEmpty())
			throw new ResourceNotFoundException("No Active Buttons Found");
		response.put("data", data);
		return response;
	}

	@Override
	public Map<String, Object> getButtonList(Map<String, Object> params){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(Buttons.class, params));
		return response;
	}

	
	@Override
	public Map<String, Object> saveOrUpdate(HttpServletRequest request, ButtonDto buttonDto, MultipartFile[] files) {
		Map<String, Object> response = new HashMap<>();
		Buttons button = this.modelMapper.map(buttonDto, Buttons.class);
		response = this.commonService.addUpdate(request, button, files);
		return response;
	}
}
