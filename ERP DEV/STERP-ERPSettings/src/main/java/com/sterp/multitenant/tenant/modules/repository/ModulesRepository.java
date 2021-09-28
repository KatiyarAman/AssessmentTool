package com.sterp.multitenant.tenant.modules.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.modules.dto.ModuleDto;
import com.sterp.multitenant.tenant.modules.entity.Modules;
import com.sterp.multitenant.tenant.modules.response.dto.SearchModuleFilterDTO;

@Repository
public interface ModulesRepository extends JpaRepository<Modules, Long> {
	List<Modules> findByParentIdAndIdInAndStatusOrderByDisplayOrderAsc(Long parentModuleId, List<Long> ids,
			Long status);
	
	@Query(value = "SELECT m from com.sterp.multitenant.tenant.modules.entity.Modules m where m.parentId=:parentModuleId and (m.id IN :ids or m.className=:className) and m.status=:status order by m.displayOrder ASC")
	List<Modules> findCustomModules(Long parentModuleId, List<Long> ids,String className, Long status);

	@Query(value = "SELECT m from com.sterp.multitenant.tenant.modules.entity.Modules m where m.parentId is null and m.status=:status order by m.displayOrder ASC")
	List<Modules> findCustom(Long status);
	
	@Query(value = "SELECT m from com.sterp.multitenant.tenant.modules.entity.Modules m where m.parentId is null and m.className is null and m.id IN :ids and m.status=:status order by m.displayOrder ASC")
	List<Modules> findCustomChildModule(Long status, List<Long> ids);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.modules.response.dto.SearchModuleFilterDTO(mod.id,mod.path,mod.apiPath,mod.title,mod.icon) "
			+ "from com.sterp.multitenant.tenant.modules.entity.Modules mod where mod.status=:status and mod.title like concat('%',:wildCard,'%')")
	List<SearchModuleFilterDTO> findCustomSearchByTitle(String wildCard, Long status);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(ms.id, ms.title)"
			+ " from com.sterp.multitenant.tenant.modules.entity.Modules ms WHERE ms.className IS NULL and ms.status=:status")
	List<CommonDropdownResponseDataTravelObject> getLastActiveChildModuleList(Long status);

	List<Modules> findByStatus(Long status);
	
	@Query("SELECT new com.sterp.multitenant.tenant.modules.dto.ModuleDto(ms.id, ms.title, ms.path, ms.apiPath, ms.moduleType) FROM "
			+ "com.sterp.multitenant.tenant.modules.entity.Modules ms")
	Page<ModuleDto> findCustom(Pageable pageable);
	
	
	@Query("SELECT new com.sterp.multitenant.tenant.modules.dto.ModuleDto(ms.id, ms.title, ms.path, ms.apiPath, ms.moduleType) FROM "
			+ "com.sterp.multitenant.tenant.modules.entity.Modules ms WHERE "
			+ "ms.title like concat('%',:wildCard,'%') OR "
			+ "ms.path like concat('%',:wildCard,'%') OR "
			+ "ms.apiPath like concat('%',:wildCard,'%') OR "
			+ "ms.moduleType like concat('%',:wildCard,'%')")
	Page<ModuleDto> findCustomSearch(@Param("wildCard") String wildCard, Pageable pageable);
	
	@Query("SELECT m FROM com.sterp.multitenant.tenant.modules.entity.Modules m WHERE m.id IN :moduleIds")
	Set<Modules> getAllModulesById(@Param("moduleIds") List<Long> moduleIds);
	
	
	public Modules findByTitle(String title);
}
