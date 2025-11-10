package com.tenpo.pruebatecnica.business.wrappers;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RequestHistoryWrapper {

  private String endpoint;
  private List<BigDecimal> parameters;
  private BigDecimal percentage;
  private BigDecimal response;
  private String status;
}
