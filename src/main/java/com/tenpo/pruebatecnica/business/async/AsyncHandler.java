package com.tenpo.pruebatecnica.business.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AsyncHandler {

  private final Executor asyncExecutor;

  public CompletableFuture<Void> voidAsync(Runnable runnable) {
    return CompletableFuture.runAsync(() -> innerWrapAsyncHandler(runnable), asyncExecutor);
  }

  private void innerWrapAsyncHandler(Runnable runnable) {
    try {
      runnable.run();
    } catch (RuntimeException exception) {
      throw new CompletionException(exception);
    }
  }
}
