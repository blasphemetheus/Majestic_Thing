package cs3500.hw02;

/**
 * To represent a single playing Card whose values cannot change.
 */
public class Card {
  // the suit of the card (from 1 to 4)
  Suit suit;
  // the value of the card (from 1 to 13)
  CardValue value;

  /**
   * Constructs an instance of a Card using integers.
   *
   * @param suit  the suit (1 = Spades, 2 = Hearts, 3 = Clubs, 4 = Daimonds)
   * @param value the value (1 = Ace, 2 = two, etc ... 11 = Jack, 12 = Queen, 13 = King)
   */
  public Card(int suit, int value) {
    this(Suit.fromInt(suit), CardValue.fromInt(value));
  }

  /**
   * Constructs an instance of a Card using enums Suit and CardValue.
   *
   * @param suit      the provided Suit
   * @param cardValue the provided CardValue
   */
  public Card(Suit suit, CardValue cardValue) {
    this.suit = suit;
    this.value = cardValue;
  }

  /**
   * Returns the Color of this Card (1 = black, 2 = red).
   *
   * @return the int representing the color of this Card
   */
  public int getColor() {
    if (this.suit == Suit.SPADES || this.suit == Suit.CLUBS) {
      return 1;
    } else {
      if (this.suit == Suit.HEARTS || this.suit == Suit.DAIMONDS) {
        return 2;
      } else {
        throw new IllegalCardException("The suit is malformed");
      }
    }
  }

  /**
   * Returns the Value of this Card.
   *
   * @return the value this Card holds
   */
  public CardValue getValue() {
    return this.value;
  }

  /**
   * Returns the suit of this Card.
   *
   * @return the suit of this Card as an int
   */
  public Suit getSuit() {
    return this.suit;
  }


  /**
   * Returns the Value of this Card.
   *
   * @return the value this Card holds
   */
  public int getNumValue() {
    return this.value.getNumericVal();
  }

  /**
   * Returns the suit of this Card.
   *
   * @return the suit of this Card as an int
   */
  public int getNumSuit() {
    return this.suit.getNumSuit();
  }

  /**
   * Returns the String representation of this Card as a String representation of its value followed
   * immediately by its String representation of its suit. (ie "A♠" or "J♣" or "2♦" or "10♥").
   *
   * @return String representation of this Card
   */
  public String toString() {
    return "" + this.value.toString() + this.suit.getSuitAsString();
  }

  /**
   * Returns the suit of this Card as a String.
   *
   * @return suit of Card in special string
   */
  public String getSuitAsString() {
    switch (this.suit) {
      case SPADES: // case for spades
        return "♠";
      case HEARTS: // case for hearts
        return "♥";
      case CLUBS: // case for clubs
        return "♣";
      case DAIMONDS: // case for daimonds
        return "♦";
      default:
        throw new IllegalCardException("There is an extra suit: " + this.suit);
    }
  }
}
