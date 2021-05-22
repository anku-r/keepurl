package com.ankur.keepurl.app.logging;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class KeepURLAppLogger {
	
	@Pointcut("execution (* com.ankur.keepurl.manager.api.*.*(..))")
	public void servicePackage() {}
	
	@Pointcut("execution (* com.ankur.keepurl.security.api.impl.*.*(..))")
	public void securityPackage() {}
	
	@Around("servicePackage() || securityPackage()")
	public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
		
		final Logger LOGGER = LoggerFactory
							.getLogger(joinPoint.getTarget().getClass());
		
		String methodName = ((MethodSignature) joinPoint
							.getSignature()).getMethod().getName();;
		
		String args = "";
		for (Object arg : joinPoint.getArgs()) {
			 args = ", " + arg.toString();
		}
		
		LOGGER.info("Entering Method [" + methodName + args + "]");
		
		try {
			
			Object returnValue = joinPoint.proceed();
			
			if (returnValue instanceof List<?>) {
				LOGGER.info("Exiting Method [" + methodName + ", " 
									+ ((List<?>) returnValue).size() + " Objects received]");
			} else {
				LOGGER.info("Exiting Method [" + methodName + ", " + returnValue + "]");
			}
			
			return returnValue;
		
		} catch (Throwable e) {
			
			LOGGER.error("Exception on Method " + methodName, e);
			throw e.getCause();
		}		
	}
}
