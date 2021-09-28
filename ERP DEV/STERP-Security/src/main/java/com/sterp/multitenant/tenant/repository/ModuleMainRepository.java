package com.sterp.multitenant.tenant.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.modules.entity.Modules;

@Repository
public interface ModuleMainRepository extends JpaRepository<Modules, Long> {

	Modules findByTitle(String string);
	
	@Query("SELECT m FROM com.sterp.multitenant.tenant.modules.entity.Modules m WHERE m.id IN :moduleIds")
	Set<Modules> getAllModulesById(List<Long> moduleIds);

}
