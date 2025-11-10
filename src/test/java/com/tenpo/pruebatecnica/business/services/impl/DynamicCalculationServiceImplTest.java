package com.tenpo.pruebatecnica.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tenpo.pruebatecnica.business.async.AsyncHandler;
import com.tenpo.pruebatecnica.business.services.PercentageService;
import com.tenpo.pruebatecnica.business.services.RequestService;
import com.tenpo.pruebatecnica.errors.exceptions.CacheNotExistsException;
import com.tenpo.pruebatecnica.web.models.DynamicCalculationResponse;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DynamicCalculationServiceImplTest {

  @Mock
  private AsyncHandler asyncHandler;
  @Mock
  private RequestService requestService;
  @Mock
  private PercentageService percentageService;
  @InjectMocks
  private DynamicCalculationServiceImpl dynamicCalculationService;

  @ParameterizedTest
  @CsvSource(value = {
      "10:20:0.60:48.00",
      "30:30:0.50:90.00",
      "80:160:0.90:456.00"}, delimiter = ':')
  void testCalculateWhenPercentageServiceIsOk(BigDecimal num1,
                                              BigDecimal num2,
                                              BigDecimal percentageRate,
                                              BigDecimal expected) {

    when(percentageService.getPercentage()).thenReturn(percentageRate);
    doAnswer(invocation -> {
      Runnable task = invocation.getArgument(0);
      task.run();
      return null;
    }).when(asyncHandler).voidAsync(any(Runnable.class));

    DynamicCalculationResponse actual = dynamicCalculationService.calculate(num1, num2);

    assertEquals(expected, actual.getResult());

    verify(requestService, times(1)).saveRequest(any());
  }

  @Test
  void testCalculateWhenPercentageServiceIsError() {

    BigDecimal num1 = new BigDecimal("10");
    BigDecimal num2 = new BigDecimal("20");

    when(percentageService.getPercentage()).thenThrow(CacheNotExistsException.class);
    doAnswer(invocation -> {
      Runnable task = invocation.getArgument(0);
      task.run();
      return null;
    }).when(asyncHandler).voidAsync(any(Runnable.class));

    assertThrows(CacheNotExistsException.class,
        () -> dynamicCalculationService.calculate(num1, num2));

    verify(requestService, times(1)).saveRequest(any());

  }
}