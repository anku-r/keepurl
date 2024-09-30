package com.ankur.keepurl.dataaccess.cacheRepository;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class CacheRepository<T> {

    private Map<String, T> cache = new HashMap<>();

    public abstract Class<?> getDatabaseEntity();

    public abstract String getKey(Object entity);

    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    public T get(String key) {
        return cache.get(key);
    }

    public void put(String key, Object data) {
        cache.put(key, (T) data);
    }

    public void delete(String key) {
        cache.remove(key);
    }

}
