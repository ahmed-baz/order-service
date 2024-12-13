package org.ecommerce.app.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.ecommerce.app.service.CacheManagerService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@AllArgsConstructor
public class CacheManagerServiceImpl implements CacheManagerService {

    private final CacheManager cacheManager;

    public void clearCacheByKey(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        } else {
            log.info("Cache '{}' not found.", cacheName);
        }
    }

}