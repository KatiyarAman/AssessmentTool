package com.sterp.multitenant.tenant.modules.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.modules.entity.Actions;

public interface ActionsRepository extends CrudRepository<Actions, Long>{
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(actions.id,actions.actions)"
			+ " from com.sterp.multitenant.tenant.modules.entity.Actions actions where actions.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);
}
 