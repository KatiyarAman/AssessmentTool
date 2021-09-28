package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentQuestionnaire;
import com.sterp.multitenant.tenant.assesmenttool.entity.Skill;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentQuestionnaireDto;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

public interface AssessmentQuestionnaireRepository extends JpaRepository<AssessmentQuestionnaire, Long>{
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(aq.id,aq.question)"
			+ " from com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentQuestionnaire aq where aq.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentQuestionnaireDto(aq.id, aq.difficultyLevel, aq.fieldType,aq.question, aq.description, aq.status, aq.timeLimit, aq.marks, aq.negetiveMarkFactor) FROM "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentQuestionnaire aq "
			+ "where aq.id like concat('%',:wildCard,'%') or aq.question like concat('%',:wildCard,'%')")
	Page<AssessmentQuestionnaireDto> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentQuestionnaireDto(aq.id, aq.difficultyLevel, aq.fieldType,aq.question, aq.description, aq.status, aq.timeLimit, aq.marks, aq.negetiveMarkFactor) FROM "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentQuestionnaire aq ")
	Page<AssessmentQuestionnaireDto> findCustom(Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.AssessmentQuestionnaireDto(aq.id, aq.difficultyLevel, aq.fieldType,aq.question, aq.description, aq.status, aq.timeLimit, aq.marks, aq.negetiveMarkFactor) FROM "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentQuestionnaire aq WHERE :skill MEMBER OF aq.skills")
	List<AssessmentQuestionnaireDto> findQuestionnairesBySkill(@Param("skill") Skill skill);
	

	@Query("SELECT aq FROM com.sterp.multitenant.tenant.assesmenttool.entity.AssessmentQuestionnaire aq WHERE aq.id IN :ids")
	List<AssessmentQuestionnaire> getAllQuestionnairesById(@Param("ids") Collection<Long> ids);


	


}
