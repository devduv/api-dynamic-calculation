package com.tenpo.pruebatecnica.business.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@ExtendWith(MockitoExtension.class)
class CacheServiceImplTest {

  @Mock
  private Cache cache;
  @Mock
  private CacheManager cacheManager;
  private CacheServiceImpl cacheService;

  @BeforeEach
  void setUp() {
    when(cacheManager.getCache(any())).thenReturn(cache);
    cacheService = new CacheServiceImpl(cacheManager);
  }

  @Test
  void testPutCache() {
    assertDoesNotThrow(() -> cacheService.put("percentage", 20));
  }

  @Test
  void get() {
    assertDoesNotThrow(() -> cacheService.get("percentage"));
  }
}