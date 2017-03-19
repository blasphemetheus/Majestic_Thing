package cs3500.hw02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static cs3500.hw02.Suit.CLUBS;
import static cs3500.hw02.Suit.DAIMONDS;
import static cs3500.hw02.Suit.HEARTS;
import static cs3500.hw02.Suit.SPADES;

/**
 * A class representing a conventional Deck of Cards.
 */
public class Deck {
  List<Card> spades;
  List<Card> hearts;
  List<Card> daimonds;
  List<Card> clubs;


  /**
   * Constructs a Deck.
   */
  public Deck() {
    this.spades = new ArrayList<>();
    this.hearts = new ArrayList<>();
    this.daimonds = new ArrayList<>();
    this.clubs = new ArrayList<>();

    this.addAllInSuit(SPADES);
    this.addAllInSuit(HEARTS);
    this.addAllInSuit(CLUBS);
    this.addAllInSuit(DAIMONDS);
  }

  /**
   * Outputs a List of all the Cards in the deck, in the conventional order.
   */
  public List<Card> giveNormalDeck() {
    List<Card> deck = new ArrayList<>();
    for (int i = 0; i < 13; i += 1) {
      deck.add(this.spades.get(i));
      deck.add(this.hearts.get(i));
      deck.add(this.clubs.get(i));
      deck.add(this.daimonds.get(i));
    }
    return deck;
  }

  /**
   * Outputs a Linked List version of a valid deck.
   *
   * @return a linked List of Cards
   */
  public List<Card> linkedList() {
    //TODO
    return null;
  }

  /**
   * Returns the Deck in the ordering first designed as default.
   *
   * @return the deck in a special order
   */
  public List<Card> giveBackwardsCompatable() {
    Card card;
    List<Card> deck = new ArrayList<>();

    // for loop counting up suit ints
    for (int suit = 1; suit <= FreecellModel.AMOUNT_SUITS; suit += 1) {
      // for loop counting up values
      for (int val = 1; val <= FreecellModel.AMOUNT_VALUES; val += 1) {
        card = new Card(suit, val);
        deck.add(card);
      }
    }
    return deck;
  }

  /**
   * Outputs a small deck (four of each suit in alternating order.
   *
   * @return a List of Cards (small deck)
   */
  public List<Card> giveHalfDeck() {
    List<Card> deck = new ArrayList<>();
    for (int i = 0; i < 4; i += 1) {
      deck.add(this.spades.get(i));
      deck.add(this.hearts.get(i));
      deck.add(this.clubs.get(i));
      deck.add(this.daimonds.get(i));
    }
    return deck;
  }

  /**
   * Outputs an ordered large deck (all of suit, then the next, etc...).
   *
   * @return a List of Cards (ordered deck)
   */
  public List<Card> giveOrderedDeck() {
    List<Card> deck = new ArrayList<>();
    deck.addAll(this.spades);
    deck.addAll(this.hearts);
    deck.addAll(this.clubs);
    deck.addAll(this.daimonds);
    return deck;
  }

  /**
   * Adds all cards to the correct stored List in the given suit.
   *
   * @param suit the given suit (1 - 4)
   * @throws IllegalArgumentException if the suit is invalid
   */
  protected void addAllInSuit(Suit suit) {
    Card temp;
    List<Card> suitSpecified = this.specifySuit(suit);

    for (int i = 1; i < 14; i += 1) {
      temp = new Card(suit.getNumSuit(), i);
      suitSpecified.add(temp);
    }
  }

  /**
   * Empties the default deck out of storage.
   */
  protected void emptyAll() {
    this.spades = new ArrayList<>();
    this.hearts = new ArrayList<>();
    this.clubs = new ArrayList<>();
    this.daimonds = new ArrayList<>();
  }

  /**
   * Replaces the stored deck with the given deck.
   *
   * @param givenDeck the given new deck
   */
  public void replaceWith(List<Card> givenDeck) {
    this.emptyAll();
    for (Card card : givenDeck) {
      this.sort(card);
    }
  }

  /**
   * Sorts the given method into the correct storage location.
   *
   * @param card the Card to be sorted
   */
  protected void sort(Card card) {
    List<Card> specified = this.specifySuit(card.getSuit());
    specified.add(card);
  }


  /**
   * Returns the suit specified given a valid suit.
   *
   * @param suit the intended suit
   * @return the List storage location of the intended suit
   */
  protected List<Card> specifySuit(Suit suit) {
    List<Card> suitSpecified;
    switch (suit) {
      case SPADES:
        suitSpecified = this.spades;
        break;
      case HEARTS:
        suitSpecified = this.hearts;
        break;
      case CLUBS:
        suitSpecified = this.clubs;
        break;
      case DAIMONDS:
        suitSpecified = this.daimonds;
        break;
      default:
        throw new IllegalArgumentException("Whoah suit is wrong: " + suit);
    }
    return suitSpecified;
  }

  /**
   * Checks whether the Cards in this Deck currently make up a valid deck.
   */
  public void checkValidDeck() {

    List<Card> all = new ArrayList<>();
    all.addAll(this.spades);
    all.addAll(this.hearts);
    all.addAll(this.clubs);
    all.addAll(this.daimonds);

    if (all.size() < FreecellModel.DECK_SIZE) {
      throw new IllegalDeckException("This deck doesn't have enough cards: " + all.size());
    }

    Set<Card> setOfDeck = new HashSet<>();
    setOfDeck.addAll(all);

    if (all.size() > setOfDeck.size()) {
      throw new IllegalDeckException("This deck has duplicate cards");
    }

    if (all.size() > FreecellModel.DECK_SIZE) {
      throw new IllegalDeckException("This deck has too many cards: " + all.size());
    }

    // Cards check their own validity in their constructor
  }
}
