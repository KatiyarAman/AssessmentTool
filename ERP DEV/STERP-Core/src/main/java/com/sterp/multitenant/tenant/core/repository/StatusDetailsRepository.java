package com.sterp.multitenant.tenant.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.model.StatusDetails;

@Repository
public interface StatusDetailsRepository extends JpaRepository<StatusDetails, Long> {
	
//	StatusDetails findByValue(Integer status);
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(sd.id,sd.description)"
			+ " from com.sterp.multitenant.tenant.model.StatusDetails sd")
	List<CommonDropdownResponseDataTravelObject> getSelectionList();
}
