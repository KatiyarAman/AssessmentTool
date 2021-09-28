package com.sterp.multitenant.mastertenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.mastertenant.entity.State;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto(st.id,st.stateName)"
			+ " from com.sterp.multitenant.mastertenant.entity.State st join st.country where st.country.id=:countryId and st.status=:status")
	List<CommonDropdownResponseDto> findActiveDataCommonDropDownListByCountryId(int countryId, Long status);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto(st.id,st.stateName)"
			+ " from com.sterp.multitenant.mastertenant.entity.State st join st.country where st.status=:status")
	List<CommonDropdownResponseDto> findActiveDataCommonDropDownList(Long status);

}
