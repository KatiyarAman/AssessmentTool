package com.sterp.multitenant.tenant.assesmenttool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType;
import com.sterp.multitenant.tenant.assesmenttool.entity.dto.IndustryTypeDto;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

@Repository
public interface IndustryTypeRepository extends JpaRepository<IndustryType, Long> {
	
	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject(industryType.id,industryType.name)"
			+ " from com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType industryType where industryType.status=:status")
	List<CommonDropdownResponseDataTravelObject> getSelectionList(Long status);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.IndustryTypeDto(it.id, it.name,it.description, it.status) from "
			+ "com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType it "
			+ "where it.id like concat('%',:wildCard,'%') or it.name like concat('%',:wildCard,'%') ")
	Page<IndustryTypeDto> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.assesmenttool.entity.dto.IndustryTypeDto(it.id, it.name, it.description, it.status) from "
			+ " com.sterp.multitenant.tenant.assesmenttool.entity.IndustryType it")
	Page<IndustryTypeDto> findCustom(Pageable pageable);
}



