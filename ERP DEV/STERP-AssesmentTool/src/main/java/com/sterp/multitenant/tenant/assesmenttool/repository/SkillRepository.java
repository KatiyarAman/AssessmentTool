package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sterp.multitenant.tenant.assesmenttool.entity.Skill;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

public interface SkillRepository extends JpaRepository<Skill, Long> {
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(s.id,s.skill)"
			+ " from com.sterp.multitenant.tenant.assesmenttool.entity.Skill s where s.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);

	@Query(value = "SELECT s FROM com.sterp.multitenant.tenant.assesmenttool.entity.Skill s "
			+ "where s.id like concat('%',:wildCard,'%') or s.skill like concat('%',:wildCard,'%')")
	Page<Skill> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT s FROM com.sterp.multitenant.tenant.assesmenttool.entity.Skill s ")
	Page<Skill> findCustom(Pageable pageable);
	
	@Query("SELECT s FROM com.sterp.multitenant.tenant.assesmenttool.entity.Skill s WHERE s.id IN :ids")
	List<Skill> getAllSkillByIds(@Param("ids") Collection<Long> skillsId);

	@Query(nativeQuery = true, value = "SELECT s.id, s.skill, s.description, COUNT(qsm.questionnaire_id) totalQuestions "
			+ " FROM skills s LEFT JOIN questionnaire_skills_mapping qsm "
			+ " ON s.id = qsm.skill_id WHERE s.id IN "
			+ "  (SELECT skill_id FROM job_role_skills_mapping WHERE job_role_id = :jobRoleId ) "
			+ " GROUP BY s.id ORDER BY s.skill;")
	List<Object[]> getSkillDetails(@Param("jobRoleId") Long jobRoleId);

	
	
}
