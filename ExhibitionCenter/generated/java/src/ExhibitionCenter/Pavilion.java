package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Pavilion extends Installation {
  public VDMSet rooms = SetUtil.set();
  public VDMSet foyers = SetUtil.set();

  public void cg_init_Pavilion_2() {

    return;
  }

  public void cg_init_Pavilion_1(
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
      final Boolean compN) {

    id = i;
    price = pr;
    capacity = c;
    heigth = h;
    Number atomicTmp_19 = w;
    Number atomicTmp_20 = l;
    Number atomicTmp_21 = w.doubleValue() * l.doubleValue();
    {
        /* Start of atomic statement */
      width = atomicTmp_19;
      lenght = atomicTmp_20;
      area = atomicTmp_21;
    } /* End of atomic statement */

    airCondition = airC;
    naturalLigth = natL;
    ceilingLighting = ceilL;
    blackOutCurtains = blackC;
    computerNetwork = compN;
  }

  public Pavilion(
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
      final Boolean compN) {

    cg_init_Pavilion_1(i, pr, c, h, w, l, airC, natL, ceilL, blackC, compN);
  }

  public Pavilion() {

    cg_init_Pavilion_2();
  }

  public void setConditions(
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean blackC,
      final Boolean ignorePattern_27,
      final Boolean compN,
      final Boolean ignorePattern_28,
      final Boolean ignorePattern_29) {

    airCondition = airC;
    naturalLigth = natL;
    ceilingLighting = ceilL;
    blackOutCurtains = blackC;
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
                new Maplet("Black Out Curtains", Utils_vdm.toStringVDM(blackOutCurtains)),
                new Maplet("Computer Network", Utils_vdm.toStringVDM(computerNetwork))));
    return Utils.copy(res);
  }

  public void addRoom(final Room r) {

    rooms = SetUtil.union(Utils.copy(rooms), SetUtil.set(r));
  }

  public void addFoyer(final Foyer f) {

    foyers = SetUtil.union(Utils.copy(foyers), SetUtil.set(f));
  }

  public void removeRoom(final Room r) {

    rooms = SetUtil.diff(Utils.copy(rooms), SetUtil.set(r));
  }

  public void removeFoyer(final Foyer f) {

    foyers = SetUtil.diff(Utils.copy(foyers), SetUtil.set(f));
  }

  public Boolean hasInstallation(final Installation inst) {

    if (inst instanceof Foyer) {
      final Foyer f = ((Foyer) inst);

      return SetUtil.inSet(f, foyers);

    } else if (inst instanceof Room) {
      final Room r = ((Room) inst);

      return SetUtil.inSet(r, rooms);

    } else {
      return false;
    }
  }

  public String toString() {

    return "Pavilion{"
        + "rooms := "
        + Utils.toString(rooms)
        + ", foyers := "
        + Utils.toString(foyers)
        + "}";
  }
}
