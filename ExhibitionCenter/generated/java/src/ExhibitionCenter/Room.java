package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Room extends Installation {
  public void cg_init_Room_2() {

    return;
  }

  public void cg_init_Room_1(
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
      final Boolean tele,
      final Boolean compN,
      final Boolean soundP,
      final Boolean movW) {

    id = i;
    price = pr;
    capacity = c;
    heigth = h;
    Number atomicTmp_22 = w;
    Number atomicTmp_23 = l;
    Number atomicTmp_24 = w.doubleValue() * l.doubleValue();
    {
        /* Start of atomic statement */
      width = atomicTmp_22;
      lenght = atomicTmp_23;
      area = atomicTmp_24;
    } /* End of atomic statement */

    airCondition = airC;
    naturalLigth = natL;
    ceilingLighting = ceilL;
    blackOutCurtains = blackC;
    telephones = tele;
    computerNetwork = compN;
    soundproofWalls = soundP;
    movingWalls = movW;
  }

  public Room(
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
      final Boolean tele,
      final Boolean compN,
      final Boolean soundP,
      final Boolean movW) {

    cg_init_Room_1(i, pr, c, h, w, l, airC, natL, ceilL, blackC, tele, compN, soundP, movW);
  }

  public Room() {

    cg_init_Room_2();
  }

  public void setConditions(
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean blackC,
      final Boolean tele,
      final Boolean compN,
      final Boolean soundW,
      final Boolean movW) {

    airCondition = airC;
    naturalLigth = natL;
    ceilingLighting = ceilL;
    blackOutCurtains = blackC;
    telephones = tele;
    computerNetwork = compN;
    soundproofWalls = soundW;
    movingWalls = movW;
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
                new Maplet("Telephones", Utils_vdm.toStringVDM(telephones)),
                new Maplet("Computer Network", Utils_vdm.toStringVDM(computerNetwork)),
                new Maplet("Soundproof Walls", Utils_vdm.toStringVDM(soundproofWalls)),
                new Maplet("Moving Walls", Utils_vdm.toStringVDM(movingWalls))));
    return Utils.copy(res);
  }

  public void addFoyer(final Foyer ignorePattern_30) {

    return;
  }

  public void removeFoyer(final Foyer ignorePattern_31) {

    return;
  }

  public void addRoom(final Room ignorePattern_32) {

    return;
  }

  public void removeRoom(final Room ignorePattern_33) {

    return;
  }

  public Boolean hasInstallation(final Installation ignorePattern_34) {

    return false;
  }

  public String toString() {

    return "Room{}";
  }
}
