package com.sterp.multitenant.tenant.settings.codegeneration.service.impl;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sterp.multitenant.tenant.branch.entity.Branch;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.company.entity.Company;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto;
import com.sterp.multitenant.tenant.entity.UserEntity;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.settings.codegeneration.dto.CodeGenerationRequestDto;
import com.sterp.multitenant.tenant.settings.codegeneration.entity.CodeGeneration;
import com.sterp.multitenant.tenant.settings.codegeneration.repository.CodeGenerationRepository;
import com.sterp.multitenant.tenant.settings.codegeneration.service.CodeGenerationService;
import com.sterp.multitenant.tenant.zone.entity.Zone;

@Service
public class CodeGenerationServiceImpl implements CodeGenerationService {

	@Autowired
	CommonService commonService;
	
	@Autowired
	CodeGenerationRepository codeGenerationRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AppService appService;
	
	@Override
	public Map<String, Object> addCodeGeneration(CodeGenerationRequestDto codeDto, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashedMap<String, Object>();
		CodeGeneration code = modelMapper.map(codeDto, CodeGeneration.class);
		UserEntity currentUser = appService.getCurrentUser();
		Long defaultLoginBranch  = Long.valueOf((Integer) httpServletRequest.getAttribute("defaultloginbranch"));
		Long defaultCompanyId =  commonService.getById(Branch.class ,defaultLoginBranch).getCompanyId();
		
		code.setCreatedBy(currentUser.getId());
		code.setModifiedBy(currentUser.getId());
		code.setCompanyId(defaultCompanyId);
		code.setBranchId(defaultLoginBranch);
		//this.codeGenerationRepository.save(code);
		response.put("data", this.codeGenerationRepository.save(code));
		response.put("message", codeDto.getId() == 0 ? "CodeGeneration Added Successfully" : "CodeGeneration Updated Successfully");
		return response;
	}
	
	
	@Override
	public Map<String, Object> updateCodeGeneration(CodeGenerationRequestDto codeDto, HttpServletRequest httpServletRequest) {
		Map<String, Object> response = new HashedMap<String, Object>();
		CodeGeneration code = modelMapper.map(codeDto, CodeGeneration.class);
		UserEntity currentUser = appService.getCurrentUser();
		Long defaultLoginBranch  = Long.valueOf((Integer) httpServletRequest.getAttribute("defaultloginbranch"));
		Long defaultCompanyId =  commonService.getById(Branch.class ,defaultLoginBranch).getCompanyId();
		
		code.setModifiedBy(currentUser.getId());
		code.setCompanyId(defaultCompanyId);
		code.setBranchId(defaultLoginBranch);
		response.put("data", this.codeGenerationRepository.save(code));
		response.put("message","CodeGeneration Updated Successfully");
		return response;
	}
	
	@Override
	public CodeGeneration getByModuleIdCustomFieldIdModuleRefFieldId
				(long moduleId, int customFieldId, long modulePrefix) {
		return codeGenerationRepository.getByModuleIdCustomFieldIdModuleRefFieldId(moduleId, customFieldId, modulePrefix, 1L);
	}
	
	@Override
	public boolean codeGenerationExist
			(long moduleId, int customFieldId, long modulePrefix) {
		CodeGeneration code = codeGenerationRepository.getByModuleIdCustomFieldIdModuleRefFieldId(moduleId, customFieldId, modulePrefix, 1L);
		return (code == null ? false : true);
	}

	
	
	//String financialStartDate = "2020-04-1";			// yyyy-mm-dd
	//String financialEndDate = "2021-03-31";			// yyyy-mm-dd
	
	/*	***********************************Sequence of code format************************************
	 * 	Prefix <> Delimeter <> Other-prefix <> Delimeter <> Module-prefix <> Delimeter <> Sequence No.
	 * 	Delimeter will append with each prefix. Delimeter -> /,//,*,- etc.
	 * 	Prefix will be append as Company/Branch short code if available.
	 * 	Other Prefix will be append as financial-year format -> yy, ddmmyy, ddmm, mmyy, yyyy etc.
	 * 	Module Prefix will be append as Module short code -> PO, GRN, MI, PI, PR etc.
	 * 	Reserve 4 char for sequence no  -> ###i or A-Z##i
	 * 	Total length of code must not exceed by total length of char-count in code-bean.
	 * */
	
