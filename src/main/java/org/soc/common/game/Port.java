package org.soc.common.game;

import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.GenericList.ImmutableList;
import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.core.property.*;
import org.soc.common.game.Resource.Clay;
import org.soc.common.game.Resource.Ore;
import org.soc.common.game.Resource.Sheep;
import org.soc.common.game.Resource.Timber;
import org.soc.common.game.Resource.Wheat;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.board.*;
import org.soc.common.internationalization.*;
import org.soc.common.utils.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

import static org.soc.common.game.Resource.*;

public interface Port extends OsModel<Integer> {
  public interface Supported {
    public final static TimberPort timberPort = new TimberPort();
    public final static WheatPort wheatPort = new WheatPort();
    public final static OrePort orePort = new OrePort();
    public final static ClayPort clayPort = new ClayPort();
    public final static SheepPort sheepPort = new SheepPort();
    public final static RandomPort randomPort = new RandomPort();
    public final static ThreeToOnePort threeToOnePort = new ThreeToOnePort();
    public final static FourToOnePort fourToOnePort = new FourToOnePort();
  }

  public HexLocation landLocation();
  public Resource resource();
  public HexLocation hexLocation();
  public HexSide hexSide();
  public Port setHexSide(HexSide hexSide);
  public RotationPosition rotationPosition();
  public Port setRotationPosition(RotationPosition position);
  public int inAmount();
  public int outAmount();
  /* Returns amount of gold tradeable for given resource */
  public int divide(ResourceList resources, Resource type);
  public boolean canTrade(Resource resource);
  public Port setHexLocation(HexLocation hexLocation);
  public Port copy();
  public String color();
  /* Returns true when this port has a resource associated with it */
  public boolean hasResource();

  public abstract class AbstractPort implements Port {
    private static final long serialVersionUID = 1247189120735462756L;
    protected HexLocation seaLocation;
    protected HexLocation landLocation;
    protected HexSide hexSide;
    protected RotationPosition rotationPosition;
    protected int id;

    @Override public Name name() {
      return new Name.Impl("No i18n for " + Util.shortName(this.getClass()));
    }
    @Override public Description description() {
      return new Description.Impl("No i18n description for  " + name());
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public HasId setId(Integer id) {
      this.id = id;
      return this;
    }
    @Override public IdScope scope() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Port copy() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public String color() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public boolean hasResource() {
      // TODO Auto-generated method stub
      return false;
    }
    @Override public Integer id() {
      return hashCode();
    }
    public HexLocation landLocation() {
      return landLocation;
    }
    public Resource resource() {
      return null;
    }
    public HexLocation hexLocation() {
      return seaLocation;
    }
    public HexSide hexSide() {
      return hexSide;
    }
    public Port setHexSide(HexSide hexSide) {
      this.hexSide = hexSide;
      return this;
    }
    public RotationPosition rotationPosition() {
      return rotationPosition;
    }
    public Port setHexLocation(HexLocation hexLocation) {
      seaLocation = hexLocation;
      hexSide = hexLocation.getSideLocation(rotationPosition);
      landLocation = hexSide.getOtherLocation(seaLocation);
      return this;
    }
    @Override public Port setRotationPosition(RotationPosition position) {
      this.rotationPosition = position;
      return this;
    }
    public AbstractPort() {
      super();
    }
    public AbstractPort(HexLocation hexLocation) {
      super();
      this.seaLocation = hexLocation;
    }
    public AbstractPort(HexLocation hexLocation, RotationPosition rotationPosition) {
      super();
      this.rotationPosition = rotationPosition;
      this.setHexLocation(hexLocation);
    }
    /** Returns amount of gold tradeable for given resource */
    public int divide(ResourceList resources, Resource type)
    {
      // Have something to return, default on 0
      int amountGold = 0;
      // Filter the list of resources by given type
      ImmutableList<Resource> resourcesOfType = resources.ofType(type);
      // When we have at least two resources, determine amount of gold we can
      // trade
      if (resourcesOfType.size() >= 2)
      {
        // Amount of gold is number of times we can trade by inAmount, times
        // the amount of cards we get per trade
        amountGold = (resourcesOfType.size() / inAmount())
                * outAmount();
      }
      return amountGold;
    }
    public int inAmount() {
      throw new RuntimeException();
    }
    public int outAmount() {
      throw new RuntimeException();
    }
    public boolean canTrade(Resource resource) {
      throw new RuntimeException();
    }
    @Override public Property getProp(Property type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class ClayPort extends TwoToOneResourcePort {
    private static Clay clay = new Clay();

    @Override public Icon icon() {
      return new IconImpl(R.icons().clayPort16(), R.icons().clayPort32(), R.icons().clayPort48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().clayPort());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().clayPortDescription());
    }
    @Override public Port copy() {
      return new ClayPort();
    }
    @Override public Resource resource() {
      return clay;
    }
  }

  /** Imaginary 2:5 port for diamonds. To have fun with those (when devstack is empty) useless
   * diamonds */
  public class FivetoTwoJunglePort extends AbstractPort {
    @Override public Port copy() {
      return new FivetoTwoJunglePort();
    }
    @Override public int inAmount() {
      return 5;
    }
    @Override public int outAmount() {
      return 2;
    }
    @Override public String color() {
      return "DarkGray";
    }
    @Override public boolean hasResource() {
      return true;
    }
    @Override public Resource resource() {
      return diamond;
    }
    @Override public Name name() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Description description() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Icon icon() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  /* Standard 4:1 trade port for <code>resource.isTradeable()</code> resources. */
  public class FourToOnePort extends AbstractPort
  {
    @Override public Icon icon() {
      return IconImpl.nullIcon();
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().fourToOnePort());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().fourToOnePortDescription());
    }
    /** Performs a 4:1 trade on a list of resources */
    @Override public int divide(ResourceList resources, Resource type) {
      return resources.size() / inAmount();
    }
    @Override public int inAmount() {
      return 4;
    }
    public int outAmount() {
      return 1;
    }
    @Override public boolean canTrade(Resource resource) {
      return true;
    }
    @Override public Port copy() {
      return new FourToOnePort();
    }
    @Override public String color() {
      return "White";
    }
    @Override public boolean hasResource() {
      return false;
    }
  }

