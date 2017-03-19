package cs3500.hw02;

/**
 * An enum representing the CardValue of a Card.
 */
public enum CardValue {
  ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
  JACK(11), QUEEN(12), KING(13);

  private final int numericVal;

  /**
   * Constructs this CardValue given an integer value representing this CardValue.
   *
   * @param numVal the int representation of this CardValue
   */
  CardValue(int numVal) {
    this.numericVal = numVal;
  }

  /**
   * Gets the numeric value of this CardValue.
   *
   * @return the int representing the value
   */
  public int getNumericVal() {
    return numericVal;
  }


  /**
   * Returns a CardValue generated from the given integer.
   *
   * @param integer the integer passed in
   * @return the Correct CardValue
   */
  public static CardValue fromInt(int integer) {
    if (integer == 1) {
      return ACE;
    } else if (integer == 2) {
      return TWO;
    } else if (integer == 3) {
      return THREE;
    } else if (integer == 4) {
      return FOUR;
    } else if (integer == 5) {
      return FIVE;
    } else if (integer == 6) {
      return SIX;
    } else if (integer == 7) {
      return SEVEN;
    } else if (integer == 8) {
      return EIGHT;
    } else if (integer == 9) {
      return NINE;
    } else if (integer == 10) {
      return TEN;
    } else if (integer == 11) {
      return JACK;
    } else if (integer == 12) {
      return QUEEN;
    } else if (integer == 13) {
      return KING;
    } else {
      throw new IllegalCardException("This given integer is an incorrect cardValue: " + integer);
    }
  }

  /**
   * Returns the String translation of this CardValue. (A, 1-10, J, Q, K)
   *
   * @return a String representing the value of this Card.
   * @throws IllegalArgumentException if the getValue() returns the wrong int
   */
  public String toString() {
    switch (this.numericVal) {
      case 1:
        return "A";
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
        return String.valueOf(this.numericVal);
      case 11:
        return "J";
      case 12:
        return "Q";
      case 13:
        return "K";
      default:
        throw new IllegalCardException("Illegal Suit: " + this.numericVal);
    }
  }
}
