package one.microstream.demo.bookstore.rest.api;

import java.util.function.Supplier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Utility class providing access to functionality for enriching responses to web requests with
 * certain meta information.
 *
 * @author Benedikt Full
 */
public class ApiResponse {

  public static final String REQUEST_PROCESSING_NANOS_HEADER_NAME = "Processing-Ns";

  private ApiResponse() {
    throw new AssertionError();
  }

  public static ApiResponseBuilder ok() {
    return new ApiResponseBuilder(HttpStatus.OK);
  }

  public static class ApiResponseBuilder {

    private final HttpStatus status;
    private boolean durationHeader;

    private ApiResponseBuilder(HttpStatus status) {
      this.status = status;
      durationHeader = false;
    }

    public ApiResponseBuilder withDurationHeader(boolean include) {
      durationHeader = include;
      return this;
    }

    public ApiResponseBuilder withDurationHeader() {
      return withDurationHeader(true);
    }

    public <T> ResponseEntity<T> toResponseEntity(Supplier<T> task) {
      if (task == null) {
        throw new IllegalArgumentException();
      }
      if (durationHeader) {
        long start = System.nanoTime();
        T value = task.get();
        long end = System.nanoTime();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(REQUEST_PROCESSING_NANOS_HEADER_NAME, "" + (end - start));
        return ResponseEntity.status(status).headers(responseHeaders).body(value);
      } else {
        return ResponseEntity.status(status).body(task.get());
      }
    }
  }
}
