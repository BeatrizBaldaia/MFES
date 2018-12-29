package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TradeFairQuote {
  private static int hc = 0;
  private static TradeFairQuote instance = null;

  public TradeFairQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static TradeFairQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new TradeFairQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof TradeFairQuote;
  }

  public String toString() {

    return "<TradeFair>";
  }
}
