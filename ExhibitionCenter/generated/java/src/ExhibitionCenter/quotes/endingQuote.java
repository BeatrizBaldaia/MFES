package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class endingQuote {
  private static int hc = 0;
  private static endingQuote instance = null;

  public endingQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static endingQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new endingQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof endingQuote;
  }

  public String toString() {

    return "<ending>";
  }
}
