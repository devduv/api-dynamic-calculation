package com.tenpo.pruebatecnica.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties
public class ConfigProperties {

  @Value("${max-expiration-time}")
  private Integer maxExpirationTime;
}
