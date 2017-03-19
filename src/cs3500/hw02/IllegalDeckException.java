package cs3500.hw02;

/**
 * An exception extending the interface IllegalArgumentException that denotes an error with Deck.
 */
public class IllegalDeckException extends IllegalArgumentException {
  public IllegalDeckException() {
    super();
  }

  public IllegalDeckException(String message) {
    super(message);
  }

  public IllegalDeckException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalDeckException(Throwable cause) {
    super(cause);
  }
}
