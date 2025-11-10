package com.tenpo.pruebatecnica.web;

import com.tenpo.pruebatecnica.web.models.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/request-history")
public interface RequestHistoryApi2 {

  @GetMapping
  ResponseEntity<PageResponse> listRequestHistory(Pageable pageable);
}
