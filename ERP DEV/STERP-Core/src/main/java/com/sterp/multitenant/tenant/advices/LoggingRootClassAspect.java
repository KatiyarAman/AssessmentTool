package com.sterp.multitenant.tenant.advices;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import com.amazonaws.services.logs.model.ResourceNotFoundException;
import com.sterp.multitenant.mastertenant.entity.MasterTenant;
import com.sterp.multitenant.mastertenant.service.MasterTenantService;
import com.sterp.multitenant.tenant.core.services.AppService;
import com.sterp.multitenant.tenant.service.UserService;

@Aspect
@Order(1)
@Component
public final class LoggingRootClassAspect {

	private static Logger logger = LoggerFactory.getLogger(LoggingRootClassAspect.class);

	@Autowired
	AppService appService;

	@Autowired
	MasterTenantService masterTenantService;

	@Autowired
	UserService userService;

	@Before("com.sterp.multitenant.tenant.advices.LoggingAspects.logUserRequestBeforeMethodExecutionAPIEndPoint()")
	public void checkPermission(JoinPoint joinPoint) throws AccessDeniedException, ClassNotFoundException,
			NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException,
			InstantiationException, InvocationTargetException, NoSuchMethodException {
		this.logIncomingRequest(joinPoint);
	}

	@Before("com.sterp.multitenant.tenant.advices.LoggingAspects.logControllersForUserParametersAndMethodCallsOnERPSettingsModules()")
	public void checkPermissionAndLogsForERPSettings(JoinPoint joinPoint) throws AccessDeniedException,
			ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		this.logIncomingRequest(joinPoint);
	}

	@AfterReturning(pointcut = "execution(* com.sterp.multitenant.tenant.*.service.*.*(..))", returning = "retVal")
	public void logAroundServiceImplementationMethods(JoinPoint joinPoint, Object retVal) throws Throwable {
		this.logOutGoingRequests(joinPoint, retVal);
	}

	@AfterReturning(pointcut = "execution(* com.sterp.multitenant.tenant.settings.*.service.*.*(..))", returning = "retVal")
	public void logAroundServiceImplementationMethodsERPSettings(JoinPoint joinPoint, Object retVal) throws Throwable {
		this.logOutGoingRequests(joinPoint, retVal);
	}

	private void logOutGoingRequests(JoinPoint joinPoint, Object retVal) {
		logger.info("main class point reference with complete path: " + joinPoint.getThis());
		logger.info("method signature on class Reference - " + joinPoint.getThis() + " : "
				+ joinPoint.getSignature().getName());
		logger.info("Successfull Termination of Program with Return Data : " + retVal);
	}

	private void logIncomingRequest(JoinPoint joinPoint) {
		MasterTenant masterTenant = null;
		Object[] methodParameters = joinPoint.getArgs();
		for (Object param : methodParameters) {
			if (param instanceof HttpServletRequest) {
				HttpServletRequest httpServletRequest = (HttpServletRequest) param;
				logger.info("Requesting Tenant Id: " + httpServletRequest.getAttribute("audience") + " Username: "
						+ SecurityContextHolder.getContext().getAuthentication().getName());
				logger.info("Request URI : " + httpServletRequest.getRequestURL());
				int tenantId = Integer.parseInt(String.valueOf(httpServletRequest.getAttribute("audience") == null ? 0
						: httpServletRequest.getAttribute("audience")));
				masterTenant = this.masterTenantService.getById(tenantId);
			}
			logger.info("recieving parameters for method " + joinPoint.getSignature().getName() + "for class path "
					+ joinPoint.getThis() + " : " + param);
		}
		logger.info("main class point reference with complete path: " + joinPoint.getThis());
		logger.info("method signature: " + joinPoint.getSignature().getName());
		if (joinPoint.getSignature().getName().equalsIgnoreCase("createUser")) {
			logger.info("User creation check with maximum allowed user for current tenant......");
			if (masterTenant == null) {
				throw new ResourceNotFoundException(
						"Please contact service provided as we cannot find your registered tenant Information.");
			} else {
				if (masterTenant.getClientage().getMaxUserAllowed() == null) {
					throw new ResourceAccessException("Your have exceeded your user creation limit");
				}
				int maxUserAllowed = masterTenant.getClientage().getMaxUserAllowed();
				int currentUserCount = this.userService.getCount().intValue();
				if (currentUserCount < maxUserAllowed) {
					logger.info("Allowed for User creation. Limit not exceeded.Current : " + currentUserCount
							+ ". Left: " + (maxUserAllowed - currentUserCount) + ". Max allowed: " + maxUserAllowed);
				} else {
					logger.info("Not allowed for User creation. Limit exceeded");
					throw new ResourceAccessException(
							"Your have exceeded your user creation limit. Please contact service provider or upgrade your package.");
				}
			}
		}
	}
}
