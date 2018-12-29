package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DecorationQuote {
  private static int hc = 0;
  private static DecorationQuote instance = null;

  public DecorationQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static DecorationQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new DecorationQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof DecorationQuote;
  }

  public String toString() {

    return "<Decoration>";
  }
}
