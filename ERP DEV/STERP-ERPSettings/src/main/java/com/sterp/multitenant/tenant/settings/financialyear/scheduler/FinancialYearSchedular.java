//package com.sterp.multitenant.tenant.settings.financialyear.scheduler;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.TreeMap;
//
//import javax.sql.DataSource;
//
//import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.sterp.multitenant.mastertenant.entity.MasterTenant;
//import com.sterp.multitenant.mastertenant.repository.MasterTenantRepository;
//import com.sterp.multitenant.tenant.common.service.CommonService;
//import com.sterp.multitenant.tenant.settings.financialyear.entity.CompanyYear;
//import com.sterp.multitenant.tenant.settings.financialyear.repository.FinancialYearRepository;
//import com.sterp.multitenant.utils.DataSourceUtil;
//
//@Component
//public class FinancialYearSchedular  {
//	
//	@Autowired
//	CommonService commonService;
//	
//	@Autowired
//	FinancialYearRepository fyRepository;
//	
//	@Autowired
//	MasterTenantRepository masterTenantRepository;
//	
//	private Map<String, DataSource> dataSourcesMtApp = new TreeMap<>();
//	
//	@Scheduled(cron = "0 09 12 * * ? ")
//	public void createFinancialYear() {
//		List<MasterTenant> masterTenants = masterTenantRepository.findAll();
//		for (MasterTenant masterTenant : masterTenants) {
//			
//		}
//	
//	}
//
//	
//}
