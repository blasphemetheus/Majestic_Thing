package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an Abstract Pile, one of PileType.
 */
public abstract class APile implements IPile {
  // the index of the pile, currently useful only in toString()
  protected final int index;
  // the stack of cards in this pile that has no constraints set upon it
  protected final List<Card> stack;
  // the one-character String representing this Pile (for toString)
  protected PileType pileType;

  /**
   * This is a Constructor for APile that takes an int index.
   */
  public APile(int index) {
    this.index = index;
    stack = new ArrayList<>();
  }

  @Override
  public String toString() {
    String temp = "" + PileType.myString(this.pileType) + String.valueOf(index) + ":";

    if (this.stack.size() != 0) {
      temp += " ";
    }

    temp += this.stack.toString().substring(1, this.stack.toString().length() - 1);
    return temp;
  }

  @Override
  public void addCard(Card card) {
    this.validateMoveTo(card);
    this.stack.add(card);
  }

  @Override
  public void dealCard(Card card) {
    this.validateNotFull();
    this.stack.add(card);
  }

  @Override
  public Card removeCard(int index) {
    Card temp = getCard(index);
    Card removed = this.stack.remove(index);
    if (!temp.equals(removed)) {
      throw new IllegalArgumentException("Something has gone terribly wrong");
    }
    return removed;
  }

  @Override
  public boolean isFull() {
    // Cascades inherit this, the others override
    if (this.stack.size() > FreecellModel.AMOUNT_VALUES) {
      throw new IllegalArgumentException("There are too many Cards in the Stack");
    }

    // HOMEWORK 4 UPDATE: I changed the criteria for a Cascade being full from having 13 Cards
    //                    to having 52 cards (since before then you could technically add more)
    return this.stack.size() == FreecellModel.AMOUNT_VALUES * FreecellModel.AMOUNT_SUITS;
  }

  // the default, meant to be overridden if extra constraints are in place for what can be moved
  @Override
  public void validateMoveTo(Card movedCard) {
    this.validateNotFull();
  }

  /**
   * Checks that this Pile is not full, throws exception if it is.
   */
  protected void validateNotFull() {
    if (this.isFull()) {
      throw new IllegalArgumentException("This Pile is full and it shouldn't be: "
              + this.toString());
    }
  }

  @Override
  public Card getCard(int cardIndex) {
    Card temp;
    try {
      try {
        temp = this.stack.get(cardIndex);
      } catch (IndexOutOfBoundsException e) {
        throw new IllegalArgumentException("The Specified Pile doesn't have a Card at that index: "
                + cardIndex);
      }
      Objects.requireNonNull(temp);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("The specified Pile doesn't have a Card at that index.");
    }
    return temp;
  }

  /**
   * Returns whether this is empty.
   *
   * @return whether this is Empty
   */
  public boolean isEmpty() {
    return this.stack.size() == 0;
  }


  @Override
  public Card getTop() {
    if (this.stack.size() == 0) {
      throw new IllegalArgumentException("Can't call getTop on an empty Pile");
    }

    return getCard(this.stack.size() - 1);
  }

  //FIX ME
  @Override
  public boolean equals(Object that) {
    if (that instanceof APile) {
      APile thatP = (APile) that;
      if (this.isFoundation()) {
        return thatP.isFoundation() && ((APile) that).index == this.index
                && ((APile) that).stack.equals(this.stack)
                && ((APile) that).pileType.equals(this.pileType);
      } else {
        if (this.isCascade()) {
          return thatP.isCascade() && ((APile) that).index == this.index
                  && ((APile) that).stack.equals(this.stack)
                  && ((APile) that).pileType.equals(this.pileType);
        } else {
          if (this.isOpen()) {
            return thatP.isOpen() && ((APile) that).index == this.index
                    && ((APile) that).stack.equals(this.stack)
                    && ((APile) that).pileType.equals(this.pileType);
          } else {
            throw new IllegalArgumentException(
                    "There shouldn't be another kind of APile here: " + this.pileType);
          }
        }
      }
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.stack, this.index, this.pileType);
  }

  /**
   * Returns if this is a Foundation.
   *
   * @return whether this APile is a Foundation
   */
  protected boolean isFoundation() {
    return false;
  }

  /**
   * Returns if this is an Open.
   *
   * @return whether this APile is an Open
   */
  protected boolean isOpen() {
    return false;
  }

  /**
   * Returns if this is a Cascade.
   *
   * @return whether this APile is a Cascade.
   */
  protected boolean isCascade() {
    return false;
  }

  @Override
  public ArrayList<Card> getStackBeforeInclusive(Card card) {
    if ((this.stack.size() > 0) && this.stack.contains(card)) {
      ArrayList<Card> list = new ArrayList<>();

      //this loop goes through stack and adds the top cards until it hits one which equals card,
      // where it short-circuits out
      for (int i = stack.size() - 1; i >= 0; i += -1) {
        if (this.getCard(i).equals(card)) {
          list.add(this.getCard(i));
          return list;
        } else {
          list.add(this.getCard(i));
        }
      }
    }
    throw new IllegalArgumentException("Stack doesn't contain given Card");
  }

  @Override
  public int getIndex(Card given) {

    if (this.stack.contains(given)) {
      return this.stack.indexOf(given);
    }
    throw new IllegalArgumentException("This Pile doesn't contain the given Card.");
  }

}