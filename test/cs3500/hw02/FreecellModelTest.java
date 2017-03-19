package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw04.FreecellModelCreator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * A class for testing the public side of the FreecellModel class (ie FreecellOperations' methods).
 */
public class FreecellModelTest {

  //Do you have a test to verify that calling startGame when a game is already in progress
  // restarts the game correctly? (0.5 points)


  //Do you have a test to verify that startGame will throw an IllegalArgumentException
  // if the number of cascade piles is less than 4? (1 point)

  //  @Test
  //  public void moveFromOpenToFoundation() {
  //
  //  }
  //
  //  @Test
  //  public void moveFromFoundationToOpen() {
  //
  //  }
  //
  //
  //  @Test
  //  public void moveFromCascadeToOpen() {
  //
  //  }
  //
  //  @Test
  //  public void moveFromOpenToCascade() {
  //
  //  }
  //
  //  @Test
  //  public void moveFromFoundationToCascade() {
  //
  //  }
  //
  //  @Test
  //  public void moveFromCascadeToFoundation() {
  //
  //  }
  //
  //  @Test
  //  public void startGameExceptionInvalidOpens() {
  //
  //  }
  //
  //  @Test
  //  public void startGameExceptionInvalidFoundations() {
  //
  //  }
  //
  //  @Test
  //  public void startGameExceptionInvalidCascades() {
  //
  //  }


  @Test
  public void getDeckNull() {
    FreecellModel model = new FreecellModel();
    Card card = new Card(1, 1);

    assertNotNull("getDeck returned null", model.getDeck());
  }

  @Test
  public void getDeckHas52Cards() {
    FreecellModel model = new FreecellModel();

    Deck deck = new Deck();

    assertEquals(model.getDeck().size(), deck.giveNormalDeck().size());
  }

  @Test
  public void getDeckTest() {
    FreecellModel model = new FreecellModel();
    Card card;
    List<Card> deck = new ArrayList<>();

    Deck newDeck = new Deck();
    newDeck.giveNormalDeck().toString();

    assertEquals(model.getDeck().toString(), newDeck.giveNormalDeck().toString());
  }

  @Test
  public void startGame() {
    FreecellModel model = new FreecellModel();
    FreecellModel model2 = new FreecellModel();
    List<Card> deck = model.getDeck();
    List<Card> deck2 = model2.getDeck();

    model.startGame(deck, 4, 1, true);
    model2.startGame(deck2, 4, 1, false);
    assertNotEquals(model.schema.cascades.get(0).toString(),
            model2.schema.cascades.get(0).toString());
    assertNotEquals(model, model2);
  }

  @Test
  public void startGameEqualInputEqualModels() {
    FreecellModel modeln = new FreecellModel();
    FreecellModel model2 = new FreecellModel();
    List<Card> deck = modeln.getDeck();

    modeln.startGame(deck, 4, 1, false);
    model2.startGame(deck, 4, 1, false);

    assertEquals(modeln, model2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameTestException() {
    FreecellModel model = new FreecellModel();
    Card card;
    List<Card> deck = new ArrayList<>();

    // for loop counting up suits
    for (int suit = -1; suit <= 3; suit += 1) {
      // for loop counting up vals
      for (int val = 1; val <= 13; val += 1) {
        card = new Card(val, suit);
        deck.add(card);
      }
    }
    model.startGame(deck, 4, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameTestException2() {
    FreecellModel model = new FreecellModel();
    Card card;
    List<Card> deck = new ArrayList<>();

    // for loop counting up suits
    for (int suit = 0; suit <= 4; suit += 1) {
      // for loop counting up vals
      for (int val = 1; val <= 13; val += 1) {
        card = new Card(val, suit);
        deck.add(card);
      }
    }
    model.startGame(deck, 4, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameTestException3() {
    FreecellModel model = new FreecellModel();
    Card card;
    List<Card> deck = new ArrayList<>();

    // for loop counting up suits
    for (int suit = 0; suit <= 3; suit += 1) {
      // for loop counting up vals
      for (int val = 0; val <= 13; val += 1) {
        card = new Card(val, suit);
        deck.add(card);
      }
    }
    model.startGame(deck, 5, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startGameTestException4() {
    FreecellModel model = new FreecellModel();
    Card card;
    List<Card> deck = new ArrayList<>();

    // for loop counting up suits
    for (int suit = 0; suit <= 3; suit += 1) {
      // for loop counting up vals
      for (int val = 1; val <= 14; val += 1) {
        card = new Card(val, suit);
        deck.add(card);
      }
    }
    model.startGame(deck, 6, 1, false);
  }

  @Test
  public void moveTest() {

    Card card1 = new Card(2, 6);
    FreecellModel model = new FreecellModel();
    FreecellModel model2 = new FreecellModel();
    Deck goodDeck = new Deck();

    model.startGame(goodDeck.giveBackwardsCompatable(),
            4, 1, false);
    model2.startGame(goodDeck.giveBackwardsCompatable(),
            4, 1, false);
    model.schema.opens.get(0).addCard(card1);
    model2.schema.opens.get(0).addCard(card1);
    model.schema.cascades.get(0).removeCard(12);
    model.schema.cascades.get(0).removeCard(11);
    model.schema.cascades.get(0).removeCard(10);
    model.schema.cascades.get(0).removeCard(9);
    model2.schema.cascades.get(0).removeCard(12);
    model2.schema.cascades.get(0).removeCard(11);
    model2.schema.cascades.get(0).removeCard(10);
    model2.schema.cascades.get(0).removeCard(9);
    model2.schema.opens.get(0).removeCard(0);
    model2.schema.cascades.get(0).dealCard(card1);
    assertNotEquals(model.getGameState(), model2.getGameState());

    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);

    assertEquals(model.getGameState(), model2.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveExceptionTest() {

    Card card2 = new Card(1, 1);
    Card card1 = new Card(1, 1);
    FreecellModel model2 = new FreecellModel();
    FreecellModel model = new FreecellModel();

    List<Card> deck = model.getDeck();

    model.startGame(deck, 4, 1, true);
    model2.startGame(deck, 4, 1, false);
    model.schema.opens.get(0).addCard(card1);
    model2.schema.opens.get(0).addCard(card2);

    model.move(PileType.OPEN, 0, 0, PileType.OPEN, 0);
    assertEquals(model, model2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveTestException4() {

    Card cardn = new Card(5, 1);

    FreecellModel model = new FreecellModel();
    FreecellModel model2 = new FreecellModel();
    List<Card> deck = model.getDeck();

    model2.startGame(deck, 4, 1, false);
    model.startGame(deck, 4, 1, false);
    model.schema.opens.get(0).addCard(cardn);
    model.schema.cascades.get(0).removeCard(12);

    model.move(PileType.OPEN, 0, 0, PileType.CASCADE, 0);
    assertEquals(model, model2);
  }

  @Test
  public void moveTest2() {

    Card card2 = new Card(1, 1);

    FreecellModel model1 = new FreecellModel();
    FreecellModel model3 = new FreecellModel();
    List<Card> deck = model1.getDeck();

    model1.startGame(deck, 4, 1, false);
    model3.startGame(deck, 4, 1, false);

    model1.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    System.out.println(model1.getGameState());
    System.out.println("hello");
    System.out.println(model3.getGameState());
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: K♠\n" +
            "C1: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
            "C2: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "C3: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "C4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦", model1.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveExceptTest() {
    Card card2 = new Card(1, 1);

    FreecellModel model1 = new FreecellModel();
    FreecellModel model3 = new FreecellModel();
    List<Card> deck = model1.getDeck();

    model1.startGame(deck, 4, 1, true);
    model3.startGame(deck, 4, 1, false);

    model1.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    model1.move(PileType.CASCADE, 0, 0, PileType.OPEN, 0);
    assertEquals(model1, model3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveTestException() {

    FreecellModel model = new FreecellModel();
    Card card;
    List<Card> deck = new ArrayList<>();

    // for loop counting up suits
    for (int suit = 0; suit <= 3; suit += 1) {
      // for loop counting up vals
      for (int val = 1; val <= 13; val += 1) {
        card = new Card(val, suit);
        deck.add(card);
      }
    }
    assertEquals(model.getGameState(), "F1: A");
  }

  @Test
  public void isGameOverTest() {
    FreecellModel model1 = new FreecellModel();
    FreecellModel model3 = new FreecellModel();
    List<Card> deck = model1.getDeck();

    model1.startGame(deck, 4, 1, true);
    model3.startGame(deck, 4, 1, false);

    for (int i = 1; i <= 13; i++) {
      model1.schema.foundations.get(0).dealCard(new Card(1, i));
    }
    for (int i = 1; i <= 13; i++) {
      model1.schema.foundations.get(1).dealCard(new Card(2, i));
    }
    for (int i = 1; i <= 13; i++) {
      model1.schema.foundations.get(2).dealCard(new Card(3, i));
    }
    for (int i = 1; i <= 13; i++) {
      model1.schema.foundations.get(3).dealCard(new Card(4, i));
    }
    System.out.println(model1.getGameState());
    assertEquals(model1.isGameOver(), true);
  }

  @Test
  public void isGameOver2Test() {
    FreecellModel model1 = new FreecellModel();
    FreecellModel model3 = new FreecellModel();
    List<Card> deck = model1.getDeck();

    model1.startGame(deck, 4, 1, true);
    model3.startGame(deck, 4, 1, false);

    for (int i = 1; i < 13; i++) {
      model1.schema.foundations.get(0).dealCard(new Card(1, i));
    }
    for (int i = 1; i <= 13; i++) {
      model1.schema.foundations.get(1).dealCard(new Card(2, i));
    }
    for (int i = 1; i <= 13; i++) {
      model1.schema.foundations.get(2).dealCard(new Card(3, i));
    }
    for (int i = 1; i <= 13; i++) {
      model1.schema.foundations.get(3).dealCard(new Card(4, i));
    }
    assertNotEquals(model1.isGameOver(), true);
  }


  @Test
  public void getGameStateTest() {
    FreecellModel model = new FreecellModel();


    assertEquals(model.getGameState(), "");
  }

  @Test
  public void getGameStateTest2() {
    FreecellModel model2 = new FreecellModel();
    model2.startGame(model2.getDeck(), 4, 1, false);

    assertEquals("F1:\nF2:\nF3:\nF4:\nO1:\nC1:"
                    + " A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\nC2:"
                    + " A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "C3: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣"
                    + "\nC4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦",
            model2.getGameState());
  }

  @Test(expected = IllegalArgumentException.class)
  public void multiMoveDoesNotWorkRight() {
    FreecellOperations<Card> model = FreecellModelCreator.create(
            FreecellModelCreator.GameType.SINGLEMOVE);

    model.startGame(model.getDeck(), 4, 4, false);
    System.out.println(model.getGameState());

    model.move(PileType.CASCADE, 0, 4, PileType.OPEN, 0);
  }

}