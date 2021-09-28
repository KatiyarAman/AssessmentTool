package com.sterp.multitenant.tenant.settings.approval.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.settings.approval.dto.ApprovalSettingsResponseDTO;
import com.sterp.multitenant.tenant.settings.approval.entity.ApprovalSettings;

@Repository
public interface ApprovalSettingsRepository extends JpaRepository<ApprovalSettings, Integer> {
	ApprovalSettings findByModuleIdAndStatus(long moduleId, Long status);

	@Query(nativeQuery = true, value = "SELECT dept.id FROM employee as emp "
			+ "left join department as dept on emp.department_id=dept.id where emp.id=:empid")
	public Long findCustom(@Param("empid") Long empid);

	@Query(nativeQuery = true, value = "SELECT e.first_name,re.first_name FROM plan1_46.user as u "
			+ "left join employee as e on e.id=u.employee_id "
			+ "left join employee as re on e.reporting_manager=re.id " + "left join user as ru on re.id=ru.employee_id "
			+ " where u.id=:userId")
	public Long findReportingUserId(@Param("userId") Long userId);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.settings.approval.dto.ApprovalSettingsResponseDTO(aps.id,m.title,t.type,aps.approvalCount,"
			+ "aps.approvalSystem,aps.minApprovals,aps.approvalBased,aps.bulkApproval,aps.status) from "
			+ "com.sterp.multitenant.tenant.settings.approval.entity.ApprovalSettings aps"
			+ " LEFT JOIN  com.sterp.multitenant.tenant.modules.entity.Modules m on m.id = aps.moduleId"
			+ " LEFT JOIN com.sterp.multitenant.tenant.model.Type t ON t.id = aps.typeId")
	Page<ApprovalSettingsResponseDTO> findCustom(Pageable pageable);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.settings.approval.dto.ApprovalSettingsResponseDTO(aps.id,m.title,t.type,aps.approvalCount," 
			+ "aps.approvalSystem,aps.minApprovals,aps.approvalBased,aps.bulkApproval,aps.status) from " 
			+ "com.sterp.multitenant.tenant.settings.approval.entity.ApprovalSettings aps"
			+ " LEFT JOIN  com.sterp.multitenant.tenant.modules.entity.Modules m on m.id = aps.moduleId" 
			+ " LEFT JOIN com.sterp.multitenant.tenant.model.Type t ON t.id = aps.typeId "
			+ "where aps.id like concat('%',:wildCard,'%') or m.title like concat('%',:wildCard,'%') or "
			+ "t.status like concat('%',:wildCard,'%') ")
	Page<ApprovalSettingsResponseDTO> findCustomSearch(String wildCard, Pageable pageable);

}
