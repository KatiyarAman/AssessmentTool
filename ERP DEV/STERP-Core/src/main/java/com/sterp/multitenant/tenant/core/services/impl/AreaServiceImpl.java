package com.sterp.multitenant.tenant.core.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.core.repository.AreaRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.core.services.AreaService;
import com.sterp.multitenant.tenant.dto.AreaRequestDTO;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.model.Area;

@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AppService appService;

	@Override	
	public Map<String, Object> addOrUpdateArea(AreaRequestDTO areaDTO) {
		UserEntity currentUser = appService.getCurrentUser();
		Area area = modelMapper.map(areaDTO, Area.class);
		area.setCreatedBy(currentUser.getId());
		area.setModifiedBy(currentUser.getId());

		// must be changed to dynamic fecth of company Id from current login bracnch
		//area.setCompanyId(1L);
		//area.setBranchId(13L);
		Map<String, Object> response = new HashedMap<String, Object>();
		response.put("data", this.areaRepository.save(area));
		response.put("message", areaDTO.getId() == 0 ? "Area Added Successfully" : "Area Updated Successfully");
		return response;
	}

	@Override
	public Map<String, Object> getAreaById(Long areaId, boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		Optional<Area> optional = this.areaRepository.findById(areaId);
		if (optional.isPresent()) {
			response.put("data", optional.get());
			return response;
		}
		response.put("message", "Area with given Id: " + areaId + " not found!");
		return response;
	}

	@Override
	public Map<String, Object> getSelectionList(boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> areas = this.areaRepository.getSelectionList(1L);
		if (areas.size() == 0) {
			throw new ResourceNotFoundException("No Active Area found");
		}
		response.put("data", areas);
		return response;
	}

}
