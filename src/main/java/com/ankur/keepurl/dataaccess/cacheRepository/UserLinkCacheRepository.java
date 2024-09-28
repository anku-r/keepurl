package com.ankur.keepurl.dataaccess.cacheRepository;

import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dto.UserLinkDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserLinkCacheRepository extends CacheRepository<List<UserLinkDTO>> {

    @Override
    public Class<?> getCacheEntity() {
        return UserLink.class;
    }

    @Override
    public String getKey(Object entity) {
        return ((UserLink) entity).getUser();
    }
}
