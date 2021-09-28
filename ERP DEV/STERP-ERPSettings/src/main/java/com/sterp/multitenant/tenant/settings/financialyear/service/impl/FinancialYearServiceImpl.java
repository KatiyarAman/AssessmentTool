package com.sterp.multitenant.tenant.settings.financialyear.service.impl;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.settings.financialyear.entity.CompanyYear;
import com.sterp.multitenant.tenant.settings.financialyear.repository.FinancialYearRepository;
import com.sterp.multitenant.tenant.settings.financialyear.service.FinancialYearService;
import com.sterp.multitenant.tenant.settings.questionnaire.entity.QuestionnaireSettings;

@Service
public class FinancialYearServiceImpl implements FinancialYearService {

	@Autowired
	CommonService commonService;
	
	@Autowired
	FinancialYearRepository fyRepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Map<String, Object> add(QuestionnaireSettings setting) {
		CompanyYear year = new CompanyYear();
		Map<String, Object> response = new HashedMap<String, Object>();
		LocalDate localDate = LocalDate.now();
		int month = Integer.valueOf(setting.getValue());
		String yearType = setting.getField().getFieldName().equals("fiscal_year") ? "fiscalYear" : "financialYear";
		year.setId(0L);
		year.setYearType(yearType);
		year.setStartDate(generateStartDate(localDate, month));
		year.setEndDate(generateEndDate(year, year.getStartDate()));
		year.setGracePeriod(0);
		year.setCreatedBy(setting.getCreatedBy());
		year.setModifiedBy(setting.getModifiedBy());
		year.setBranchId(setting.getBranchId());
		year.setCompanyId(setting.getCompanyId());
		response.put("data", fyRepo.save(year));
		response.put("message", setting.getClass().getSimpleName() + " Add Successfully");
		return response;
	}

	@Override
	public Map<String, Object> update(QuestionnaireSettings setting) {
		Map<String, Object> response = new HashMap<String, Object>();
		String yearType = setting.getField().getFieldName().equals("fiscal_year") ? "fiscalYear" : "financialYear";
		List<CompanyYear> years = fyRepo.getByFilter(yearType, setting.getBranchId());
		CompanyYear year = years.get(0);
		int month = Integer.valueOf(setting.getValue());
		LocalDate localDate = LocalDate.now().withMonth(month);
		year.setEndDate(generateEndDate(year, localDate));				// Modify Existing fy end date
		response.put("data", fyRepo.save(year));
		response.put("message", setting.getClass().getSimpleName() + " Update Successfully");
		
		/* New entry for financial/fiscal year */
		CompanyYear newYear = new CompanyYear();
		newYear.setId(year.getId());
		newYear.setYearType(yearType);
		newYear.setGracePeriod(year.getGracePeriod());
		newYear.setCreatedBy(year.getCreatedBy());
		newYear.setCreatedDate(year.getCreatedDate());
		newYear.setModifiedBy(setting.getModifiedBy());
		newYear.setBranchId(year.getBranchId());
		newYear.setCompanyId(year.getCompanyId());
		newYear.setStartDate(generateStartDate(year.getEndDate(), 0));
		
		if(years.size() > 1) {
			newYear.setEndDate(generateEndDate(year, null));
			newYear.setId(years.get(1).getId());
			fyRepo.save(newYear);
			//commonService.update(newYear);
		}else {
			newYear.setEndDate(generateEndDate(year, null));
			newYear.setId(0L);
			fyRepo.save(newYear);
			//commonService.add(newYear);
		}
		return response;
	}
	
	
	/* Logic for calculate financial/fiscal year start date 
	 * @param LocalDate currentDate, int month
	 * @currentDate object of java.time.LocalDate in format -> (yyyy-dd-mm)
	 * @month month value in int
	 * return result in form of java.time.LocalDate in format -> (yyyy-dd-mm)
	 * */
	private LocalDate generateStartDate(LocalDate currentDate, int month) {
		return (month == 0) ? currentDate.withMonth(((currentDate.getMonthValue() + 1) == 13 ? 1 : currentDate.getMonthValue() + 1))
				.with(TemporalAdjusters.firstDayOfMonth()) 
			: currentDate.withMonth(month).with(TemporalAdjusters.firstDayOfMonth());
	}

	/* Logic for calculate financial/fiscal year end date
	 * @param CompanyYear year, LocalDate date
	 * @year object of previous generated financial/fiscal year
	 * @date object of java.time.LocalDate in format -> (yyyy-dd-mm) or null reference
	 * return result in form of java.time.LocalDate in format -> (yyyy-dd-mm)
	 *  */
	private LocalDate generateEndDate(CompanyYear year, LocalDate date) {
		if(year.getId() != 0) {
			if(date == null){
				LocalDate currentDate = year.getEndDate();
				return (currentDate.getYear() == year.getStartDate().getYear()) ? currentDate.plusYears(1).with(TemporalAdjusters.lastDayOfMonth())
						: currentDate.plusYears(1).withMonth(((currentDate.getMonthValue() - 1) == 0 ? 12 : currentDate.getMonthValue() - 1))
							.with(TemporalAdjusters.lastDayOfMonth());
			}else {
				LocalDate currentDate = date.with(TemporalAdjusters.lastDayOfMonth());
				return (currentDate.getMonthValue() < year.getStartDate().getMonthValue()) ? currentDate.plusYears(1) : currentDate;
			}
		}else {
			return date.withMonth(((date.getMonthValue() - 1) == 0 ? 12 : date.getMonthValue() - 1)).plusYears(1).with(TemporalAdjusters.lastDayOfMonth());
		}
	}
	
}
