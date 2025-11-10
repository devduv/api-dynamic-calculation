package com.tenpo.pruebatecnica.web.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.tenpo.pruebatecnica.business.services.DynamicCalculationService;
import com.tenpo.pruebatecnica.errors.exceptions.CacheNotExistsException;
import com.tenpo.pruebatecnica.web.models.DynamicCalculationResponse;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class NumberApiImplTest {

  @Mock
  private DynamicCalculationService dynamicCalculationService;

  @InjectMocks
  private NumberApiImpl numberApi;

  @Test
  void testGetDynamicCalculationOk() {
    BigDecimal num1 = new BigDecimal("10");
    BigDecimal num2 = new BigDecimal("20");

    DynamicCalculationResponse expected = new DynamicCalculationResponse(new BigDecimal("30"));

    when(dynamicCalculationService.calculate(any(BigDecimal.class), any(BigDecimal.class)))
        .thenReturn(expected);

    ResponseEntity<DynamicCalculationResponse> actual = numberApi.getDynamicCalculation(num1, num2);
    assertDoesNotThrow(() -> actual);
    assertEquals(expected , actual.getBody());
  }

  @Test
  void testGetDynamicCalculationError() {
    BigDecimal num1 = new BigDecimal("10");
    BigDecimal num2 = new BigDecimal("20");

    when(dynamicCalculationService.calculate(any(BigDecimal.class), any(BigDecimal.class)))
        .thenThrow(CacheNotExistsException.class);

    assertThrows(CacheNotExistsException.class, () -> numberApi.getDynamicCalculation(num1, num2));
  }
}