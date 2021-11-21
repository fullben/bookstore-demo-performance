package one.microstream.demo.bookstore.rest.api;

/**
 * Thrown whenever an API endpoint encounters an invalid value for an expected parameter.
 *
 * @author Benedikt Full
 */
public class InvalidRequestParamException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final String requestParamName;

  public InvalidRequestParamException(String requestParamName) {
    super();
    this.requestParamName = requestParamName;
  }

  public String getRequestParamName() {
    return requestParamName;
  }
}
