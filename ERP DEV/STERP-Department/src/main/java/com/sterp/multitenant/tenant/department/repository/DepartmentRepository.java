package com.sterp.multitenant.tenant.department.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.department.dto.DepartmentResponseDTO;
import com.sterp.multitenant.tenant.department.entity.Department;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(dept.id,dept.department)"
			+ " from com.sterp.multitenant.tenant.department.entity.Department dept where dept.status= 2")
	List<CommonDropdownResponseDataTravelObject> getSelectionList();

	@Query(value = "SELECT new com.sterp.multitenant.tenant.department.dto.DepartmentResponseDTO(dpt.id,dpt.department,dpt.shortName,dpt.description,dpt.status) from "
			+ "com.sterp.multitenant.tenant.department.entity.Department dpt "
			+ "where dpt.id like concat('%',:wildCard,'%') or dpt.department like concat('%',:wildCard,'%') or "
			+ "dpt.description like concat('%',:wildCard,'%') ")
	Page<DepartmentResponseDTO> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.department.dto.DepartmentResponseDTO(dpt.id,dpt.department,dpt.shortName,dpt.description,dpt.status) from "
			+ "com.sterp.multitenant.tenant.department.entity.Department dpt ")
	Page<DepartmentResponseDTO> findCustom(Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.department.dto.DepartmentResponseDTO(dpt.id,dpt.department,dpt.shortName,dpt.description,dpt.status) from "
			+ "com.sterp.multitenant.tenant.department.entity.Department dpt "
			+ "WHERE dpt.department = :department")
	DepartmentResponseDTO finByDepartmentName(String department);

}
