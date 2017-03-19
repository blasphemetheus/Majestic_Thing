package cs3500.hw03;

import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;

/**
 * The interface for a FreecellController, where the only public method should be playGame.
 */
public interface IFreecellController<K> {

  /**
   * Starts a new game of Freecell using the provided model, number of cascade and open piles
   * and the provided deck. If shuffle is set to false the deck is used as is, else it could
   * be shuffled. Throws an IllegalStateException only if the controller hasn't been intitialized
   * properly to recieve input and transmit output. 'Properly' is tbd for ME!.
   *
   * @param deck        the deck given to the controller
   * @param model       the model given to the controller
   * @param numCascades the number of Cascade piles to use
   * @param numOpens    the number of Open piles to use
   * @param shuffle     a boolean saying whether the deck will be shuffled or not
   * @throws IllegalStateException if the controller isn't initialized 'properly'
   */
  public void playGame(List<K> deck, FreecellOperations<K> model, int numCascades,
                       int numOpens, boolean shuffle) throws IllegalStateException;

  /**
   * Has the model get the deck.
   *
   * @return a List of Cards representing the deck
   */
  public List<Card> haveModelGetDeck(FreecellModel model);
}
