package com.tenpo.pruebatecnica.business.services;

public interface CacheService {

  void put(String key, Integer value);

  Integer get(String key);
}
