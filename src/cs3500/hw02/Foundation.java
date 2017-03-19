package cs3500.hw02;

/**
 * Represents a Foundation Pile.
 *
 * <p> - need a isFull method so we can tell if game is won
 * - need to tell if incoming card's suit matches
 * (cardIsValid() method - suit matches, val is one more than top card)
 * - if empty, any ace can be added to it
 * - no required ordering of suits
 * </p>
 */
public class Foundation extends APile {

  /**
   * Constructs a Foundation Pile, taking an int index and initializing myString and stack.
   */
  public Foundation(int index) {
    super(index);
    this.pileType = PileType.FOUNDATION;
  }

  @Override
  protected boolean isFoundation() {
    return true;
  }

  // HOMEWORK 4 UPDATE: Previously, Foundation did not have its own version of this method -
  //             it just used the shoddy one, now that shoddy one (counts if it has 13) is here
  @Override
  public boolean isFull() {
    if (this.stack.size() > FreecellModel.AMOUNT_VALUES) {
      throw new IllegalArgumentException(
              "There are too many Cards in the Foundation: " + this.stack);
    }
    return this.stack.size() == FreecellModel.AMOUNT_VALUES;
  }

  @Override
  public void validateMoveTo(Card movedCard) {
    this.validateNotFull();

    if (this.isEmpty()) {
      if (movedCard.getValue() != CardValue.ACE) {
        throw new IllegalArgumentException(
                "This isn't an ace dumb person, you can't move it to this Pile when it's empty: "
                        + movedCard.toString());
      }
    } else {
      if (movedCard.getSuit() != this.getTop().getSuit()
              || this.getTop().getNumValue() != movedCard.getNumValue() - 1) {
        throw new IllegalArgumentException("This incoming Card doesn't fit the requirements: "
                + movedCard.toString());
      }
    }
  }
}
