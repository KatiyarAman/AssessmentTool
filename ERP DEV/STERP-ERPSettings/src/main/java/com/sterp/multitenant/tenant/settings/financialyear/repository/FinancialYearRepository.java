package com.sterp.multitenant.tenant.settings.financialyear.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sterp.multitenant.tenant.settings.financialyear.entity.CompanyYear;

public interface FinancialYearRepository extends JpaRepository<CompanyYear, Long> {
	
	@Query("from CompanyYear where yearType=:yearType and branchId=:branchId order by id")
	public List<CompanyYear> getByFilter(String yearType, Long branchId);

	
//	@Async
//	@Query("select cy from CompanyYear cy")
//	public List<CompanyYear> findAllCompanyYearAsync();

}
