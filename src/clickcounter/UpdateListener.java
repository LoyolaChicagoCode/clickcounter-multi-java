package clickcounter;

import java.util.EventListener;

public interface UpdateListener extends EventListener {
  public void displayUpdated(UpdateEvent e);
}