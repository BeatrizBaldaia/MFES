package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class AudioVisualQuote {
  private static int hc = 0;
  private static AudioVisualQuote instance = null;

  public AudioVisualQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static AudioVisualQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new AudioVisualQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof AudioVisualQuote;
  }

  public String toString() {

    return "<AudioVisual>";
  }
}
