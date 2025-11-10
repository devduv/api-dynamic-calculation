package com.tenpo.pruebatecnica.business.external;

import com.tenpo.pruebatecnica.errors.exceptions.PercentageConsumerException;
import com.tenpo.pruebatecnica.utils.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PercentageConsumer {

  /**
   * Mock. Get percentage from "external service" using random.
   *
   * @return integer
   */
  public Integer getPercentage() {
    Integer percentage = RandomUtil.getRandomNumber();
    if (percentage % 2 == 0) {
      log.info("Simulate error in external service... {} %", percentage);
      throw new PercentageConsumerException();
    }
    return percentage;
  }
}
