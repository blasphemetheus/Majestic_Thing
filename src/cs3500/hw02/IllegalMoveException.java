package cs3500.hw02;

/**
 * An exception extending the interface IllegalArgumentException that denotes an error with a move.
 */
public class IllegalMoveException extends IllegalArgumentException {
  public IllegalMoveException() {
    super();
  }

  public IllegalMoveException(String message) {
    super(message);
  }

  public IllegalMoveException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalMoveException(Throwable cause) {
    super(cause);
  }
}
