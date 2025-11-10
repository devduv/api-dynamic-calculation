package com.tenpo.pruebatecnica.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "request_history")
public class RequestHistoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "date")
  private LocalDateTime date;

  @Column(name = "endpoint")
  private String endpoint;

  @Column(name = "parameters")
  private String parameters;

  @Column(name = "percentage")
  private BigDecimal percentage;

  @Column(name = "response")
  private BigDecimal response;

  @Column(name = "status")
  private String status;
}
