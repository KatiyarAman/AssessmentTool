package com.sterp.multitenant.tenant.exceptionhandler.service;

import com.sterp.multitenant.tenant.exceptionhandler.ExceptionResponse;

public interface ExceptionService {
	public void storeExceptionDeveloperZone(ExceptionResponse exceptionResponse, String tenantInfo);
}
