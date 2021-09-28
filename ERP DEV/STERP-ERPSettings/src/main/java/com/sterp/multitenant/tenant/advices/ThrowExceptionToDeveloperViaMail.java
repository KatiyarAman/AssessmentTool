//package com.sterp.multitenant.tenant.advices;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import com.sterp.multitenant.tenant.exceptionhandler.ExceptionResponse;
//
//@Aspect
//@Order(2)
//@Component
//public class ThrowExceptionToDeveloperViaMail {
//
//	@AfterReturning(pointcut = "execution(* com.sterp.multitenant.exceptionhandler.ExceptionHandlerController.*(..))", returning = "retVal")
//	public void logAroundServiceImplementationMethods(JoinPoint joinPoint, Object retVal) throws Throwable {
//		Object[] methodParameters = joinPoint.getArgs();
//		ExceptionResponse exceptionResponse = new ExceptionResponse();
//		for (Object object : methodParameters) {
//			if (object instanceof HttpServletRequest) {
//				HttpServletRequest request = (HttpServletRequest) object;
//				exceptionResponse.setPath(request.getRequestURI());
//			}
//			if (object instanceof Exception) {
//				Exception exception = (Exception) object;
//				exceptionResponse.setError(exception.getLocalizedMessage());
//				System.out.println(object);
//			}
//		}
//	}
//}
