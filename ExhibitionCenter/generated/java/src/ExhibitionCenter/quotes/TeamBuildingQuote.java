package ExhibitionCenter.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TeamBuildingQuote {
  private static int hc = 0;
  private static TeamBuildingQuote instance = null;

  public TeamBuildingQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static TeamBuildingQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new TeamBuildingQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof TeamBuildingQuote;
  }

  public String toString() {

    return "<TeamBuilding>";
  }
}
