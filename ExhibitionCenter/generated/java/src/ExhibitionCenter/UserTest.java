package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UserTest extends Test_vdm {
  public void testAddEvent(final User u, final Event e) {

    u.addEvent(e.name);
    assertTrue(u.attendEvent(e.name));
  }

  public void testRemoveEvent(final User u, final Event e) {

    u.removeEvent(e.name);
    assertTrue(!(u.attendEvent(e.name)));
  }

  public void testAll() {

    User user1 = new User("anabela", "1234");
    Room room1 =
        new Room(
            "Room1", 15L, 10L, 7L, 20L, 20L, false, true, true, true, false, false, true, false);
    Event event1 =
        new Event(
            "New Years",
            100L,
            1000L,
            new Utils_vdm.Date(2018L, 12L, 31L),
            new Utils_vdm.Date(2019L, 1L, 1L),
            true,
            ExhibitionCenter.quotes.PartyQuote.getInstance(),
            room1,
            user1.name);
    testAddEvent(user1, event1);
    testRemoveEvent(user1, event1);
  }

  public UserTest() {}

  public String toString() {

    return "UserTest{}";
  }
}
