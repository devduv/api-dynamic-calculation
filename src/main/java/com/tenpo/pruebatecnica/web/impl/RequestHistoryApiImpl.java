package com.tenpo.pruebatecnica.web.impl;

import com.tenpo.pruebatecnica.business.services.RequestService;
import com.tenpo.pruebatecnica.web.RequestHistoryApi;
import com.tenpo.pruebatecnica.web.models.PageResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RequestHistoryApiImpl implements RequestHistoryApi {

  private RequestService requestService;

  /**
   * List request history.
   *
   * @param page Número de página. (required)
   * @param size Cantidad de elementos de la página (required)
   * @return list request history paginated
   */
  @Override
  public ResponseEntity<PageResponse> listRequestHistory(Integer page, Integer size) {
    return new ResponseEntity<>(requestService
        .listRequestHistory(Pageable.ofSize(size).withPage(page)), HttpStatus.OK);
  }
}
