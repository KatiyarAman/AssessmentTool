package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterp.multitenant.tenant.assesmenttool.entity.JobPosting;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobPostingDto;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobPostingDto(jp.id, jp.jobApplyType, jp.interviewType, jp.interviewStartTime, "
			+ "	jp.interviewEndTime, jp.interviewStartDate, jp.interviewEndDate, jp.venue, "
			+ "	jp.askGeneralQuestions, jp.remarks, jp.shareableToken) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.JobPosting jp ")
	Page<JobPostingDto> findCustom(Pageable createPageRequest);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(jp.id, jp.remarks)"
			+ " from com.sterp.multitenant.tenant.assesmenttool.entity.JobPosting jp where jp.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.JobPostingDto(jp.id, jp.jobApplyType, jp.interviewType, jp.interviewStartTime, "
			+ "	jp.interviewEndTime, jp.interviewStartDate, jp.interviewEndDate, jp.venue, "
			+ "	jp.askGeneralQuestions, jp.remarks, jp.shareableToken) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.JobPosting jp ")
	Page<JobPostingDto> findCustomSearch(String search, Pageable createPageRequest);
	

}
