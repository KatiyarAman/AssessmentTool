package com.sterp.multitenant.tenant.designation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.designation.dto.DesignationRequestDTO;
import com.sterp.multitenant.tenant.designation.dto.DesignationResponseDTO;
import com.sterp.multitenant.tenant.designation.entity.Designation;
import com.sterp.multitenant.tenant.designation.repository.DesignationRepository;
import com.sterp.multitenant.tenant.designation.service.DesignationService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	AppService appService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	DesignationRepository designationRepository;

	@Autowired
	CommonService commonService;

	public Long getCount() {
		return designationRepository.count();
	}

	@Override
	public Map<String, Object> addOrUpdateDesignation(HttpServletRequest request, DesignationRequestDTO designationDTO,
			MultipartFile[] files) {
		Map<String, Object> response = new HashedMap<String, Object>();
		// UserEntity currentUser = appService.getCurrentUser();
		// Long defaultLoginBranch = Long.valueOf((Integer)
		// request.getAttribute("defaultloginbranch"));
		// Long defaultCompanyId = commonService.getById(Branch.class,
		// defaultLoginBranch).getCompanyId();

		Designation designation = modelMapper.map(designationDTO, Designation.class);
		// designation.setCreatedBy(currentUser.getId());
		// designation.setModifiedBy(currentUser.getId());
		// designation.setBranchId(defaultLoginBranch);
		// designation.setCompanyId(defaultCompanyId);

		// must be changed to dynamic fecth of company Id from current login bracnch

		// response.put("data", this.designationRepository.save(designation));
		// response.put("message",
		// designationDTO.getId() == 0 ? "Designation Added Successfully" : "Designation
		// Updated Successfully");
		if (designation.getId() == 0) {

			DesignationResponseDTO responseDto = designationRepository.findByDesignation(designation.getDesignation());
			if (responseDto != null) {
				response.put("message", "Designation Already Exist");
				response.put("data", responseDto);

			} else {
				response = this.commonService.addUpdate(request, designation, files);
			}

		} else {
			response = this.commonService.addUpdate(request, designation, files);
		}

		return response;
	}

	@Override
	public Designation getDesignationById(Long designationId, boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		Optional<Designation> optional = this.designationRepository.findById(designationId);
		if (optional.isPresent()) {
			response.put("data", optional.get());
			return optional.get();
		}
		response.put("message", "Designation with given Id: " + designationId + " not found!");
		return optional.get();
	}

	@Override
	@Transactional(readOnly = true)
	public Designation getDesignationById(Long designationId) {
		return this.designationRepository.getOne(designationId);
	}

	@Override
	public Map<String, Object> getSelectionList(boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> designation = this.designationRepository.getSelectionList();
		if (designation.size() == 0) {
			throw new ResourceNotFoundException("No Active Designation found");
		}
		response.put("data", designation);
		return response;
	}

	@Override
	public Map<String, Object> getDesignationList(int page, int limit, String search, String sort, String order,
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
		Page<DesignationResponseDTO> designationList = null;
		if (totalCount == 0) {
			throw new ResourceNotFoundException("No Record Found");
		}
		if ((search == null) || (search.isEmpty())) {
			designationList = this.designationRepository
					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			designationList = this.designationRepository.findCustomSearch(search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}
		// customQuery.findCustom("ac");
		response.put("totalCount", this.designationRepository.count());
		response.put("data", designationList);
		return response;
	}

}
