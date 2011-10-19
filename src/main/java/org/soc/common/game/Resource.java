package org.soc.common.game;

import org.soc.common.core.GenericList.Filters.Filter;
import org.soc.common.core.GenericList.HasId;
import org.soc.common.core.GenericList.ModelPresenter;
import org.soc.common.core.GenericList.ModelView;
import org.soc.common.core.OpenZettlers.OsModel;
import org.soc.common.core.Props.IsChild;
import org.soc.common.core.Props.PropertyList.PropertyTypeList;
import org.soc.common.core.property.*;
import org.soc.common.game.Resource.ResourcePresenterP.ResourceView;

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
    protected String color;
    protected boolean isTradeable;
    protected int hashCode;

    public AbstractResource() {}
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
    @Override public IsChild getProp(StaticProperty type) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override public boolean isTradeable() {
      return isTradeable;
    }
    @Override public String color() {
      return color;
    }
  }

  public class Clay extends AbstractResource {
    public Clay() {
      super();
      color = "Red";
      hashCode = 1;
      isTradeable = true;
    }
    @Override public Resource copy() {
      return new Clay();
    }
  }

  public class Diamond extends AbstractResource {
    public Diamond() {
      super();
      color = "Grey";
      hashCode = 2;
      isTradeable = false;
    }
    @Override public Resource copy() {
      return new Diamond();
    }
  }

  public class Ore extends AbstractResource {
    public Ore() {
      super();
      color = "Purple";
      hashCode = 4;
      isTradeable = true;
    }
    @Override public Resource copy() {
      return new Ore();
    }
  }

  public class Sheep extends AbstractResource {
    public Sheep() {
      super();
      color = "LightGreen";
      hashCode = 5;
      isTradeable = true;
    }
    @Override public Resource copy() {
      return new Sheep();
    }
  }

  public class Timber extends AbstractResource {
    public Timber() {
      super();
      color = "DarkGreen";
      hashCode = 6;
      isTradeable = true;
    }
    @Override public Resource copy() {
      return new Timber();
    }
  }

  public class Wheat extends AbstractResource {
    public Wheat() {
      super();
      color = "Yellow";
      hashCode = 7;
      isTradeable = true;
    }
    @Override public Resource copy() {
      return new Wheat();
    }
  }

  public class Gold extends AbstractResource {
    public Gold() {
      super();
      color = "Gold";
      hashCode = 3;
      isTradeable = false;
    }
    @Override public Resource copy() {
      return new Gold();
    }
  }

  public static class CanTrade implements Filter<Resource> {
    @Override public boolean matches(Resource resource) {
      return resource.isTradeable();
    }
  }
}
