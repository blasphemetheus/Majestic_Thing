package cs3500.hw03;

import org.junit.Test;

import java.io.StringReader;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.Deck;
import cs3500.hw02.FreecellModel;

import static junit.framework.TestCase.assertEquals;

/**
 * A class to test the FreecellController class and the playGame method specifically.
 */
public class FreecellControllerTest {
  Readable in;
  Appendable out;
  FreecellModel model;

  public void correctInitialize() {
    this.in = new StringReader("");
    this.out = new StringBuffer();
  }

  public List<Card> defaultDeck() {
    Deck deckMaker = new Deck();
    return deckMaker.giveNormalDeck();
  }

  @Test
  public void deckIsLinkedList() {
    // The thing I change
    in = new StringReader("C1 A 01");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    Deck deckMaker = new Deck();
    // the fun
    List<Card> deck = deckMaker.linkedList();
    controller.playGame(deck, model, 8, 0, false);

    assertEquals("Should be fine even with linked list", this.out.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void badInitializaion() {
    Appendable badAP = new StringBuffer();
    Readable badRD = null;
    FreecellController badController = new FreecellController(badRD, badAP);
    List<Card> deck = this.defaultDeck();
    badController.playGame(this.defaultDeck(), model, 8, 1, true);
    assertEquals("what?", badAP.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void badInitializaion2() {
    Appendable badAP = null;
    Readable badRD = new StringReader("AWESOME");
    FreecellController badController = new FreecellController(badRD, badAP);
    List<Card> deck = this.defaultDeck();
    badController.playGame(this.defaultDeck(), model, 8, 1, true);
    assertEquals("what?", badAP.toString());
  }

  @Test
  public void invalidOpens() {
    // The thing I change
    in = new StringReader("C1 A 01");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 0, false);

    assertEquals("Invalid Opens", this.out.toString());
  }

  @Test
  public void invalidOpens2() {
    // The thing I change
    in = new StringReader("C1 A 01");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 5, true);

    assertEquals("Invalid Opens 2", this.out.toString());
  }

  @Test
  public void reachingOutsideOpens() {
    this.in = new StringReader("O5 1 O1");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("You looked at too far away of opens", this.out.toString());
  }

  @Test
  public void invalidCascades() {
    // The thing I change
    in = new StringReader("C1 A 01");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 11, 5, false);

    assertEquals("Invalid Cascades", this.out.toString());
  }

  @Test
  public void invalidCascades2() {
    // The thing I change
    in = new StringReader("C1 A 01");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 3, 1, true);

    assertEquals("Invalid Cascades 2", this.out.toString());
  }

  @Test
  public void reachingTooFarCascades() {
    this.in = new StringReader("C9 1 O1");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("Reaching too far on those cascades", this.out.toString());
  }

  @Test
  public void nullModel() {
    // The thing I change
    this.in = new StringReader("C1 A 01");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, null, 8, 1, false);

    assertEquals("Invalid model", this.out.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void nullDeck() {
    this.in = new StringReader("C1 A 01");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(null, model, 8, 1, false);

    assertEquals("Invalid deck", this.out.toString());
  }

  @Test
  public void nonsenseWords() {
    this.in = new StringReader("@higgins fun every");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("Those are nonsense words", this.out.toString());
  }

  @Test
  public void noSpaces() {
    this.in = new StringReader("C17C3");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("Those are nonsense words", this.out.toString());
  }

  @Test
  public void invalidMoniker() {
    this.in = new StringReader("D8 1 O1");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("Nice you did it correctly", this.out.toString());
  }

  @Test
  public void reachingTooFarFoundations() {
    this.in = new StringReader("F4 1 O1");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("Nice you did it correctly", this.out.toString());
  }

  @Test
  public void reachingTooFarFoundations2() {
    this.in = new StringReader("C4 1 F1");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("Nice you did it correctly", this.out.toString());
  }

  @Test
  public void totallyValidMove() {
    Readable in = new StringReader(" C8 1 O1 q");

    FreecellModel model = new FreecellModel();
    Appendable out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "C1: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n" +
            "C2: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥\n" +
            "C3: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n" +
            "C4: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦\n" +
            "C5: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠\n" +
            "C6: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
            "C7: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣\n" +
            "C8: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦\n" +
            "Game quit prematurely.", out.toString());
  }

  @Test
  public void quitGameAfterValidMove() {
    this.in = new StringReader(" C1 6 O1 q");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("\n" + "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "C1: A♠, 3♠, 5♠, 7♠, 9♠, J♠, K♠\n" +
                    "C2: A♥, 3♥, 5♥, 7♥, 9♥, J♥, K♥\n" +
                    "C3: A♣, 3♣, 5♣, 7♣, 9♣, J♣, K♣\n" +
                    "C4: A♦, 3♦, 5♦, 7♦, 9♦, J♦, K♦\n" +
                    "C5: 2♠, 4♠, 6♠, 8♠, 10♠, Q♠\n" +
                    "C6: 2♥, 4♥, 6♥, 8♥, 10♥, Q♥\n" +
                    "C7: 2♣, 4♣, 6♣, 8♣, 10♣, Q♣\n" +
                    "C8: 2♦, 4♦, 6♦, 8♦, 10♦, Q♦" +
                    "\nGame quit prematurely."
            , this.out.toString());
  }

  @Test
  public void quitGame() {
    this.in = new StringReader(" q q");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("\nGame quit prematurely.", this.out.toString());
  }

  @Test
  public void quitGame2() {
    this.in = new StringReader(" Q");

    FreecellModel model = new FreecellModel();
    this.out = new StringBuffer();
    FreecellController controller = new FreecellController(in, out);

    // the fun
    List<Card> deck = this.defaultDeck();
    controller.playGame(deck, model, 8, 1, false);

    assertEquals("\nGame quit prematurely.", this.out.toString());
  }
}
