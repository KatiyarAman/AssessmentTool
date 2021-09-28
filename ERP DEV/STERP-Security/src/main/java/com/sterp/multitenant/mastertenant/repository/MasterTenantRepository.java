package com.sterp.multitenant.mastertenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.mastertenant.entity.Clientage;
import com.sterp.multitenant.mastertenant.entity.MasterTenant;

/**
 * @author Navinder Singh
 */

@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant, Integer> {
	MasterTenant findByTenantClientId(Integer tenantClientId);

	MasterTenant findByClientage(Clientage clientage);

}
