package com.sterp.multitenant.tenant.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.model.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long>{
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(area.id,area.area)"
			+ " from com.sterp.multitenant.tenant.model.Area area where area.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);
	
}
