package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Utils_vdm {
  public Utils_vdm() {}

  public static Boolean before(final Date b, final Date a) {

    Boolean orResult_40 = false;

    if (b.year.longValue() < a.year.longValue()) {
      orResult_40 = true;
    } else {
      Boolean orResult_41 = false;

      Boolean andResult_140 = false;

      if (Utils.equals(b.year, a.year)) {
        if (b.month.longValue() < a.month.longValue()) {
          andResult_140 = true;
        }
      }

      if (andResult_140) {
        orResult_41 = true;
      } else {
        Boolean andResult_141 = false;

        if (Utils.equals(b.year, a.year)) {
          Boolean andResult_142 = false;

          if (Utils.equals(b.month, a.month)) {
            if (b.day.longValue() < a.day.longValue()) {
              andResult_142 = true;
            }
          }

          if (andResult_142) {
            andResult_141 = true;
          }
        }

        orResult_41 = andResult_141;
      }

      orResult_40 = orResult_41;
    }

    return orResult_40;
  }

  public static Number DaysOfMonth(final Number y, final Number m) {

    if (Utils.equals(m, 2L)) {
      if (isLeapYear(y)) {
        return 29L;

      } else {
        return 28L;
      }

    } else {
      return 31L - Utils.mod(Utils.mod(m.longValue() - 1L, 7L), 2L);
    }
  }

  public static Boolean isLeapYear(final Number y) {

    Boolean orResult_45 = false;

    Boolean andResult_150 = false;

    if (Utils.equals(Utils.mod(y.longValue(), 4L), 0L)) {
      if (!(Utils.equals(Utils.mod(y.longValue(), 100L), 0L))) {
        andResult_150 = true;
      }
    }

    if (andResult_150) {
      orResult_45 = true;
    } else {
      orResult_45 = Utils.equals(Utils.mod(y.longValue(), 400L), 0L);
    }

    return orResult_45;
  }

  public static Number getDatesDifference(final Date date1, final Date date2) {

    return dateCount(
                date2.year, date2.month, 1L, date2.year.longValue() * 365L + date2.day.longValue())
            .longValue()
        - dateCount(
                date1.year, date1.month, 1L, date1.year.longValue() * 365L + date1.day.longValue())
            .longValue();
  }

  public static Number dateCount(
      final Number year, final Number month, final Number i, final Number res) {

    if (Utils.equals(i, month)) {
      return res;

    } else {
      return dateCount(
          year, month, i.longValue() + 1L, res.longValue() + DaysOfMonth(year, i).longValue());
    }
  }

  public static <keyType, returnType, valueType> Func_1<VDMSeq, VDMMap, VDMMap> fMap(
      final Func_2<valueType, returnType> f) {

    return new Func_1<VDMSeq, VDMMap, VDMMap>() {
      public VDMMap eval(final VDMSeq keys, final VDMMap m) {

        VDMMap ternaryIfExp_4 = null;

        if (Utils.empty(keys)) {
          ternaryIfExp_4 = MapUtil.map();
        } else {
          final keyType key = ((keyType) keys.get(0));

          {
            final returnType res = f.eval(((valueType) Utils.get(m, key)));

            VDMMap ternaryIfExp_5 = null;

            if (Utils.is_bool(res)) {
              VDMMap ternaryIfExp_6 = null;

              if (Utils.equals(res, true)) {
                ternaryIfExp_6 =
                    MapUtil.override(
                        MapUtil.map(new Maplet(key, ((valueType) Utils.get(m, key)))),
                        fMap(f).eval(SeqUtil.tail(Utils.copy(keys)), Utils.copy(m)));
              } else {
                ternaryIfExp_6 = fMap(f).eval(SeqUtil.tail(Utils.copy(keys)), Utils.copy(m));
              }

              ternaryIfExp_5 = Utils.copy(ternaryIfExp_6);

            } else {
              ternaryIfExp_5 =
                  MapUtil.override(
                      MapUtil.map(new Maplet(key, res)),
                      fMap(f).eval(SeqUtil.tail(Utils.copy(keys)), Utils.copy(m)));
            }

            ternaryIfExp_4 = Utils.copy(ternaryIfExp_5);
          }
        }

        return Utils.copy(ternaryIfExp_4);
      }
    };
  }

  public static <keyType, returnType, valueType> Number mfMap(
      final Func_2<valueType, returnType> ignorePattern_35,
      final VDMSeq keys,
      final VDMMap ignorePattern_36) {

    return keys.size();
  }

  public static <elem> VDMSeq setTOseq(final VDMSet tmpSet) {

    VDMSeq seqCompResult_1 = SeqUtil.seq();
    VDMSet set_8 = Utils.copy(tmpSet);
    for (Iterator iterator_8 = set_8.iterator(); iterator_8.hasNext(); ) {
      elem x = ((elem) iterator_8.next());
      seqCompResult_1.add(x);
    }
    return Utils.copy(seqCompResult_1);
  }

  public static <elem> Number msetTOseq(final VDMSet tmpSet) {

    return tmpSet.size();
  }

  public static String toStringVDM(final String value) {

    return value;
  }

  public static String toStringVDM(final Boolean value) {

    return booltoStringVDM(value);
  }

  public static String toStringVDM(final Date value) {

    return datetoStringVDM(Utils.copy(value));
  }

  public static String toStringVDM(final Number value) {

    return nattoStringVDM(value)
        + new String(new char[] {',', ' '})
        + nattoStringVDM(getRemainder(value, 0L));
  }

  public static String booltoStringVDM(final Boolean value) {

    if (value) {
      return "yes";

    } else {
      return "no";
    }
  }

  public static String datetoStringVDM(final Date value) {

    return nattoStringVDM(value.day)
        + new String(new char[] {'/'})
        + nattoStringVDM(value.month)
        + new String(new char[] {'/'})
        + nattoStringVDM(value.year);
  }

  public static String nattoStringVDM(final Number value) {

    if (Math.round(Utils.floor(value)) < 10L) {
      return mapNat(Math.round(Utils.floor(value)));

    } else {
      return nattoStringVDM(Utils.div(Math.round(Utils.floor(value)), 10L))
          + mapNat(Utils.rem(Math.round(Utils.floor(value)), 10L));
    }
  }

  public static Number getRemainder(final Number value, final Number n) {

    if (Utils.is_int(value)) {
      return Utils.rem(value.longValue(), Utils.pow(10L, n.longValue()));

    } else {
      return getRemainder(value.doubleValue() * 10L, n.longValue() + 1L);
    }
  }

  public static String mapNat(final Number n) {

    String casesExpResult_3 = null;

    Number intPattern_1 = n;
    Boolean success_6 = Utils.equals(intPattern_1, 0L);

    if (!(success_6)) {
      Number intPattern_2 = n;
      success_6 = Utils.equals(intPattern_2, 1L);

      if (!(success_6)) {
        Number intPattern_3 = n;
        success_6 = Utils.equals(intPattern_3, 2L);

        if (!(success_6)) {
          Number intPattern_4 = n;
          success_6 = Utils.equals(intPattern_4, 3L);

          if (!(success_6)) {
            Number intPattern_5 = n;
            success_6 = Utils.equals(intPattern_5, 4L);

            if (!(success_6)) {
              Number intPattern_6 = n;
              success_6 = Utils.equals(intPattern_6, 5L);

              if (!(success_6)) {
                Number intPattern_7 = n;
                success_6 = Utils.equals(intPattern_7, 6L);

                if (!(success_6)) {
                  Number intPattern_8 = n;
                  success_6 = Utils.equals(intPattern_8, 7L);

                  if (!(success_6)) {
                    Number intPattern_9 = n;
                    success_6 = Utils.equals(intPattern_9, 8L);

                    if (!(success_6)) {
                      Number intPattern_10 = n;
                      success_6 = Utils.equals(intPattern_10, 9L);

                      if (success_6) {
                        casesExpResult_3 = "9";
                      }

                    } else {
                      casesExpResult_3 = "8";
                    }

                  } else {
                    casesExpResult_3 = "7";
                  }

                } else {
                  casesExpResult_3 = "6";
                }

              } else {
                casesExpResult_3 = "5";
              }

            } else {
              casesExpResult_3 = "4";
            }

          } else {
            casesExpResult_3 = "3";
          }

        } else {
          casesExpResult_3 = "2";
        }

      } else {
        casesExpResult_3 = "1";
      }

    } else {
      casesExpResult_3 = "0";
    }

    return casesExpResult_3;
  }

  public String toString() {

    return "Utils_vdm{}";
  }

  public static class Date implements Record {
    public Number year;
    public Number month;
    public Number day;

    public Date(final Number _year, final Number _month, final Number _day) {

      year = _year;
      month = _month;
      day = _day;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(year, other.year))
          && (Utils.equals(month, other.month))
          && (Utils.equals(day, other.day));
    }

    public int hashCode() {

      return Utils.hashCode(year, month, day);
    }

    public Date copy() {

      return new Date(year, month, day);
    }

    public String toString() {

      return "mk_Utils_vdm`Date" + Utils.formatFields(year, month, day);
    }
  }

  public static Boolean inv_Date(final Date d) {

    Boolean andResult_154 = false;

    if (d.month.longValue() <= 12L) {
      if (d.day.longValue() <= DaysOfMonth(d.year, d.month).longValue()) {
        andResult_154 = true;
      }
    }

    return andResult_154;
  }
}
