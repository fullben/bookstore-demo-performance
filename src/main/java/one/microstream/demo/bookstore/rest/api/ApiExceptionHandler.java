package one.microstream.demo.bookstore.rest.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}
