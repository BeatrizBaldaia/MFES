package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Foyer extends Installation {
  public void cg_init_Foyer_2() {

    return;
  }

  public void cg_init_Foyer_1(
      final String i,
      final Number pr,
      final Number c,
      final Number h,
      final Number w,
      final Number l,
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean compN) {

    id = i;
    price = pr;
    capacity = c;
    heigth = h;
    Number atomicTmp_11 = w;
    Number atomicTmp_12 = l;
    Number atomicTmp_13 = w.doubleValue() * l.doubleValue();
    {
        /* Start of atomic statement */
      width = atomicTmp_11;
      lenght = atomicTmp_12;
      area = atomicTmp_13;
    } /* End of atomic statement */

    airCondition = airC;
    naturalLigth = natL;
    ceilingLighting = ceilL;
    computerNetwork = compN;
  }

  public Foyer(
      final String i,
      final Number pr,
      final Number c,
      final Number h,
      final Number w,
      final Number l,
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean compN) {

    cg_init_Foyer_1(i, pr, c, h, w, l, airC, natL, ceilL, compN);
  }

  public Foyer() {

    cg_init_Foyer_2();
  }

  public void setConditions(
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean ignorePattern_18,
      final Boolean ignorePattern_19,
      final Boolean compN,
      final Boolean ignorePattern_20,
      final Boolean ignorePattern_21) {

    airCondition = airC;
    naturalLigth = natL;
    ceilingLighting = ceilL;
    computerNetwork = compN;
  }

  public VDMMap showDetails() {

    VDMMap res = MapUtil.map();
    res =
        MapUtil.override(
            Utils.copy(res),
            MapUtil.map(
                new Maplet("ID", id),
                new Maplet("Price", Utils_vdm.toStringVDM(price)),
                new Maplet("Capacity", Utils_vdm.toStringVDM(capacity)),
                new Maplet("Area", Utils_vdm.toStringVDM(area)),
                new Maplet("Heigth", Utils_vdm.toStringVDM(heigth)),
                new Maplet("Width", Utils_vdm.toStringVDM(width)),
                new Maplet("Lenght", Utils_vdm.toStringVDM(lenght)),
                new Maplet("Air Condition", Utils_vdm.toStringVDM(airCondition)),
                new Maplet("Natural Ligth", Utils_vdm.toStringVDM(naturalLigth)),
                new Maplet("Ceiling Lighting", Utils_vdm.toStringVDM(ceilingLighting)),
                new Maplet("Computer Network", Utils_vdm.toStringVDM(computerNetwork))));
    return Utils.copy(res);
  }

  public void addFoyer(final Foyer ignorePattern_22) {

    return;
  }

  public void removeFoyer(final Foyer ignorePattern_23) {

    return;
  }

  public void addRoom(final Room ignorePattern_24) {

    return;
  }

  public void removeRoom(final Room ignorePattern_25) {

    return;
  }

  public Boolean hasInstallation(final Installation ignorePattern_26) {

    return false;
  }

  public String toString() {

    return "Foyer{}";
  }
}
