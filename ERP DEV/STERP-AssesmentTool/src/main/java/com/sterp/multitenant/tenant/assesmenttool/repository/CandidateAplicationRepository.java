package com.sterp.multitenant.tenant.assesmenttool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateApplication;

public interface CandidateAplicationRepository extends JpaRepository<CandidateApplication,Long> {
@Query(value ="SELECT c FROM  com.sterp.multitenant.tenant.assesmenttool.entity.CandidateApplication c WHERE c.candidateId =:id and c.jobPosting.id =:jobPostingid")
	CandidateApplication findCustomCandidateIdAndJobPostingId(Long id,Long jobPostingid);

	CandidateApplication findByCandidateId(Long id);

}
