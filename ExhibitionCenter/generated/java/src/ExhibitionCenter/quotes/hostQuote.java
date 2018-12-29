package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class hostQuote {
  private static int hc = 0;
  private static hostQuote instance = null;

  public hostQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static hostQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new hostQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof hostQuote;
  }

  public String toString() {

    return "<host>";
  }
}
