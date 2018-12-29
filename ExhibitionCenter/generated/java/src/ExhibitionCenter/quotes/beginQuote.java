package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class beginQuote {
  private static int hc = 0;
  private static beginQuote instance = null;

  public beginQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static beginQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new beginQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof beginQuote;
  }

  public String toString() {

    return "<begin>";
  }
}
