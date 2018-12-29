package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ConferenceQuote {
  private static int hc = 0;
  private static ConferenceQuote instance = null;

  public ConferenceQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ConferenceQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ConferenceQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ConferenceQuote;
  }

  public String toString() {

    return "<Conference>";
  }
}
