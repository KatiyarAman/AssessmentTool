package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentTest;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentTestDto;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

public interface AssessmentTestRepository extends JpaRepository<AssessmentTest, Long> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentTestDto(at.id, at.title, at.timeDurationPolicy, at.timeOutDuration, at.enableNegetiveMarking, at.testPassPercentage, at.totalQuestions, at.status) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentTest at")
	Page<AssessmentTestDto>findCustom(Pageable createPageRequest);
	
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentTestDto(at.id, at.title, at.timeDurationPolicy, at.timeOutDuration, at.enableNegetiveMarking, at.testPassPercentage, at.totalQuestions, at.status) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentTest at "
			+ "where at.id like concat('%',:wildCard,'%') or at.title like concat('%',:wildCard,'%')")
	Page<AssessmentTestDto> findCustomSearch(@Param("wildCard") String wildCard, Pageable createPageRequest);

	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(at.id,at.title)"
			+ " from com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentTest at")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);
}
