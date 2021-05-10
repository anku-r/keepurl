package com.ankur.keepurl.app.logging;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ankur.keepurl.logic.exception.RequestNotFoundException;

@Aspect
@Component
public class ComicsAppLogger {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComicsAppLogger.class);
	
	@Pointcut("execution (* com.ankur.keepurl.logic.api.impl.*.*(..))")
	public void servicePackage() {}
	
	@Before("servicePackage()")	
	public void beforeMethod(JoinPoint joinPoint) {
		
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		String args = "";
		for (Object arg : joinPoint.getArgs()) {
			 args = ", " + arg.toString();
		}
		LOGGER.info("Entering Method [" + signature + args + "]");
	}
	
	@AfterReturning(pointcut = "servicePackage()", returning = "returnValue")
	public void afterReturningMethod(JoinPoint joinPoint, Object returnValue) {
		
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		if (returnValue instanceof List<?>) {
			LOGGER.info("Exiting Method [" + signature + "]");
		} else {
			LOGGER.info("Exiting Method [" + signature + ", " + returnValue + "]");
		}	
	}
	
	@AfterThrowing(pointcut = "servicePackage()", throwing = "thrownException")
	public void afterException(JoinPoint joinPoint, Throwable thrownException) {
		
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		if (thrownException instanceof RequestNotFoundException) {
			LOGGER.error("Exception on Method [" + signature + ", RequestNotFoundException: URL Details not found]");
		} else {
			LOGGER.error("Exception on Method " + signature, thrownException);
		}
	}
}
