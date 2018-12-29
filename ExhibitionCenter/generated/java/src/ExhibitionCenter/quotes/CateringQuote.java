package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CateringQuote {
  private static int hc = 0;
  private static CateringQuote instance = null;

  public CateringQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static CateringQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new CateringQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof CateringQuote;
  }

  public String toString() {

    return "<Catering>";
  }
}
