package clickcounter;

import java.awt.event.ActionListener;

/**
 * An interface for ClickCounter presentation classes.
 */

public interface Presentation extends UpdateListener, StateListener {

  void addActionListener(ActionListener l);
  void removeActionListener(ActionListener l);

} // end class Presentation.
