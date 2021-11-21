package one.microstream.demo.bookstore.rest.service;

/**
 * Thrown whenever a service or other component fails to find a resource based on some condition or
 * provided parameter.
 *
 * @author Benedikt Full
 */
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -6338486823541308897L;

  public ResourceNotFoundException(String msg) {
    super(msg);
  }
}
