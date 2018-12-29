package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ITQuote {
  private static int hc = 0;
  private static ITQuote instance = null;

  public ITQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ITQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ITQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ITQuote;
  }

  public String toString() {

    return "<IT>";
  }
}
