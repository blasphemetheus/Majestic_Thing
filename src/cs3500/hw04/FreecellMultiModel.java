package cs3500.hw04;


import java.util.Objects;

import cs3500.hw02.PileType;

/**
 * Represents a FreecellModel that has enabled moves that can move multiple cards at a time.
 */
public class FreecellMultiModel extends AFreecellModel {

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {
    try {
      Objects.requireNonNull(source);
      Objects.requireNonNull(destination);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Your move passed in a null PileType -- invalid");
    }
    // Because the PileSchema holds everything we're working with for this move, we pass the buck
    this.schema.multiMove(source, pileNumber, cardIndex, destination, destPileNumber);
  }
}