	@Override
	public <T> String generateCode(Class<T> T, Long docId,HttpServletRequest httpServletRequest, Long moduleId,
			CommonDropdownResponseDto field, int modulePrefix, long refDocId) {
		Long currentloginBranch  = Long.valueOf((Integer) httpServletRequest.getAttribute("defaultloginbranch"));
		Branch currentBranch =  commonService.getById(Branch.class, currentloginBranch);								
		Company currentCompany =  commonService.getById(Company.class, currentBranch.getCompanyId());					
		String lastGeneratedCode = null;
		String refLastGeneratedCode = null;
		int customFieldId = field.getId();
		
		CodeGeneration codeBean = null;
		CodeGeneration refCodeBean = null;
		/* Check Code Generation Philosophy existence */
		if(codeGenerationExist(moduleId, customFieldId, modulePrefix)) {
			codeBean = getByModuleIdCustomFieldIdModuleRefFieldId(moduleId, customFieldId, modulePrefix);
			if(codeBean.getCodeBase().contentEquals("company"))
				lastGeneratedCode =  commonService.getLastGeneratedCode(T, 0L, currentBranch.getCompanyId(), field.getName());
			else if(codeBean.getCodeBase().contentEquals("branch"))
				lastGeneratedCode =  commonService.getLastGeneratedCode(T, currentloginBranch, 0L, field.getName());
			else
				lastGeneratedCode =  commonService.getLastGeneratedCode(T, 0L, 0L, field.getName());
			if(modulePrefix > 0) {
				CommonDropdownResponseDto refField = commonService.getAutogeneratedFields("CommonDropdownResponseDto", codeBean.getModulePrefix()).get(0);
				if(codeGenerationExist(codeBean.getModulePrefix(), (int)(long)codeBean.getModulePrefixRef(), 0L)) {
					refCodeBean = getByModuleIdCustomFieldIdModuleRefFieldId(moduleId, customFieldId, modulePrefix);
					refLastGeneratedCode =  commonService.getRefLastGeneratedCode(Zone.class, refDocId,refField.getName());
				}else {
					throw new CustomException("Document Not Submitted. Please create Reference Code Generation Philosophy first.");
				}
			}
				
		}else {
			throw new CustomException("Document Not Submitted. Please create Code Generation Philosophy first.");
		}
		/* If code already Generated ---- return that code */
		if(docId > 0) {
			T t =  commonService.getById(T, docId);
			Field field1;
			String code = "";
			try {
				field1 = T.getDeclaredField(field.getName());
				field1.setAccessible(true);
				code = (String) field1.get(t);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			if(code.equals(lastGeneratedCode))
				return lastGeneratedCode;
		}
		
		
		//CodeGeneration codeBean = getByModuleIdCustomFieldIdModuleRefFieldId(moduleId, customFieldId, moduleRefAsPrefix); 
		StringBuilder generatedCode = new StringBuilder();
		Integer startFrom;
		
		if(!(lastGeneratedCode == null || lastGeneratedCode.isEmpty())) {
			if((codeBean.getCodeBase().contentEquals("company") && !( lastGeneratedCode.contains(currentCompany.getCompanyCode()) 
					|| lastGeneratedCode.contains(currentBranch.getBranchCode())) && (commonService.getLastGeneratedCode(T, 0L, 0L, field.getName()) == null))	
					|| (codeBean.getCodeBase().contentEquals("branch") && !(lastGeneratedCode.contains(currentBranch.getBranchCode()) 
					||  lastGeneratedCode.contains(currentCompany.getCompanyCode())) && (commonService.getLastGeneratedCode(T, 0L, 0L, field.getName()) == null)) 	
					|| (codeBean.isFyReset() && !lastGeneratedCode.contains(getFinalcialYearWithFormat(codeBean.getOtherPrefix()))))
				startFrom = 1;
			else {
				String codeForm = "0";
				if(codeBean.getDelimiter()!= null) {
					codeForm = lastGeneratedCode.substring(lastGeneratedCode.lastIndexOf(codeBean.getDelimiter())+1);
				}else {
					codeForm = lastGeneratedCode.substring(codeBean.getPrefix().length());
				}
				if(!Character.isDigit(codeForm.charAt(0)))
					codeForm = codeForm.substring(1);
				startFrom  = Integer.valueOf(codeForm)+1;				//	get last index of code
			}
				
		}else if(commonService.getLastGeneratedCode(T, 0L, 0L, field.getName()) != null) {
			startFrom = 1;
		}else {
			startFrom = Integer.valueOf(codeBean.getStartFrom());
		}
		
		//String seqformat = String.valueOf(startFrom);
		Integer charAvailable = codeBean.getCharCount();					//	max no of char allow in code
		if(codeBean.getCodeFormat().contains("A-Z"))
			charAvailable = charAvailable - codeBean.getCodeFormat().length() + 2;
		else 
			charAvailable = charAvailable - codeBean.getCodeFormat().length();

		/* Apend Company/Branch if Available */
		if(codeBean.getCodeBase().contentEquals("company")) {
			generatedCode.append(currentCompany.getCompanyCode());
			if(codeBean.getDelimiter() != null) {
				generatedCode.append(codeBean.getDelimiter());			//	append company-code here and delimeter
			}
		}else if(codeBean.getCodeBase().contentEquals("branch")) {
			generatedCode.append(currentBranch.getBranchCode());
			if(codeBean.getDelimiter() != null) {
				generatedCode.append(codeBean.getDelimiter());			//	append company-code here and delimeter
			}
		}
		checkCodeCount(generatedCode.length(), charAvailable); 					//	to check code limit not exceded
		
		/* Apend Prefix */
		if(codeBean.getPrefix().length() > 0) {
			generatedCode.append(codeBean.getPrefix());					//	append prefix with delimeter
			if(codeBean.getDelimiter() != null) {
				generatedCode.append(codeBean.getDelimiter());			//	append company-code here and delimeter
			}
		}
		checkCodeCount(generatedCode.length(), charAvailable); 					//	to check code limit not exceded
		
		/* Apend other-prefix (financial year format) */
		if(codeBean.getOtherPrefix()!=null){
			generatedCode.append(getFinalcialYearWithFormat(codeBean.getOtherPrefix()));
			if(codeBean.getDelimiter() != null) {
				generatedCode.append(codeBean.getDelimiter());			//	append company-code here and delimeter
			}
		}
		checkCodeCount(generatedCode.length(), charAvailable); 				//	to check code limit not exceded
		
		/* Apend module-prefix (PO,GRN,PI,PR,) */
		if(codeBean.getModulePrefix() > 0) {
			int refCodeLength = (refCodeBean.getCodeFormat().contains("A-Z") ? (refCodeBean.getCodeFormat().length() - 1) : (refCodeBean.getCodeFormat().length() + 1));
			checkCodeCount(generatedCode.length() + refCodeLength, charAvailable); 				//	to check code limit not exceded
			refLastGeneratedCode = refLastGeneratedCode.substring(refLastGeneratedCode.lastIndexOf(refCodeBean.getDelimiter()) + 1);
			generatedCode.append(refLastGeneratedCode);
			if(codeBean.getDelimiter() != null) {
				generatedCode.append(codeBean.getDelimiter());			//	append company-code here and delimeter
			}
		}
		//checkCodeCount(generatedCode.length(), charAvailable); 				//	to check code limit not exceded
		
		/* Append Sequence number with Given format */
		generatedCode.append(getCodeSequence(codeBean.getCharCount() - charAvailable, 
				codeBean.getCodeFormat(), startFrom));
		checkCodeCount(generatedCode.length(), codeBean.getCharCount()); 				//	to check code limit not exceded
		//response.put("Code", generatedCode);
		return generatedCode.toString();
	}


	private void checkCodeCount(Integer generatedCode, Integer availableCharLimit) {
		if(generatedCode > availableCharLimit)
			throw new CustomException("Code couldn't Generated - Code length exceded ");
	}
	
	
	private String getFinalcialYearWithFormat(String fyDateFormat){
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		String marchDate = currentYear + "-03-31";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date3 = null;
		Date date2 = null;
		try {
			date3 = sdf.parse(getCurrentDateString());
			date2 = sdf.parse(marchDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(date3.before(date2) || (!checkFiscalByDate(getCurrentDateString())))	currentYear-=1;
		String year = "";
		switch (fyDateFormat) {
		case "yy":	
			year = String.valueOf(currentYear).substring(2);	
			break;
		case "mmyy":
			year = (calendar.get(Calendar.MONTH) + 1)+String.valueOf(currentYear).substring(2);
			break;
		case "ddmm":	
			year = String.valueOf(calendar.get(Calendar.DATE)) + String.valueOf(calendar.get(Calendar.MONTH) + 1);	
			break;
		case "ddmmyy":	
			year = String.valueOf(calendar.get(Calendar.DATE)) + String.valueOf(calendar.get(Calendar.MONTH) + 1)
						 + String.valueOf(currentYear).substring(2);	
			break;
		case "yyyy":	
			year = String.valueOf(currentYear);
		case "yy-yy":	
			year = String.valueOf(currentYear).substring(2) + "-" + String.valueOf(currentYear+1).substring(2);
		default:
		}
		
		return year;
	}
	
	private String getCurrentDateString(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	private Boolean checkFiscalByDate(String date){
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR) - (now.get(Calendar.MONTH)+1 < 4?1:0);
		String start=year + "-04-01";
		String end=(year+1) + "-03-31";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		Date date2 = null;
        Date date3 = null;
		try {
			date1 = sdf.parse(start);
			date2 = sdf.parse(end);
	        date3 = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return ((date3.after(date1) || date3.equals(date1)) && date3.before(date2)) || (date3.equals(date1) && date3.equals(date2));
	}
	
	private StringBuilder getCodeSequence(int charCount, String codeFormat, Integer startFrom) {
		StringBuilder sequence = new StringBuilder();
		if (charCount > 0) {
			Integer r = charCount - startFrom.toString().length();
			if (codeFormat.contains("###A")) {
				for (int i = 0; i < r; i++) {
					sequence.append("0");
				}
			} else if (codeFormat.equals("A-Z##1")) {
				for (int i = 0; i < r; i++) {
					if (i == 0) {
						String s = String.valueOf((char) (65 + i));
						sequence.append(s);
					} else {
						sequence.append("0");
					}
				}
			} else if (codeFormat.equals("###i")) {
				sequence.append("");
			}
			sequence.append(startFrom);
		}
		return sequence;
	}



}
