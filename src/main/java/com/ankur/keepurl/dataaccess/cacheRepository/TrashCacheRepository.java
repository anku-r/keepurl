package com.ankur.keepurl.dataaccess.cacheRepository;

import com.ankur.keepurl.dataaccess.document.Trash;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrashCacheRepository extends CacheRepository<List<Trash>> {

    @Override
    public Class<?> getCacheEntity() {
        return Trash.class;
    }

    @Override
    public String getKey(Object object) {
        return ((Trash) object).getUser();
    }

}
