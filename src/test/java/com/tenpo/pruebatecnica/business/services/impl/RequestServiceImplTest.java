package com.tenpo.pruebatecnica.business.services.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tenpo.pruebatecnica.business.mappers.RequestHistoryMapper;
import com.tenpo.pruebatecnica.business.wrappers.RequestHistoryWrapper;
import com.tenpo.pruebatecnica.repository.RequestHistoryRepository;
import com.tenpo.pruebatecnica.repository.entities.RequestHistoryEntity;
import com.tenpo.pruebatecnica.web.models.PageResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class RequestServiceImplTest {

  private final RequestHistoryMapper requestHistoryMapper =
      Mappers.getMapper(RequestHistoryMapper.class);
  @Mock
  private RequestHistoryRepository requestHistoryRepository;
  @Mock
  private Pageable pageable;
  @Mock
  private Page<RequestHistoryEntity> page;
  @InjectMocks
  private RequestServiceImpl requestService;

  @Test
  void saveRequest() {
    RequestHistoryWrapper wrapper = RequestHistoryWrapper.builder()
        .parameters(List.of(new BigDecimal("10"), new BigDecimal("20")))
        .endpoint("/v1")
        .status("OK").build();
    assertDoesNotThrow(() -> requestService.saveRequest(wrapper));

    verify(requestHistoryRepository, times(1)).save(any());
  }

  @Test
  void listRequestHistory() {
    RequestHistoryEntity requestHistoryEntity = new RequestHistoryEntity();
    requestHistoryEntity.setDate(LocalDateTime.now());
    requestHistoryEntity.setEndpoint("/v1/numbers");
    requestHistoryEntity.setStatus("OK");
    requestHistoryEntity.setParameters("30,70");
    requestHistoryEntity.setPercentage(new BigDecimal("0.10"));
    requestHistoryEntity.setResponse(new BigDecimal("110.00"));

    when(requestHistoryRepository.findRequestHistoryEntities(any())).thenReturn(page);

    PageResponse actual = requestService.listRequestHistory(pageable);

    assertNotNull(actual);
  }
}