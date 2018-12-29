package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MusicalQuote {
  private static int hc = 0;
  private static MusicalQuote instance = null;

  public MusicalQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static MusicalQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new MusicalQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof MusicalQuote;
  }

  public String toString() {

    return "<Musical>";
  }
}
