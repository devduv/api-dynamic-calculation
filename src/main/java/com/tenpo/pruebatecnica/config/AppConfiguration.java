package com.tenpo.pruebatecnica.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.tenpo.pruebatecnica.utils.Constants;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@AllArgsConstructor
@Slf4j
public class AppConfiguration {

  private final ConfigProperties configProperties;

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager(Constants.PERCENTAGE_CACHE);
    cacheManager.setCaffeine(Caffeine.newBuilder()
        .expireAfterWrite(configProperties.getMaxExpirationTime(), TimeUnit.MINUTES)
        .removalListener((key, value, cause)
            -> {
          if (!cause.toString().equals("REPLACED")) {
            log.info("Percentage: {} was eliminated after {} minute(s)", value,
                configProperties.getMaxExpirationTime());
          }
        }));
    return cacheManager;
  }

  @Bean(name = "asyncExecutor")
  public Executor asyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(5);
    executor.setMaxPoolSize(10);
    executor.setQueueCapacity(20);
    executor.setThreadNamePrefix("AsyncThread");
    executor.initialize();
    return executor;
  }
}
