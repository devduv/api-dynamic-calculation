package com.tenpo.pruebatecnica.business.services.impl;

import com.tenpo.pruebatecnica.business.external.PercentageConsumer;
import com.tenpo.pruebatecnica.business.services.CacheService;
import com.tenpo.pruebatecnica.business.services.PercentageService;
import com.tenpo.pruebatecnica.errors.exceptions.CacheNotExistsException;
import com.tenpo.pruebatecnica.errors.exceptions.PercentageConsumerException;
import com.tenpo.pruebatecnica.utils.Constants;
import com.tenpo.pruebatecnica.utils.PercentageUtil;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PercentageServiceImpl implements PercentageService {

  private final CacheService cacheService;
  private final PercentageConsumer percentageConsumer;

  /**
   * Get percentage.
   *
   * @return BigDecimal
   */
  @Override
  public BigDecimal getPercentage() {
    return getPercentageRate();
  }

  /**
   * Get percentage value from percentage consumer and convert to rate.
   *
   * @return BigDecimal
   */
  private BigDecimal getPercentageRate() {
    Integer percentageValue;
    try {
      percentageValue = percentageConsumer.getPercentage();
      log.info("Percentage from external service: {} %", percentageValue);
      cacheService.put(Constants.PERCENTAGE_VALUE, percentageValue);
    } catch (PercentageConsumerException ex) {
      log.error("Error get percentage from external service, consulting cache");
      percentageValue = cacheService.get(Constants.PERCENTAGE_VALUE);

      if (percentageValue == null) {
        log.error("Not exists value in cache, cache is empty");
        throw new CacheNotExistsException();
      }
    }

    return PercentageUtil.convertToRate(percentageValue);
  }

}
