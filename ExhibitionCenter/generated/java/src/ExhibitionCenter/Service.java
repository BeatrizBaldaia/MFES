package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Service {
  public Number price;
  public Object type;

  public void cg_init_Service_1(final Number p, final Object t) {

    price = p;
    type = t;
  }

  public Service(final Number p, final Object t) {

    cg_init_Service_1(p, t);
  }

  public void setPrice(final Number p) {

    price = p;
  }

  public VDMMap showDetails() {

    VDMMap res = MapUtil.map();
    res =
        MapUtil.override(
            Utils.copy(res),
            MapUtil.map(
                new Maplet("Type", typetoStringVDM(((Object) type))),
                new Maplet("Price (per day)", Utils_vdm.toStringVDM(price))));
    return Utils.copy(res);
  }

  public Service() {}

  public static String typetoStringVDM(final Object t) {

    String casesExpResult_2 = null;

    Object quotePattern_13 = t;
    Boolean success_5 =
        Utils.equals(quotePattern_13, ExhibitionCenter.quotes.AudioVisualQuote.getInstance());

    if (!(success_5)) {
      Object quotePattern_14 = t;
      success_5 =
          Utils.equals(quotePattern_14, ExhibitionCenter.quotes.CateringQuote.getInstance());

      if (!(success_5)) {
        Object quotePattern_15 = t;
        success_5 = Utils.equals(quotePattern_15, ExhibitionCenter.quotes.ITQuote.getInstance());

        if (!(success_5)) {
          Object quotePattern_16 = t;
          success_5 =
              Utils.equals(quotePattern_16, ExhibitionCenter.quotes.CleaningQuote.getInstance());

          if (!(success_5)) {
            Object quotePattern_17 = t;
            success_5 =
                Utils.equals(quotePattern_17, ExhibitionCenter.quotes.SecurityQuote.getInstance());

            if (!(success_5)) {
              Object quotePattern_18 = t;
              success_5 =
                  Utils.equals(
                      quotePattern_18, ExhibitionCenter.quotes.DecorationQuote.getInstance());

              if (success_5) {
                casesExpResult_2 = "Decoration";
              }

            } else {
              casesExpResult_2 = "Security";
            }

          } else {
            casesExpResult_2 = "Cleaning";
          }

        } else {
          casesExpResult_2 = "IT";
        }

      } else {
        casesExpResult_2 = "Catering";
      }

    } else {
      casesExpResult_2 = "Audio Visual";
    }

    return casesExpResult_2;
  }

  public String toString() {

    return "Service{"
        + "price := "
        + Utils.toString(price)
        + ", type := "
        + Utils.toString(type)
        + "}";
  }
}
