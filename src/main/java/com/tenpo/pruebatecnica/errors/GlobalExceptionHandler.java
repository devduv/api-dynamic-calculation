package com.tenpo.pruebatecnica.errors;

import com.tenpo.pruebatecnica.errors.exceptions.CacheNotExistsException;
import com.tenpo.pruebatecnica.errors.exceptions.PercentageConsumerException;
import com.tenpo.pruebatecnica.web.models.ModelApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {


  /**
   * Handle own exception response entity.
   *
   * @param exception exception
   * @return response entity
   */
  @ExceptionHandler({
      CacheNotExistsException.class,
      PercentageConsumerException.class
  })
  public ResponseEntity<ModelApiException> handleOwnException(Exception exception) {
    return new ResponseEntity<>(
        ModelApiException.builder()
            .code(HttpStatus.BAD_REQUEST.value())
            .message(exception.getMessage())
            .description(exception.getClass().getSimpleName())
            .build(),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle method argument type mismatch exception response entity.
   *
   * @param exception exception
   * @return response entity
   */
  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<ModelApiException> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException exception) {

    String message = String.format("Param '%s' not found", exception.getValue());

    return new ResponseEntity<>(
        ModelApiException.builder()
            .message(message)
            .description(exception.getClass().getSimpleName())
            .build(),
        HttpStatus.BAD_REQUEST);
  }
}
