package com.ankur.keepurl.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ankur.keepurl.annotation.Cached;
import com.ankur.keepurl.annotation.EvictCache;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class CacheManager {
    
    private Map<String, Object> cache = new HashMap<>();
    
    @Around("@annotation(cachedMethod)")
    public Object cacheCheck(ProceedingJoinPoint joinPoint, Cached cachedMethod) throws Throwable {
	String key = validateAndComputeKey(joinPoint.getArgs(), cachedMethod.keyArgumentIndex());
	if (cache.containsKey(key)) {
	    return cache.get(key);
	}
	Object object = joinPoint.proceed();
	cache.put(key, object);
	return object;
    }
    
    @After("@annotation(cachedMethod)")
    public void cacheEvict(JoinPoint joinPoint, EvictCache cachedMethod) throws Throwable {
	cache.remove(validateAndComputeKey(joinPoint.getArgs(), cachedMethod.keyArgumentIndex()));
    }
    
    private static String validateAndComputeKey(Object[] args, int keyArgumentIndex) {
	if (keyArgumentIndex < 0) {
	    throw new IllegalArgumentException("Invalid method argument index for key");
	}
	if (args.length < 1 && !(args[keyArgumentIndex] instanceof String)) {
	    throw new IllegalArgumentException("Method not applicable for the annotation @Cached");
	}
	String key = (String) args[keyArgumentIndex];
	if (!StringUtils.hasLength(key)) {
	    throw new IllegalArgumentException("Invalid key. Key must not be empty for cache");
	}
	return key;
    }
    
    @After("@annotation(com.ankur.keepurl.annotation.EvictAll)")
    public void evictAll() {
	log.info("Evicting cache");
	cache.clear();
    }

}
