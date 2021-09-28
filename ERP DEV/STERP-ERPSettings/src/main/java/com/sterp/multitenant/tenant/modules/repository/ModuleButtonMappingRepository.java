package com.sterp.multitenant.tenant.modules.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.sterp.multitenant.tenant.modules.entity.ModuleButtonMapping;

public interface ModuleButtonMappingRepository extends CrudRepository<ModuleButtonMapping, Long> {
	List<ModuleButtonMapping> findByModuleActionId(Long moduleActionId);
	
	
	@Transactional
	@Modifying
	@Query("DELETE FROM com.sterp.multitenant.tenant.modules.entity.ModuleButtonMapping mbm WHERE mbm.moduleActionId IN :moduleActionMappingIds")
	void deleteModuleButtonMappingWhereModuleActionIdIn(Collection<Long> moduleActionMappingIds);
}
