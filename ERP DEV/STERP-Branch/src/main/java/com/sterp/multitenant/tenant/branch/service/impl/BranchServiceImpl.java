package com.sterp.multitenant.tenant.branch.service.impl;

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
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.branch.dto.BranchRequestDto;
import com.sterp.multitenant.tenant.branch.dto.BranchResponseDTO;
import com.sterp.multitenant.tenant.branch.entity.Branch;
import com.sterp.multitenant.tenant.branch.repository.BranchRepository;
import com.sterp.multitenant.tenant.branch.service.BranchService;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.BranchResponse;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.entity.Authority;
import com.sterp.multitenant.tenant.entity.AuthorityType;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.settings.codegeneration.service.CodeGenerationService;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;
import com.sterp.multitenant.tenant.uploadutility.dto.IfDefaultLoggedInData;
import com.sterp.multitenant.tenant.uploadutility.service.DocumentUploadService;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	BranchRepository branchRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AppService appService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	DocumentUploadService documentUploadService;
	
	@Autowired
	CodeGenerationService codeGenerationService;

	public Long getCount() {
		return this.branchRepository.count();
	}

	@Override
	public Map<String, Object> addOrUpdateBranch(HttpServletRequest request, Branch branchDto, MultipartFile[] files) {
		Map<String, Object> response = new HashedMap<String, Object>();
		UserEntity currentUser = appService.getCurrentUser();
		Long defaultLoginBranch  = Long.valueOf((Integer) request.getAttribute("defaultloginbranch"));
		Long defaultCompanyId =  getBranchById(defaultLoginBranch, false).getCompanyId();
		Branch branch = modelMapper.map(branchDto, Branch.class);
		AuthorityType userRole = null;
		for(Authority role : currentUser.getAuthorities()) { userRole =  role.getName();}
		if(userRole == AuthorityType.ROLE_USER)
			branch.setParentCompanyId(defaultCompanyId);
		
		branch.setCreatedBy(currentUser.getId());
		branch.setModifiedBy(currentUser.getId());
		branch.setCompanyId(defaultCompanyId);
		branch.setBranchId(defaultLoginBranch);
		
		response.put("data", this.branchRepository.save(branch));
		response.put("message",
				branch.getId() == 0 ? "Branch Added Successfully" : "Branch Updated Successfully");
		if (branch.getDocuments().size() != 0) {
			// get consolidated data as passing parameter with corresponding to current
			// logged in branch
			// Note: The information of current logged in branch will be executed if uploads
			// are performed other than the company and branch module
			BranchResponse branchResponse = this.appService.getBranchCompanyByDefaultLoggedInBranch(request);
			this.documentUploadService.uploadDocuments(branch.getDocuments(), files, new IfDefaultLoggedInData(defaultCompanyId, defaultLoginBranch,
					branchResponse.getCompanyName(), branchResponse.getBranchName(),
					this.appService.getCurrentUser().getId(), 0L), branch.getId(), branch.getBranchName(),request);
			

		}
//		response = commonService.addUpdate(request ,company);
		return response;
		}

	@Override
	public Branch getBranchById(Long branchId, boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		Optional<Branch> optional = this.branchRepository.findById(branchId);
		if (optional.isPresent()) {
			response.put("data", optional.get());
			return optional.get();
		}
		response.put("message", "Branch with given Id: " + branchId + " not found!");
		return null;
	}

	@Override
	public Map<String, Object> getSelectionList(boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> branches = this.branchRepository
				.getSelectionList();
		if (branches.size() == 0) {
			throw new ResourceNotFoundException("No Active Branches found");
		}
		response.put("data", branches);
		return response;
	}

	@Override
	public Map<String, Object> getBranchList(int page, int limit, String search, String sort, String order,
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
		Page<BranchResponseDTO> branchList = null;
		if (totalCount == 0) {
			throw new ResourceNotFoundException("No Record Found");
		}
		if ((search == null) || (search.isEmpty())) {
			branchList = this.branchRepository
					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			branchList = this.branchRepository.findCustomSearch(search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}
		// customQuery.findCustom("ac");
		response.put("totalCount", this.branchRepository.count());
		response.put("data", branchList);
		return response;
	}

}
