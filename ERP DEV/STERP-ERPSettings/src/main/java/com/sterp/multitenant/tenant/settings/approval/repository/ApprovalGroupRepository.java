package com.sterp.multitenant.tenant.settings.approval.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.settings.approval.dto.ApprovalGroupResponseDTO;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalGroup;

@Repository
public interface ApprovalGroupRepository extends JpaRepository<ApprovalGroup, Long> {
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto."
			+ "CommonDropdownResponseDataTravelObject(apg.id,apg.approvalGroupName)"
			+ " from com.sterp.multitenant.tenant.settings.approval.entity.ApprovalGroup apg where apg.status =:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.settings.approval.dto.ApprovalGroupResponseDTO(apg.id,apg.approvalGroupName,apg.status) from "
			+ "com.sterp.multitenant.tenant.settings.approval.entity.ApprovalGroup apg ")
	Page<ApprovalGroupResponseDTO> findCustom(Pageable pageable);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.settings.approval.dto.ApprovalGroupResponseDTO(apg.id,apg.approvalGroupName,apg.status) from "
			+ "com.sterp.multitenant.tenant.settings.approval.entity.ApprovalGroup apg "
			+ "where apg.id like concat('%',:wildCard,'%') or apg.approvalGroupName like concat('%',:wildCard,'%') or "
			+ "apg.status like concat('%',:wildCard,'%') ")
	Page<ApprovalGroupResponseDTO> findCustomSearch(String wildCard, Pageable pageable);
}
