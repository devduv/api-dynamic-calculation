package com.tenpo.pruebatecnica.business.services;

import com.tenpo.pruebatecnica.business.wrappers.RequestHistoryWrapper;
import com.tenpo.pruebatecnica.web.models.PageResponse;
import org.springframework.data.domain.Pageable;

public interface RequestService {

  void saveRequest(RequestHistoryWrapper requestHistoryWrapper);

  PageResponse listRequestHistory(Pageable pageable);
}
