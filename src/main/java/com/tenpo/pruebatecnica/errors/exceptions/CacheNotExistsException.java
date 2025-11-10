package com.tenpo.pruebatecnica.errors.exceptions;

import com.tenpo.pruebatecnica.utils.Constants;

public class CacheNotExistsException extends RuntimeException {

  public CacheNotExistsException() {
    super(Constants.CACHE_EMPTY);
  }
}
