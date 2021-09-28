package com.sterp.multitenant.tenant.advices;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class AutoDraftSerialization {
	@AfterReturning(pointcut = "execution(* com.sterp.multitenant.tenant.*.*(..))", returning = "retVal")
	public void logAroundServiceImplementationMethods(JoinPoint joinPoint, Object retVal) throws Throwable {
		System.out.println("Hello");
	}
}
