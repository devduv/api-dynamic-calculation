package com.tenpo.pruebatecnica.repository;

import com.tenpo.pruebatecnica.repository.entities.RequestHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RequestHistoryRepository extends CrudRepository<RequestHistoryEntity, String> {

  @Query("SELECT e FROM RequestHistoryEntity e")
  Page<RequestHistoryEntity> findRequestHistoryEntities(Pageable pageable);
}
