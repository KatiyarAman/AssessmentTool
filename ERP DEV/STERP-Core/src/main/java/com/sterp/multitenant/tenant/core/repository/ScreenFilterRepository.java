package com.sterp.multitenant.tenant.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.settings.template.entity.ScreenFilter;
import com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO;

@Repository
public interface ScreenFilterRepository extends JpaRepository<ScreenFilter, Long> {
	//List<ScreenFilter> findByUserIdAndModuleId(long userId, long moduleId);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.settings.template.entity.dto.ScreenFilterDTO("
			+ " filter.id, filter.userId, filter.fieldIndexingId, filter.filterValue,"
			+ " filter.hidden, filter.moduleId, filter.filterRange,filter.sortFlag,filter.sortOrder,fI)"
			+ " from com.sterp.multitenant.tenant.settings.template.entity.ScreenFilter filter"
			+ " left outer join com.sterp.multitenant.tenant.settings.template.entity.FieldIndexing fI on filter.fieldIndexingId = fI.id")
	List<ScreenFilterDTO> findAllByUserIdAndModuleId(long userId, long moduleId);
}
 