package com.sterp.multitenant.tenant.core.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.core.repository.StatutoryDetailsRepository;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.core.services.StatutoryDetailsService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.dto.StatutoryDetailsRequestDTO;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.model.StatutoryDetails;

@Service
public class StatutoryDetailsServiceImpl implements StatutoryDetailsService {
	
	@Autowired
	AppService appService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	StatutoryDetailsRepository statutoryDetailsRepository;

	@Override
	public Map<String, Object> addOrUpdateStatutoryDetails(StatutoryDetailsRequestDTO statutoryDetailsDTO) {
		UserEntity currentUser = appService.getCurrentUser();
		StatutoryDetails statutoryDetails = modelMapper.map(statutoryDetailsDTO, StatutoryDetails.class);
		statutoryDetails.setCreatedBy(currentUser.getId());
		statutoryDetails.setModifiedBy(currentUser.getId());

		// must be changed to dynamic fecth of company Id from current login bracnch
		//area.setCompanyId(1L);
		//area.setBranchId(13L);
		Map<String, Object> response = new HashedMap<String, Object>();
		response.put("data", this.statutoryDetailsRepository.save(statutoryDetails));
		response.put("message", statutoryDetailsDTO.getId() == 0 ? "StatutoryDetail Added Successfully" : "StatutoryDetail Updated Successfully");
		return response;
	}

	@Override
	public Map<String, Object> getAreaById(Long statutoryDetailsId, boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		Optional<StatutoryDetails> optional = this.statutoryDetailsRepository.findById(statutoryDetailsId);
		if (optional.isPresent()) {
			response.put("data", optional.get());
			return response;
		}
		response.put("message", "StatutoryDetail with given Id: " + statutoryDetailsId + " not found!");
		return response;
	}

	@Override
	public Map<String, Object> getSelectionList(boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> statutoryDetails = this.statutoryDetailsRepository.getSelectionList(1L);
		if (statutoryDetails.size() == 0) {
			throw new ResourceNotFoundException("No Active StatutoryDetail found");
		}
		response.put("data", statutoryDetails);
		return response;
	}

}
