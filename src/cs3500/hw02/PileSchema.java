package cs3500.hw02;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * To represent a Pile Schema for Freecell.
 */
public class PileSchema {
  List<Foundation> foundations;
  List<Cascade> cascades;
  List<Open> opens;

  /**
   * The public constructor for PileSchema.
   */
  public PileSchema() {
    this.foundations = new ArrayList<>();
    this.cascades = new ArrayList<>();
    this.opens = new ArrayList<>();
  }


  /**
   * Starts the game, as specified in the FreecellOperations method.
   *
   * @param numOpens       the number of Open Piles
   * @param numFoundations the number of Foundation Piles
   * @param numCascades    the number of Cascade Piles
   * @param deck           the deck of Cards
   */
  public void startGame(int numOpens, int numFoundations, int numCascades, List<Card> deck) {

    this.makePile(numOpens, PileType.OPEN);
    this.makePile(numFoundations, PileType.FOUNDATION);
    this.makePile(numCascades, PileType.CASCADE);

    this.dealCascades(deck);
  }

  /**
   * Inserts the specified number of piles of the correct type into the specified ArrayList.
   *
   * @param numPiles the number of piles to insert
   * @param type     the type of Pile
   */
  private void makePile(int numPiles, PileType type) {
    if (type == PileType.OPEN) {
      for (int i = 1; i <= numPiles; i += 1) {
        Open open = new Open(i);
        opens.add(open);
      }
    } else {
      if (type == PileType.CASCADE) {
        for (int i = 1; i <= numPiles; i += 1) {
          Cascade cascade = new Cascade(i);
          cascades.add(cascade);
        }
      } else {
        if (type == PileType.FOUNDATION) {
          for (int i = 1; i <= numPiles; i += 1) {
            Foundation foundation = new Foundation(i);
            foundations.add(foundation);
          }
        } else {
          throw new IllegalArgumentException("Invalid PileType: " + type);
        }
      }
    }
  }

  /**
   * Returns whether the game is over based on standards set in FreecellModel documentation.
   *
   * @return whether the game is over
   */
  public boolean isGameOver() {
    // maps .isFull() onto every foundation f
    boolean allFull = true;
    for (APile f : this.foundations) {

      allFull = f.isFull() && allFull;
    }
    return allFull;
  }

  /**
   * Returns the a String to represent the gameState at the time.
   *
   * @return a String representing the gamestate
   */
  public String getGameState() {
    String string = "";

    for (APile f : foundations) {
      string += (f.toString());
      string += ("\n");
    }

    for (APile o : opens) {
      string += (o.toString());
      string += ("\n");
    }

    for (APile c : cascades) {
      string += (c.toString());
      string += ("\n");
    }

    if (string.length() == 0) {
      return "";
    }
    return string.substring(0, string.length() - 1);
  }

  // distributes the cards in deck to the respective IPiles in cascades
  // MUTATION: adds Cards in deck to Piles in cascades in round robin fashion
  private void dealCascades(List<Card> deck) {

    // for every card c in the deck
    int c = 1;
    while (c < deck.size()) {
      // foreach loop, goes through every cascade in cascades and adds one card from deck,
      // removing it in the process. Then adds one to index c.
      for (Cascade cascade : cascades) {

        if (c <= deck.size()) {
          cascade.dealCard(deck.get(c - 1));
        }
        c += 1;
      }
    }
  }

  /**
   * Throws an IllegalSchemaException if the number of desired piles is not valid.
   *
   * @throws IllegalArgumentException when the numbers of desired piles is outside a valid range
   */
  public void checkPileNumbers(int numCascades, int numOpens) {
    if (numCascades < FreecellModel.MIN_CASCADES) {
      throw new IllegalSchemaException("There should be more Cascade Piles: " + numCascades);
    }
    if (numOpens < FreecellModel.MIN_OPEN) {
      throw new IllegalSchemaException("There should be more Open Piles: " + numOpens);
    } else if (numOpens > FreecellModel.MAX_OPEN) {
      throw new IllegalSchemaException("There should be more Open Piles: " + numOpens);
    }
  }


  // retrieves the correct IPile corresponding to the given PileType and pileNumber

  /**
   * Retrieves the correct IPile corresponding to the given PileType and pileNumber.
   *
   * @param pileType   the PileType specified
   * @param pileNumber the number of the pile
   * @return the specified IPile
   * @throws NullPointerException if the pile we find is null
   */
  public IPile retrieveWhichPile(PileType pileType, int pileNumber) {
    // Stores which specific Pile we're pointing too given the type and number
    IPile whichPile;

    try {
      // applies pileType to correct List of Piles field
      switch (pileType) {
        case OPEN:
          whichPile = this.opens.get(pileNumber);
          break;
        case FOUNDATION:
          whichPile = this.foundations.get(pileNumber);
          break;
        case CASCADE:
          whichPile = this.cascades.get(pileNumber);
          break;
        default:
          throw new IllegalArgumentException("Invalid PileType: " + pileType);
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Incorrect pileNumber for the given PileType: "
              + pileNumber + " - " + pileType);
    }
    return Objects.requireNonNull(whichPile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cascades, opens, foundations);
  }

