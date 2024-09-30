package com.ankur.keepurl.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ankur.keepurl.dataaccess.cacheRepository.CacheRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ankur.keepurl.annotation.Cached;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Aspect
@Component
@Slf4j
@SuppressWarnings("unchecked")
public class CacheManager {

	@Autowired
	private List<CacheRepository<?>> cacheRepositories;

	private final Map<Class<?>, CacheRepository<?>> cacheMap = new HashMap<>();

	@Pointcut("execution (* com.ankur.keepurl.dataaccess.repository.*.save(..))")
	public void saveEntity() {}

	@Pointcut("execution (* com.ankur.keepurl.dataaccess.repository.*.delete(..))")
	public void deleteEntity() {}

	@Pointcut("execution (* com.ankur.keepurl.dataaccess.repository.*.deleteAll(..))")
	public void deleteMultipleEntities() {}

	@PostConstruct
	private void init() {
		log.info("Initializing Cache Repositories: {}", cacheRepositories);
		for (CacheRepository<?> cacheRepository : cacheRepositories) {
			cacheMap.put(cacheRepository.getDatabaseEntity(), cacheRepository);
		}
	}

    @Around("@annotation(cachedMethod)")
    public Object cacheCheck(ProceedingJoinPoint joinPoint, Cached cachedMethod) throws Throwable {
		if (!cacheMap.containsKey(cachedMethod.databaseEntity())) {
			log.info("Cache is not set up for provided entity");
			return joinPoint.proceed();
		}
		String key = getKeyFromArguments(joinPoint, cachedMethod.keyArgumentIndex());
		CacheRepository<?> cache = cacheMap.get(cachedMethod.databaseEntity());
		if (cache.contains(key)) {
			log.debug("Cache Hit {}: {}", cachedMethod.databaseEntity(), key);
			return cache.get(key);
		}
		log.debug("Cache Miss {}: {}", cachedMethod.databaseEntity(), key);
		Object object = joinPoint.proceed();
		cache.put(key, object);
		return object;
    }

	@After("saveEntity() || deleteEntity()")
	public void evictCacheOnEntityOperation(JoinPoint joinPoint) {
		Object entity = joinPoint.getArgs()[0];
		evictCache(entity);
	}

	@After("deleteMultipleEntities()")
	public void evictCacheOnCollectionOperation(JoinPoint joinPoint) {
		List<?> entityList = (List<?>) joinPoint.getArgs()[0];
		entityList.forEach(this::evictCache);
	}

	private void evictCache(Object entity) {
		if (!cacheMap.containsKey(entity.getClass())) {
			log.info("Cache is not set up for provided entity");
			return;
		}
		log.debug("Evicting Cache: {}", entity);
		CacheRepository<?> cache = cacheMap.get(entity.getClass());
		cache.delete(cache.getKey(entity));
	}

	private static String getKeyFromArguments(ProceedingJoinPoint joinPoint, int keyArgumentIndex) {
		if (keyArgumentIndex < 0) {
			throw new IllegalArgumentException("Invalid method argument index for key");
		}
		Object[] args = joinPoint.getArgs();
		if (args.length < 1 || !(args[keyArgumentIndex] instanceof String)) {
			throw new IllegalArgumentException("Method not applicable for the annotation @Cached");
		}
		String key = (String) args[keyArgumentIndex];
		if (!StringUtils.hasLength(key)) {
			throw new IllegalArgumentException("Invalid key. Key must not be empty for cache");
		}
		return key;
	}

}
