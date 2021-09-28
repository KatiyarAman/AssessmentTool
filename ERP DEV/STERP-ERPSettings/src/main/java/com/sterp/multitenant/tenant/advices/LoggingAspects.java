package com.sterp.multitenant.tenant.advices;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspects {
	@Pointcut("execution(* com.sterp.multitenant.tenant.*.controller.*.*(..))")
	public void logUserRequestBeforeMethodExecutionAPIEndPoint() {
	}

	@Pointcut("execution(* com.sterp.multitenant.tenant.controller.*.*(..))")
	public void logControllersForUserParametersAndMethodCalls() {
	}

	@Pointcut("execution(* com.sterp.multitenant.tenant.settings.*.controller.*.*(..))")
	public void logControllersForUserParametersAndMethodCallsOnERPSettingsModules() {
	}

	@Pointcut("execution(* com.sterp.*.service.*(..))")
	public void checkReturnMethodAfterReturningMethodExecutionAPIEndPoint() {
	}
	
	
}
