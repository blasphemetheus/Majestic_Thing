package cs3500.lecture8;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Demonstrates a simple command-line-based calculator
 */
public class SimpleCalc {
  public static void main(String[] args) {

    Readable in;
    InputStream inputStream = System.in;

    Appendable out;
    OutputStream outputStream = System.out;

    in = new InputStreamReader(inputStream);
    out = new OutputStreamWriter(outputStream);

    Calculator model = new Calculator();
    Controller controller = new Controller(in, out);

    new Controller(System.in, System.out).go(new Calculator());
  }
}
