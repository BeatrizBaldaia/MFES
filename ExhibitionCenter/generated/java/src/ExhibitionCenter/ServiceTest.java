package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ServiceTest extends Test_vdm {
  public void testAll() {

    Service service = new Service(10L, ExhibitionCenter.quotes.ITQuote.getInstance());
    testSetPrice(service, 20L);
  }

  public void testSetPrice(final Service s, final Number pr) {

    s.setPrice(pr);
    assertTrue(Utils.equals(s.price, pr));
  }

  public ServiceTest() {}

  public String toString() {

    return "ServiceTest{}";
  }
}
