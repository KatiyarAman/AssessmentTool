package com.sterp.multitenant.tenant.modules.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.modules.dto.ModuleActionMappingResponseDTO;
import com.sterp.multitenant.tenant.modules.entity.ModuleActionsMapping;

@Repository
public interface ModuleActionMappingRepository extends JpaRepository<ModuleActionsMapping, Long> {
	ModuleActionsMapping findByModuleIdAndActionId(long moduleId, long actionId);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.modules.dto.ModuleActionMappingResponseDTO(ma.id,a.actions) "
			+ "from com.sterp.multitenant.tenant.modules.entity.ModuleActionsMapping ma "
			+ "left join com.sterp.multitenant.tenant.modules.entity.Actions a on ma.actionId=a.id where ma.moduleId=:moduleId")
	List<ModuleActionMappingResponseDTO> findCustomForRoleCreation(long moduleId);

	List<ModuleActionsMapping> findByModuleId(Long moduleId);
	
	@Query("SELECT mam FROM com.sterp.multitenant.tenant.modules.entity.ModuleActionsMapping mam WHERE mam.moduleId = :moduleId AND mam.actionId IN :actionIds")
	List<ModuleActionsMapping> findByModuleIdWhereActionIdIn(Long moduleId, Collection<Long> actionIds);
	
}
