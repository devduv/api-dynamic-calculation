package com.tenpo.pruebatecnica.business.services.impl;

import com.tenpo.pruebatecnica.business.mappers.RequestHistoryMapper;
import com.tenpo.pruebatecnica.business.services.RequestService;
import com.tenpo.pruebatecnica.business.wrappers.RequestHistoryWrapper;
import com.tenpo.pruebatecnica.repository.RequestHistoryRepository;
import com.tenpo.pruebatecnica.repository.entities.RequestHistoryEntity;
import com.tenpo.pruebatecnica.web.models.PageResponse;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

  private final RequestHistoryRepository requestHistoryRepository;
  private final RequestHistoryMapper requestHistoryMapper =
      Mappers.getMapper(RequestHistoryMapper.class);

  /**
   * Save request history entity.
   *
   * @param requestHistoryWrapper request history wrapper
   */
  @Override
  public void saveRequest(RequestHistoryWrapper requestHistoryWrapper) {
    RequestHistoryEntity requestHistoryEntity = requestHistoryMapper
        .mapToRequestHistoryEntity(requestHistoryWrapper);
    requestHistoryRepository.save(requestHistoryEntity);
    log.info("Request history saved by async");
  }

  /**
   * List request history.
   *
   * @return list request history entity
   */
  @Override
  public PageResponse listRequestHistory(Pageable pageable) {
    log.info("List request history");
    return Optional.of(requestHistoryRepository.findRequestHistoryEntities(pageable))
        .map(entities -> entities.map(requestHistoryMapper::mapToRequestHistoryResponse))
        .map(page -> new PageResponse(page.getTotalPages(),
            (int) page.getTotalElements(), page.getContent()))
        .orElse(new PageResponse(0, 0, null));
  }
}
