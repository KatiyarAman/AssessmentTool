package com.sterp.multitenant.tenant.department.service.impl;

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

import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.department.dto.DepartmentRequestDTO;
import com.sterp.multitenant.tenant.department.dto.DepartmentResponseDTO;
import com.sterp.multitenant.tenant.department.entity.Department;
import com.sterp.multitenant.tenant.department.repository.DepartmentRepository;
import com.sterp.multitenant.tenant.department.service.DepartmentService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AppService appService;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	CommonService commonService;

	public Long getCount() {
		return this.departmentRepository.count();
	}

	@Override
	public Map<String, Object> addOrUpdateDepartment(HttpServletRequest request, DepartmentRequestDTO departmentDto,
			MultipartFile[] files) {
		Map<String, Object> response = new HashedMap<String, Object>();
		// UserEntity currentUser = appService.getCurrentUser();
		// Long defaultLoginBranch = Long.valueOf((Integer)
		// request.getAttribute("defaultloginbranch"));
		// Long defaultCompanyId = commonService.getById(Branch.class,
		// defaultLoginBranch).getCompanyId();

		Department department = modelMapper.map(departmentDto, Department.class);
		// department.setCreatedBy(currentUser.getId());
		// department.setModifiedBy(currentUser.getId());
		// department.setBranchId(defaultLoginBranch);
		// department.setCompanyId(defaultCompanyId);

		// response.put("data", this.departmentRepository.save(department));
		// response.put("message",
		// departmentDto.getId() == 0 ? "Department Added Successfully" : "Department
		// Updated Successfully");

		if (department.getId() == 0) {
			DepartmentResponseDTO departmentresponse = departmentRepository
					.finByDepartmentName(department.getDepartment());
			if (departmentresponse != null) {
				response.put("message", "Department Already Exist ");
				response.put("data", departmentresponse);
			} else {
				response = commonService.addUpdate(request, department, files);
			}

		} else {
			response = commonService.addUpdate(request, department, files);

		}

		return response;
	}

	@Override
	public Department getDepartmentById(Long departmentId, boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		Optional<Department> optional = this.departmentRepository.findById(departmentId);
		if (optional.isPresent()) {
			response.put("data", optional.get());
			return optional.get();
		}
		response.put("message", "Department with given Id: " + departmentId + " not found!");
		return null;
	}

	@Override
	
	public Department getDepartmentById(Long departmentId) {
		return this.departmentRepository.getOne(departmentId);
	}

	@Override
	public Map<String, Object> getDepartmentList(int page, int limit, String search, String sort, String order,
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
		Page<DepartmentResponseDTO> deptList = null;
		if (totalCount == 0) {
			throw new ResourceNotFoundException("No Record Found");
		}
		if ((search == null) || (search.isEmpty())) {
			deptList = this.departmentRepository
					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			deptList = this.departmentRepository.findCustomSearch(search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}
		// customQuery.findCustom("ac");
		response.put("totalCount", this.departmentRepository.count());
		response.put("data", deptList);
		return response;
	}

	@Override
	public Map<String, Object> getSelectionList(boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> designation = this.departmentRepository.getSelectionList();
		if (designation.size() == 0) {
			throw new ResourceNotFoundException("No Active Department found");
		}
		response.put("data", designation);
		return response;
	}

}
