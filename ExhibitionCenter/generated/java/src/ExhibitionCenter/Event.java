package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Event {
  public String name = "untitled";
  public Number totalTickets = 1L;
  public Number ticketPrice = 0L;
  public Utils_vdm.Date begin = new Utils_vdm.Date(2018L, 1L, 1L);
  public Utils_vdm.Date ending = new Utils_vdm.Date(2018L, 1L, 2L);
  public Boolean privacy = false;
  public Object type = null;
  public Installation installation;
  public VDMSeq services = SeqUtil.seq();
  public VDMSet attendees = SetUtil.set();
  public VDMSet staff = SetUtil.set();
  public String host = "undefined";
  public VDMSet guests = SetUtil.set();

  public void cg_init_Event_1(
      final String n,
      final Number tTickets,
      final Number tPrice,
      final Utils_vdm.Date b,
      final Utils_vdm.Date e,
      final Boolean p,
      final Object t,
      final Installation inst,
      final String h) {

    Installation atomicTmp_7 = inst;
    String atomicTmp_8 = h;
    {
        /* Start of atomic statement */
      installation = atomicTmp_7;
      host = atomicTmp_8;
    } /* End of atomic statement */

    name = n;
    totalTickets = tTickets;
    ticketPrice = tPrice;
    Utils_vdm.Date atomicTmp_9 = Utils.copy(b);
    Utils_vdm.Date atomicTmp_10 = Utils.copy(e);
    {
        /* Start of atomic statement */
      begin = Utils.copy(atomicTmp_9);
      ending = Utils.copy(atomicTmp_10);
    } /* End of atomic statement */

    privacy = p;
    type = t;
  }

  public Event(
      final String n,
      final Number tTickets,
      final Number tPrice,
      final Utils_vdm.Date b,
      final Utils_vdm.Date e,
      final Boolean p,
      final Object t,
      final Installation inst,
      final String h) {

    cg_init_Event_1(n, tTickets, tPrice, Utils.copy(b), Utils.copy(e), p, t, inst, h);
  }

  public void setName(final String n) {

    name = n;
  }

  public void setTotalTickets(final Number tTickets) {

    totalTickets = tTickets;
  }

  public void setTicketPrice(final Number tPrice) {

    ticketPrice = tPrice;
  }

  public void setBeginDate(final Utils_vdm.Date b) {

    begin = Utils.copy(b);
  }

  public void setEndingDate(final Utils_vdm.Date e) {

    ending = Utils.copy(e);
  }

  public void setPrivacy(final Boolean p) {

    privacy = p;
    if (p) {
      guests = Utils.copy(attendees);
    }
  }

  public void setType(final Object t) {

    type = t;
  }

  public VDMMap showDetails() {

    VDMMap res = MapUtil.map();
    res =
        MapUtil.override(
            Utils.copy(res),
            MapUtil.map(
                new Maplet("Name", name),
                new Maplet("Total number of tickets", Utils_vdm.toStringVDM(totalTickets)),
                new Maplet(
                    "Sold tickets",
                    Utils_vdm.toStringVDM(totalTickets.longValue() - attendees.size())),
                new Maplet("Ticket's price", Utils_vdm.toStringVDM(ticketPrice)),
                new Maplet("Is private", Utils_vdm.toStringVDM(privacy)),
                new Maplet("Starting date", Utils_vdm.toStringVDM(Utils.copy(begin))),
                new Maplet("Ending date", Utils_vdm.toStringVDM(Utils.copy(ending))),
                new Maplet("Installation", installation.id),
                new Maplet("Event type", typetoStringVDM(((Object) type))),
                new Maplet("Services", servicestoStringVDM()),
                new Maplet("Attendees", usersSettoStringVDM(Utils.copy(attendees))),
                new Maplet("Staff", usersSettoStringVDM(Utils.copy(staff))),
                new Maplet("Host", host)));
    if (privacy) {
      res =
          MapUtil.override(
              Utils.copy(res),
              MapUtil.map(new Maplet("Guests", usersSettoStringVDM(Utils.copy(guests)))));
    }

    return Utils.copy(res);
  }

  public void changeInstallation(final Installation inst) {

    installation = inst;
  }

  public void addService(final Object service) {

    services = SeqUtil.conc(Utils.copy(services), SeqUtil.seq(service));
  }

  public void removeService(final Object service) {

    VDMSeq tmpServices = Utils.copy(services);
    services = SeqUtil.seq();
    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 = !(Utils.empty(tmpServices));
      if (!(whileCond_1)) {
        break;
      }

      {
        if (!(Utils.equals(((Object) tmpServices.get(0)), service))) {
          services = SeqUtil.conc(Utils.copy(services), SeqUtil.seq(((Object) tmpServices.get(0))));
        }

        tmpServices = SeqUtil.tail(Utils.copy(tmpServices));
      }
    }
  }

  public Number earnedMoney() {

    return attendees.size() * ticketPrice.doubleValue();
  }

  public Number remainingTickets() {

    return totalTickets.longValue() - attendees.size();
  }

  public void setHost(final String user) {

    if (SetUtil.inSet(user, attendees)) {
      attendees = SetUtil.diff(Utils.copy(attendees), SetUtil.set(user));
    } else if (SetUtil.inSet(user, staff)) {
      staff = SetUtil.diff(Utils.copy(staff), SetUtil.set(user));
    }

    host = user;
  }

  public void addAttendee(final String user) {

    attendees = SetUtil.union(Utils.copy(attendees), SetUtil.set(user));
  }

  public void addStaff(final String user) {

    staff = SetUtil.union(Utils.copy(staff), SetUtil.set(user));
  }

  public void removeAttendee(final String user) {

    attendees = SetUtil.diff(Utils.copy(attendees), SetUtil.set(user));
  }

  public void removeStaff(final String user) {

    staff = SetUtil.diff(Utils.copy(staff), SetUtil.set(user));
  }

  public void inviteUser(final String h, final String user) {

    guests = SetUtil.union(Utils.copy(guests), SetUtil.set(user));
  }

  public String servicestoStringVDM() {

    String res = "";
    if (Utils.equals(services.size(), 0L)) {
      return "";
    }

    for (Iterator iterator_22 = SeqUtil.elems(Utils.copy(services)).iterator();
        iterator_22.hasNext();
        ) {
      Object service = (Object) iterator_22.next();
      res = res + Service.typetoStringVDM(((Object) service)) + ", ";
    }
    res = SeqUtil.subSeq(res, 1L, res.length() - 2L);
    return res;
  }

  public String usersSettoStringVDM(final VDMSet users) {

    String res = "";
    if (Utils.equals(users.size(), 0L)) {
      return "";
    }

    for (Iterator iterator_23 = users.iterator(); iterator_23.hasNext(); ) {
      String user = (String) iterator_23.next();
      res = res + user + ", ";
    }
    res = SeqUtil.subSeq(res, 1L, res.length() - 2L);
    return res;
  }

  public Event() {}

  public static String typetoStringVDM(final Object t) {

    String casesExpResult_1 = null;

    Object quotePattern_8 = t;
    Boolean success_4 =
        Utils.equals(quotePattern_8, ExhibitionCenter.quotes.ConferenceQuote.getInstance());

    if (!(success_4)) {
      Object quotePattern_9 = t;
      success_4 =
          Utils.equals(quotePattern_9, ExhibitionCenter.quotes.TradeFairQuote.getInstance());

      if (!(success_4)) {
        Object quotePattern_10 = t;
        success_4 = Utils.equals(quotePattern_10, ExhibitionCenter.quotes.PartyQuote.getInstance());

        if (!(success_4)) {
          Object quotePattern_11 = t;
          success_4 =
              Utils.equals(quotePattern_11, ExhibitionCenter.quotes.MusicalQuote.getInstance());

          if (!(success_4)) {
            Object quotePattern_12 = t;
            success_4 =
                Utils.equals(
                    quotePattern_12, ExhibitionCenter.quotes.TeamBuildingQuote.getInstance());

            if (success_4) {
              casesExpResult_1 = "Team Building";
            }

          } else {
            casesExpResult_1 = "Musical";
          }

        } else {
          casesExpResult_1 = "Party";
        }

      } else {
        casesExpResult_1 = "Trade Fair";
      }

    } else {
      casesExpResult_1 = "Conference";
    }

    return casesExpResult_1;
  }

  public String toString() {

    return "Event{"
        + "name := "
        + Utils.toString(name)
        + ", totalTickets := "
        + Utils.toString(totalTickets)
        + ", ticketPrice := "
        + Utils.toString(ticketPrice)
        + ", begin := "
        + Utils.toString(begin)
        + ", ending := "
        + Utils.toString(ending)
        + ", privacy := "
        + Utils.toString(privacy)
        + ", type := "
        + Utils.toString(type)
        + ", installation := "
        + Utils.toString(installation)
        + ", services := "
        + Utils.toString(services)
        + ", attendees := "
        + Utils.toString(attendees)
        + ", staff := "
        + Utils.toString(staff)
        + ", host := "
        + Utils.toString(host)
        + ", guests := "
        + Utils.toString(guests)
        + "}";
  }
}
