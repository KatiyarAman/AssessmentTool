package com.sterp.multitenant.tenant.zone.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;
import com.sterp.multitenant.tenant.zone.dto.ZoneResponseDTO;
import com.sterp.multitenant.tenant.zone.entity.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

	List<Zone> getZoneById(Long zoneId);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(zone.id,zone.zoneName)"
			+ " from com.sterp.multitenant.tenant.zone.entity.Zone zone where zone.status= 2")
	List<CommonDropdownResponseDataTravelObject> getSelectionList();

	@Query(value = "SELECT new com.sterp.multitenant.tenant.zone.dto.ZoneResponseDTO(zn.id,zn.zoneCode,zn.zoneName,zn.description,zn.status) from "
			+ "com.sterp.multitenant.tenant.zone.entity.Zone zn "
			+ "where zn.id like concat('%',:wildCard,'%') or zn.zoneName like concat('%',:wildCard,'%') or "
			+ "zn.description like concat('%',:wildCard,'%') ")
	Page<ZoneResponseDTO> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.zone.dto.ZoneResponseDTO(zn.id,zn.zoneCode,zn.zoneName,zn.description,zn.status) from "
			+ "com.sterp.multitenant.tenant.zone.entity.Zone zn")
	Page<ZoneResponseDTO> findCustom(Pageable pageable);

}
