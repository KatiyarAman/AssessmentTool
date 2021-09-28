package com.sterp.multitenant.tenant.employee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sterp.multitenant.tenant.branch.entity.Branch;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.department.entity.Department;
import com.sterp.multitenant.tenant.department.repository.DepartmentRepository;
import com.sterp.multitenant.tenant.department.service.DepartmentService;
import com.sterp.multitenant.tenant.designation.entity.Designation;
import com.sterp.multitenant.tenant.designation.repository.DesignationRepository;
import com.sterp.multitenant.tenant.designation.service.DesignationService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.employee.dto.EmployeeDTO;
import com.sterp.multitenant.tenant.employee.dto.EmployeeResponseDTO;
import com.sterp.multitenant.tenant.employee.entity.Employee;
import com.sterp.multitenant.tenant.employee.repository.EmployeeRepository;
import com.sterp.multitenant.tenant.employee.service.EmployeeService;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.exceptionhandler.ResourceNotFoundException;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AppService appService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	DesignationService designationService;

	@Autowired
	CommonService commonService;

	
	public Long getCount() {
		return this.employeeRepository.count();
	}

	@Override
	public Map<String, Object> addOrUpdateEmployee(HttpServletRequest request, EmployeeDTO employeeDTO,
			MultipartFile[] files) {
		Map<String, Object> response = new HashedMap<String, Object>();
		 UserEntity currentUser = appService.getCurrentUser();

		 Long defaultLoginBranch = Long.valueOf((Integer)request.getAttribute("defaultloginbranch"));
		 Long defaultCompanyId = commonService.getById(Branch.class,defaultLoginBranch).getCompanyId();

		Employee employee = modelMapper.map(employeeDTO, Employee.class);
		
		/*
		 * employee.setDepartment(this.departmentService.getDepartmentById(employeeDTO.
		 * getDepartment(), false));
		 * employee.setDesignation(this.designationService.getDesignationById(
		 * employeeDTO.getDesignation(), false));
		 */
		 
	System.out.println("Designation:"+employeeDTO.getDesignationId());
		System.out.println("Department:"+employeeDTO.getDepartmentId());
//		employee.setDepartment(this.departmentService.getDepartmentById(employeeDTO.getDepartment()));
//		employee.setDesignation(this.designationService.getDesignationById(employeeDTO.getDesignation()));
		 employee.setCreatedBy(currentUser.getId());
		 employee.setModifiedBy(currentUser.getId());
		 employee.setBranchId(defaultLoginBranch);
		 employee.setCompanyId(defaultCompanyId);

		// response.put("data", this.employeeRepository.save(employee));
		// response.put("message",
		// employeeDTO.getId() == 0 ? "Employee Added Successfully" : "Employee Updated
		// Successfully");
		response = commonService.addUpdate(request, employee, files);
		return response;
	}

	@Override
	public EmployeeDTO getEmployeeById(Long employeeId, boolean iscacheable) {
		Map<String, Object> response = new HashedMap<String, Object>();

		// Need to change this part of comment
		Optional<Employee> needToChange = this.employeeRepository.findById(employeeId);
		// Need to change this part of comment
		Optional<EmployeeDTO> optional = this.employeeRepository.findCustomResponseById(employeeId);
		if (optional.isPresent()) {
			optional.get().setCustomFieldDatas(needToChange.get().getCustomFieldDatas());
			response.put("data", optional.get());
			return optional.get();
		}
		response.put("message", "Employee with given Id: " + employeeId + " not found!");
		return null;
	}

	@Override
	public Map<String, Object> getSelectionList(boolean iscacheable, List<Boolean> isERPUser) {
		List<Boolean> isERP = new ArrayList<>();
		if (isERPUser == null) {
			isERP.add(true);
			isERP.add(false);
		} else {
			isERP = isERPUser;
		}
		Map<String, Object> response = new HashedMap<String, Object>();
		List<CommonDropdownResponseDataTravelObject> employee = this.employeeRepository.getSelectionList(isERP);
		if (employee.size() == 0) {
			throw new ResourceNotFoundException(
					isERPUser == null ? "No Employee found" : "No Active Employee found to be a User");
		}
		response.put("data", employee);
		return response;
	}

	@Override
	public Map<String, Object> getEmployeeList(int page, int limit, String search, String sort, String order,
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
		Page<EmployeeResponseDTO> designationList = null;
		if (totalCount == 0) {
			throw new ResourceNotFoundException("No Record Found");
		}
		if ((search == null) || (search.isEmpty())) {
			designationList = this.employeeRepository
					.findCustom(this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		} else {
			designationList = this.employeeRepository.findCustomSearch(search,
					this.appService.createPageRequest((page - 1), limit, order.toUpperCase(), sort));
		}
		// customQuery.findCustom("ac");
		response.put("totalCount", this.employeeRepository.count());
		response.put("data", designationList);
		return response;
	}

}
