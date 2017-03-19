package cs3500.music.view;


import java.awt.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events
import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class VisualView extends javax.swing.JFrame implements ViewOperations {
  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel

  // Fossils from the MVC Class Example
  private JLabel display;
  private JButton echoButton, exitButton;
  private JTextField input;


  /**
   * Default public constructor, creates new VisualView.
   */
  public VisualView() {
    super("Music Visualizer");

    // FOSSIL:
    //    setSize(500, 300);
    //    setLocation(200, 200);
    //    this.setResizable(false);
    //		this.setMinimumSize(new Dimension(300,300));

    this.displayPanel = new ConcreteGuiViewPanel();
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);

    //FOSSIL UNTIL PACK() ------------------------------
    this.setLayout(new FlowLayout());

    display = new JLabel("To be displayed");
    //label = new JLabel(new ImageIcon("Jellyfish.JPG"));

    this.add(display);

    //the textfield
    input = new JTextField(10);
    this.add(input);

    //echobutton
    echoButton = new JButton("Echo");
    echoButton.setActionCommand("Echo Button");
    this.add(echoButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    // END OF FOSSIL ------------------------------------


    this.pack();
    setVisible(true);
  }

  @Override
  public void initialize(){
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(100, 100);
  }

  /**
   * Returns the state of the Model as a String (in columnar form) <p>Technical Specs: - A column of
   * numbers representing the beats, printed right-justified and padded with leading spaces, that is
   * exactly as wide as necessary. (So if your piece is 999 beats long, it uses three columns of
   * characters; if it’s 1000 beats long, it uses four.) - A sequence of columns, each five
   * characters wide, representing each pitch. The first line prints out the names of the pitches,
   * more-or-less centered within the five-character column. I.e., "  F2 " and " G#3 " and " D#10".
   * Because we need to represent at least ten octaves, three-character columns won’t be wide
   * enough.) - Use exactly as many columns as are needed for your piece, from its lowest to its
   * highest note. - Each note-head is rendered as an "  X  ", and each note-sustain is rendered as
   * "  |  ". When a note is not played, five spaces are rendered (as "     "). - As a consequence:
   * every line should be exactly the same length, as shown above. - Every item, including the last
   * one, ends in a newline. - Use the # character (the standard hash or pound sign) to represent
   * sharps, rather than the more correct ♯, to avoid any formatting errors when running your
   * code.</p>
   *
   * @return the state of the game
   */
  String getStringRepresentation();


}
