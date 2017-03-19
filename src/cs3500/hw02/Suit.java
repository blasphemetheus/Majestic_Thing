package cs3500.hw02;

/**
 * An enum representing the Suit of a Card.
 */
public enum Suit {
  SPADES(1), HEARTS(2), CLUBS(3), DAIMONDS(4);

  private final int numSuit;

  /**
   * Constructs a Suit given an int numSuit.
   *
   * @param numSuit an integer representing this suit
   */
  Suit(int numSuit) {
    this.numSuit = numSuit;
  }

  /**
   * Returns the numeric value of this Suit (Spades = 1, Hearts = 2, Clubs = 3, Daimonds = 4).
   *
   * @return an int representing this Suit
   */
  public int getNumSuit() {
    return this.numSuit;
  }

  /**
   * Returns a Suit generated from the given integer.
   *
   * @param integer the integer passed in
   * @return the Correct Suit
   */
  public static Suit fromInt(int integer) {
    if (integer == 1) {
      return SPADES;
    } else if (integer == 2) {
      return HEARTS;
    } else if (integer == 3) {
      return CLUBS;
    } else if (integer == 4) {
      return DAIMONDS;
    } else {
      throw new IllegalCardException("This given integer is an incorrect Suit: " + integer);
    }
  }

  /**
   * Returns this Suit as a String.
   *
   * @return suit of Card in special string
   */
  public String getSuitAsString() {
    switch (this) {
      case SPADES: // case for spades
        return "♠";
      case HEARTS: // case for hearts
        return "♥";
      case CLUBS: // case for clubs
        return "♣";
      case DAIMONDS: // case for daimonds
        return "♦";
      default:
        throw new IllegalCardException("There is an extra suit: " + this);
    }
  }


}
