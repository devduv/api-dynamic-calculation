package com.tenpo.pruebatecnica.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class PercentageUtilTest {


  @ParameterizedTest
  @CsvSource(value = {"50:0.50", "20:0.20", "1:0.01"}, delimiter = ':')
  void testConvertToRate(Integer value, BigDecimal expected) {
    BigDecimal actual = PercentageUtil.convertToRate(value);

    assertEquals(expected, actual);
  }
}