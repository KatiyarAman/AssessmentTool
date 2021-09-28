package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterp.multitenant.tenant.assesmenttool.entity.JobRole;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobRoleDto;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

public interface JobRoleRepository extends JpaRepository<JobRole, Long> {
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(jr.id,jr.role)"
			+ " from com.sterp.multitenant.tenant.assesmenttool.entity.JobRole jr where jr.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobRoleDto(jr.id, jr.role, jr.description,jr.industryType.id, jr.status) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.JobRole jr "
			+ "where jr.id like concat('%',:wildCard,'%') or jr.role like concat('%',:wildCard,'%')")
	Page<JobRoleDto> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobRoleDto(jr.id, jr.role, jr.description, jr.industryType.id, jr.status) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.JobRole jr")
	Page<JobRoleDto> findCustom(Pageable pageable);
}
