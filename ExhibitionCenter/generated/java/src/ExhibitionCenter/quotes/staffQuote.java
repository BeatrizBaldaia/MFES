package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class staffQuote {
  private static int hc = 0;
  private static staffQuote instance = null;

  public staffQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static staffQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new staffQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof staffQuote;
  }

  public String toString() {

    return "<staff>";
  }
}
