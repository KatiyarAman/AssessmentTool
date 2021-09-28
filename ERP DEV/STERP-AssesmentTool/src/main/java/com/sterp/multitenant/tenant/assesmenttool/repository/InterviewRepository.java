package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.assesmenttool.entity.Interview;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.InterviewDto;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.InterviewDto(i.id, i.interviewName,i.jobProfile.id, i.totalQuestions) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.Interview i")
	Page<InterviewDto> findCustom(Pageable createPageRequest);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.InterviewDto(i.id, i.interviewName,i.jobProfile.id, i.totalQuestions) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.Interview i "
			+ "where i.id like concat('%',:search,'%') or i.interviewName like concat('%',:search,'%')")
	Page<InterviewDto> findCustomSearch(@Param("search") String search, Pageable createPageRequest);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(i.id,i.interviewName)"
			+ " from com.sterp.multitenant.tenant.assesmenttool.entity.Interview i where i.status = 2")
	List<CommonDropdownResponseDataTravelObject> getSelectionList();
	
}
