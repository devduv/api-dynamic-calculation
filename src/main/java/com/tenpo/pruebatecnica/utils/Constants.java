package com.tenpo.pruebatecnica.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

  public static final String PERCENTAGE_CACHE = "PERCENTAGE_CACHE";
  public static final String PERCENTAGE_VALUE = "PERCENTAGE_VALUE";
  public static final String DYNAMIC_CALCULATION_ENDPOINT = "/v1/numbers/dynamic-calculation";
  public static final String CACHE_EMPTY = "Not exists percentage because cache is empty";
  public static final String PERCENTAGE_CONSUMER_ERROR =
      "Error get percentage from external service";
  public static final String ERROR = "ERROR";
  public static final String OK = "OK";
  public static final String FIRST_PARAMETER = "num1";
  public static final String SECOND_PARAMETER = "num2";
}
