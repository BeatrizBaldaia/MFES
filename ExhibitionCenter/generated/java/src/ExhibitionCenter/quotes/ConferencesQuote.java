package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ConferencesQuote {
  private static int hc = 0;
  private static ConferencesQuote instance = null;

  public ConferencesQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ConferencesQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ConferencesQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ConferencesQuote;
  }

  public String toString() {

    return "<Conferences>";
  }
}
