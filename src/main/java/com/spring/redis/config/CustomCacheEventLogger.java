package com.spring.redis.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomCacheEventLogger implements CacheEventListener<Object, Object> {
 
  @Override
  public void onEvent(@SuppressWarnings("rawtypes") CacheEvent cacheEvent) {
    log.info("Cache event = {}, Key = {},  이전 값 = {}, 현재 값 = {}", cacheEvent.getType(),
        cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
  }
}
