package cs3500.hw02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the Card class and its methods.
 */
public class CardTest {

  @Test
  public void getSuitTest() {
    Card card0 = new Card(1, 1);
    assertEquals(card0.getSuit(), 1);
    Card card1 = new Card(2, 1);
    assertEquals(card1.getSuit(), 2);
    Card card2 = new Card(3, 2);
    assertEquals(card2.getSuit(), 3);
    Card card3 = new Card(4, 3);
    assertEquals(card3.getSuit(), 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getSuitExcept() {
    Card card4 = new Card(5, 4);
    assertNotEquals(card4.getSuit(), 0);
  }

  @Test
  public void getValueTest() {
    Card cardn = new Card(1, 1);
    assertEquals(cardn.getValue(), 1);
    Card card1 = new Card(2, 13);
    assertEquals(card1.getValue(), 13);
    Card card2 = new Card(3, 2);
    assertEquals(card2.getValue(), 2);
    Card card3 = new Card(4, 3);
    assertEquals(card3.getValue(), 3);
  }

  @Test
  public void getColorTest() throws Exception {
    Card cardQ = new Card(1, 12);
    assertEquals(cardQ.getColor(), 1);
    Card cardK = new Card(2, 13);
    assertEquals(cardK.getColor(), 2);
    Card card4 = new Card(3, 12);
    assertEquals(card4.getColor(), 2);
    Card card5 = new Card(4, 13);
    assertEquals(card5.getColor(), 1);
  }

  @Test
  public void toStringTest() throws Exception {
    Card cardQ = new Card(1, 12);
    assertEquals(cardQ.toString(), "Q♠");
    Card cardK = new Card(2, 12);
    assertEquals(cardK.toString(), "Q♥");
    Card cardA = new Card(3, 12);
    assertEquals(cardA.toString(), "Q♣");
    Card cardB = new Card(4, 12);
    assertEquals(cardB.toString(), "Q♦");

  }

  @Test
  public void getSuitAsString() {
    Card cardQ = new Card(1, 12);
    assertEquals(cardQ.getSuitAsString(), "♠");
    Card cardK = new Card(2, 12);
    assertEquals(cardK.getSuitAsString(), "♥");
    Card cardA = new Card(3, 12);
    assertEquals(cardA.getSuitAsString(), "♣");
    Card cardB = new Card(4, 12);
    assertEquals(cardB.getSuitAsString(), "♦");
  }

}