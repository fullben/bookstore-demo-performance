package one.microstream.demo.bookstore.rest.api;

import one.microstream.demo.bookstore.rest.service.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Provides exception handling for certain exceptions expected to be encountered in the API.
 *
 * @author Benedikt Full
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {InvalidRequestParamException.class})
  protected ResponseEntity<Object> handleConflict(
      InvalidRequestParamException ex, WebRequest request) {
    return handleExceptionInternal(
        ex,
        "Invalid value for parameter " + ex.getRequestParamName(),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST,
        request);
  }

  @ExceptionHandler(value = {ResourceNotFoundException.class})
  protected ResponseEntity<Object> handleConflict(
      ResourceNotFoundException ex, WebRequest request) {
    return handleExceptionInternal(
        ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }
}
