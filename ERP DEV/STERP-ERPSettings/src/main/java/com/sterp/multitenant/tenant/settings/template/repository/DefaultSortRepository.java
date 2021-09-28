package com.sterp.multitenant.tenant.settings.template.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.settings.template.entity.DefaultSort;

@Repository
public interface DefaultSortRepository extends JpaRepository<DefaultSort, Long>{
	List<DefaultSort> findByModuleId(long moduleId);
}
