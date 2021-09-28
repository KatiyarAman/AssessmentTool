package com.sterp.multitenant.tenant.advices;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sterp.multitenant.tenant.common.service.CommonService;
import com.sterp.multitenant.tenant.exceptionhandler.CustomException;
import com.sterp.multitenant.tenant.settings.template.entity.CustomField;
import com.sterp.multitenant.tenant.settings.template.entity.FieldValidations;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
@Component
public class DataValidation {
	
	@Autowired CommonService commonService;
	
	private static final Logger LOG = LoggerFactory.getLogger(DataValidation.class);
	
	@Before("execution(* com.sterp.multitenant.tenant.common.service.impl.CommonServiceImpl.addUpdate(..))")
	private void validationCheck(JoinPoint thisJoinPoint){
        Object[] args = thisJoinPoint.getArgs();
        long moduleId = Long.parseLong(((HttpServletRequest) args[0]).getHeader("moduleId"));
//        validate(moduleId, args[1], null);
        return;
    }
	
	private void validate(long moduleId, Object object, Integer parentFieldId) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("module", moduleId);	filters.put("parentFieldId", parentFieldId); 
		List<CustomField> validationFields = commonService.getByFilters(CustomField.class, filters);
        Class<? extends Object> clazz = object.getClass();
        
        validationFields.forEach((validationField) -> {
        	if(validationField != null) {
        		if("required".equals(validationField.getIsMandatory())) {
        			String fieldName = validationField.getFieldIndexing().getDataFieldName();
        			Integer id = validationField.getId();
        			requiredValidation(getFieldValue(clazz, fieldName, object, moduleId, id), fieldName,
        					validationField.getErrorMessage());
        		}
        		
        		if(validationField.getFieldValidations().size() > 0) {
        			String fieldName = validationField.getFieldIndexing().getDataFieldName();
        			Integer id = validationField.getId();
        			validationField.getFieldValidations().forEach((validation) -> {
        				switch(validation.getValidationName()) {
        					case "maxlength": maxLengthValidation(getFieldValue(clazz, fieldName, object, moduleId, id), fieldName, validation); break;
        					case "minlength": minLengthValidation(getFieldValue(clazz, fieldName, object, moduleId, id), fieldName, validation); break;
        					case "pattern"  : patterenValidation (getFieldValue(clazz, fieldName, object, moduleId, id), fieldName, validation); break;
        				}
        			});
        		}
        	}
        });
	}
	
	private void requiredValidation(String data, String fieldName, String errorMessage) {
		if(data == null || data.length() < 1)
			validationMessage(fieldName, errorMessage);
	}
	
	private void maxLengthValidation(String data, String fieldName, FieldValidations validation) {
		if(data.length() > Integer.valueOf(validation.getMatchCase()))
			validationMessage(fieldName, validation.getErrorMessage());
	}
	
	private void minLengthValidation(String data, String fieldName, FieldValidations validation) {
		if(data.length() < Integer.valueOf(validation.getMatchCase()))
			validationMessage(fieldName, validation.getErrorMessage());
	}
	
	private void patterenValidation(String data, String fieldName, FieldValidations validation) {
		Pattern VALID_REGEX = Pattern.compile(validation.getMatchCase(), Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_REGEX.matcher(data);
		if(!matcher.find())
			validationMessage(fieldName, validation.getErrorMessage());
	}
	
	private String getFieldValue(Class<? extends Object> clazz, String fieldName, Object object, long moduleId, Integer parentFieldId) {
		Field field = null;
		String data = null;
		try {
			field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);	
			if(field.getType().getPackage().toString().contains("com.sterp.multitenant.tenant"))
				validate(moduleId, field.get(object), parentFieldId);
			else
				data = (String) field.get(object); 
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			LOG.error(fieldName + " Field not found in : " + clazz.getSimpleName());
		}
		return data;
	}
	
	private void validationMessage(String fieldName, String errorMessage) {
		throw new CustomException(fieldName + " " + errorMessage);
	}
}


