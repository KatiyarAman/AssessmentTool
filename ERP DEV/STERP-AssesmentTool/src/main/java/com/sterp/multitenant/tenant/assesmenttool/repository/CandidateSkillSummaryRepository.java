package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateSkillSummary;

public interface CandidateSkillSummaryRepository extends JpaRepository<CandidateSkillSummary,Long>{
	
	@Query(value="SELECT e FROM com.sterp.multitenant.tenant.assesmenttool.entity.CandidateSkillSummary e WHERE e.candidateRegId=:candidateId and candidateApplicationId IS NULL")
	List<CandidateSkillSummary> findCustomByCandidateRegId(Long candidateId);

}
