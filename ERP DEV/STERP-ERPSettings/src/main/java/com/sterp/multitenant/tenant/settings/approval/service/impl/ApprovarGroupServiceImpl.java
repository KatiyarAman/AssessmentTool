package com.sterp.multitenant.tenant.settings.approval.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.settings.approval.dto.ApprovalGroupResponseDTO;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalGroup;
import com.sterp.multitenant.tenant.settings.approval.repository.ApprovalGroupRepository;
import com.sterp.multitenant.tenant.settings.approval.service.ApprovalGroupService;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;


@Service
public class ApprovarGroupServiceImpl implements ApprovalGroupService {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AppService appService;
	
	@Autowired
	ApprovalGroupRepository approvalGroupRepository;
	
	
	public Long getCount() {
		return this.approvalGroupRepository.count();
	}
	
	@Override
	public Map<String, Object> addOrUpdateApprovalGroup(ApprovalGroup aprovalGroup) {
		UserEntity currentUser = appService.getCurrentUser();

		aprovalGroup.setCreatedBy(currentUser.getId());
		aprovalGroup.setModifiedBy(currentUser.getId());
		Map<String, Object> response = new HashedMap<String, Object>();
		response.put("data", this.approvalGroupRepository.save(aprovalGroup));
		response.put("message",
				aprovalGroup.getId() == 0 ? "Employee Added Successfully" : "Employee Updated Successfully");
		return response;
	}

	@Override
	public ApprovalGroup getApprovalGroupById(Long approvalGroupId, boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();

		// Need to change this part of comment
		Optional<ApprovalGroup> optional = this.approvalGroupRepository.findById(approvalGroupId);
		if (optional.isPresent()) {
			response.put("data", optional.get());
			return optional.get();
		}
		response.put("message", "Employee with given Id: " + approvalGroupId + " not found!");
		return null;
	}

	@Override
	public Map<String, Object> getSelectionList(boolean iscacheable, Long status) {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> approvalGroups = this.approvalGroupRepository.getSelectionList(status);
		if (approvalGroups.size() == 0) {
			throw new ResourceNotFoundException("No Active Employee found to be a User");
		}
		response.put("data", approvalGroups);
		return response;
	}

	@Override
	public Map<String, Object> getApprovarGroupList(int page, int limit, String search, String sort, String order,
			List<ScreenFilterDTO> screenFilterDTO, boolean filterRemove, int moduleId) {
		UserEntity currentUser = this.appService.getCurrentUser();
		if (currentUser == null)
			throw new CustomException("Session has been timed out!");

		Map<String, Object> response = new HashMap<String, Object>();
		// List<ScreenFilter> screenFilter = new ArrayList<ScreenFilter>();

		if ((sort == null) || (sort.isEmpty())) {
			sort = "id";
		}
		if ((order == null) || (order.isEmpty())) {
			order = Sort.Direction.ASC.toString();
		}
		int totalCount = Integer.parseInt(Long.valueOf(this.getCount()).toString());
		Page<ApprovalGroupResponseDTO> approvalGroupList = null;
		if (totalCount == 0) {
			throw new ResourceNotFoundException("No Record Found");
		}
		if ((search == null) || (search.isEmpty())) {
			approvalGroupList = this.approvalGroupRepository
					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			approvalGroupList = this.approvalGroupRepository.findCustomSearch(search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}
		// customQuery.findCustom("ac");
		response.put("totalCount", this.approvalGroupRepository.count());
		response.put("data", approvalGroupList);
		return response;
	}

}
