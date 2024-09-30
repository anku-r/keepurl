package com.ankur.keepurl.dataaccess.cacheRepository;

import com.ankur.keepurl.dataaccess.document.UserData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserCacheRepository extends CacheRepository<UserDetails> {
    @Override
    public Class<?> getDatabaseEntity() {
        return UserData.class;
    }

    @Override
    public String getKey(Object entity) {
        return ((UserData) entity).getUsername();
    }
}
