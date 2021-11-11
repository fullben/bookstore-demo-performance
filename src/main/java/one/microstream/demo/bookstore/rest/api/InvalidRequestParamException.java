package one.microstream.demo.bookstore.rest.api;

public class InvalidRequestParamException extends RuntimeException {

  static final long serialVersionUID = 1L;
  private final String requestParamName;

  public InvalidRequestParamException(String requestParamName) {
    super();
    this.requestParamName = requestParamName;
  }

  public String getRequestParamName() {
    return requestParamName;
  }
}
