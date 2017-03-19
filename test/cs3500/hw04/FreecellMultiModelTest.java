package cs3500.hw04;

import org.junit.Test;

import cs3500.hw02.Card;
import cs3500.hw02.Deck;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;

import static org.junit.Assert.assertEquals;

/**
 * Tests the FreecellMultiModel class and it's methods.
 */
public class FreecellMultiModelTest {

  // The naming convention for tests uses F to represent Foundation, O for Open,
  //         C for Cascades, 2 for "To"

  // Invalid starting values/initiation
  /////////////////////////////////////////////////////////////////////////////////////////////////
  @Test(expected = IllegalArgumentException.class)
  public void invalidValueSourcePileNumber() {
    // creates a multimove model
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveNormalDeck(), 8, 4, false);
    model.move(PileType.CASCADE, 9, 0, PileType.FOUNDATION, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidValueDestPileNumber() {
    // creates a multimove model
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveNormalDeck(), 8, 4, false);
    model.move(PileType.CASCADE, 6, 1, PileType.FOUNDATION, 5);

  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidValueCardIndex() {
    // creates a multimove model
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveNormalDeck(), 8, 4, false);
    model.move(PileType.CASCADE, 5, 14, PileType.FOUNDATION, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullSourcePile() {
    // creates a multimove model
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveNormalDeck(), 8, 4, false);
    model.move(null, 9, 0, PileType.FOUNDATION, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullDestPile() {    // creates a multimove model
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveNormalDeck(), 8, 4, false);
    model.move(PileType.CASCADE, 9, 0, null, 2);
  }

  // Invalid SingleMoves
  /////////////////////////////////////////////////////////////////////////////////////////////////

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleF20() {

    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4, false);
    System.out.println(model.getGameState());
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    System.out.println(model.getGameState());
    model.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 1);
    System.out.println(model.getGameState());
    model.move(PileType.FOUNDATION, 1, 0, PileType.OPEN, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleF2C() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4,
            false);
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 1);
    System.out.println(model.getGameState());
    model.move(PileType.CASCADE, 6, 6, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 6, 5, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 6, 4, PileType.OPEN, 2);
    System.out.println(model.getGameState());

