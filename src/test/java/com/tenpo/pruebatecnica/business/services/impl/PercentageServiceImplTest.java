package com.tenpo.pruebatecnica.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tenpo.pruebatecnica.business.external.PercentageConsumer;
import com.tenpo.pruebatecnica.business.services.CacheService;
import com.tenpo.pruebatecnica.errors.exceptions.CacheNotExistsException;
import com.tenpo.pruebatecnica.errors.exceptions.PercentageConsumerException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PercentageServiceImplTest {

  @Mock
  private CacheService cacheService;
  @Mock
  private PercentageConsumer percentageConsumer;
  @InjectMocks
  private PercentageServiceImpl percentageService;

  @ParameterizedTest
  @CsvSource(value = {"50:0.50", "60:0.60", "15:0.15"}, delimiter = ':')
  void testGetPercentageWhenPercentageConsumerIsOk(Integer percentageValue, String percentageRate) {

    when(percentageConsumer.getPercentage()).thenReturn(percentageValue);

    BigDecimal actual = percentageService.getPercentage();

    assertEquals(new BigDecimal(percentageRate), actual);

    verify(cacheService, times(1)).put(any(), any());
    verify(cacheService, times(0)).get(any());
  }

  @Test
  void testGetPercentageWhenPercentageConsumerIsErrorAndCacheExists() {
    Integer percentageValue = 20;

    when(percentageConsumer.getPercentage()).thenThrow(PercentageConsumerException.class);
    when(cacheService.get(any())).thenReturn(percentageValue);

    BigDecimal actual = percentageService.getPercentage();

    assertEquals(new BigDecimal("0.20"), actual);

    verify(cacheService, times(0)).put(any(), any());
    verify(cacheService, times(1)).get(any());
  }

  @Test
  void testGetPercentageWhenPercentageConsumerIsErrorAndCacheNotExists() {
    when(percentageConsumer.getPercentage()).thenThrow(PercentageConsumerException.class);
    when(cacheService.get(any())).thenReturn(null);

    assertThrows(CacheNotExistsException.class, () -> percentageService.getPercentage());

    verify(cacheService, times(0)).put(any(), any());
    verify(cacheService, times(1)).get(any());
  }
}