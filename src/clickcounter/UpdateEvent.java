package clickcounter;

import java.util.EventObject;

public class UpdateEvent extends EventObject {

  private int value;

  public UpdateEvent(Object source, int value) {
    super(source);
    this.value = value;
  }

  public int getValue() { return value; }
}