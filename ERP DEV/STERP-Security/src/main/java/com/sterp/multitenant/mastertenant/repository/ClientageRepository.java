package com.sterp.multitenant.mastertenant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.mastertenant.entity.Clientage;

@Repository
public interface ClientageRepository extends JpaRepository<Clientage, Integer> {
	Clientage findByEmail(String email);

	Optional<Clientage> findBySubDomainName(String subDomain);
}
