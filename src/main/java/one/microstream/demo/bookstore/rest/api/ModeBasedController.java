package one.microstream.demo.bookstore.rest.api;

/**
 * Base class for controllers that have a mode switch for using either a JPA-based or a
 * MicroStream-based approach.
 *
 * @author Benedikt Full
 */
public abstract class ModeBasedController {

  protected static final String MODE_MS = "ms";
  protected static final String MODE_JPA = "jpa";

  String requireValidMode(String mode) {
    if (!isValidMode(mode)) {
      throw new InvalidRequestParamException("mode");
    }
    return mode;
  }

  boolean isValidMode(String mode) {
    return MODE_MS.equals(mode) || MODE_JPA.equals(mode);
  }

  boolean isMsMode(String mode) {
    return MODE_MS.equals(mode);
  }

  boolean isJpaMode(String mode) {
    return MODE_JPA.equals(mode);
  }
}
