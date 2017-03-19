package cs3500.hw02;

/**
 * Represents an Open Pile.
 */
public class Open extends APile {

  /**
   * Constructs an Open Pile.
   */
  public Open(int index) {
    super(index);
    this.pileType = PileType.OPEN;
  }

  @Override
  public boolean isFull() {
    int temp = this.stack.size();
    if (temp > 1) {
      throw new IllegalArgumentException("Too many cards in Open pile. (should never happen");
    }
    return this.stack.size() > 0;
  }

  @Override
  protected boolean isOpen() {
    return true;
  }
}
