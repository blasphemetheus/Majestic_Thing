package cs3500.hw03;

import java.io.StringReader;

/**
 * An interaction consists of some input to send the program
 * and some output to expect.  We represent it as an object that takes in a StringReader()
 * and a StringBuffer()
 * StringBuilders and produces the intended effects on them
 */
public interface Interaction {
  void apply(StringBuilder in, StringBuilder out);

  void apply(StringReader in, StringBuffer out);
}
