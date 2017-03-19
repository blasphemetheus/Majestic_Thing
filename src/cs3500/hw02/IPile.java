package cs3500.hw02;

import java.util.ArrayList;

/**
 * Represents the IPile interface.
 */
public interface IPile {

  /**
   * Returns a String representing the CardSpaces in this Pile.
   *
   * @return a String for this APile (space + suit-string + (if !last -> comma) for each card)
   */
  String toString();

  @Override
  boolean equals(Object that);

  @Override
  int hashCode();

  /**
   * Gets the Card at the given index.
   *
   * @param cardIndex the index of the Card we're getting
   * @return the Card at the appropriate place, does not remove.
   * @throws IllegalArgumentException if index is out of bounds
   */
  Card getCard(int cardIndex);

  /**
   * Returns the top Card.
   *
   * @return the first Card
   */
  Card getTop();

  /**
   * Adds a card to this APile within the confines of the rules of the game.
   */
  void addCard(Card card);

  /**
   * Tests whether (@code movedCard) can be moved to this Pile. Throws exception if it cannot.
   *
   * @param movedCard the Card we're testing
   */
  void validateMoveTo(Card movedCard);

  /**
   * Removes the card at the given index.
   *
   * @param index the index of the card in this Pile.
   * @throws IllegalArgumentException if index is out of bounds
   */
  Card removeCard(int index);

  /**
   * Returns whether this Pile is full.
   *
   * @return whether this is Full
   */
  boolean isFull();

  /**
   * Deals a card to this Pile assuming this isn't part of the play of the game.
   *
   * @param card the card being dealt
   */
  void dealCard(Card card);

  /**
   * Returns whether this Pile is empty or not.
   *
   * @return whether this Pile is empty
   */
  boolean isEmpty();

  /**
   * Takes all the Cards on top of the given Card (including the given Card), adds them in order
   * encountered to a List and returns it as an ArrayList of Card.
   *
   * @param given the given Card
   * @return the List of Cards before the given Card
   */
  ArrayList<Card> getStackBeforeInclusive(Card given);


  /**
   * Gets the index the given Card is at.
   *
   * @param given the Card being located
   * @return the index given Card is at
   * @throws IllegalArgumentException if Card is not in Pile
   */
  int getIndex(Card given);

}
