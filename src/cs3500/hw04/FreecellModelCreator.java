package cs3500.hw04;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;

/**
 * A class intended to enable the creation of FreecellModels of multimove and singlemove types.
 */
public class FreecellModelCreator {

  /**
   * A static method that creates a FreecellModel based on the gametype passed in.
   *
   * @param type the GameType specified
   * @return a FreecellModel of the specified single or multi move functionality
   */
  public static FreecellOperations<Card> create(GameType type) {
    if (type == GameType.SINGLEMOVE) {
      return new FreecellModel();
    } else if (type == GameType.MULTIMOVE) {
      return new FreecellMultiModel();
    } else {
      throw new IllegalArgumentException("There is an illegal GameType specified: " + type);
    }
  }

  /**
   * Represents a GameType as an enum - either a single or multi move model.
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }
}
