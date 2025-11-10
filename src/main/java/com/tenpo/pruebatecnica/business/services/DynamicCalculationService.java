package com.tenpo.pruebatecnica.business.services;

import com.tenpo.pruebatecnica.web.models.DynamicCalculationResponse;
import java.math.BigDecimal;

public interface DynamicCalculationService {

  DynamicCalculationResponse calculate(BigDecimal num1, BigDecimal num2);
}
