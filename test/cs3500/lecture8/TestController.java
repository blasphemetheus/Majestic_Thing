package cs3500.lec08;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;

import cs3500.lecture8.CalcController;
import cs3500.lecture8.Calculator;

import static org.junit.Assert.assertEquals;

public class TestController4 {
  @Test
  public void testGo() throws Exception {
    Calculator model = new Calculator();
    String input = "2 6";

   /// InputStream in = new ByteArrayInputStream(input.getBytes());
    //ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    //PrintStream out = new PrintStream(bytes);

    StringWriter output = new StringWriter();


    CalcController controller = new Controller(model, new StringReader(input), output);
    controller.go(new Calculator());

    assertEquals("7", output.toString());
  }
}