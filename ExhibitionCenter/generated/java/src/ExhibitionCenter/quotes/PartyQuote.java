package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PartyQuote {
  private static int hc = 0;
  private static PartyQuote instance = null;

  public PartyQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static PartyQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new PartyQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof PartyQuote;
  }

  public String toString() {

    return "<Party>";
  }
}
