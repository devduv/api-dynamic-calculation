package com.tenpo.pruebatecnica.business.services.impl;

import com.tenpo.pruebatecnica.business.services.CacheService;
import com.tenpo.pruebatecnica.utils.Constants;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheServiceImpl implements CacheService {

  private final Cache cache;

  public CacheServiceImpl(final CacheManager cacheManager) {
    this.cache = cacheManager.getCache(Constants.PERCENTAGE_CACHE);
  }

  /**
   * Save in cache value, where value has an integer and a current time millis.
   *
   * @param key   key
   * @param value value
   */
  @Override
  public void put(String key, Integer value) {
    this.cache.put(key, value);
  }

  @Override
  public Integer get(String key) {
    return this.cache.get(key, Integer.class);
  }
}
