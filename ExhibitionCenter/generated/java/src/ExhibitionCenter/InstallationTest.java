package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class InstallationTest extends Test_vdm {
  public void testAddFoyer(final Installation inst, final Foyer foy) {

    inst.addFoyer(foy);
    assertTrue(inst.hasInstallation(foy));
  }

  public void testAddRoom(final Installation inst, final Room ro) {

    inst.addRoom(ro);
    assertTrue(inst.hasInstallation(ro));
  }

  public void testRemoveFoyer(final Installation inst, final Foyer foy) {

    inst.removeFoyer(foy);
    assertTrue(!(inst.hasInstallation(foy)));
  }

  public void testRemoveRoom(final Installation inst, final Room ro) {

    inst.removeRoom(ro);
    assertTrue(!(inst.hasInstallation(ro)));
  }

  public void testChangePrice(final Installation inst, final Number pr) {

    inst.setPrice(pr);
    assertTrue(Utils.equals(inst.price, pr));
  }

  public void testHasInstallation(final Installation inst1, final Installation inst2) {

    Boolean a = inst1.hasInstallation(inst2);
    assertTrue(Utils.equals(a, inst1.hasInstallation(inst2)));
  }

  public void testSetMeasures(
      final Installation inst, final Number c, final Number h, final Number w, final Number l) {

    inst.setMeasures(c, h, w, l);
    assertTrue(Utils.equals(inst.capacity, c));
    assertTrue(Utils.equals(inst.heigth, h));
    assertTrue(Utils.equals(inst.width, w));
    assertTrue(Utils.equals(inst.lenght, l));
  }

  public void testSetConditions(
      final Installation inst,
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean blackC,
      final Boolean tele,
      final Boolean compN,
      final Boolean soundW,
      final Boolean movW) {

    inst.setConditions(airC, natL, ceilL, blackC, tele, compN, soundW, movW);
    assertTrue(Utils.equals(inst.airCondition, airC));
    assertTrue(Utils.equals(inst.naturalLigth, natL));
    assertTrue(Utils.equals(inst.ceilingLighting, ceilL));
    assertTrue(Utils.equals(inst.blackOutCurtains, blackC));
    assertTrue(Utils.equals(inst.telephones, tele));
    assertTrue(Utils.equals(inst.computerNetwork, compN));
    assertTrue(Utils.equals(inst.soundproofWalls, soundW));
    assertTrue(Utils.equals(inst.movingWalls, movW));
  }

  public void testAll() {

    Room room1 =
        new Room(
            "Room1", 15L, 10L, 7L, 20L, 20L, false, true, true, true, false, false, true, false);
    Pavilion pavilion1 =
        new Pavilion("Pavilion1", 150L, 50L, 10L, 50L, 70L, false, false, true, false, false);
    Foyer foyer1 = new Foyer("Foyer1", 10L, 15L, 4L, 6L, 6L, false, true, false, true);
    Auditorium auditorium1 =
        new Auditorium("Auditorium1", 10L, 15L, 4L, 6L, 6L, false, true, false, true, true, true);
    CarParking carParking1 = new CarParking("CarParking1", 10L, 15L, 4L, 6L, 6L);
    testAddFoyer(pavilion1, foyer1);
    testAddRoom(pavilion1, room1);
    testRemoveFoyer(pavilion1, foyer1);
    testRemoveRoom(pavilion1, room1);
    testAddFoyer(auditorium1, foyer1);
    testRemoveFoyer(auditorium1, foyer1);
    testChangePrice(room1, 100L);
    testSetMeasures(room1, 1L, 1L, 1L, 1L);
    testSetConditions(room1, false, false, false, false, false, false, false, false);
    testSetConditions(room1, true, true, true, true, true, true, true, true);
    testSetConditions(pavilion1, false, false, false, false, false, false, false, false);
    testSetConditions(pavilion1, true, true, true, true, false, true, false, false);
    testSetConditions(foyer1, false, false, false, false, false, false, false, false);
    testSetConditions(foyer1, true, true, true, false, false, true, false, false);
    testSetConditions(auditorium1, false, false, false, false, false, false, false, false);
    testSetConditions(auditorium1, true, true, true, true, false, true, true, false);
    testHasInstallation(carParking1, room1);
    testHasInstallation(foyer1, room1);
    testHasInstallation(room1, foyer1);
  }

  public InstallationTest() {}

  public String toString() {

    return "InstallationTest{}";
  }
}
