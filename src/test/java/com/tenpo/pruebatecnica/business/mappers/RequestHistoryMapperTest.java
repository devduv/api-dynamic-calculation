package com.tenpo.pruebatecnica.business.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tenpo.pruebatecnica.business.wrappers.RequestHistoryWrapper;
import com.tenpo.pruebatecnica.repository.entities.RequestHistoryEntity;
import com.tenpo.pruebatecnica.web.models.RequestHistoryResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RequestHistoryMapperTest {
  private final RequestHistoryMapper mapper = Mappers.getMapper(RequestHistoryMapper.class);

  @Test
  void testMapToRequestHistoryResponse() {
    RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
    requestHistoryEntity.setDate(LocalDateTime.now());
    requestHistoryEntity.setEndpoint("/v1/numbers");
    requestHistoryEntity.setStatus("OK");
    requestHistoryEntity.setParameters("30,70");
    requestHistoryEntity.setPercentage(new BigDecimal("0.10"));
    requestHistoryEntity.setResponse(new BigDecimal("110.00"));


    RequestHistoryResponse actual = mapper.mapToRequestHistoryResponse(requestHistoryEntity);

    assertEquals("/v1/numbers", actual.getEndpoint());
    assertEquals(new BigDecimal("110.00"), actual.getResponse());
    assertEquals("OK", actual.getStatus());
    assertEquals(new BigDecimal("30"), actual.getParameters().get("num1"));
  }

  @Test
  void testMapToRequestHistoryEntity() {

    RequestHistoryWrapper wrapper = RequestHistoryWrapper.builder()
        .response(new BigDecimal("120.00"))
        .percentage(new BigDecimal("0.2"))
        .endpoint("/v1/numbers")
        .parameters(List.of(new BigDecimal(70), new BigDecimal(30)))
        .build();
    RequestHistoryEntity actual = mapper.mapToRequestHistoryEntity(wrapper);

    assertEquals("/v1/numbers", actual.getEndpoint());
    assertEquals("70,30", actual.getParameters());
  }
}