package clickcounter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.event.EventListenerList;

/**
 * Provides the interface behaviour for the interactive
 * ClickCounter.
 */

public class ClickCounterTranslation implements ActionListener, EventLabels {

  private ClickCounter theCounter;
  private ClickCounterState theState;

  /** The list of all types of event listeners. */
  private EventListenerList listeners = new EventListenerList();

  public ClickCounterTranslation() {
    theState = MINIMUM;
  }

  /**
   * This method provides this translation with a reference to the
   * application.
   */
  public void setApplication(ClickCounter app) {
    theCounter = app;
  }

  /**
   * This method provides this translation with a visible interface
   * (presentation).
   */
  public void initInterfaces() {
    scheduleUpdate(theCounter.countIs());
    scheduleState(StateEvent.MINIMUM);
  } // end init.

  public void actionPerformed(ActionEvent event) {
    // handle global events first
    String buttonPressed = event.getActionCommand();
    if (RESET.equals(buttonPressed)) {
        theCounter.reset();
        changeState(MINIMUM);
        scheduleUpdate(theCounter.countIs());
        scheduleState(StateEvent.MINIMUM);
    } else {
      // use the State pattern for handling state-specific events
      theState.actionPerformed(event);
    }
  }

  protected void changeState(ClickCounterState state) {
    theState = state;
  }

  /**
   * Interface to support the State pattern.
   */
  private interface ClickCounterState extends ActionListener {
  }

  private final ClickCounterState MINIMUM = new ClickCounterState() {
    public void actionPerformed(ActionEvent event) {
      String buttonPressed = event.getActionCommand();
      if (INCREMENT.equals(buttonPressed)) {
        theCounter.count();
        if (theCounter.isAtMaximum()) {
          changeState(MAXIMUM);
          scheduleState(StateEvent.MAXIMUM);
        } else {
          changeState(COUNTING);
          scheduleState(StateEvent.COUNTING);
        }
        scheduleUpdate(theCounter.countIs());
      }
    }
  };

  private final ClickCounterState COUNTING = new ClickCounterState() {
    public void actionPerformed(ActionEvent event) {
      String buttonPressed = event.getActionCommand();
      if (INCREMENT.equals(buttonPressed)) {
        theCounter.count();
        if (theCounter.isAtMaximum()) {
          changeState(MAXIMUM);
          scheduleState(StateEvent.MAXIMUM);
        }
        scheduleUpdate(theCounter.countIs());
      } else if (DECREMENT.equals(buttonPressed)) {
        theCounter.unCount();
        if (theCounter.isAtMinimum()) {
          changeState(MINIMUM);
          scheduleState(StateEvent.MINIMUM);
        } // End if.
        scheduleUpdate(theCounter.countIs());
      } // End if.
    }
  };

  private final ClickCounterState MAXIMUM = new ClickCounterState() {
    public void actionPerformed(ActionEvent event) {
      String buttonPressed = event.getActionCommand();
      if (DECREMENT.equals(buttonPressed)) {
        theCounter.unCount();
        if (theCounter.isAtMinimum()) {
          changeState(MINIMUM);
          scheduleState(StateEvent.MINIMUM);
        } else {
          changeState(COUNTING);
          scheduleState(StateEvent.COUNTING);
        } // End if.
        scheduleUpdate(theCounter.countIs());
      } // End if.
    }
  };

  public synchronized void addUpdateListener(UpdateListener l) {
    listeners.add(UpdateListener.class, l);
  }

  public synchronized void removeUpdateListener(UpdateListener l) {
    listeners.remove(UpdateListener.class, l);
  }

  public synchronized void addStateListener(StateListener l) {
    listeners.add(StateListener.class, l);
  }

  public synchronized void removeStateListener(StateListener l) {
    listeners.remove(StateListener.class, l);
  }

  protected void scheduleUpdate(final int value) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        fireUpdate(value);
      }
    });
  }

  protected void scheduleState(final int state) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        fireState(state);
      }
    });
  }

  protected void fireUpdate(int value) {
    UpdateEvent e = new UpdateEvent(this, value);
    EventListener[] ls = listeners.getListeners(UpdateListener.class);
    for (int i = ls.length - 1; i >= 0; i -= 1) {
      ((UpdateListener) ls[i]).displayUpdated(e);
    }
  }

  protected void fireState(int state) {
    StateEvent e = new StateEvent(this, state);
    EventListener[] ls = listeners.getListeners(StateListener.class);
    for (int i = ls.length - 1; i >= 0; i -= 1) {
      ((StateListener) ls[i]).stateChanged(e);
    }
  }
} // end class ClickCounterTranslation.
