package cs3500.hw03;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.PileType;


// way to go String to Appendable and read ints and Strings from a Readable. (look at Scanner).

/**
 * Represents the Controller for the Freecell game.
 */
public class FreecellController implements IFreecellController<Card> {
  private Readable rd;
  private Appendable ap;
  // whether the controller is currently running
  private boolean running;
  // what fields and of what types do I need?

  /**
   * Constructs a FreecellController, default constr, uses Readable and Appendable.
   */
  public FreecellController(Readable rd, Appendable ap) {
    this.rd = this.validate(rd);
    this.ap = this.validate(ap);
    this.running = false;
  }

  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
                       int numOpens, boolean shuffle) throws IllegalStateException {
    // Makes sure deck is valid.
    this.validate(deck);

    // Makes sure the model is valid.
    this.validate(model);

    // Tell provided model to start a game with provided parameters
    // (If these valued are incorrect, should communicate back to before the Controller)
    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      this.transmitNoNewLine("Could not start game.");
      return;
    }

    // tell model to run the game in following sequence:::::
    //  //Transmit game state to Appendable Object exactly as model provides

    //  //If game ongoing, wait for user input from Readable.
    //        - a valid user input is a three input sequence (seperated by spaces or \n)
    //            i.
    //            ii.
    //            iii.
    //        - Controller parses these inputs and passes info to the model to make the move
    //  //If game is won, method should transmit final game state and "\n" + "Game over." and return
    this.runGameSequence(model);
  }

  /**
   * Writes the given String to the out Appendable not on a new line.
   *
   * @param s the string to be written out
   */
  private void transmitNoNewLine(String s) {
    try {
      this.ap.append(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes the given String to the out Appendable on a new line.
   *
   * @param string the string to be written
   */
  private void transmit(String string) {
    this.transmitNoNewLine("\n" + string);
  }

  /**
   * Toggle's this game's running boolean. Only to be used during runGameSequence.
   * (if null throws exception).
   */
  private void toggleRunning() {
    Objects.requireNonNull(this.running);
    if (this.running) {
      this.running = false;
    } else {
      if (!this.running) {
        this.running = true;
      } else {
        throw new IllegalArgumentException("I'm confused");
      }
    }
  }

  /**
   * Runs through the game sequence with the given model as input.
   *
   * @param model the model that we are running the game on
   */
  private void runGameSequence(FreecellOperations<Card> model) {
    // tell model to run the game in following sequence:
    //  //Transmit game state to Appendable Object exactly as model provides
    this.transmit(model.getGameState());

    // sets game to running
    this.toggleRunning();

    // is game won? if so transmit final game state and "\n" + "Game over." and return
    if (model.isGameOver()) {
      // the game is over legally
      this.transmit(model.getGameState());
      this.transmit("Game over.");

      this.toggleRunning();
      return;
    } else {
      // the game is ongoing

      // this branch waits for user input from readable
      this.processInput(model);
    }
  }

  /**
   * Processes user input from the Readable.
   * <p>
   * If game ongoing, wait for user input from Readable.
   * - a valid user input is a three input sequence (seperated by spaces or \n)
   * i.
   * ii.
   * iii.
   * - Controller parses these inputs and passes info to the model to make the move
   * If game is won, method should transmit final game state and "\n" + "Game over." and return}</p>
   *
   * @param model the given model
   */
  private void processInput(FreecellOperations<Card> model) {
    Scanner scan = new Scanner(this.rd);

    Integer pileNumber;
    PileType destination;

    PileType source;
    Integer cardIndex;
    Integer destPileNumber;

    while (this.running) {

      // pull out the first three inputs
      //////////////////////////
      String inputOne = scan.next();
      if (inputOne.equalsIgnoreCase("q")) {
        this.transmit("Game quit prematurely.");
        this.toggleRunning();
        return;
      }
      //////////////////////////
      String inputTwo = scan.next();
      if (inputTwo.equalsIgnoreCase("q")) {
        this.transmit("Game quit prematurely.");
        this.toggleRunning();
        return;
      }
      //////////////////////////
      String inputThree = scan.next();
      if (inputThree.equalsIgnoreCase("q")) {
        this.transmit("Game quit prematurely.");
        this.toggleRunning();
        return;
      }    //////////////////////////

      // the booleans that hold whether the inputs were wrong
      boolean inputOneValid = false;
      boolean inputTwoValid = false;
      boolean inputThreeValid = false;

      while (inputOneValid && inputTwoValid && inputThreeValid) {

        // assigns PileType and Integer from inputOne
        String firstCharInputOne = this.firstChar(inputOne);
        destination = this.pullOutPileType(firstCharInputOne);
        //TODO does the below substring break things if we input a Single Character String?
        String restInputOne = inputOne.substring(1);
        // if no Integer found, pileNumber will be null
        pileNumber = Integer.getInteger(restInputOne);


        // assigns Integer (to cardIndex) from inputTwo
        cardIndex = Integer.getInteger(inputTwo);


        // assigns PileType and Integer from inputThree
        String firstCharInputThree = this.firstChar(inputThree);
        source = this.pullOutPileType(firstCharInputThree);
        String restInputThree = inputThree.substring(1);
        // If no Integer found, destPileNumber will be null
        destPileNumber = Integer.getInteger(restInputThree);


        // asks for reinput if null values were returned to the relevant inputs
        if (pileNumber == null || source == null) {
          this.askForReinput("SourcePile");
        } else {
          inputOneValid = true;
        }
        if (cardIndex == null) {
          this.askForReinput("CardIndex");
        } else {
          inputTwoValid = true;
        }
        if (destPileNumber == null || destination == null) {
          this.askForReinput("DestinationPile");
        } else {
          inputThreeValid = true;
        }

        try {
          model.move(source, pileNumber, cardIndex, destination, destPileNumber);
          this.transmit(model.getGameState());
        } catch (IllegalArgumentException e) {
          this.transmit("Invalid move. Try again." + e.toString());
        }

        // checks if game is over
        if (model.isGameOver()) {
          // the game is over legally
          this.transmit(model.getGameState());
          this.transmit("Game over.");
          this.toggleRunning();
          return;
        } else {
          this.processInput(model);
        }
      }
    }
  }

  /**
   * Transmits that an input is invalid and asks for the input specified by the message be reinput.
   *
   * @param message specifies which input should be reinput
   */
  private void askForReinput(String message) {
    this.transmit("Please input: " + message + " again.");
  }

  /**
   * Outputs the PileType of the given String (or null if malformed [ie not "C" "F" or "O"]).
   *
   * @param firstChar the first character of the inputted string
   * @return the requisite PileType
   */
  private PileType pullOutPileType(String firstChar) {
    PileType specifiedPileType;

    switch (firstChar) {
      case "C":
        specifiedPileType = PileType.CASCADE;
        break;
      case "F":
        specifiedPileType = PileType.FOUNDATION;
        break;
      case "O":
        specifiedPileType = PileType.OPEN;
        break;
      default:
        specifiedPileType = null;
    }
    return specifiedPileType;
  }

  /**
   * Returns the first character of the given string, or null if empty.
   *
   * @param string the given string
   * @return first character of the given String in the form of a String
   */
  private String firstChar(String string) {
    if (string.length() == 0) {
      return null;
    } else {
      return string.substring(0, 1);
    }
  }

  @Override
  public List<Card> haveModelGetDeck(FreecellModel model) {
    return model.getDeck();
  }

  /**
   * Validates the given deck as acceptable (non-null).
   *
   * @param deck the given deck
   * @throws IllegalArgumentException if deck is null
   */
  private void validate(List<Card> deck) {
    try {
      Objects.requireNonNull(deck);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("The given deck is null.");
    }
  }

  /**
   * Validates the given model as acceptable (non-null).
   *
   * @param model the given model
   * @throws IllegalArgumentException if model is null
   */
  private void validate(FreecellOperations model) {
    try {
      Objects.requireNonNull(model);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("The given model is null.");
    }
  }

  /**
   * Validates the given Readable as acceptable (i.e. non-null).
   *
   * @param rd the given Readable
   * @throws IllegalStateException if Readable is null
   */
  private Readable validate(Readable rd) {
    try {
      Objects.requireNonNull(rd);
    } catch (NullPointerException e) {
      throw new IllegalStateException("The given Readable points to null.");
    }
    return rd;
  }

  /**
   * Validates the given Appendable as acceptable (i.e. non-null).
   *
   * @param ap the given Appendable
   * @throws IllegalStateException if Appendable is null
   */
  private Appendable validate(Appendable ap) {
    try {
      Objects.requireNonNull(ap);
    } catch (NullPointerException e) {
      throw new IllegalStateException("The given Appendable points to null.");
    }
    return ap;
  }
}
