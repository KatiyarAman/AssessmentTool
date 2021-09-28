package com.sterp.multitenant.tenant.assesmenttool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateProfile;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile,Long> {

	CandidateProfile findByCandidateId(Long id);

}
