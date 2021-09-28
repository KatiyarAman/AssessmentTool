package com.sterp.multitenant.tenant.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.employee.dto.EmployeeDTO;
import com.sterp.multitenant.tenant.employee.dto.EmployeeResponseDTO;
import com.sterp.multitenant.tenant.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto."
			+ "CommonDropdownResponseDataTravelObject(emp.id,concat(emp.firstName,' ',(CASE WHEN emp.middleName IS NULL THEN '' ELSE emp.middleName END),(CASE WHEN emp.middleName IS NULL THEN '' ELSE ' ' END),emp.lastName))"
			+ " from com.sterp.multitenant.tenant.employee.entity.Employee emp where emp.erpUser in :isErpUser and emp.status= 2")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(List<Boolean> isErpUser);

//	@Query(value = "SELECT new com.sterp.multitenant.tenant.employee.dto.EmployeeResponseDTO(emp.id,dpt.department,desg.designation,emp.firstName,emp.middleName,emp.lastName,"
//			+ "emp.fatherName,emp.fatherName,emp.dob,emp.joiningDate,emp.fatherName,emp.erpUser,emp.empCode,emp.gender,emp.status) from "
//			+ "com.sterp.multitenant.tenant.employee.entity.Employee emp "
//			+ "left join com.sterp.multitenant.tenant.department.entity.Department dpt on dpt.id=emp.department "
//			+ "left join com.sterp.multitenant.tenant.designation.entity.Designation desg on desg.id=emp.designation "
//			+ "where emp.id like concat('%',:wildCard,'%') or desg.designation like concat('%',:wildCard,'%') or "
//			+ "dpt.department like concat('%',:wildCard,'%') ")
//	Page<EmployeeResponseDTO> findCustomSearch(String wildCard, Pageable pageable);
//
//	@Query(value = "SELECT new com.sterp.multitenant.tenant.employee.dto.EmployeeResponseDTO(emp.id,dpt.department,desg.designation,emp.firstName,emp.middleName,emp.lastName,"
//			+ "emp.fatherName,emp.fatherName,emp.dob,emp.joiningDate,emp.fatherName,emp.erpUser,emp.gender,emp.status) from "
//			+ "com.sterp.multitenant.tenant.employee.entity.Employee emp "
//			+ "left join com.sterp.multitenant.tenant.department.entity.Department dpt on dpt.id=emp.department "
//			+ "left join com.sterp.multitenant.tenant.designation.entity.Designation desg on desg.id=emp.designation")
//	Page<EmployeeResponseDTO> findCustom(Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.employee.dto.EmployeeResponseDTO(emp.id,dpt.department,desg.designation,emp.firstName,emp.middleName,emp.lastName,"
			+ "emp.fatherName,emp.fatherName,emp.dob,emp.joiningDate,emp.fatherName,emp.erpUser,emp.empCode,emp.gender,emp.status) from "
			+ "com.sterp.multitenant.tenant.employee.entity.Employee emp "
			+ "left join com.sterp.multitenant.tenant.department.entity.Department dpt on dpt.id=emp.department "
			+ "left join com.sterp.multitenant.tenant.designation.entity.Designation desg on desg.id=emp.designation "
			+ "where emp.id like concat('%',:wildCard,'%') or desg.designation like concat('%',:wildCard,'%') or "
			+ "dpt.department like concat('%',:wildCard,'%') ")
	Page<EmployeeResponseDTO> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.employee.dto.EmployeeResponseDTO(emp.id,dpt.department,desg.designation,emp.firstName,emp.middleName,emp.lastName,"
			+ " emp.fatherName,emp.fatherName,emp.dob,emp.joiningDate,emp.fatherName,emp.erpUser,emp.empCode,emp.gender,emp.status) from "
			+ "com.sterp.multitenant.tenant.employee.entity.Employee emp "
			+ "left join com.sterp.multitenant.tenant.department.entity.Department dpt on dpt.id=emp.department "
			+ "left join com.sterp.multitenant.tenant.designation.entity.Designation desg on desg.id=emp.designation")
	Page<EmployeeResponseDTO> findCustom(Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.employee.dto.EmployeeDTO(emp.id,emp.department.id, emp.designation.id,"
			+ "emp.firstName,emp.lastName,emp.middleName,emp.fatherName,emp.reportingManager,emp.dob,emp.joiningDate,emp.reportingBranch,"
			+ "emp.erpUser,emp.empCode,emp.gender,emp.status) "
			+ "from com.sterp.multitenant.tenant.employee.entity.Employee emp where emp.id=:employeeId")
	Optional<EmployeeDTO> findCustomResponseById(long employeeId);

}
