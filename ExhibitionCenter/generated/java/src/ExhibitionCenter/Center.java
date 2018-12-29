package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Center {
  private static final String adminName = "admin";
  private static final String adminPass = "admin1234";
  public String name;
  public VDMMap installations;
  public VDMMap services = MapUtil.map();
  public VDMMap events = MapUtil.map();
  public VDMMap users = MapUtil.map();

  public void cg_init_Center_1(final String n, final Installation inst) {

    {
      final String instID = inst.getID();
      installations = MapUtil.map(new Maplet(instID, inst));
    }

    name = n;
  }

  public Center(final String n, final Installation inst) {

    cg_init_Center_1(n, inst);
  }

  public VDMMap showInstallationDetails(final String inst) {

    return ((Installation) Utils.get(installations, inst)).showDetails();
  }

  public VDMSet showInstallationsDetails() {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_10 = MapUtil.dom(Utils.copy(installations)).iterator();
        iterator_10.hasNext();
        ) {
      String inst = (String) iterator_10.next();
      res = SetUtil.union(Utils.copy(res), SetUtil.set(showInstallationDetails(inst)));
    }
    return Utils.copy(res);
  }

  public void addInstallations(final String uName, final Installation inst) {

    final String instID = inst.getID();
    installations =
        MapUtil.override(Utils.copy(installations), MapUtil.map(new Maplet(instID, inst)));
  }

  public void addInstallationToInstallation(
      final String uName, final String instID, final Installation inst1) {

    installations =
        MapUtil.override(Utils.copy(installations), MapUtil.map(new Maplet(inst1.id, inst1)));
    {
      final Installation inst2 = ((Installation) Utils.get(installations, instID));
      {
        if (inst1 instanceof Foyer) {
          final Foyer foyer = ((Foyer) inst1);
          inst2.addFoyer(foyer);

        } else if (inst1 instanceof Room) {
          final Room room = ((Room) inst1);
          inst2.addRoom(room);
        }
      }
    }
  }

  public void removeInstallationFromInstallation(
      final String uName, final String instID, final Installation inst1) {

    {
      final Installation inst2 = ((Installation) Utils.get(installations, instID));
      {
        if (inst1 instanceof Foyer) {
          final Foyer foyer = ((Foyer) inst1);
          inst2.removeFoyer(foyer);

        } else if (inst1 instanceof Room) {
          final Room room = ((Room) inst1);
          inst2.removeRoom(room);
        }
      }
    }
  }

  public void removeInstallation(final String uName, final String instID) {

    VDMSet instToRemove = SetUtil.set(instID);
    for (Iterator iterator_11 =
            SetUtil.diff(
                    MapUtil.rng(Utils.copy(installations)),
                    SetUtil.set(((Installation) Utils.get(installations, instID))))
                .iterator();
        iterator_11.hasNext();
        ) {
      Installation inst = (Installation) iterator_11.next();
      if (hasInstallation(inst, ((Installation) Utils.get(installations, instID)))) {
        instToRemove = SetUtil.union(Utils.copy(instToRemove), SetUtil.set(inst.id));
      }
    }
    installations = MapUtil.domResBy(Utils.copy(instToRemove), Utils.copy(installations));
  }

  public void changeInstallationMeasures(
      final String uName,
      final String inst,
      final Number c,
      final Number h,
      final Number w,
      final Number l) {

    ((Installation) Utils.get(installations, inst)).setMeasures(c, h, w, l);
  }

  public void changeInstallationRent(final String uName, final String inst, final Number pr) {

    ((Installation) Utils.get(installations, inst)).setPrice(pr);
  }

  public void changeInstallationConditions(
      final String uName,
      final String inst,
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean blackC,
      final Boolean tele,
      final Boolean compN,
      final Boolean soundW,
      final Boolean movW) {

    ((Installation) Utils.get(installations, inst))
        .setConditions(airC, natL, ceilL, blackC, tele, compN, soundW, movW);
  }

  public VDMMap getAvailableInstallations(final Utils_vdm.Date b, final Utils_vdm.Date e) {

    VDMSet tmpUsedInsts = SetUtil.set();
    VDMSet usedInsts = SetUtil.set();
    VDMMap tmpFreeInsts = MapUtil.map();
    for (Iterator iterator_12 =
            MapUtil.rng(
                    Utils_vdm.fMap(
                            new Func_2<Event, Boolean>() {
                              public Boolean eval(final Event x) {

                                return hasDatesConflict(x, Utils.copy(b), Utils.copy(e));
                              }
                            })
                        .eval(
                            Utils_vdm.setTOseq(MapUtil.dom(Utils.copy(events))),
                            Utils.copy(events)))
                .iterator();
        iterator_12.hasNext();
        ) {
      Event ev = (Event) iterator_12.next();
      tmpUsedInsts = SetUtil.union(Utils.copy(tmpUsedInsts), SetUtil.set(ev.installation));
    }
    tmpFreeInsts = MapUtil.rngResBy(Utils.copy(installations), Utils.copy(usedInsts));
    usedInsts = Utils.copy(tmpUsedInsts);
    for (Iterator iterator_13 = MapUtil.rng(Utils.copy(tmpFreeInsts)).iterator();
        iterator_13.hasNext();
        ) {
      Installation inst1 = (Installation) iterator_13.next();
      for (Iterator iterator_14 = tmpUsedInsts.iterator(); iterator_14.hasNext(); ) {
        Installation inst2 = (Installation) iterator_14.next();
        if (inst1.hasInstallation(inst2)) {
          usedInsts = SetUtil.union(Utils.copy(usedInsts), SetUtil.set(inst1));
        }
      }
    }
    return MapUtil.rngResBy(Utils.copy(tmpFreeInsts), Utils.copy(usedInsts));
  }

  public Boolean associatedInstallations(final Installation inst1, final Installation inst2) {

    if (Utils.equals(inst1, inst2)) {
      return true;

    } else {
      return inst1.hasInstallation(inst2);
    }
  }

  public VDMSet showServicesDetails() {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_15 = MapUtil.rng(Utils.copy(services)).iterator();
        iterator_15.hasNext();
        ) {
      Service service = (Service) iterator_15.next();
      res = SetUtil.union(Utils.copy(res), SetUtil.set(service.showDetails()));
    }
    return Utils.copy(res);
  }

  public void addService(final String uName, final Service serv) {

    final Object type = serv.type;
    services = MapUtil.override(Utils.copy(services), MapUtil.map(new Maplet(type, serv)));
  }

  public void removeService(final String uName, final Object service) {

    for (Iterator iterator_16 = MapUtil.rng(Utils.copy(events)).iterator();
        iterator_16.hasNext();
        ) {
      Event e = (Event) iterator_16.next();
      if (SetUtil.inSet(service, SeqUtil.elems(e.services))) {
        e.removeService(service);
      }
    }
    services = MapUtil.domResBy(SetUtil.set(service), Utils.copy(services));
  }

  public void changeServicePrice(final String uName, final Object serviceType, final Number price) {

    final Service service = ((Service) Utils.get(services, serviceType));
    service.setPrice(price);
  }

  public Event createEvent(
      final String n,
      final Number tTickets,
      final Number tPrice,
      final Utils_vdm.Date b,
      final Utils_vdm.Date e,
      final Boolean p,
      final Object t,
      final Installation inst,
      final String h) {

    Event event =
        new Event(n, tTickets, tPrice, Utils.copy(b), Utils.copy(e), p, ((Object) t), inst, h);
    events = MapUtil.override(Utils.copy(events), MapUtil.map(new Maplet(n, event)));
    return event;
  }

  public VDMMap showEventDetails(final String uName, final String evName) {

    return ((Event) Utils.get(events, evName)).showDetails();
  }

  public Number moneySpentWithServices(final String uName, final String evName) {

    Event ev = ((Event) Utils.get(events, evName));
    Number res = 0L;
    for (Iterator iterator_17 = SeqUtil.elems(ev.services).iterator(); iterator_17.hasNext(); ) {
      Object service = (Object) iterator_17.next();
      res = res.doubleValue() + ((Service) Utils.get(services, service)).price.doubleValue();
    }
    return res;
  }

  public Number moneySpentWithInstallation(final String uName, final String evName) {

    Number days =
        Utils_vdm.getDatesDifference(
            ((Event) Utils.get(events, evName)).begin, ((Event) Utils.get(events, evName)).ending);
    return days.longValue() * ((Event) Utils.get(events, evName)).ticketPrice.doubleValue();
  }

  public void inviteToEvent(final String evName, final String hName, final String uName) {

    ((Event) Utils.get(events, evName)).inviteUser(hName, uName);
  }

  public VDMSet showAvailableEvents(final String uName) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_18 = MapUtil.rng(Utils.copy(events)).iterator();
        iterator_18.hasNext();
        ) {
      Event e = (Event) iterator_18.next();
      if (Utils.equals(e.attendees.size(), e.totalTickets)) {
        /* skip */
      }

      Boolean andResult_64 = false;

      if (e.privacy) {
        if (SetUtil.inSet(uName, e.guests)) {
          andResult_64 = true;
        }
      }

      if (andResult_64) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(e));
      }

      if (!(e.privacy)) {
        res = SetUtil.union(Utils.copy(res), SetUtil.set(e));
      }
    }
    return Utils.copy(res);
  }

  public VDMSet showAvailableEventsBetweenDates(
      final String uName, final Utils_vdm.Date b, final Utils_vdm.Date e) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_19 = MapUtil.rng(Utils.copy(events)).iterator();
        iterator_19.hasNext();
        ) {
      Event ev = (Event) iterator_19.next();
      if (hasDatesConflict(ev, Utils.copy(b), Utils.copy(e))) {
        if (Utils.equals(ev.attendees.size(), ev.totalTickets)) {
          /* skip */
        }

        Boolean andResult_67 = false;

        if (ev.privacy) {
          if (SetUtil.inSet(uName, ev.guests)) {
            andResult_67 = true;
          }
        }

        if (andResult_67) {
          res = SetUtil.union(Utils.copy(res), SetUtil.set(ev));
        }

        if (!(ev.privacy)) {
          res = SetUtil.union(Utils.copy(res), SetUtil.set(ev));
        }
      }
    }
    return Utils.copy(res);
  }

  public void changeEventName(final String evName, final String hName, final String value) {

    Event ev = ((Event) Utils.get(events, evName));
    ev.setName(value);
    events = MapUtil.override(Utils.copy(events), MapUtil.map(new Maplet(value, ev)));
  }

  public void changeEventTotalTickets(final String evName, final String hName, final Number value) {

    Event ev = ((Event) Utils.get(events, evName));
    ev.setTotalTickets(value);
  }

  public void changeEventTicketPrice(final String evName, final String hName, final Number value) {

    Event ev = ((Event) Utils.get(events, evName));
    ev.setTicketPrice(value);
  }

  public void changeEventDate(
      final String evName, final String hName, final Object dateType, final Utils_vdm.Date value) {

    Event ev = ((Event) Utils.get(events, evName));
    Object quotePattern_1 = dateType;
    Boolean success_1 =
        Utils.equals(quotePattern_1, ExhibitionCenter.quotes.beginQuote.getInstance());

    if (!(success_1)) {
      Object quotePattern_2 = dateType;
      success_1 = Utils.equals(quotePattern_2, ExhibitionCenter.quotes.endingQuote.getInstance());

      if (success_1) {
        ev.setEndingDate(Utils.copy(value));
      }

    } else {
      ev.setBeginDate(Utils.copy(value));
    }
  }

  public void changeEventPrivacy(final String evName, final String hName, final Boolean value) {

    Event ev = ((Event) Utils.get(events, evName));
    ev.setPrivacy(value);
  }

  public void changeEventType(final String evName, final String hName, final Object value) {

    Event ev = ((Event) Utils.get(events, evName));
    ev.setType(value);
  }

  public void changeEventInstallation(
      final String evName, final String hName, final String instName) {

    Event ev = ((Event) Utils.get(events, evName));
    ev.changeInstallation(((Installation) Utils.get(installations, instName)));
  }

  public void addServiceToEvent(final String evName, final String hName, final Object servType) {

    ((Event) Utils.get(events, evName)).addService(servType);
  }

  public void removeServiceFromEvent(
      final String evName, final String hName, final Object servType) {

    ((Event) Utils.get(events, evName)).removeService(servType);
  }

  public VDMMap showUserDetails(final String uName1, final String uName2) {

    VDMMap res = ((User) Utils.get(users, uName2)).showDetails();
    Number money = 0L;
    for (Iterator iterator_20 = ((User) Utils.get(users, uName2)).events.iterator();
        iterator_20.hasNext();
        ) {
      String evName = (String) iterator_20.next();
      if (SetUtil.inSet(uName2, ((Event) Utils.get(events, evName)).attendees)) {
        money = money.doubleValue() + ((Event) Utils.get(events, evName)).ticketPrice.doubleValue();
      }
    }
    res =
        MapUtil.override(
            Utils.copy(res), MapUtil.map(new Maplet("Money spent", Utils_vdm.toStringVDM(money))));
    return Utils.copy(res);
  }

  public VDMSet showUsersDetails(final String uName) {

    VDMSet res = SetUtil.set();
    for (Iterator iterator_21 = MapUtil.dom(Utils.copy(users)).iterator();
        iterator_21.hasNext();
        ) {
      String user = (String) iterator_21.next();
      res = SetUtil.union(Utils.copy(res), SetUtil.set(showUserDetails(uName, user)));
    }
    return Utils.copy(res);
  }

  public void addUser(final User user) {

    users = MapUtil.override(Utils.copy(users), MapUtil.map(new Maplet(user.name, user)));
  }

  public void addUserToEvent(final String evName, final String uName, final Object uPos) {

    Event ev = ((Event) Utils.get(events, evName));
    User user = ((User) Utils.get(users, uName));
    Object quotePattern_3 = uPos;
    Boolean success_2 =
        Utils.equals(quotePattern_3, ExhibitionCenter.quotes.attendeeQuote.getInstance());

    if (!(success_2)) {
      Object quotePattern_4 = uPos;
      success_2 = Utils.equals(quotePattern_4, ExhibitionCenter.quotes.staffQuote.getInstance());

      if (!(success_2)) {
        Object quotePattern_5 = uPos;
        success_2 = Utils.equals(quotePattern_5, ExhibitionCenter.quotes.hostQuote.getInstance());

        if (success_2) {
          {
            ev.setHost(uName);
            user.addEvent(evName);
          }
        }

      } else {
        {
          ev.addStaff(uName);
          user.addEvent(evName);
        }
      }

    } else {
      {
        ev.addAttendee(uName);
        user.addEvent(evName);
      }
    }
  }

  public void removeUserFromEvent(final String evName, final String uName, final Object uPos) {

    Event ev = ((Event) Utils.get(events, evName));
    User user = ((User) Utils.get(users, uName));
    Object quotePattern_6 = uPos;
    Boolean success_3 =
        Utils.equals(quotePattern_6, ExhibitionCenter.quotes.attendeeQuote.getInstance());

    if (!(success_3)) {
      Object quotePattern_7 = uPos;
      success_3 = Utils.equals(quotePattern_7, ExhibitionCenter.quotes.staffQuote.getInstance());

      if (success_3) {
        {
          ev.removeStaff(uName);
          user.removeEvent(evName);
        }
      }

    } else {
      {
        ev.removeAttendee(uName);
        user.removeEvent(evName);
      }
    }
  }

  public Center() {}

  public static Boolean hasInstallation(final Installation inst1, final Installation inst2) {

    if (inst2 instanceof Auditorium) {
      final Auditorium inst = ((Auditorium) inst2);

      Boolean ternaryIfExp_2 = null;

      if (inst1 instanceof Foyer) {
        final Foyer foyer = ((Foyer) inst1);

        ternaryIfExp_2 = SetUtil.inSet(foyer, inst.foyers);

      } else {
        ternaryIfExp_2 = false;
      }

      return ternaryIfExp_2;

    } else {
      if (inst2 instanceof Pavilion) {
        final Pavilion inst = ((Pavilion) inst2);

        Boolean ternaryIfExp_3 = null;

        if (inst1 instanceof Foyer) {
          final Foyer foyer = ((Foyer) inst1);

          ternaryIfExp_3 = SetUtil.inSet(foyer, inst.foyers);

        } else {
          final Room room = ((Room) inst1);

          ternaryIfExp_3 = SetUtil.inSet(room, inst.rooms);
        }

        return ternaryIfExp_3;

      } else {
        return false;
      }
    }
  }

  public static Boolean hasDatesConflict(
      final Event ev, final Utils_vdm.Date b2, final Utils_vdm.Date e2) {

    if (Utils_vdm.before(Utils.copy(b2), ev.begin)) {
      return Utils_vdm.before(ev.begin, Utils.copy(e2));

    } else {
      return Utils_vdm.before(Utils.copy(b2), ev.ending);
    }
  }

  public String toString() {

    return "Center{"
        + "adminName = "
        + Utils.toString(adminName)
        + ", adminPass = "
        + Utils.toString(adminPass)
        + ", name := "
        + Utils.toString(name)
        + ", installations := "
        + Utils.toString(installations)
        + ", services := "
        + Utils.toString(services)
        + ", events := "
        + Utils.toString(events)
        + ", users := "
        + Utils.toString(users)
        + "}";
  }
}
