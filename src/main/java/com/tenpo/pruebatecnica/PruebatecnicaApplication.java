package com.tenpo.pruebatecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PruebatecnicaApplication {

  public static void main(String[] args) {
    SpringApplication.run(PruebatecnicaApplication.class, args);
  }

}
