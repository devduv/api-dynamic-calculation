package com.tenpo.pruebatecnica.business.services.impl;

import com.tenpo.pruebatecnica.business.async.AsyncHandler;
import com.tenpo.pruebatecnica.business.services.DynamicCalculationService;
import com.tenpo.pruebatecnica.business.services.PercentageService;
import com.tenpo.pruebatecnica.business.services.RequestService;
import com.tenpo.pruebatecnica.business.wrappers.RequestHistoryWrapper;
import com.tenpo.pruebatecnica.errors.exceptions.CacheNotExistsException;
import com.tenpo.pruebatecnica.utils.Constants;
import com.tenpo.pruebatecnica.web.models.DynamicCalculationResponse;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class DynamicCalculationServiceImpl implements DynamicCalculationService {

  private final AsyncHandler asyncHandler;
  private final RequestService requestService;
  private final PercentageService percentageService;

  /**
   * Calculate num1 with num2 and add additional percentage, then save request history.
   *
   * @param num1 first number
   * @param num2 second number
   * @return BigDecimal
   */
  @Override
  public DynamicCalculationResponse calculate(BigDecimal num1, BigDecimal num2) {
    BigDecimal result;
    try {
      BigDecimal percentage = percentageService.getPercentage();
      BigDecimal sum = num1.add(num2);
      BigDecimal additionalPercentage = sum.multiply(percentage);
      result = sum.add(additionalPercentage);

      registerRequest(num1, num2, percentage, result, Constants.OK);

      log.info("Dynamic calculation result: {}", result);

    } catch (CacheNotExistsException ex) {
      registerRequest(num1, num2, null, null, Constants.ERROR);
      throw ex;
    }

    return buildDynamicCalculationResponse(result);
  }

  /**
   * Buildl dynamic calculation response.
   *
   * @param result result
   * @return DynamicCalculationResponse
   */
  private DynamicCalculationResponse buildDynamicCalculationResponse(BigDecimal result) {
    return DynamicCalculationResponse.builder().result(result).build();
  }

  /**
   * Register request.
   *
   * @param num1       num1
   * @param num2       num2
   * @param percentage percentage
   * @param response   response (final result)
   * @param status     status (OK or ERROR)
   */
  private void registerRequest(BigDecimal num1, BigDecimal num2, BigDecimal percentage,
                               BigDecimal response, String status) {
    RequestHistoryWrapper requestHistoryWrapper = RequestHistoryWrapper.builder()
        .endpoint(Constants.DYNAMIC_CALCULATION_ENDPOINT)
        .parameters(List.of(num1, num2))
        .percentage(percentage)
        .response(response)
        .status(status).build();

    asyncHandler.voidAsync(() -> requestService.saveRequest(requestHistoryWrapper));
  }

}
