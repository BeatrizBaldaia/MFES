package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CenterTest extends Test_vdm {
  public Center createCenter(final String name, final Installation inst) {

    Center res = new Center(name, inst);
    res.addUser(new User("admin", "admin1234"));
    return res;
  }

  public void testInstallations(final Center center) {

    Installation room1 =
        new Room(
            "Room1", 15L, 10L, 7L, 20L, 20L, false, true, true, true, false, false, true, false);
    Installation pavilion1 =
        new Pavilion("Pavilion1", 150L, 50L, 10L, 50L, 70L, false, false, true, false, false);
    Installation foyer2 = new Foyer("Foyer2", 10L, 15L, 4L, 6L, 6L, false, true, false, true);
    Installation parking1 = new CarParking("Car Parking1", 30L, 50L, 7L, 50L, 50L);
    Installation auditorium1 =
        new Auditorium(
            "Auditorium1", 50L, 120L, 10L, 40L, 40L, true, false, true, false, true, false);
    center.addInstallations("admin", room1);
    center.addInstallations("admin", pavilion1);
    assertTrue(SetUtil.subset(SetUtil.set(room1, pavilion1), MapUtil.rng(center.installations)));
    center.addInstallationToInstallation("admin", "Pavilion1", room1);
    assertTrue(center.associatedInstallations(pavilion1, pavilion1));
    assertTrue(center.associatedInstallations(pavilion1, room1));
    center.addInstallationToInstallation("admin", "Pavilion1", foyer2);
    assertEqual(
        ((Pavilion) ((Installation) Utils.get(center.installations, "Pavilion1"))).rooms,
        SetUtil.set(room1));
    assertEqual(
        ((Pavilion) ((Installation) Utils.get(center.installations, "Pavilion1"))).foyers,
        SetUtil.set(foyer2));
    assertTrue(SetUtil.inSet(foyer2, MapUtil.rng(center.installations)));
    center.removeInstallationFromInstallation("admin", "Pavilion1", room1);
    assertEqual(
        ((Pavilion) ((Installation) Utils.get(center.installations, "Pavilion1"))).rooms,
        SetUtil.set());
    assertTrue(SetUtil.inSet(room1, MapUtil.rng(center.installations)));
    center.removeInstallation("admin", "Pavilion1");
    assertTrue(
        Utils.empty(
            SetUtil.intersect(MapUtil.rng(center.installations), SetUtil.set(pavilion1, foyer2))));
    center.addInstallations("admin", pavilion1);
    center.addInstallations("admin", foyer2);
    center.addInstallations("admin", parking1);
    center.addInstallations("admin", auditorium1);
    center.changeInstallationMeasures("admin", "Room1", 20L, 10L, 10L, 25L);
    center.changeInstallationMeasures("admin", "Pavilion1", 20L, 10L, 10L, 25L);
    center.changeInstallationMeasures("admin", "Foyer2", 20L, 10L, 10L, 25L);
    center.changeInstallationMeasures("admin", "Car Parking1", 20L, 10L, 10L, 25L);
    center.changeInstallationMeasures("admin", "Auditorium1", 20L, 10L, 10L, 25L);
    Boolean andResult_157 = false;

    if (Utils.equals(room1.capacity, 20L)) {
      Boolean andResult_158 = false;

      if (Utils.equals(room1.heigth, 10L)) {
        Boolean andResult_159 = false;

        if (Utils.equals(room1.width, 10L)) {
          if (Utils.equals(room1.lenght, 25L)) {
            andResult_159 = true;
          }
        }

        if (andResult_159) {
          andResult_158 = true;
        }
      }

      if (andResult_158) {
        andResult_157 = true;
      }
    }

    assertTrue(andResult_157);

    center.changeInstallationRent("admin", "Room1", 32L);
    center.changeInstallationRent("admin", "Pavilion1", 32L);
    center.changeInstallationRent("admin", "Foyer2", 32L);
    center.changeInstallationRent("admin", "Car Parking1", 32L);
    center.changeInstallationRent("admin", "Auditorium1", 32L);
    assertEqual(room1.price, 32L);
    center.changeInstallationConditions(
        "admin", "Room1", true, true, true, true, true, true, true, true);
    center.changeInstallationConditions(
        "admin", "Pavilion1", true, true, true, true, true, true, true, true);
    center.changeInstallationConditions(
        "admin", "Foyer2", true, true, true, true, true, true, true, true);
    center.changeInstallationConditions(
        "admin", "Car Parking1", true, true, true, true, true, true, true, true);
    center.changeInstallationConditions(
        "admin", "Auditorium1", true, true, true, true, true, true, true, true);
    Boolean andResult_160 = false;

    if (room1.airCondition) {
      Boolean andResult_161 = false;

      if (room1.naturalLigth) {
        Boolean andResult_162 = false;

        if (room1.ceilingLighting) {
          Boolean andResult_163 = false;

          if (room1.blackOutCurtains) {
            Boolean andResult_164 = false;

            if (room1.telephones) {
              Boolean andResult_165 = false;

              if (room1.computerNetwork) {
                Boolean andResult_166 = false;

                if (room1.soundproofWalls) {
                  if (room1.movingWalls) {
                    andResult_166 = true;
                  }
                }

                if (andResult_166) {
                  andResult_165 = true;
                }
              }

              if (andResult_165) {
                andResult_164 = true;
              }
            }

            if (andResult_164) {
              andResult_163 = true;
            }
          }

          if (andResult_163) {
            andResult_162 = true;
          }
        }

        if (andResult_162) {
          andResult_161 = true;
        }
      }

      if (andResult_161) {
        andResult_160 = true;
      }
    }

    assertTrue(andResult_160);

    Boolean orResult_47 = false;

    if (parking1.airCondition) {
      orResult_47 = true;
    } else {
      Boolean orResult_48 = false;

      if (parking1.naturalLigth) {
        orResult_48 = true;
      } else {
        Boolean orResult_49 = false;

        if (parking1.ceilingLighting) {
          orResult_49 = true;
        } else {
          Boolean orResult_50 = false;

          Boolean andResult_167 = false;

          if (parking1.blackOutCurtains) {
            if (parking1.telephones) {
              andResult_167 = true;
            }
          }

          if (andResult_167) {
            orResult_50 = true;
          } else {
            Boolean orResult_51 = false;

            if (parking1.computerNetwork) {
              orResult_51 = true;
            } else {
              Boolean orResult_52 = false;

              if (parking1.soundproofWalls) {
                orResult_52 = true;
              } else {
                orResult_52 = parking1.movingWalls;
              }

              orResult_51 = orResult_52;
            }

            orResult_50 = orResult_51;
          }

          orResult_49 = orResult_50;
        }

        orResult_48 = orResult_49;
      }

      orResult_47 = orResult_48;
    }

    assertTrue(Utils.equals(orResult_47, false));

    assertEqual(center.showInstallationsDetails().size(), MapUtil.dom(center.installations).size());
  }

  public void testServices(final Center center) {

    Service service1 = new Service(10L, ExhibitionCenter.quotes.AudioVisualQuote.getInstance());
    center.addService("admin", service1);
    assertEqual(MapUtil.dom(center.services).size(), 1L);
    center.removeService("admin", ExhibitionCenter.quotes.AudioVisualQuote.getInstance());
    assertEqual(MapUtil.dom(center.services).size(), 0L);
    center.addService("admin", service1);
    assertEqual(center.showServicesDetails().size(), 1L);
  }

  public void testUsers(final Center center) {

    User user1 = new User("User1", "1234");
    User user2 = new User("User2", "1234");
    User user3 = new User("User3", "1234");
    User user4 = new User("User4", "1234");
    center.addUser(user1);
    center.addUser(user2);
    center.addUser(user3);
    center.addUser(user4);
    assertEqual(MapUtil.dom(center.users).size(), 5L);
    assertEqual(center.showUsersDetails("admin").size(), 5L);
  }

  public void testEvent(final Center center) {

    Event event1 =
        center.createEvent(
            "Event1",
            3L,
            10L,
            new Utils_vdm.Date(2018L, 12L, 1L),
            new Utils_vdm.Date(2018L, 12L, 3L),
            true,
            ExhibitionCenter.quotes.ConferenceQuote.getInstance(),
            ((Installation) Utils.get(center.installations, "Room1")),
            "User1");
    User user1 = ((User) Utils.get(center.users, "User1"));
    User user2 = ((User) Utils.get(center.users, "User2"));
    assertTrue(
        !(SetUtil.inSet(
            "Room1",
            MapUtil.dom(
                center.getAvailableInstallations(
                    new Utils_vdm.Date(2018L, 12L, 1L), new Utils_vdm.Date(2018L, 12L, 3L))))));
    assertEqual(center.showAvailableEvents("User2"), SetUtil.set());
    center.inviteToEvent("Event1", "User1", "User2");
    assertEqual(center.showAvailableEvents("User2"), SetUtil.set(event1));
  }

  public static void main() {

    CenterTest centerTest = new CenterTest();
    Center center =
        centerTest.createCenter(
            "Super Center", new Foyer("Foyer1", 10L, 15L, 4L, 6L, 6L, false, true, false, true));
    centerTest.testInstallations(center);
    centerTest.testServices(center);
    centerTest.testUsers(center);
    centerTest.testEvent(center);
    new InstallationTest().testAll();
    new EventTest().testAll();
    new ServiceTest().testAll();
    new UserTest().testAll();
  }

  public CenterTest() {}

  public String toString() {

    return "CenterTest{}";
  }
}
