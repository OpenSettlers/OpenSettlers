package org.soc.common.game;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.Adds.*;
import org.soc.common.core.GenericList.AddsList.*;
import org.soc.common.core.GenericList.FullImmutableList.*;
import org.soc.common.core.GenericList.ImmutableList.*;
import org.soc.common.core.GenericList.MutableList.*;
import org.soc.common.core.GenericList.MutableList.MutableModelList.*;
import org.soc.common.core.GenericList.Removes.*;
import org.soc.common.core.GenericList.RemovesList.*;
import org.soc.common.game.Resource.Wheat;

import com.google.inject.*;
import com.gwtplatform.dispatch.annotation.*;

import static org.soc.common.game.Resource.*;

/** Base interface name type always represents the immutable variant of the class */
public class Resources {
  public static final ResourceList basicResources = newResources(wheat, timber, ore, clay, sheep);
  public static final ResourceList tradableResources = newResources(basicResources);
  public static final ResourceList monoPolyableResources = newResources(basicResources);
  public static final ResourceList supportedResources = newResources(basicResources, newResources(gold, diamond));
  public static final ResourceMaker maker = new ResourceMakerImpl();

  public interface ResourceMaker
          extends
          Makes<Resource, MutableResourceList, ResourceList, Integer> {}

  public static final class ResourceMakerImpl implements ResourceMaker {
    @Override public ImmutableList<ResourceList> supportedModelLists() {
      return new ImmutableListImpl<ResourceList>(tradableResources, basicResources, supportedResources);
    }
    @Override public ResourceList supportedModels() {
      return supportedResources;
    }
    @Override public Resource newItem() {
      return new Wheat();
    }
    @Override public MutableResourceList emptyFullMutable() {
      return new MutableResourceListImpl();
    }
  };

  public static ResourceList newResources(Resource... resources) {
    return new ResourceListImpl(resources);
  }
  public static ResourceList newResources(ResourceList... lists) {
    MutableResourceListImpl result = new MutableResourceListImpl();
    for (ResourceList list : lists)
      result.addList(list);
    return result.toImmutable();
  }
  public static ResourceList emptyResources() {
    return new ResourceListImpl();
  }
  public static ResourceList one(Resource resource) {
    return new ResourceListImpl(new Resource[] { resource });
  }

  public interface ResourceList
          extends
          FullImmutableList<Resource, Integer> {
    public boolean hasAtLeast(ResourceList toHave);
    public ResourceList getNeededResources(ResourceList neededResources);
    public boolean hasTradeableResources();
    public ResourceList nonTradeableResources();
    public ResourceList tradeableResources();
    public ResourceList ofType(Object type);
    public int halfCount();
    public ResourceList copy();
  }

  public interface MutableResourceList
          extends
          ResourceList,
          MutableModelList<Resource, ResourceList, Integer> {
    public ResourceList toImmutable();
    public MutableResourceList copy();

    public interface MutableResourceListPresenter
            extends
            MutableListPresenter<Resource, ResourceList, MutableResourceList, MutableListView, Integer> {
      public ResourceList toImmutable();
      public MutableResourceList copy();
      public MutableResourceList ofType(Object type);
    }
  }

  public static class ResourceListImpl // Immutable default impl
          extends
          AbstractFullImmutableList<Resource, ResourceList, Integer>
          implements
          ResourceList {
    protected ResourceListImpl() {}
    public ResourceListImpl(Resource[] resources) {
      super(resources);
    }
    public ResourceListImpl(ImmutableList<Resource> resources) {
      super(resources);
    }
    public ResourceListImpl(FullImmutableList<Resource, Integer> resources) {
      super(resources);
    }
    @Override public ResourceList copy() {
      return new ResourceListImpl(this);
    }
    /** Returns amount of items halfed and rounded down */
    @Override public int halfCount() {
      int count = size();
      // Make number even
      if (count % 2 != 0)
        count++;
      return count / 2;
    }
    @Override public ResourceList ofType(Object type) {
      return new ResourceListImpl(super.ofType(type));
    }
    @Override public ResourceList tradeableResources() {
      return tradeableResources(true);
    }
    @Override public ResourceList nonTradeableResources() {
      return tradeableResources(false);
    }
    private ResourceList tradeableResources(boolean tradeable) {
      MutableResourceList result = new MutableResourceListImpl();
      for (Resource resource : this)
        if (resource.isTradeable() == tradeable)
          result.add(resource);
      return result.toImmutable();
    }
    /** True if this list contains resources which are tradeable */
    @Override public boolean hasTradeableResources() {
      return tradeableResources().size() > 0;
    }
    /** Returns a list of resources needed */
    @Override public ResourceList getNeededResources(ResourceList neededResources) {
      MutableResourceList result = new MutableResourceListImpl();
      MutableResourceList copy = new MutableResourceListImpl(this);
      result.addList(neededResources);
      for (Resource resource : neededResources)
        if (copy.contains(resource)) {
          result.remove(resource);
          copy.remove(resource);
        }
      return result.toImmutable();
    }
    /** Returns true if given resources are available in this ResourceList */
    @Override public boolean hasAtLeast(ResourceList toHave) {
      for (Class type : toHave.types())
        if (!hasType(type) || ofType(type).size() < toHave.ofType(type).size())
          return false;
      return true;
    }
  }