  @Override
  public boolean equals(Object schema) {
    if (schema instanceof PileSchema) {
      return this.cascades.equals((((PileSchema) schema).cascades))
              && this.opens.equals((((PileSchema) schema).opens))
              && this.foundations.equals(((PileSchema) schema).foundations);
    } else {
      return false;
    }
  }

  // HOMEWORK 4 UPDATE: This method was added in this iteration of Freecell to deal with multimoves

  /**
   * Enacts a move (validating correctly) while allowing for the movement of multiple cards.
   *
   * @param source         the PileType of the source Pile of the move
   * @param pileNumber     the index of the source pile
   * @param cardIndex      the index of the Card within the source pile
   * @param destination    the PileType of the destination Pile for the move
   * @param destPileNumber the index of the destination pile
   * @throws IllegalArgumentException if the move is invalid
   */
  public void multiMove(PileType source, int pileNumber, int cardIndex, PileType destination,
                        int destPileNumber) {
    /*
    ------------ The PLAN ------------
    Check if source index, destIndex, cardIndex, are valid.

    See if the move iis valid
         - get card to be moved
         - see if card can move to destination pile

    Move to destination pile and remove from source pile
    */

    // Check if source pile to be moved from and source card is valid
    this.checkValidSource(source, pileNumber, cardIndex);
    // Check if destination pile at the end of the pile is a valid place to be moved
    this.checkValidDest(destination, destPileNumber);

    // Next checks if the actual move is valid
    // (by first getting the card to be moved and seeing if it can be moved to the destination pile)
    //-----------------------------------------------------------------------------------------

    // retrieves the specified IPile that corresponds with the sourcePile we're working with
    IPile sourcePile = this.retrieveWhichPile(source, pileNumber);
    // retrieves from that sourcePile the card at the specified index
    Card tempCard = sourcePile.getCard(cardIndex);

    // retrieves the IPile that corresponds to the destPile
    IPile destPile = this.retrieveWhichPile(destination, destPileNumber);


    Card topCard = sourcePile.getTop();

    // if below is true, the card we pulled is the topCard (there can be no duplicates in the Piles)
    // It used to be we could only move the top Card
    if (tempCard.equals(topCard)) {
      // cool, It's a movement of a single card.

      // Validates that the movedCard can be added to the given pile
      destPile.validateMoveTo(tempCard);

      //-----------------------------------------------------------------------------------------
      // Performs the actual move [ removal and addition of the card(s) ]
      Card transitCard = sourcePile.removeCard(cardIndex);
      destPile.addCard(transitCard);
    } else {
      // its an attempted multimove

      if (source == PileType.FOUNDATION) {
        throw new IllegalArgumentException("Trying to MultiMove from Foundation");
      }

      if (destination == PileType.FOUNDATION) {
        throw new IllegalArgumentException("Trying to MultiMove to Foundation");
      }

      List<Open> emptyOpens = this.getAllEmptyOpens();
      List<Cascade> emptyCascades = this.getAllEmptyCascades();

      // the Card is at the top of this listOfCard (highest index)
      // (the other indexes are the other Cards to be multimoved, starting with the Card at index 0)
      List<Card> listOfCard = sourcePile.getStackBeforeInclusive(tempCard);


      double maxCardsToMove = (emptyOpens.size() + 1) * Math.pow(2.0, emptyCascades.size());

      if (maxCardsToMove <= listOfCard.size()) {
        for (int j = listOfCard.size(); j > 0; j += -1) {
          this.multiMove(source, pileNumber, cardIndex + j - 1, destination,
                  destPileNumber);
        }
      } else {
        throw new IllegalArgumentException("Can't make this MultiMove");
      }
      // it can be proved that the maximum number of cards that can be moved when there
      // are N free open piles and K empty cascade piles is (N+1)*2^K
      //
    }
  }


  /**
   * Checks that the destination pile we are moving to is a valid pile to move to.
   *
   * @param destination    the PileType of the destination pile
   * @param destPileNumber the index number for the destination pile
   */
  private void checkValidDest(PileType destination, int destPileNumber) {
    // TODO
  }

  /**
   * Checks that the source pile we are drawing from (for a move) is a valid pile to move from and
   * that the cardIndex is a legitimate index that points to a Card.
   *
   * @param source     the PileType of the source pile
   * @param pileNumber the index number of the source pile
   * @param cardIndex  the index number for the specific card within the pile
   */
  private void checkValidSource(PileType source, int pileNumber, int cardIndex) {
    // TODO
  }

  /**
   * Gets all the Empty Open Piles in opens and adds em to a list and returns the list.
   *
   * @return a list of all empty open piles
   */
  public List<Open> getAllEmptyOpens() {
    List<Open> list = new ArrayList<>();

    for (Open o : this.opens) {
      if (o.isEmpty()) {
        list.add(o);
      }
    }
    return list;
  }

  /**
   * Gets all the Empty Cascade Piles in cascades, adds them to a list and returns the list.
   *
   * @return a list of all empty cascade piles
   */
  public List<Cascade> getAllEmptyCascades() {
    List<Cascade> list = new ArrayList<>();

    for (Cascade c : this.cascades) {
      if (c.isEmpty()) {
        list.add(c);
      }
    }
    return list;
  }
}