    // Before move
    model.move(PileType.FOUNDATION, 1, 0, PileType.CASCADE, 5);
    // after move
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleO2F() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4,
            false);
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleO2C() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4,
            false);
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    model.move(PileType.OPEN, 1, 0, PileType.CASCADE, 1);
  }

  // fails not because of multimove
  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleC20() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4,
            false);
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 1, 7, PileType.OPEN, 1);
    System.out.println(model.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleC2F() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4,
            false);
    model.move(PileType.CASCADE, 4, 6, PileType.FOUNDATION, 1);
  }

  // Valid SingleMoves
  /////////////////////////////////////////////////////////////////////////////////////////////////

  @Test
  public void singleF2O() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4, false);
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 1);
    model.move(PileType.FOUNDATION, 1, 0, PileType.OPEN, 2);
    assertEquals(model.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 8♦\n" +
            "O3: A♦\n" +
            "O4:\n" +
            "C1: A♠, 8♠, 2♥, 9♥, 3♣, 10♣, 4♦, J♦\n" +
            "C2: 2♠, 9♠, 3♥, 10♥, 4♣, J♣, 5♦, Q♦\n" +
            "C3: 3♠, 10♠, 4♥, J♥, 5♣, Q♣, 6♦, K♦\n" +
            "C4: 4♠, J♠, 5♥, Q♥, 6♣, K♣, 7♦\n" +
            "C5: 5♠, Q♠, 6♥, K♥, 7♣\n" +
            "C6: 6♠, K♠, 7♥, A♣, 8♣, 2♦, 9♦\n" +
            "C7: 7♠, A♥, 8♥, 2♣, 9♣, 3♦, 10♦");
  }

  @Test
  public void singleF2C() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4, false);
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 1);
    model.move(PileType.CASCADE, 6, 6, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 6, 5, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 6, 4, PileType.OPEN, 2);

    // Before move
    model.move(PileType.FOUNDATION, 1, 0, PileType.CASCADE, 6);
    // after move
    assertEquals(model.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2: 8♦\n" +
            "O3: 9♣\n" +
            "O4: 3♦\n" +
            "C1: A♠, 8♠, 2♥, 9♥, 3♣, 10♣, 4♦, J♦\n" +
            "C2: 2♠, 9♠, 3♥, 10♥, 4♣, J♣, 5♦, Q♦\n" +
            "C3: 3♠, 10♠, 4♥, J♥, 5♣, Q♣, 6♦, K♦\n" +
            "C4: 4♠, J♠, 5♥, Q♥, 6♣, K♣, 7♦\n" +
            "C5: 5♠, Q♠, 6♥, K♥, 7♣\n" +
            "C6: 6♠, K♠, 7♥, A♣, 8♣, 2♦, 9♦\n" +
            "C7: 7♠, A♥, 8♥, 2♣, A♦");
  }

  @Test
  public void singleO2F() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4, false);
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 4, 5, PileType.OPEN, 2);
    System.out.println(model.getGameState());


    // Before move
    model.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 2);
    // after move

    assertEquals(model.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3: A♦\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 8♦\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 8♠, 2♥, 9♥, 3♣, 10♣, 4♦, J♦\n" +
            "C2: 2♠, 9♠, 3♥, 10♥, 4♣, J♣, 5♦, Q♦\n" +
            "C3: 3♠, 10♠, 4♥, J♥, 5♣, Q♣, 6♦, K♦\n" +
            "C4: 4♠, J♠, 5♥, Q♥, 6♣, K♣, 7♦\n" +
            "C5: 5♠, Q♠, 6♥, K♥, 7♣\n" +
            "C6: 6♠, K♠, 7♥, A♣, 8♣, 2♦, 9♦\n" +
            "C7: 7♠, A♥, 8♥, 2♣, 9♣, 3♦, 10♦");
  }

  @Test
  public void singleO2C() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 6, 4, false);
    System.out.println(model.getGameState());

    model.move(PileType.CASCADE, 4, 7, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    System.out.println(model.getGameState());

    // the move tested
    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 4);

    assertEquals(model.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 2♦\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 7♠, K♠, 6♥, Q♥, 5♣, J♣, 4♦, 10♦\n" +
            "C2: 2♠, 8♠, A♥, 7♥, K♥, 6♣, Q♣, 5♦, J♦\n" +
            "C3: 3♠, 9♠, 2♥, 8♥, A♣, 7♣, K♣, 6♦, Q♦\n" +
            "C4: 4♠, 10♠, 3♥, 9♥, 2♣, 8♣, A♦, 7♦, K♦\n" +
            "C5: 5♠, J♠, 4♥, 10♥, 3♣, 9♣, 8♦\n" +
            "C6: 6♠, Q♠, 5♥, J♥, 4♣, 10♣, 3♦, 9♦");
  }

  @Test
  public void singleC2O() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4, false);
    System.out.println(model.getGameState());
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    assertEquals(model.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 8♦\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 8♠, 2♥, 9♥, 3♣, 10♣, 4♦, J♦\n" +
            "C2: 2♠, 9♠, 3♥, 10♥, 4♣, J♣, 5♦, Q♦\n" +
            "C3: 3♠, 10♠, 4♥, J♥, 5♣, Q♣, 6♦, K♦\n" +
            "C4: 4♠, J♠, 5♥, Q♥, 6♣, K♣, 7♦\n" +
            "C5: 5♠, Q♠, 6♥, K♥, 7♣, A♦\n" +
            "C6: 6♠, K♠, 7♥, A♣, 8♣, 2♦, 9♦\n" +
            "C7: 7♠, A♥, 8♥, 2♣, 9♣, 3♦, 10♦");
  }

  @Test
  public void singleC2F() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4, false);
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 4, 5, PileType.FOUNDATION, 1);
    assertEquals(model.getGameState(), "F1:\n" +
            "F2: A♦\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2: 8♦\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 8♠, 2♥, 9♥, 3♣, 10♣, 4♦, J♦\n" +
            "C2: 2♠, 9♠, 3♥, 10♥, 4♣, J♣, 5♦, Q♦\n" +
            "C3: 3♠, 10♠, 4♥, J♥, 5♣, Q♣, 6♦, K♦\n" +
            "C4: 4♠, J♠, 5♥, Q♥, 6♣, K♣, 7♦\n" +
            "C5: 5♠, Q♠, 6♥, K♥, 7♣\n" +
            "C6: 6♠, K♠, 7♥, A♣, 8♣, 2♦, 9♦\n" +
            "C7: 7♠, A♥, 8♥, 2♣, 9♣, 3♦, 10♦");
  }

  // Invalid MultiMoves
  //////////////////////////////////////////////////////////////////////////////////////////////////

  // fails because of multimove (too deep in the cascade, not enough opens)
  @Test(expected = IllegalArgumentException.class)
  public void invalidMultiC20() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.MULTIMOVE);

    Deck deck = new Deck();

    model.startGame(deck.giveBackwardsCompatable(), 7, 4,
            false);
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 1, 2, PileType.OPEN, 2);
    System.out.println(model.getGameState());
  }

  // Valid MultiMoves
  /////////////////////////////////////////////////////////////////////////////////////////////////


}