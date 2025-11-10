package com.tenpo.pruebatecnica.utils;

import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtil {
  private static final Random random = new Random();

  /**
   * Get random number from 0 to 100.
   *
   * @return integer number
   */
  public static Integer getRandomNumber() {
    return random.nextInt(101);
  }
}
