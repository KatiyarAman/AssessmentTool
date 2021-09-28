package com.sterp.multitenant.tenant.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.model.StatutoryDetails;

public interface StatutoryDetailsRepository extends JpaRepository<StatutoryDetails, Long> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(detail.id,detail.labelName)"
				+ " from com.sterp.multitenant.tenant.model.StatutoryDetails detail where detail.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);

}
