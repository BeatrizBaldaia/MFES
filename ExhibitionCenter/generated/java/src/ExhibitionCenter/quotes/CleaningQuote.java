package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CleaningQuote {
  private static int hc = 0;
  private static CleaningQuote instance = null;

  public CleaningQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static CleaningQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new CleaningQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof CleaningQuote;
  }

  public String toString() {

    return "<Cleaning>";
  }
}
