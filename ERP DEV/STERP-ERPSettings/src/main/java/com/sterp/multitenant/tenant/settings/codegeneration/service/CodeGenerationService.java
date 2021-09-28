package com.sterp.multitenant.tenant.settings.codegeneration.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sterp.multitenant.tenant.dto.CommonDropdownResponseDto;
import com.sterp.multitenant.tenant.settings.codegeneration.dto.CodeGenerationRequestDto;
import com.sterp.multitenant.tenant.settings.codegeneration.entity.CodeGeneration;


public interface CodeGenerationService {

	public abstract Map<String, Object> addCodeGeneration(CodeGenerationRequestDto codeDto, HttpServletRequest httpServletRequest);

	public abstract Map<String, Object> updateCodeGeneration(CodeGenerationRequestDto codeDto, HttpServletRequest httpServletRequest);

	public abstract <T> String generateCode(Class<T> T, Long docId, HttpServletRequest httpServletRequest, Long moduleId,
			CommonDropdownResponseDto field, int modulePrefix, long refDocId);

	public abstract boolean codeGenerationExist(long moduleId, int customFieldId, long modulePrefix);

	CodeGeneration getByModuleIdCustomFieldIdModuleRefFieldId(
			long moduleId, int customFieldId, long modulePrefix);


	
}
