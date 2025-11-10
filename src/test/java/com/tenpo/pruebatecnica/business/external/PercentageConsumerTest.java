package com.tenpo.pruebatecnica.business.external;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tenpo.pruebatecnica.errors.exceptions.PercentageConsumerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PercentageConsumerTest {

  @InjectMocks
  private PercentageConsumer percentageConsumer;

  @Test
  void testGetPercentageOk() {
    assertDoesNotThrow(() -> percentageConsumer.getPercentage());
  }

  @Test
  void testGetPercentageError() {
    percentageConsumer.setCounter(3);
    assertThrows(PercentageConsumerException.class, () -> percentageConsumer.getPercentage());
  }
}