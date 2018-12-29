package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class EventTest extends Test_vdm {
  public void addServices(final Event event, final VDMSeq services) {

    VDMSeq oldServicesSet = event.services;
    for (Iterator iterator_25 = SeqUtil.elems(Utils.copy(services)).iterator();
        iterator_25.hasNext();
        ) {
      Object s = (Object) iterator_25.next();
      event.addService(s);
    }
    assertEqual(event.services, SeqUtil.conc(Utils.copy(oldServicesSet), Utils.copy(services)));
  }

  public void removeServices(final Event event, final VDMSeq services) {

    for (Iterator iterator_26 = SeqUtil.elems(Utils.copy(services)).iterator();
        iterator_26.hasNext();
        ) {
      Object s = (Object) iterator_26.next();
      event.removeService(s);
    }
    Boolean forAllExpResult_6 = true;
    VDMSeq set_9 = event.services;
    for (Iterator iterator_9 = set_9.iterator(); iterator_9.hasNext() && forAllExpResult_6; ) {
      Object service = ((Object) iterator_9.next());
      forAllExpResult_6 = !(SetUtil.inSet(service, SeqUtil.elems(Utils.copy(services))));
    }
    assertTrue(forAllExpResult_6);
  }

  public void changeInstallation(final Event event, final VDMSeq installations) {

    VDMSeq tmpInstallations = Utils.copy(installations);
    Boolean whileCond_2 = true;
    while (whileCond_2) {
      whileCond_2 = !(Utils.empty(tmpInstallations));
      if (!(whileCond_2)) {
        break;
      }

      {
        event.changeInstallation(((Installation) tmpInstallations.get(0)));
        tmpInstallations = SeqUtil.tail(Utils.copy(tmpInstallations));
      }
    }

    assertEqual(
        event.installation, ((Installation) Utils.get(installations, installations.size())));
  }

  public void analiseEvent(final Event event, final Number earned, final Number remaining) {

    assertEqual(event.earnedMoney(), earned);
    assertEqual(event.remainingTickets(), remaining);
  }

  public void changeEventUsers(
      final Event event,
      final String type,
      final VDMSet users,
      final String host,
      final Boolean add) {

    String stringPattern_1 = type;
    Boolean success_7 = Utils.equals(stringPattern_1, "attendees");

    if (!(success_7)) {
      String stringPattern_2 = type;
      success_7 = Utils.equals(stringPattern_2, "staff");

      if (!(success_7)) {
        String stringPattern_3 = type;
        success_7 = Utils.equals(stringPattern_3, "host");

        if (!(success_7)) {
          String stringPattern_4 = type;
          success_7 = Utils.equals(stringPattern_4, "guests");

          if (success_7) {
            {
              for (Iterator iterator_27 = users.iterator(); iterator_27.hasNext(); ) {
                String user = (String) iterator_27.next();
                event.inviteUser(host, user);
              }
              assertTrue(SetUtil.subset(users, event.guests));
            }
          }

        } else {
          {
            for (Iterator iterator_28 = users.iterator(); iterator_28.hasNext(); ) {
              String user = (String) iterator_28.next();
              event.setHost(user);
            }
            assertTrue(SetUtil.inSet(event.host, users));
            assertTrue(Utils.empty(SetUtil.intersect(Utils.copy(users), event.attendees)));
            assertTrue(Utils.empty(SetUtil.intersect(Utils.copy(users), event.staff)));
          }
        }

      } else {
        {
          if (add) {
            for (Iterator iterator_29 = users.iterator(); iterator_29.hasNext(); ) {
              String user = (String) iterator_29.next();
              event.addStaff(user);
            }
            assertTrue(SetUtil.subset(users, event.staff));

          } else {
            for (Iterator iterator_30 = users.iterator(); iterator_30.hasNext(); ) {
              String user = (String) iterator_30.next();
              event.removeStaff(user);
            }
            assertTrue(!(SetUtil.subset(users, event.staff)));
          }
        }
      }

    } else {
      {
        if (add) {
          for (Iterator iterator_31 = users.iterator(); iterator_31.hasNext(); ) {
            String user = (String) iterator_31.next();
            event.addAttendee(user);
          }
          assertTrue(SetUtil.subset(users, event.attendees));

        } else {
          for (Iterator iterator_32 = users.iterator(); iterator_32.hasNext(); ) {
            String user = (String) iterator_32.next();
            event.removeAttendee(user);
          }
          assertTrue(!(SetUtil.subset(users, event.attendees)));
        }
      }
    }
  }

  public void showEventDetails(final Event event) {

    VDMMap res = event.showDetails();
    if (event.privacy) {
      assertEqual(MapUtil.dom(Utils.copy(res)).size(), 14L);
    } else {
      assertEqual(MapUtil.dom(Utils.copy(res)).size(), 13L);
    }
  }

  public void testAll() {

    Installation room1 =
        new Room(
            "Room1", 15L, 10L, 7L, 20L, 20L, false, true, true, true, false, false, true, false);
    Installation room2 =
        new Room("Room2", 10L, 5L, 4L, 10L, 20L, true, false, true, true, false, false, true, true);
    Installation pavilion1 =
        new Pavilion("Pavilion1", 150L, 50L, 10L, 50L, 70L, false, false, true, false, false);
    Installation pavilion2 =
        new Pavilion("Pavilion2", 220L, 60L, 15L, 60L, 70L, true, false, true, false, true);
    Installation foyer1 = new Foyer("Foyer1", 10L, 15L, 4L, 6L, 6L, false, true, false, true);
    Event event =
        new Event(
            "Exit Games",
            5L,
            10L,
            new Utils_vdm.Date(2018L, 12L, 28L),
            new Utils_vdm.Date(2018L, 12L, 28L),
            true,
            ExhibitionCenter.quotes.TeamBuildingQuote.getInstance(),
            room1,
            "Beatriz");
    addServices(
        event,
        SeqUtil.seq(
            ExhibitionCenter.quotes.AudioVisualQuote.getInstance(),
            ExhibitionCenter.quotes.CateringQuote.getInstance(),
            ExhibitionCenter.quotes.ITQuote.getInstance()));
    removeServices(
        event,
        SeqUtil.seq(
            ExhibitionCenter.quotes.AudioVisualQuote.getInstance(),
            ExhibitionCenter.quotes.ITQuote.getInstance()));
    changeInstallation(event, SeqUtil.seq(room2, pavilion1, room1, pavilion1));
    analiseEvent(event, 0L, 5L);
    changeEventUsers(event, "guests", SetUtil.set("Ana", "Tiago"), "Beatriz", true);
    changeEventUsers(event, "attendees", SetUtil.set("Ana", "Tiago"), "Beatriz", true);
    changeEventUsers(event, "attendees", SetUtil.set("Tiago"), "Beatriz", false);
    changeEventUsers(event, "staff", SetUtil.set("Eduardo"), "Beatriz", true);
    changeEventUsers(event, "host", SetUtil.set("Tiago"), "Beatriz", true);
    changeEventUsers(event, "host", SetUtil.set("Eduardo"), "Tiago", true);
    changeEventUsers(event, "host", SetUtil.set("Ana"), "Eduardo", true);
    changeEventUsers(event, "staff", SetUtil.set("Eduardo"), "Ana", true);
    changeEventUsers(event, "staff", SetUtil.set("Eduardo"), "Ana", false);
    changeEventUsers(event, "guests", SetUtil.set("Beatriz"), "Ana", true);
    showEventDetails(event);
  }

  public EventTest() {}

  public String toString() {

    return "EventTest{}";
  }
}
