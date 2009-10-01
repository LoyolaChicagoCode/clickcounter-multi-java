package clickcounter;

import java.util.EventObject;

public class StateEvent extends EventObject {

  public static final int MINIMUM  = 777;
  public static final int COUNTING = 778;
  public static final int MAXIMUM  = 779;

  private int state;

  public StateEvent(Object source, int state) {
    super(source);
    this.state = state;
  }

  public int getState() { return state; }
}