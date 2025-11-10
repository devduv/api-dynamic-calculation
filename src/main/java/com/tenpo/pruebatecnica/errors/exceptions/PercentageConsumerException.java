package com.tenpo.pruebatecnica.errors.exceptions;

import com.tenpo.pruebatecnica.utils.Constants;

public class PercentageConsumerException extends RuntimeException {

  public PercentageConsumerException() {
    super(Constants.PERCENTAGE_CONSUMER_ERROR);
  }
}
