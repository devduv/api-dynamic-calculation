package com.tenpo.pruebatecnica.business.external;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

import com.tenpo.pruebatecnica.errors.exceptions.PercentageConsumerException;
import com.tenpo.pruebatecnica.utils.RandomUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PercentageConsumerTest {

  @InjectMocks
  private PercentageConsumer percentageConsumer;

  @Test
  void testGetPercentageOk() {
    try (MockedStatic<RandomUtil> mocked = mockStatic(RandomUtil.class)) {
      mocked.when(RandomUtil::getRandomNumber).thenReturn(95);
      assertDoesNotThrow(() -> percentageConsumer.getPercentage());

    }
  }

  @Test
  void testGetPercentageError() {
    try (MockedStatic<RandomUtil> mocked = mockStatic(RandomUtil.class)) {
      mocked.when(RandomUtil::getRandomNumber).thenReturn(20);
      assertThrows(PercentageConsumerException.class, () -> percentageConsumer.getPercentage());

    }
  }
}