package clickcounter;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Provides the interface behaviour for the interactive ClickCounter.
 */

public class ClickCounterMain extends JFrame {

  private final static int DEFAULT_NUMBER_OF_FRAMES = 5;

  private static int num = 0;

  private JComponent theInterface;

  public ClickCounterMain(JComponent theInterface) {
    num ++;
    this.theInterface = theInterface;
    getContentPane().add(theInterface);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocation(200, 100 * num);
//    setVisible(true);
  }

  public static void main(String args[]) {
    // use the command-line argument for the number of frames if present
    int numberOfFrames = DEFAULT_NUMBER_OF_FRAMES;
    try {
      numberOfFrames = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      System.err.println("usage: clickcounter.ClickCounterMain [ numberOfFrames ]");
    }

    // create a translation independently from the presentation
    ClickCounterTranslation translation = new ClickCounterTranslation();
    translation.setApplication(new ClickCounter(0, 5));

    JFrame[] frames = new JFrame[numberOfFrames];

    for (int i = 0; i < numberOfFrames; i ++) {
      Presentation visibleInterface = new AlternatePresentation();
      translation.addStateListener(visibleInterface);
      translation.addUpdateListener(visibleInterface);
      visibleInterface.addActionListener(translation);
      frames[i] = new ClickCounterMain((JComponent) visibleInterface);
    }

    // initialize the presentations before making them visible
    translation.initInterfaces();

    for (int i = 0; i < numberOfFrames; i ++) {
      frames[i].setVisible(true);
    }
  } // end main.
} // end class ClickCounterTranslation.

