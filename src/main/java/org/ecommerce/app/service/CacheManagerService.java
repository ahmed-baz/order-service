package org.ecommerce.app.service;

public interface CacheManagerService {

    void clearCacheByKey(String cacheName, String key);

}