package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Utils_vdmTest extends Test_vdm {
  public void testAll() {

    Utils_vdm.Date date_0 = new Utils_vdm.Date(2018L, 12L, 27L);
    Utils_vdm.Date date_1 = new Utils_vdm.Date(2018L, 12L, 28L);
    Utils_vdm.Date date_2 = new Utils_vdm.Date(2020L, 2L, 28L);
    Utils_vdm.Date date_3 = new Utils_vdm.Date(2100L, 2L, 28L);
    testGetDatesDifference(Utils.copy(date_0), Utils.copy(date_1), 1L);
  }

  private void testGetDatesDifference(
      final Utils_vdm.Date d1, final Utils_vdm.Date d2, final Number n) {

    assertTrue(Utils.equals(Utils_vdm.getDatesDifference(Utils.copy(d1), Utils.copy(d2)), n));
  }

  public Utils_vdmTest() {}

  public String toString() {

    return "Utils_vdmTest{}";
  }
}
