package cs3500.hw02;

/**
 * An exception extending the interface IllegalArgumentException that denotes an error with Card.
 */
public class IllegalCardException extends IllegalArgumentException {
  public IllegalCardException() {
    super();
  }

  public IllegalCardException(String message) {
    super(message);
  }

  public IllegalCardException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalCardException(Throwable cause) {
    super(cause);
  }
}