  public class OrePort extends TwoToOneResourcePort {
    private static Ore ore = new Ore();

    @Override public Icon icon() {
      return new IconImpl(R.icons().orePort16(), R.icons().orePort32(), R.icons().orePort48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().orePort());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().orePortDescription());
    }
    @Override public Port copy() {
      return new OrePort();
    }
    @Override public Resource resource() {
      return ore;
    }
  }

  /* Represents a port which can be placed on the board. */
  public class PossiblePort extends AbstractPort {
    @Override public Icon icon() {
      return new IconImpl(R.icons().port16(), R.icons().port32(), R.icons().port48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().possiblePort());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().possiblePortDescription());
    }
    public PossiblePort() {}
    public PossiblePort(HexLocation hexLocation, RotationPosition rotationPosition) {
      super(hexLocation, rotationPosition);
    }
    @Override public Port copy() {
      return new PossiblePort();
    }
    @Override public String color() {
      return "White";
    }
    @Override public boolean hasResource() {
      return false;
    }
  }

  /* Placeholder for replacement of random ports at board preperation */
  public class RandomPort extends AbstractPort {
    @Override public Icon icon() {
      return new IconImpl(R.icons().randomPort16(), R.icons().randomPort32(), R
              .icons()
              .randomPort48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().randomPortDescription());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().randomPortDescription());
    }
    public RandomPort(HexLocation hexLocation, RotationPosition rotationPosition) {
      super(hexLocation, rotationPosition);
    }
    public RandomPort() {}
    @Override public Port copy() {
      return new RandomPort();
    }
    @Override public String color() {
      return "Gray";
    }
    @Override public boolean hasResource() {
      return false;
    }
  }

  public class SheepPort extends TwoToOneResourcePort
  {
    private static Sheep sheep = new Sheep();

    @Override public Icon icon() {
      return new IconImpl(R.icons().sheepPort16(), R.icons().sheepPort32(), R.icons().sheepPort48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().sheepPort());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().sheepPortDescription());
    }
    @Override public Port copy() {
      return new SheepPort();
    }
    @Override public Resource resource() {
      return sheep;
    }
  }

  public class ThreeToOnePort extends AbstractPort {
    private static final long serialVersionUID = -2264455291636281867L;

    @Override public Icon icon() {
      return new IconImpl(R.icons().threeToOnePort16(), R.icons().threeToOnePort32(), R
              .icons()
              .threeToOnePort48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().threeToOnePort());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().threeToOnePortDescription());
    }
    /* Performs a 3:1 trade on a list of resources */
    @Override public int divide(ResourceList resources, Resource type) {
      return resources.size() / inAmount();
    }
    @Override public int inAmount() {
      return 3;
    }
    @Override public int outAmount() {
      return 1;
    }
    @Override public boolean canTrade(Resource resource) {
      return resource.isTradeable();
    }
    @Override public Port copy() {
      return new ThreeToOnePort();
    }
    @Override public String color() {
      return "White";
    }
    @Override public boolean hasResource() {
      return false;
    }
  }

  public class TimberPort extends TwoToOneResourcePort {
    private Timber timber = new Timber();

    @Override public Icon icon() {
      return new IconImpl(R.icons().timberPort32(), null, null, null);
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().timberPort());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().timberPortDescription());
    }
    @Override public Port copy() {
      return new TimberPort();
    }
    @Override public Resource resource() {
      return timber;
    }
  }

  public abstract class TwoToOneResourcePort extends AbstractPort {
    private static final long serialVersionUID = 2615564785346537011L;
    private Resource resource;

    @Override public int inAmount() {
      return 2;
    }
    @Override public int outAmount() {
      return 1;
    }
    /** Returns amount of gold gained by given list of resources */
    @Override public int divide(ResourceList resources, Resource type) {
      return resources.size() / inAmount();
    }
    @Override public String color() {
      return resource().color();
    }
    @Override public boolean hasResource() {
      return true;
    }
    @Override public boolean canTrade(Resource resource) {
      return resource().equals(resource);
    }
  }

  public class WheatPort extends TwoToOneResourcePort {
    public static Wheat wheat = new Wheat();

    @Override public Icon icon() {
      return new IconImpl(R.icons().wheatPort16(), R.icons().wheatPort32(), R.icons().wheatPort48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().wheatPort());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().wheatPortDescription());
    }
    @Override public Port copy() {
      return new WheatPort();
    }
    @Override public Resource resource() {
      return wheat;
    }
  }
}