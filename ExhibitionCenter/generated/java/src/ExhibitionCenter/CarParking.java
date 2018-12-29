package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CarParking extends Installation {
  public void cg_init_CarParking_2() {

    return;
  }

  public void cg_init_CarParking_1(
      final String i,
      final Number pr,
      final Number c,
      final Number h,
      final Number w,
      final Number l) {

    id = i;
    price = pr;
    capacity = c;
    heigth = h;
    Number atomicTmp_4 = w;
    Number atomicTmp_5 = l;
    Number atomicTmp_6 = w.doubleValue() * l.doubleValue();
    {
        /* Start of atomic statement */
      width = atomicTmp_4;
      lenght = atomicTmp_5;
      area = atomicTmp_6;
    } /* End of atomic statement */
  }

  public CarParking(
      final String i,
      final Number pr,
      final Number c,
      final Number h,
      final Number w,
      final Number l) {

    cg_init_CarParking_1(i, pr, c, h, w, l);
  }

  public CarParking() {

    cg_init_CarParking_2();
  }

  public void setConditions(
      final Boolean ignorePattern_5,
      final Boolean ignorePattern_6,
      final Boolean ignorePattern_7,
      final Boolean ignorePattern_8,
      final Boolean ignorePattern_9,
      final Boolean ignorePattern_10,
      final Boolean ignorePattern_11,
      final Boolean ignorePattern_12) {

    /* skip */

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
                new Maplet("Lenght", Utils_vdm.toStringVDM(lenght))));
    return Utils.copy(res);
  }

  public void addFoyer(final Foyer ignorePattern_13) {

    return;
  }

  public void removeFoyer(final Foyer ignorePattern_14) {

    return;
  }

  public void addRoom(final Room ignorePattern_15) {

    return;
  }

  public void removeRoom(final Room ignorePattern_16) {

    return;
  }

  public Boolean hasInstallation(final Installation ignorePattern_17) {

    return false;
  }

  public String toString() {

    return "CarParking{}";
  }
}
