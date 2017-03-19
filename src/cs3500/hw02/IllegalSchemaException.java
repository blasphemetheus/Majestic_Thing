package cs3500.hw02;

/**
 * An exception extending the interface IllegalArgumentException that denotes a structural error.
 */
public class IllegalSchemaException extends IllegalArgumentException {
  public IllegalSchemaException() {
    super();
  }

  public IllegalSchemaException(String message) {
    super(message);
  }

  public IllegalSchemaException(String message, Throwable cause) {
    super(message, cause);
  }

  public IllegalSchemaException(Throwable cause) {
    super(cause);
  }
}
