package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterp.multitenant.tenant.assesmenttool.entity.GeneralQuestion;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.GeneralQuestionDto;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

public interface GeneralQuestionRepository extends JpaRepository<GeneralQuestion, Long> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.GeneralQuestionDto(gq.id, gq.question, gq.answerType, gq.timeout, gq.description, gq.maxAttempt, gq.thinkTime, gq.generalQuestionType,gq.questionCategoryType.id) "
			+ "  FROM com.sterp.multitenant.tenant.assesmenttool.entity.GeneralQuestion gq")
	Page<GeneralQuestionDto> findCustom(Pageable createPageRequest);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.GeneralQuestionDto(gq.id, gq.question, gq.answerType, gq.timeout, gq.description, gq.maxAttempt, gq.thinkTime, gq.generalQuestionType,gq.questionCategoryType.id)  "
			+ " FROM com.sterp.multitenant.tenant.assesmenttool.entity.GeneralQuestion gq "
			+ "where gq.id like concat('%',:wildCard,'%') or gq.question like concat('%',:wildCard,'%')")
	Page<GeneralQuestionDto> findCustomSearch(String wildCard, Pageable createPageRequest);

	@Query("SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(gq.id,gq.question) "
			+ "FROM com.sterp.multitenant.tenant.assesmenttool.entity.GeneralQuestion gq")
	List<CommonDropdownResponseDataTravelObject> getSelectionList();

}
