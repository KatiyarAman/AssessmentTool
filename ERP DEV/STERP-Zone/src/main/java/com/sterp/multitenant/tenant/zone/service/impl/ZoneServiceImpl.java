package com.sterp.multitenant.tenant.zone.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.settings.approval.service.ApprovalSettingsService;
import com.sterp.multitenant.tenant.settings.codegeneration.service.CodeGenerationService;
import com.sterp.multitenant.tenant.zone.dto.ZoneRequestDTO;
import com.sterp.multitenant.tenant.zone.entity.Zone;
import com.sterp.multitenant.tenant.zone.repository.ZoneRepository;
import com.sterp.multitenant.tenant.zone.service.ZoneService;

@Service
public class ZoneServiceImpl implements ZoneService {

	@Autowired
	ZoneRepository zoneRepository;

	@Autowired
	CommonService commonService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AppService appService;
	
	@Autowired
	CodeGenerationService codeGenerationService;

	@Autowired
	ApprovalSettingsService approvalSettingsService;

	public Long getCount() {
		return this.zoneRepository.count();
	}

	public Zone getZoneById(Long zoneId, boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		Optional<Zone> optional = this.zoneRepository.findById(zoneId);
		if (optional.isPresent()) {
			response.put("data", optional.get());
			return optional.get();
		}
		response.put("message", "Zone with given Id: " + zoneId + " not found!");
		return null;
	}

	@Override
	public Map<String, Object> addOrUpdateZone(HttpServletRequest request ,ZoneRequestDTO zoneDTO, MultipartFile[] files) {
		/*
		 * UserEntity currentUser = appService.getCurrentUser(); Long loginBranchId =
		 * Long.valueOf((Integer) request.getAttribute("defaultloginbranch")); Long
		 * loginCompanyId = commonService.getById(Branch.class,
		 * loginBranchId).getCompanyId(); int modulePrefix = 0;
		 */
		
		Zone zone = modelMapper.map(zoneDTO, Zone.class);
		//zone.setCreatedBy(currentUser.getId());
		//zone.setModifiedBy(currentUser.getId());
		//zone.setBranchId(loginBranchId);
		//zone.setCompanyId(loginCompanyId);
		
		/* Generate code for Zone */
		/*
		 * List<CommonDropdownResponseDto> fields =
		 * commonService.getAutogeneratedFields("CommonDropdownResponseDto", moduleId);
		 * fields.forEach((field) -> {
		 * zone.setZoneCode(codeGenerationService.generateCode(Zone.class ,request,
		 * moduleId, field, modulePrefix, 0L)); });
		 * 
		 * 
		 * Map<String, Object> response = new HashedMap<String, Object>(); Zone
		 * storedZone = this.zoneRepository.save(zone);
		 * 
		 * // int approvalStatus =
		 * this.approvalSettingsService.getApprovalSetting(false, moduleId,
		 * storedZone.getId(), "NA", // 2500, null, 25, currentUser.getId(), 0L);
		 * 
		 * Long approvalStatus = 0L; if(zoneDTO.getId() == 0) { approvalStatus =
		 * this.approvalSettingsService.getApprovalSetting(false, moduleId,
		 * storedZone.getId(), zone.getZoneCode(), 2500, null, 25, currentUser.getId(),
		 * zone.getStatus(),0L); }else { approvalStatus =
		 * this.approvalSettingsService.updateApproval(false, moduleId, "Approval",
		 * storedZone.getId(), currentUser.getId(), zone.getZoneCode(), 2500, null, 25,
		 * 8L, storedZone.getCreatedBy(), "This is returned",
		 * Long.valueOf(zone.getStatus()+"")); }
		 * 
		 * 
		 * System.out.println("Approval Status: " + approvalStatus);
		 
		
		response.put("data", storedZone);
		response.put("message", zoneDTO.getId() == 0 ? "Zone Added Successfully" : "Zone Updated Successfully");
		*/
		Map<String, Object> response = new HashedMap<String, Object>();
		response = commonService.addUpdate(request ,zone, files);
		return response;
	}

	@Override
	public Map<String, Object> getSelectionList(boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> zones = this.zoneRepository.getSelectionList();
		if (zones.size() == 0) {
			throw new ResourceNotFoundException("No Active Zone found");
		}
		response.put("data", zones);
		return response;
	}

	@Override
	public Map<String, Object> getZoneList(Map<String, Object> params){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("data", this.commonService.getCommonFilteredList(Zone.class, params));
		return response;
	}
}
