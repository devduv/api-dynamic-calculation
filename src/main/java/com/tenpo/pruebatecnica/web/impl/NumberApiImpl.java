package com.tenpo.pruebatecnica.web.impl;

import com.tenpo.pruebatecnica.business.services.DynamicCalculationService;
import com.tenpo.pruebatecnica.web.NumbersApi;
import com.tenpo.pruebatecnica.web.models.DynamicCalculationResponse;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NumberApiImpl implements NumbersApi {

  private DynamicCalculationService dynamicCalculationService;

  /**
   * Get dynamic calculation mum1 and num2 add a percentage.
   *
   * @param num1 num1
   * @param num2 num2
   * @return ResponseEntity BigDecimal
   */
  @Override
  public ResponseEntity<DynamicCalculationResponse> getDynamicCalculation(BigDecimal num1, BigDecimal num2) {
    DynamicCalculationResponse response = dynamicCalculationService.calculate(num1, num2);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
