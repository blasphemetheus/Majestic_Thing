package cs3500.hw02;

/**
 * Represents a Cascade Pile.
 */
public class Cascade extends APile {

  /**
   * Constructs a Cascade Pile.
   *
   * @param index the index of the Cascade Pile
   */
  public Cascade(int index) {
    super(index);
    this.pileType = PileType.CASCADE;
  }

  @Override
  public boolean isCascade() {
    return true;
  }

  @Override
  public void validateMoveTo(Card movedCard) {
    this.validateNotFull();

    if (movedCard.getColor() == 1) {
      if (this.getTop().getColor() != 2) {
        throw new IllegalArgumentException(
                "The Color of movedCard needs to be Red for this move to be correct.");
      }
    } else {
      if (movedCard.getColor() == 2) {
        if (this.getTop().getColor() != 1) {
          throw new IllegalArgumentException(
                  "The Color of movedCard needs to be Black for this move to be correct.");
        }
      } else {
        throw new IllegalArgumentException("The Color of the movedCard is neither red nor black.");
      }
    }
  }
}
