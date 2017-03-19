package cs3500.hw03;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.FreecellModel;

/**
 * Contains the main method, allows us to 'play' Freecell (sans a real view currently).
 */
public class PlayGame {
  public static void main(String[] args) throws IOException {

    InputStream inputStream = System.in;
    OutputStream outputStream = System.out;

    Readable in = new InputStreamReader(inputStream);
    Appendable out = new PrintStream(outputStream);

    // instantiate model
    FreecellModel model = new FreecellModel();


    // instantiate controller
    FreecellController controller = new FreecellController(in, out);

    // play game (passing in Model)
    List<Card> deck = controller.haveModelGetDeck(model);
    controller.playGame(deck, model, 8, 1, false);
  }
}


//TODO MAKE SURE THAT NEWLINE IN TRANSMIT BELONGS
// MA