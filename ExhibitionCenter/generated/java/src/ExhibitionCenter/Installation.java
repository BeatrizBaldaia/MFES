package ExhibitionCenter;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
abstract public class Installation {
  public String id = "untitled";
  public Number price = 0L;
  public Number capacity = 0L;
  public Number area = 0L;
  public Number heigth = 0L;
  public Number width = 0L;
  public Number lenght = 0L;
  public Boolean airCondition = false;
  public Boolean naturalLigth = false;
  public Boolean ceilingLighting = false;
  public Boolean blackOutCurtains = false;
  public Boolean telephones = false;
  public Boolean computerNetwork = false;
  public Boolean soundproofWalls = false;
  public Boolean movingWalls = false;

  public String getID() {

    return id;
  }

  public abstract void addFoyer(final Foyer f);

  public abstract void removeFoyer(final Foyer f);

  public abstract void addRoom(final Room r);

  public abstract void removeRoom(final Room r);

  public abstract Boolean hasInstallation(final Installation inst);

  public abstract void setConditions(
      final Boolean airC,
      final Boolean natL,
      final Boolean ceilL,
      final Boolean blackC,
      final Boolean tele,
      final Boolean compN,
      final Boolean soundW,
      final Boolean movW);

  public abstract VDMMap showDetails();

  public void setPrice(final Number pr) {

    price = pr;
  }

  public void setMeasures(final Number c, final Number h, final Number w, final Number l) {

    Number atomicTmp_14 = c;
    Number atomicTmp_15 = h;
    Number atomicTmp_16 = w;
    Number atomicTmp_17 = l;
    Number atomicTmp_18 = w.doubleValue() * l.doubleValue();
    {
        /* Start of atomic statement */
      capacity = atomicTmp_14;
      heigth = atomicTmp_15;
      width = atomicTmp_16;
      lenght = atomicTmp_17;
      area = atomicTmp_18;
    } /* End of atomic statement */
  }

  public Installation() {}

  public String toString() {

    return "Installation{"
        + "id := "
        + Utils.toString(id)
        + ", price := "
        + Utils.toString(price)
        + ", capacity := "
        + Utils.toString(capacity)
        + ", area := "
        + Utils.toString(area)
        + ", heigth := "
        + Utils.toString(heigth)
        + ", width := "
        + Utils.toString(width)
        + ", lenght := "
        + Utils.toString(lenght)
        + ", airCondition := "
        + Utils.toString(airCondition)
        + ", naturalLigth := "
        + Utils.toString(naturalLigth)
        + ", ceilingLighting := "
        + Utils.toString(ceilingLighting)
        + ", blackOutCurtains := "
        + Utils.toString(blackOutCurtains)
        + ", telephones := "
        + Utils.toString(telephones)
        + ", computerNetwork := "
        + Utils.toString(computerNetwork)
        + ", soundproofWalls := "
        + Utils.toString(soundproofWalls)
        + ", movingWalls := "
        + Utils.toString(movingWalls)
        + "}";
  }
}
