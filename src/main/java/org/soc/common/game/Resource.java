package org.soc.common.game;

import org.soc.common.core.GenericList.Filters.Filter;
import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.GenericList.ModelPresenter;
import org.soc.common.core.GenericList.ModelView;
import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.core.property.*;
import org.soc.common.game.Resource.ResourcePresenterP.ResourceView;
import org.soc.common.internationalization.*;
import org.soc.common.utils.*;
import org.soc.common.views.meta.*;
import org.soc.gwt.client.images.*;

/** Represents an resource to be traded by players to other opponents or the bank. Can also be used
 * to buy pieces, such as roads, development cards or towns. */
public interface Resource extends OsModel<Integer> {
  public interface ResourcePresenterP<R extends Resource, V extends ResourceView>
          extends ModelPresenter<R, V> {
    public interface ResourceView<R extends Resource> extends ModelView<R> {}
  }

  //  public interface Picker
  //          extends
  //          ModelPicker<Resource, ResourceList, MutableResourceList, Picker.View, Integer> {
  //    public static interface View
  //            extends
  //            Picker<Resource, ResourceList, MutableResourceList, Integer> {
  //      /** User indicates he is done choosing and he made a choice from prototypes */
  //      @GenEvent public class UseSelection {
  //        @Order(0) ResourceList picked;
  //      }
  //    }
  //  }
  public boolean isTradeable();
  public String color();
  public Resource copy();

  public static final Timber timber = new Timber();
  public static final Sheep sheep = new Sheep();
  public static final Clay clay = new Clay();
  public static final Ore ore = new Ore();
  public static final Wheat wheat = new Wheat();
  public static final Gold gold = new Gold();
  public static final Diamond diamond = new Diamond();

  public abstract class AbstractResource implements Resource {
    private int id = 0;

    public AbstractResource() {}
    @Override public String toString() {
      return "Resource [name=" + name() + "]";
    }
    public Name name() {
      return new Name.Impl(Util.shortName(this.getClass()));
    }
    @Override public boolean equals(Object obj) {
      return obj.getClass() == this.getClass();
    }
    @Override public Integer id() {
      return id;
    }
    @Override public HasId setId(Integer id) {
      return this;
    }
    @Override public IdScope scope() {
      return IdScope.APPLICATION;
    }
    @Override public PropertyTypeList properties() {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public Property getProp(Property type) {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public class Clay extends AbstractResource {
    @Override public Icon icon() {
      return new IconImpl(R.icons().clay16(), R.icons().clay32(), R.icons().clay48());
    }
    @Override public Name name() {
      return new Name.Impl(I.get().constants().clay());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().clayDescription());
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
    @Override public Name name() {
      return new Name.Impl(I.get().constants().diamond());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().diamondDescription());
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
    @Override public Name name() {
      return new Name.Impl(I.get().constants().ore());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().oreDescription());
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
    @Override public Name name() {
      return new Name.Impl(I.get().constants().sheep());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().sheepDescription());
    }
    @Override public String color() {
      return "LightGreen";
    }
    @Override public boolean isTradeable() {
      return true;
    }
    @Override public Resource copy() {
      return new Sheep();
    }
    @Override public int hashCode() {
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
    @Override public Name name() {
      return new Name.Impl(I.get().constants().timber());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().timberDescription());
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
    @Override public Name name() {
      return new Name.Impl(I.get().constants().wheat());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().wheatDescription());
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
    @Override public Name name() {
      return new Name.Impl(I.get().constants().gold());
    }
    @Override public Description description() {
      return new Description.Impl(I.get().constants().goldDescription());
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

  public static class CanTrade implements Filter<Resource> {
    @Override public boolean matches(Resource resource) {
      return resource.isTradeable();
    }
  }
}