  public static class MutableResourceListImpl
          extends
          FullMutableListImpl<Resource, ResourceList, Integer>
          implements
          MutableResourceList {
    public MutableResourceListImpl(FullImmutableList<Resource, Integer> list) {
      super(list);
    }
    public MutableResourceListImpl() {
      super();
    }
    public MutableResourceListImpl(ImmutableList<Resource> list) {
      super(list);
    }
    public MutableResourceListImpl(ResourceList resources) {
      super(resources);
    }
    @Override public ResourceList toImmutable() {
      return new ResourceListImpl(this);
    }
    @Override public MutableResourceList copy() {
      return new MutableResourceListImpl(super.copy());
    }
    @Override public void add(Resource item) {
      super.add(item);
    }
    @Override public MutableResourceList ofType(Object type) {
      return new MutableResourceListImpl(super.ofType(type));
    }
    public Resource getRandom(Random random) {
      int randomResource = random.nextInt(size(), false);
      return get(randomResource);
    }
    @Override public boolean hasAtLeast(ResourceList toHave) {
      return toImmutable().hasAtLeast(toHave);
    }
    @Override public ResourceList getNeededResources(ResourceList neededResources) {
      return toImmutable().getNeededResources(neededResources);
    }
    @Override public boolean hasTradeableResources() {
      return toImmutable().hasTradeableResources();
    }
    @Override public ResourceList nonTradeableResources() {
      return toImmutable().nonTradeableResources();
    }
    @Override public ResourceList tradeableResources() {
      return toImmutable().tradeableResources();
    }
    @Override public int halfCount() {
      return toImmutable().halfCount();
    }
  }

  /** Displays a mutable list of resources which can be modified */
  public static class ResourceListPresenterImpl
          implements
          MutableListPresenter<Resource, ResourceList, MutableResourceList, MutableListView<Resource, ResourceList, MutableResourceList, Integer>, Integer> {
    public interface MutableView<M extends Resource> {
      public void remove(Resource resource);
      public void add(Resource resource);
    }

    private MutableView view;
    private MutableResourceList resources;

    @Inject public ResourceListPresenterImpl(MutableResourceList resources, final MutableView view) {
      this.resources = resources;
      this.view = view;
      resources.addRemovedHandler(new Removed<Resource>() {
        @Override public void removed(Resource resource) {
          view.remove(resource);
        }
      });
      resources.addListRemovedHandler(new ListRemoved<Resource>() {
        @Override public void listRemoved(ImmutableList<Resource> items) {
          for (Resource resource : items)
            view.remove(resource);
        }
      });
      resources.addAddedHandler(new Added<Resource>() {
        @Override public void added(Resource resource) {
          view.add(resource);
        }
      });
      resources.addListAddedHandler(new ListAdded<Resource>() {
        @Override public void listAdded(ImmutableList<Resource> items) {
          for (Resource resource : items)
            view.add(resource);
        }
      });
    }
  }

  /** User can select a list of resources */
  public static class PickerPresenter {
    public interface View {
      /** The user clicked a resource he wants to pick */
      @GenEvent public class Picked {
        @Order(0) Resource picked;
      }

      /** Remove a picked resource from the list of pickedresources */
      @GenEvent public class CancelPick {
        @Order(0) Resource cancelledResource;
      }
    }
    //private ListPresenter viewPresenter;
  }
}
