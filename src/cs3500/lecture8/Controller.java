package cs3500.lecture8;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

class Controller implements CalcController {
  final Readable in;
  final Appendable out;
  final Calculator model;

  public Controller(Calculator model, Readable in, Appendable out) {
    this.in = new InputStreamReader(in);
    this.out = new OutputStreamWriter(out);
    this.model = model;
  }

  public Controller(Calculator model) {
    this.in = new InputStreamReader(System.in);
    this.out = System.out;
    this.model = model;
  }
  public void go(Calculator calc) {
    Objects.requireNonNull(calc);
    int num1, num2;
    Scanner scan = new Scanner(this.in);
    num1 = scan.nextInt();
    num2 = scan.nextInt();

    try {
      this.out.append(String.format("%d", calc.add(num1, num2)));
    } catch (IOException e) {
      return;
    }
  }
}