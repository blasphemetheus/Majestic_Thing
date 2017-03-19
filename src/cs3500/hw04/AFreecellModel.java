package cs3500.hw04;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import cs3500.hw02.Card;
import cs3500.hw02.Deck;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileSchema;

/**
 * The abstract class that holds most of the methods for the FreecellModel subclasses.
 */
public abstract class AFreecellModel implements FreecellOperations<Card> {
  // AMOUNT corresponds to amount allowed in this instance of FreecellModel :
  //---- amount of suits
  public static final int AMOUNT_SUITS = 4;
  //---- amount of card values
  public static final int AMOUNT_VALUES = 13;
  // The size of the deck based on the Amount_suit and Amount_values
  public static final int DECK_SIZE = AMOUNT_SUITS * AMOUNT_VALUES;
  // The minimum amount of Open Piles
  public static final int MIN_OPEN = 1;
  // The maximum amount of Open Piles
  public static final int MAX_OPEN = 4;
  // The minimum amount of Cascade Piles
  public static final int MIN_CASCADES = 4;


  // Controls all the stored data in an instance of the model (It makes it simpler to think about).
  public PileSchema schema;

  /**
   * Constructs a Freecellmodel.
   */
  public AFreecellModel() {
    this.schema = new PileSchema();
  }

  @Override
  public List<Card> getDeck() {
    Deck deck = new Deck();
    return deck.giveNormalDeck();
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
          throws IllegalArgumentException {

    // shuffles the deck if shuffle is true
    if (shuffle) {
      Random r = new Random();
      Collections.shuffle(deck, r);
    }

    Deck d = new Deck();
    d.replaceWith(deck);
    d.checkValidDeck();
    this.schema.checkPileNumbers(numCascadePiles, numOpenPiles);

    this.schema.startGame(numOpenPiles, AMOUNT_SUITS, numCascadePiles, deck);
  }

  @Override
  public boolean isGameOver() {
    return this.schema.isGameOver();
  }

  @Override
  public String getGameState() {
    return this.schema.getGameState();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.schema);
  }

  @Override
  public boolean equals(Object model) {
    if (model instanceof FreecellModel) {
      return this.schema.equals((((FreecellModel) model).schema));
    } else {
      return false;
    }
  }

}
