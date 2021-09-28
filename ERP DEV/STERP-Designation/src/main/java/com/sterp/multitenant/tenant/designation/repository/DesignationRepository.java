package com.sterp.multitenant.tenant.designation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.designation.dto.DesignationResponseDTO;
import com.sterp.multitenant.tenant.designation.entity.Designation;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(des.id,des.designation)"
			+ " from com.sterp.multitenant.tenant.designation.entity.Designation des where des.status=2")
	List<CommonDropdownResponseDataTravelObject> getSelectionList();

	@Query(value = "SELECT new com.sterp.multitenant.tenant.designation.dto.DesignationResponseDTO(desg.id,desg.designation,desg.shortName,desg.description,desg.status) from "
			+ "com.sterp.multitenant.tenant.designation.entity.Designation desg "
			+ "where desg.id like concat('%',:wildCard,'%') or desg.designation like concat('%',:wildCard,'%') or "
			+ "desg.description like concat('%',:wildCard,'%') ")
	Page<DesignationResponseDTO> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.designation.dto.DesignationResponseDTO(desg.id,desg.designation,desg.shortName,desg.description,desg.status) from "
			+ "com.sterp.multitenant.tenant.designation.entity.Designation desg ")
	Page<DesignationResponseDTO> findCustom(Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.designation.dto.DesignationResponseDTO(desg.id,desg.designation,desg.shortName,desg.description,desg.status) from "
			+ "com.sterp.multitenant.tenant.designation.entity.Designation desg "
			+ "WHERE desg.designation = :designation")
	DesignationResponseDTO findByDesignation(String designation);
}
