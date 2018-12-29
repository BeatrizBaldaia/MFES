package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class attendeeQuote {
  private static int hc = 0;
  private static attendeeQuote instance = null;

  public attendeeQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static attendeeQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new attendeeQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof attendeeQuote;
  }

  public String toString() {

    return "<attendee>";
  }
}
