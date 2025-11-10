package com.tenpo.pruebatecnica.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PercentageUtil {

  /**
   * Convert percentage value to rate.
   *
   * @param percentageValue percentage value
   * @return BigDecimal
   */
  public static BigDecimal convertToRate(Integer percentageValue) {
    return BigDecimal.valueOf(percentageValue)
        .divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN);
  }
}
