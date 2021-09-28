package com.sterp.multitenant.mastertenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.mastertenant.entity.Country;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto(ctry.id,ctry.countryName)"
			+ " from com.sterp.multitenant.mastertenant.entity.Country ctry where ctry.status=:status")
	List<CommonDropdownResponseDto> findActiveDataCommonDropDownList(Long status);
}
