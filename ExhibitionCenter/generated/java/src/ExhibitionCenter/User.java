package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  public String name = "default_name";
  public String password = "pass1234";
  public VDMSet events = SetUtil.set();

  public void cg_init_User_1(final String n, final String p) {

    name = n;
    password = p;
  }

  public User(final String n, final String p) {

    cg_init_User_1(n, p);
  }

  public VDMMap showDetails() {

    VDMMap res = MapUtil.map();
    res =
        MapUtil.override(
            Utils.copy(res),
            MapUtil.map(
                new Maplet("Name", name),
                new Maplet("Tickets bought", eventsSettoStringVDM(Utils.copy(events)))));
    return Utils.copy(res);
  }

  public void addEvent(final String evName) {

    events = SetUtil.union(Utils.copy(events), SetUtil.set(evName));
  }

  public void removeEvent(final String evName) {

    events = SetUtil.diff(Utils.copy(events), SetUtil.set(evName));
  }

  public Boolean attendEvent(final String evName) {

    return SetUtil.inSet(evName, events);
  }

  public String eventsSettoStringVDM(final VDMSet events_var) {

    String res = "";
    if (Utils.equals(events_var.size(), 0L)) {
      return "";
    }

    for (Iterator iterator_24 = events_var.iterator(); iterator_24.hasNext(); ) {
      String event = (String) iterator_24.next();
      res = res + event + ", ";
    }
    res = SeqUtil.subSeq(res, 1L, res.length() - 2L);
    return res;
  }

  public User() {}

  public String toString() {

    return "User{"
        + "name := "
        + Utils.toString(name)
        + ", password := "
        + Utils.toString(password)
        + ", events := "
        + Utils.toString(events)
        + "}";
  }
}
