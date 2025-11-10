package com.tenpo.pruebatecnica.business.external;

import com.tenpo.pruebatecnica.errors.exceptions.PercentageConsumerException;
import com.tenpo.pruebatecnica.utils.Constants;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Setter
@Component
@Slf4j
public class PercentageConsumer {

  private int counter = 0;

  /**
   * Mock. Get percentage from "external service".
   *
   * @return integer
   */
  public Integer getPercentage() {
    Integer percentage = Constants.PERCENTAGE;
    if (counter < Constants.MAX_REQUEST) {
      counter = counter + 1;
      return percentage;
    }
    counter = 0;
    log.info("Simulate error in external service... {} %", percentage);
    throw new PercentageConsumerException();
  }

}
