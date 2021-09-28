package com.sterp.multitenant.tenant.settings.template.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sterp.multitenant.tenant.settings.template.entity.ModuleButtonsMapping;

public interface ModuleButtonsMappingRepository extends JpaRepository<ModuleButtonsMapping, Integer> {
	List<ModuleButtonsMapping> findByModuleActionId(long moduleActionId);

}
