package clickcounter;

/** The ClickCounter application class. */

public class ClickCounter extends Object {

  private static final int DEFAULT_MAXIMUM = 999;
  private static final int DEFAULT_MINIMUM = 0;

  private int minimumCount;
  private int maximumCount;
  private int clicksCounted;

  public ClickCounter() {
    this(DEFAULT_MINIMUM, DEFAULT_MAXIMUM);
  } // End default constructor.

  public ClickCounter(int minimum) {
    this(minimum, DEFAULT_MAXIMUM);
  } // End alternative constructor.

  public ClickCounter(int minimum, int maximum) {
    super();
    minimumCount  = minimum;
    maximumCount  = maximum;
    clicksCounted = minimum;
  } // End alternative constructor.


  public boolean isAtMinimum() {
    return clicksCounted == minimumCount;
  } // End isAtMinimum.

  public boolean isAtMaximum() {
    return clicksCounted == maximumCount;
  } // End isAtMaximum .


  public void count() {
    if (! this.isAtMaximum()) {
      clicksCounted++;
    } // End if.
  } // End count.


  public void unCount() {
    if (! this.isAtMinimum()) {
      clicksCounted--;
    } // End if.
  } // End unCount.


  public void reset() {
    clicksCounted = minimumCount;
  } // End reset.


  public int countIs() {
    return clicksCounted;
  } // End countIs.
} // End ClickCounter;
