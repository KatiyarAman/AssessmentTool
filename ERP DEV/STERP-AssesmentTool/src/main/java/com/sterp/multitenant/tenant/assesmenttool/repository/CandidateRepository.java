package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterp.multitenant.tenant.assesmenttool.entity.Candidate;
import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateVerificationToken;
import com.sterp.multitenant.tenant.assesmenttool.entity.Candidates;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateDto;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	/*
	 * @Query(value =
	 * "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateDto (c.id, c.name, c.email, c.contactNo, c.assessmentTestResult,"
	 * + " c.assessmentTestScore, c.appliedDate, c.jobPosting.id, c.status," +
	 * " c.statusDetail, c.resume, c.introVideo, c.totalExperience, c.totalExperienceUnit) from "
	 * + "com.sterp.multitenant.tenant.assesmenttool.entity.Candidate c " +
	 * "where c.id like concat('%',:wildCard,'%') or c.name like concat('%',:wildCard,'%') or "
	 * + "c.email like concat('%',:wildCard,'%') ")
	 */
	
	  @Query(value =
	  "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateDto (c.id, c.name, c.email, c.contactNo, c.assessmentTestResult,"
	  + " c.assessmentTestScore, c.createdDate, c.status,p.candidateId," +
	  " c.statusDetail, c.resume, c.introVideo, p.totalExperience, p.totalExperienceUnit) from "
	  + "com.sterp.multitenant.tenant.assesmenttool.entity.Candidate c " 
	  + " LEFT JOIN com.sterp.multitenant.tenant.assesmenttool.entity.CandidateProfile p ON c.id=p.candidateId "
	  + "where c.id like concat('%',:wildCard,'%') or c.name like concat('%',:wildCard,'%') or "
	  + "c.email like concat('%',:wildCard,'%') ")
	Page<CandidateDto> findCustomSearch(String wildCard, Pageable pageable);

	/*
	 * @Query(value =
	 * "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateDto (c.id, c.name, c.email, c.contactNo, c.assessmentTestResult,"
	 * + " c.assessmentTestScore, c.appliedDate, c.jobPosting.id, c.status," +
	 * " c.statusDetail, c.resume, c.introVideo, c.totalExperience, c.totalExperienceUnit) from com.sterp.multitenant.tenant.assesmenttool.entity.Candidate c "
	 * )
	 */
	
	  @Query(value =
	  "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateDto (c.id, c.name, c.email, c.contactNo, c.assessmentTestResult,"
	  + " c.assessmentTestScore, c.createdDate, c.status,p.candidateId," +
	  " c.statusDetail, c.resume, c.introVideo,p.totalExperience, p.totalExperienceUnit) from com.sterp.multitenant.tenant.assesmenttool.entity.Candidate c "
	  + " LEFT JOIN com.sterp.multitenant.tenant.assesmenttool.entity.CandidateProfile p ON c.id=p.candidateId"
	  )
	Page<CandidateDto> findCustom(Pageable pageable);

	Candidate findByVerificationToken(String receivedToken);
     @Query(value="SELECT c FROM com.sterp.multitenant.tenant.assesmenttool.entity.Candidate c where c.email=:email")
	List<Candidate> findCustomEmail(String email);
     
     @Query(value =
    		  "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.CandidateDto (c.id, c.email,"
    		  + "p.candidateId, p.jobPosting.id) from "
    		  + "com.sterp.multitenant.tenant.assesmenttool.entity.Candidate c " 
    		  + " INNER JOIN com.sterp.multitenant.tenant.assesmenttool.entity.CandidateApplication p ON c.id = p.candidateId"
    		  + " where p.jobPosting.id =:jobPostingId and "
    		  + " c.email = :email")
    List<CandidateDto> findCustomWithJobPosting(String email,Long jobPostingId);
	
}
