package com.sterp.multitenant.tenant.branch.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.branch.dto.BranchResponseDTO;
import com.sterp.multitenant.tenant.branch.entity.Branch;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDataTravelObject;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.dto."
			+ "CommonDropdownResponseDataTravelObject(branch.id,branch.branchName)"
			+ " from com.sterp.multitenant.tenant.branch.entity.Branch branch where branch.status = 2")
	List<CommonDropdownResponseDataTravelObject> getSelectionList();

	@Query(value = "SELECT new com.sterp.multitenant.tenant.branch.dto.BranchResponseDTO(br.id,tp.type,br.branchName,br.branchCode,br.shortName,"
			+ "zn.zoneName,br.cityId,br.address,br.email,br.description,br.description,br.contactPerson,br.phone,br.mobile,br.pincode,br.website,br.status) from "
			+ "com.sterp.multitenant.tenant.branch.entity.Branch br "
			+ "left outer join com.sterp.multitenant.tenant.model.Type tp on tp.id=br.type "
			+ "left outer join com.sterp.multitenant.tenant.zone.entity.Zone zn on zn.id=br.zoneId "
			+ "where br.id like concat('%',:wildCard,'%') or br.branchName like concat('%',:wildCard,'%') or "
			+ "br.address like concat('%',:wildCard,'%') ")
	Page<BranchResponseDTO> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.branch.dto.BranchResponseDTO(br.id,tp.type,br.branchName,br.branchCode,br.shortName,"
			+ "zn.zoneName,br.cityId,br.address,br.email,br.description,br.description,br.contactPerson,br.phone,br.mobile,br.pincode,br.website,br.status) from "
			+ "com.sterp.multitenant.tenant.branch.entity.Branch br "
			+ "left outer join com.sterp.multitenant.tenant.model.Type tp on tp.id=br.type "
			+ "left outer join com.sterp.multitenant.tenant.zone.entity.Zone zn on zn.id=br.zoneId")
	Page<BranchResponseDTO> findCustom(Pageable pageable);

}
