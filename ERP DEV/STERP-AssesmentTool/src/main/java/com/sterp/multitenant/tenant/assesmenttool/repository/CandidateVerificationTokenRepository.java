package com.sterp.multitenant.tenant.assesmenttool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sterp.multitenant.tenant.assesmenttool.entity.CandidateVerificationToken;

public interface CandidateVerificationTokenRepository extends JpaRepository<CandidateVerificationToken, Long>{
	CandidateVerificationToken findByToken(String token);
}
