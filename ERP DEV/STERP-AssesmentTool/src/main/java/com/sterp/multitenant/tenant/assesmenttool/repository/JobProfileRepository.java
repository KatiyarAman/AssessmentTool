package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobProfileDto;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

@Repository
public interface JobProfileRepository extends JpaRepository<JobProfile, Long> {
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobProfileDto(jp.id, jp.jobTitle, jp.postedDate, jp.applicableDate, jp.description, jp.status) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile jp "
			+ "where jp.jobTitle like concat('%',:wildCard,'%') or jp.description like concat('%',:wildCard,'%') or jp.id like concat('%',:wildCard,'%')")
	Page<JobProfileDto> findCustomSearch(String wildCard, Pageable pageable);

	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobProfileDto(jp.id, jp.jobTitle, jp.postedDate, jp.applicableDate, jp.description, jp.status) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile jp ")
	Page<JobProfileDto> findCustom(Pageable pageable);


	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(jp.id,jp.jobTitle)"
			+ " from com.sterp.multitenant.tenant.assesmenttool.entity.JobProfile jp where jp.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);

}
