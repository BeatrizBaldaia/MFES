package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SecurityQuote {
  private static int hc = 0;
  private static SecurityQuote instance = null;

  public SecurityQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static SecurityQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new SecurityQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof SecurityQuote;
  }

  public String toString() {

    return "<Security>";
  }
}
