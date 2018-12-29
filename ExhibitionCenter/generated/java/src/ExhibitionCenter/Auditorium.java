package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Auditorium extends Installation {
  public VDMSet foyers = SetUtil.set();

  public void cg_init_Auditorium_2() {

    return;
  }

  public void cg_init_Auditorium_1(
      final String i,
      final Number pr,
      final Number c,
      final Number h,
      final Number w,
      final Number l,
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean blackC,
      final Boolean compN,
      final Boolean soundP) {

    id = i;
    price = pr;
    capacity = c;
    heigth = h;
    Number atomicTmp_1 = w;
    Number atomicTmp_2 = l;
    Number atomicTmp_3 = w.doubleValue() * l.doubleValue();
    {
        /* Start of atomic statement */
      width = atomicTmp_1;
      lenght = atomicTmp_2;
      area = atomicTmp_3;
    } /* End of atomic statement */

    airCondition = airC;
    naturalLigth = natL;
    ceilingLighting = ceilL;
    blackOutCurtains = blackC;
    computerNetwork = compN;
    soundproofWalls = soundP;
  }

  public Auditorium(
      final String i,
      final Number pr,
      final Number c,
      final Number h,
      final Number w,
      final Number l,
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean blackC,
      final Boolean compN,
      final Boolean soundP) {

    cg_init_Auditorium_1(i, pr, c, h, w, l, airC, natL, ceilL, blackC, compN, soundP);
  }

  public Auditorium() {

    cg_init_Auditorium_2();
  }

  public void setConditions(
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean blackC,
      final Boolean ignorePattern_1,
      final Boolean compN,
      final Boolean soundW,
      final Boolean ignorePattern_2) {

    airCondition = airC;
    naturalLigth = natL;
    ceilingLighting = ceilL;
    blackOutCurtains = blackC;
    computerNetwork = compN;
    soundproofWalls = soundW;
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
                new Maplet("Black Out Curtains", Utils_vdm.toStringVDM(blackOutCurtains)),
                new Maplet("Computer Network", Utils_vdm.toStringVDM(computerNetwork)),
                new Maplet("Soundproof Walls", Utils_vdm.toStringVDM(soundproofWalls))));
    return Utils.copy(res);
  }

  public void addFoyer(final Foyer f) {

    foyers = SetUtil.union(Utils.copy(foyers), SetUtil.set(f));
  }

  public void removeFoyer(final Foyer f) {

    foyers = SetUtil.diff(Utils.copy(foyers), SetUtil.set(f));
  }

  public void addRoom(final Room ignorePattern_3) {

    return;
  }

  public void removeRoom(final Room ignorePattern_4) {

    return;
  }

  public Boolean hasInstallation(final Installation inst) {

    if (inst instanceof Foyer) {
      final Foyer f = ((Foyer) inst);

      return SetUtil.inSet(f, foyers);

    } else {
      return false;
    }
  }

  public String toString() {

    return "Auditorium{" + "foyers := " + Utils.toString(foyers) + "}";
  }
}
