package org.soc.common.game;

import java.io.Serializable;

import org.soc.common.internationalization.I;
import org.soc.common.utils.Util;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.meta.Meta;
import org.soc.gwt.client.images.R;

/** Represents an resource to be traded by players to other opponents or the bank. Can also be used
 * to buy pieces, such as roads, development cards or towns. */
public interface Resource extends Serializable, Meta {
  public boolean isTradeable();
  public String color();
  public Resource copy();

  public abstract class AbstractResource implements Resource {
    public AbstractResource() {}
    @Override public String toString() {
      return "Resource [name=" + name() + "]";
    }
    public String name() {
      return Util.shortName(this.getClass());
    }
    @Override public boolean equals(Object obj) {
      return obj.getClass() == this.getClass();
    }
  }

  public class Clay extends AbstractResource {
    @Override public Icon icon() {
      return new IconImpl(R.icons().clay16(), R.icons().clay32(), R.icons().clay48());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().clay();
    }
    @Override public String getDescription() {
      return I.get().constants().clayDescription();
    }
    @Override public String color() {
      return "Red";
    }
    @Override public boolean isTradeable() {
      return true;
    }
    @Override public Resource copy() {
      return new Clay();
    }
    @Override public int hashCode() {
      return 1;
    }
  }

  public class Diamond extends AbstractResource {
    @Override public Icon icon() {
      return IconImpl.nullIcon();
    }
    @Override public String getLocalizedName() {
      return I.get().constants().diamond();
    }
    @Override public String getDescription() {
      return I.get().constants().diamondDescription();
    }
    @Override public String color() {
      return "Grey";
    }
    @Override public boolean isTradeable() {
      return false;
    }
    @Override public Resource copy() {
      return new Diamond();
    }
    @Override public int hashCode() {
      return 2;
    }
  }

  public class Ore extends AbstractResource {
    @Override public Icon icon() {
      return new IconImpl(R.icons().ore16(), R.icons().ore32(), R.icons().ore48());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().ore();
    }
    @Override public String getDescription() {
      return I.get().constants().oreDescription();
    }
    @Override public String color() {
      return "Purple";
    }
    @Override public boolean isTradeable() {
      return true;
    }
    @Override public Resource copy() {
      return new Ore();
    }
    @Override public int hashCode() {
      return 4;
    }
  }

  public class Sheep extends AbstractResource
  {
    @Override public Icon icon()
    {
      return new IconImpl(R.icons().sheep16(), R
              .icons().sheep32(), R.icons().sheep48());
    }
    @Override public String getLocalizedName()
    {
      return I.get().constants().sheep();
    }
    @Override public String getDescription()
    {
      return I.get().constants().sheepDescription();
    }
    @Override public String color()
    {
      return "LightGreen";
    }
    @Override public boolean isTradeable()
    {
      return true;
    }
    @Override public Resource copy()
    {
      return new Sheep();
    }
    @Override public int hashCode()
    {
      return 5;
    }
  }

  public class Timber extends AbstractResource {
    @Override public Icon icon() {
      return new IconImpl(
              R.icons().timber16(), R.icons()
                      .timber32(), R.icons()
                      .timber48());
    }
    @Override public String getLocalizedName() {
      return I.get().constants().timber();
    }
    @Override public String getDescription() {
      return I.get().constants().timberDescription();
    }
    @Override public String color() {
      return "DarkGreen";
    }
    @Override public boolean isTradeable() {
      return true;
    }
    @Override public Resource copy() {
      return new Timber();
    }
    @Override public int hashCode() {
      return 6;
    }
  }

  public class Wheat extends AbstractResource
  {
    @Override public Icon icon() {
      return new IconImpl(R.icons().wheat16(), R.icons().wheat32(), R.icons().wheat48());
    }
    @Override public String name() {
      return "Wheat";
    }
    @Override public String getLocalizedName() {
      return I.get().constants().wheat();
    }
    @Override public String getDescription() {
      return I.get().constants().wheatDescription();
    }
    @Override public String color() {
      return "Yellow";
    }
    @Override public boolean isTradeable() {
      return true;
    }
    @Override public Resource copy() {
      return new Wheat();
    }
    @Override public int hashCode() {
      return 7;
    }
  }

  public class Gold extends AbstractResource {
    @Override public Icon icon() {
      return new IconImpl(R.icons().gold16(), R.icons().gold32(), R.icons().gold48());
    }
    @Override public String name() {
      return "Gold";
    }
    @Override public String getLocalizedName() {
      return I.get().constants().gold();
    }
    @Override public String getDescription() {
      return I.get().constants().goldDescription();
    }
    @Override public String color() {
      return "Gold";
    }
    @Override public boolean isTradeable() {
      return false;
    }
    @Override public Resource copy() {
      return new Gold();
    }
    @Override public int hashCode() {
      return 3;
    }
  }
}
