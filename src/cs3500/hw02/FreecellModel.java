package cs3500.hw02;

import cs3500.hw04.AFreecellModel;

// HOMEWORK 4 UPDATE: FreecellModel class now extends AFreecellModel abstract class
// (which itself implements FreecellOperations) rather than implementing the interface directly.

// HOMEWORK 4 UPDATE AGAIN: Actually FreecellModel will only contain the move method for
// this singlemove variation of AFreecellModel. All the fields will be housed in AFreecellModel.

/**
 * To represent a Model of the game of Freecell. This is a gamestate essentially.
 */
public class FreecellModel extends AFreecellModel {

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {
    /*
    Check if source index, destIndex, cardIndex, are valid.

    See if the move iis valid
         - get card to be moved
         - see if card can move to destination pile

    Move to destination pile and remove from source pile
      */

    // retrieves the specified IPile that corresponds with the sourcePile we're working with
    IPile sourcePile = this.schema.retrieveWhichPile(source, pileNumber);

    // retrieves from that sourcePile the card at the specified index
    Card tempCard = sourcePile.getCard(cardIndex);

    // HOMEWORK 4 UPDATE: clarified internal documentation a bit.
    // if the card we found at the specified index is equal to the top card in the sourcePile then
    // we proceed (otherwise we throw an IllegalMoveException
    if (sourcePile.getTop().equals(tempCard)) {
      // retrieves the IPile that corresponds to the destPile
      IPile destPile = this.schema.retrieveWhichPile(destination, destPileNumber);

      // Validates that the movedCard can be added to the given pile
      destPile.validateMoveTo(tempCard);

      //within Pile, removeCard should THROW EXCEPTION IF CARDINDEX POINTS TO indexOutOfBounds
      Card transitCard = sourcePile.removeCard(cardIndex);

      // Should be removed eventually because of redundancy. Basically checking an invariant
      if (!tempCard.equals(transitCard)) {
        throw new IllegalMoveException(
                "THIS SHOULD NEVER HAPPEN!! REMOVE AND GET GOT DIFFERENT CARDS!!!");
      }

      destPile.addCard(transitCard);

    } else {
      // if
      throw new IllegalMoveException("Can't move a Card that isn't at the top of its Pile");
    }
  }

}

