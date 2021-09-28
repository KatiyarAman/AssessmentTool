package com.sterp.multitenant.tenant.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sterp.multitenant.tenant.company.dto.CompanyDTO;
import com.sterp.multitenant.tenant.company.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query(value = "SELECT new com.sterp.multitenant.tenant.company.dto.CompanyDTO(comp.id,comp.companyCode,comp.companyName,"
			+ "comp.address,comp.cityId,comp.companyEmail,comp.phone,comp.pinCode,comp.status,comp.msmeReg,"
			+ "comp.msmeRegNo,comp.createdBy,comp.modifiedBy,comp.createdDate,comp.modifiedDate) from "
			+ "com.sterp.multitenant.tenant.company.entity.Company comp "
			+ "where comp.id like concat('%',:wildCard,'%') or comp.companyCode like concat('%',:wildCard,'%') or comp.companyName like concat('%',:wildCard,'%') or "
			+ "comp.address like concat('%',:wildCard,'%') ")
	Page<CompanyDTO> findCustomSearch(String wildCard, Pageable pageable);

	@Query(value = "SELECT new com.sterp.multitenant.tenant.company.dto.CompanyDTO(comp.id,comp.companyCode,comp.companyName,"
			+ "comp.address,comp.cityId,comp.companyEmail,comp.phone,comp.pinCode,comp.status,comp.msmeReg,"
			+ "comp.msmeRegNo,comp.createdBy,comp.modifiedBy,comp.createdDate,comp.modifiedDate) from "
			+ "com.sterp.multitenant.tenant.company.entity.Company comp")
	Page<CompanyDTO> findCustom(Pageable pageable);
}
