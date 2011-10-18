package org.soc.common.core;

import java.io.*;
import java.util.*;

import org.soc.common.core.GenericList.Adds.Added;
import org.soc.common.core.GenericList.AddsList.ListAdded;
import org.soc.common.core.GenericList.Makes.ImmutableMakerList.Impl;
import org.soc.common.core.GenericList.ModelView.InfoView;
import org.soc.common.core.GenericList.Moves.Moved;
import org.soc.common.core.GenericList.MovesList.ListMoved;
import org.soc.common.core.GenericList.MutableList.MutableListImpl;
import org.soc.common.core.GenericList.MutableList.MutableModelList;
import org.soc.common.core.GenericList.MutableList.MutableModelList.FullMutableListImpl;
import org.soc.common.core.GenericList.MutableList.MutableModelList.MutableListView;
import org.soc.common.core.GenericList.Removes.Removed;
import org.soc.common.core.GenericList.RemovesList.ListRemoved;
import org.soc.common.core.Props.Value;
import org.soc.common.game.*;
import org.soc.common.game.Random;
import org.soc.common.utils.*;

import com.google.inject.*;
import com.google.web.bindery.event.shared.*;

/** Base interface for lists of things used in model classes Fixes several shortcomings
 * and omissions of the java.util package: 
 * - Fully generic List<T>/Set<T> 
 * - Listenable lists
 * - Fine-grained support of lists using interfaces
 * - Immutable collections have mutator methods by default
 * - Unicity of items which have an identifier in global/local scope
 * - Literate methods like doesNotContain(), notEmpty() 
 * - Moving items from one to another collection
 * 
 * FAQ
 * 
 * - Main advantages over java.util?
 *    * Clear seperation over mutable and immutable lists
 *    * 100% generic methods (java.util is not 100% geenric!)
 *    * Lists have change events
 *    * Lists have O(1) lookup for keyed model
 *    * O(1) filtering by type
 *    * Defined types for narrow collections (i.e. log)
 *    * Model<K> allows for richer default implementations of list (i.e. FullMutableList)
 *    
 * - Mutable _and_ Immutable lists for every model?
 *    
 * - I need to implement a mutable and immutable list for every model?
 *    * Yes. Override toImmutable() in your base mutable list interface to allow
 *      typecast-free delegation of methods.
 * 
 * - A lot of interfaces! Why?
 *    * It allows to do fun stuff. A logger only needs an Adds<T>, and no removal. Think WORM. 
 *    * Better testing. 
 *    * Literate definition of collections. 
 * 
 * - Why not just one interface for every method?
 *    * Some methods should be grouped logically. 
 * 
 * - Why all those syntactical sugary methods like doesNotContain? And we have org.junit.not()?
 *    * Less reliance on static util  methods
 *    * You'll need them eventually. doesNotContain is less confusing then !contains
 *    * Java is evil enough. Why not ease the burden and add some more literate code?
 *    
 * - Isn't it too finely-grained? 
 *    * Isn't the current java.util not rough enough for you?
 *    
 * - Whoa! All those Abstract and Default classes! 
 *    * You don't have to use them. I don´t like AbstractDefaultMutableCollectionWithIdsAndSorting<T, K, C, H>
 *      kind of names either, but in any case it's better then to use just plain List<T> for everything.
 *      Java7 should support simpler generics instantiation, which cuts lines using such class names with 
 *      about 40% (YMMV).
 *    * I'm not 100% sure (although pretty sure, say 90%) about Abstract classes either.
 *    * Interfaces should always get the "normal" name, not classes.   
 * 
 * - What happened with the boolean remove(T item) method?
 *    * I don't like it. It produces code like if (list.remove(item)) { }, which is less clear then
 *      list.remove(item); if (list.isRemoved(item)) { }
 * 
 * - What about backwards compatibility with java.util? Java will never adopt this!
 *    * Most likely. At least, can't say I tried.
 *    * It's fun to build.
 *    * 
 *    
 * - All the inheritance of interfaces! You want my brain in overload mode?
 *    * F
 *      
 * 
 * TODO: Decide if moves/moveslist, adds/addslist, removes/removeslist should be combined
 * TODO: Decide if Moves/Adds/Removes should be split into MovesAndNotifes or MovesWithEvents
 * TODO: Sorting/ChangeOrder (should remove on item N != last() fire orderchanged event?)
 * TODO: Decide on Collection naming/spirit versus Lists
 * TODO: Methods without assertions (e.g. remove(T item) asserts contains(T item)
 * 
 * T: Any type
 * M: Model
 * K: Key/Id
 * P: Presenter
 * V: View
 * L: List
 * ImmutableL: Immutable list
 * MutableL: Mutable list
 * 
 * Impl:     Concrete dynamic object
 * Abstract: Abstract base class for Default, final and Impl classes
 * final:    Static final immutable classes with values set in constructor, extending Abstract 
 */
public class GenericList {// @formatter:off
  
  /** Base interface for every domain model of the application 
   *  A model is a thing resembling some domain specific thing. Every model gets an icon, wiki page
   *  and name/description i18n parsed from the wiki page.
   * */ 
  public interface Model<K>
    extends
    Serializable,  // I can has stretch
    Copies<Model>, // Mirror kitteh looks in mirror and sees clone
    HasId<K> {     // Copycat differs from copycat
  }
  
  /** Store the model somewhere */
  public interface Persists {}

  /** Pick your favorite Id! */
  public interface Id {
    public interface IntegerId 
      extends 
        Id, Value.IntValue {
      
      public static abstract class Abstract implements IntegerId {
        private int id;
        public Abstract(int id) {
          this.id = id;
        }
        public int id() {
          return id;
        }
        @Override public int value() {
          return id;
        }
      } 
      
      public static class Impl extends Abstract {
        public Impl(int id) {
          super(id);
        }
      }
    }
    public interface Guid extends Id { }
    public interface HashCode extends Id { }
    
    public abstract class AbstractIntegerId implements Id {
      int id;
      protected AbstractIntegerId(int id) {
        this.id = id;
      }
    }
  }
  
  /** Any code gluing a KISS view and a model together
   * 
   * 
   *    M:  Model
   *    P:  Presenter
   *    V:  View
   * 
   *                                      a user doing something
   *                                      |
   *       +-----+  changed  +-----+   action  +-----+
   *       |     |     -->   |     |   <---    |     |
   *       |  M  |           |  P  |           |  V  |    
   *       |     |  mutate   |     |   update  |     |
   *       +-----+   <--  |  +-----+   |  -->   +-----+
   *                      |            |
   *                      |            update the view 
   *                      |
   *                      Perform a mutation on the model   
   *       
   *       changed: model notifications that it has been changed
   *       mutate: a presenter modifying the model
   *       
   *       update: 
   *       action: 
   *       
   *       Model  --> org.soc.common
   *        ModelPresenter
   *        ModelPresenterImpl
   *        ModelView
   *        
   *        ViewImpl --> com.google.gwt, com.gwtgraphics, 
   *        
   *        
   *   
   *   A presenter is defined in the Model itself. View are therefore defined
   *   by interfaces, subject to implementation by modules dependent on other GUI API's.
   *    
   *   A view is "dumb", containing the least amount possible "business logic". 
   *   Views may contain interaction, defined using events. 
   *   
   *   Models and Views fire events, @GenEvent is used to minimize boilerplate. 
   *   
   *   Collections
   *   Clean seperation of mutable and immutable collections is a base assumption.   
   *   
   *   
   *      *   
   *   A java.util.Collection<T> is a mutable collection, extension from that interface gives 
   *   us mutability by default. Thats a problem - not all our lists of models are mutable.
   *   To come around that problem, wrapping could be considered. This however leads to 
   *   enourmous amounts of boilerplate.  
   *   
   *  */
  public interface ModelPresenter<M extends Model, V extends ModelView> {
    public V view();
    
    /** Picks a model from a set of default model prototypes, and yields a model as result 
     * 
     *  Examples:
     *    - A city from a list of cities
     *    - A zipcode from a list of zipcodes
     *    - A player who must pick half of his resources to remove
     *    - A player defining his offered or desired resources when trading
     *    - A player offering resources to the bank
     *    - A location picker for a SiteDTO
     * */
    public interface ModelPicker
      <M extends Model<K>,                          
       Li extends FullImmutableList<M, K>,             // End result
       Lm extends MutableModelList<M, Li, K>,          // List of choices, "picks"
       V extends MutableListView<M, Li, Lm, K>,        // View showing choices and possible choices 
       K> 
      extends 
        ModelPresenter<M, V> {
      
      /*
       * @GenEvent public class Picked {
       *   @order(0) Li picked();
       * }
       */
      
      public interface PickerView
        <M extends Model<K>, 
         ImmutableL extends FullImmutableList<M, K>,
         MutableL extends MutableModelList<M, ImmutableL, K>,
         K> 
        extends
          MutableListView<M, ImmutableL,  MutableL, K> {
      }
    }
    /** Tries to textualize and visualize as much information about the model as possible 
     * 
     * Examples: 
     *  -A widget showing name, axe, latitudeLongitude, adminlevels of location
     *  -A widget showing info about a user/player
     * 
     * */
    public interface InfoPresenter
      <M extends Model, 
       V extends InfoView> {
      
      public class InfoPresenterImpl
        <M extends Model,
         V extends InfoView>
        implements 
          InfoPresenter {
        
        private M model;
        private V view;
        
        public InfoPresenterImpl(M model, V view) {
          this.model = model;
          this.view = view;
          
          this.view.setModel(model);
        }
      }
    }
  }
  /**
   * Must the view be defined in the presenter or in this View?
   * -Both, as the view in the presenter inherits from one or more 
   * generic views defined within ModelView.
   * 
   */
  /** Base interface for all Views */
  public  interface ModelView<M extends Model> {
    /** View general info about the model representation with meta info */ 
    public  interface InfoView<M extends Model>
      extends 
        ModelView {
      public void setModel(M model);
    }
  
    /** Render model on a painting surface (i.e. html5 canvas, svg canvas, PlayN layer) */
    public interface VisualView<M extends Model> extends ModelView {
      public void setModel(M model);
    }
  }
  public static final SupportedMakers supportedMakers = new SupportedMakers();
  
  public final static class SupportedMakers extends Impl {
    public SupportedMakers() {
      super(Resources.maker);
    }
  }
  public interface HasPresenters<M extends Model<K>, K> {
    public ModelPresenter createInfo(ModelView view);
    //public MutableListPresenter createMutableList();
  }
  public interface Makes
    <M extends Model<K>,
     Lm extends MutableModelList<M, Li,  K>,
     Li extends FullImmutableList<M, K>,
     K> {
    
    /** Returns all the non-Impl concrete implementors of ImmutableList */
    public ImmutableList<Li> supportedModelLists();
    /** All concrete implementors */
    public Li supportedModels();
    /** Any item to test with --> Is this one necessary? Maybe use supported.get(0).copy()? */
    public M newItem();
    /** Empty mutable list for model */
    public Lm emptyFullMutable();
    
    public interface ImmutableMakerList 
      extends
        ImmutableList<Makes> {
     
      public static class Impl
        extends 
          ImmutableListImpl<Makes>
        implements
          ImmutableMakerList {

        public Impl(Makes... makers) {
          super(makers);
        }
      }
    }
  }

  public interface HasId<K>  {
    /** Attempt to specify unicity scope of the identifier. Usually this is implicitly defined in
     *  an application, but being explicit here means we can do more fun stuff with the id */
    // TODO: move into id
    public enum IdScope { 
      /** Instances are unique among complete application */
      APPLICATION,
      /** Instances are unique among other instances of the same type/subtype */
      TYPE
    }

    public K id();
    public HasId setId(K id);
    public IdScope scope();
  }

  public interface IdGenerator<K> {
    public K generate();
  }

  public interface ByType<T extends Model> {
    public ImmutableList<T> ofType(Object type);
    public Set<Class> types();
    public boolean hasType(Class type);
  }

  public interface HasAddedHandlers<T> {
    public HandlerRegistration addAddedHandler(Added<T> handler);
  }

  public interface HasListAddedHandlers<T> {
    public HandlerRegistration addListAddedHandler(ListAdded<T> handler);
  }

  public interface HasRemovedHandlers<T> {
    public HandlerRegistration addRemovedHandler(Removed<T> handler);
  }

  public interface HasListRemovedHandlers<T> {
    public HandlerRegistration addListRemovedHandler(ListRemoved<T> handler);
  }

  public interface HasListMovedHandlers<T> {
    public HandlerRegistration addListMovedHandler(ListMoved<T> handler);
  }

  public interface HasMovedHandler<T> {
    public HandlerRegistration addMovedHandler(Moved<T> handler);
  }

  public interface Moves<T> extends HasMovedHandler<T>, Adds<T> {
    public void move(Removes<T> from, T itemToMove);

    public interface Moved<T> {
      public void moved(T item);
    }
  }

  public interface MovesList<T> extends HasListMovedHandlers<T>, ImmutableList<T> {
    public void moveListFrom(RemovesList<T> from, ImmutableList<T> itemsToMove);

    public interface ListMoved<T> {
      public void listMoved(ImmutableList<T> items);
    }
  }

  public interface Adds<T> extends HasAddedHandlers<T>, ImmutableList<T> {
    public void add(T item);

    public interface Added<T> {
      public void added(T item);
    }
  }

  public interface AddsList<T> extends HasListAddedHandlers<T>, ImmutableList<T> {
    public void addList(ImmutableList<T> items);

    public interface ListAdded<T> {
      public void listAdded(ImmutableList<T> items);
    }
  }

  public interface Removes<T> extends HasRemovedHandlers<T>, ImmutableList<T> {
    public void remove(T item);
    public void clear();

    public interface Removed<T> {
      public void removed(T item);
    }
  }

  public interface RemovesList<T> extends HasListRemovedHandlers<T>, ImmutableList<T> {
    public void removeList(ImmutableList<T> items);

    public interface ListRemoved<T> {
      public void listRemoved(ImmutableList<T> items);
    }
  }

  public interface ChangesOrder<T> extends ImmutableList<T> {
    public void changeOrder(T item, int newIndex, int oldIndex);

    public interface OrderChanged<T> {
      public void orderChanged(T item, int newIndex, int oldIndex);
    }
  }
  
  public interface Copies<M> {
    public M copy();
  }

  public interface HasIds<T extends HasId<K>, K> {
    public T byId(K id);
    public void giveIdToAll(IdGenerator<K> keyGen);
    public ImmutableList<T> byIds(ImmutableList<K> ids);
    public boolean containsWith(K id);
    public boolean containsWith(List<K> ids);
  }

  /** Preferably, we'd like to have a generic I index instead of an int */
  public interface Indexes<T> extends Collection<T> {
    public T get(int index); // This really should be byIndex
    public boolean isFirst(T item);
    public boolean isLast(T item);
    public int indexOf(T item);
    public int size();
  }

  public static <T> T getRandom(Indexes<T> indexedCollection, Random random) {
    if (indexedCollection.size() == 1)
      return indexedCollection.get(0);
    return indexedCollection.get(random.nextInt(indexedCollection.size() -1, true));
  }
  public static <M extends Model<K>, K> FullImmutableList<M, K> cloneModels(Iterable<M> models) {
    MutableModelList result  = new FullMutableListImpl();
    for (M copyable : models)
      result.add(copyable.copy());
    return result.toImmutableModels();
  }

  public interface Collection<T> {
    public int size();
  }

  public interface Orders<T> {
    /** Item at newIndex becomes the 0th (first) item in the list, the item at (newIndex -1) the last
     * item in the list */
    public void shiftIndex(int newIndex);
    /** Index of item at indexOfItem becomes indexToSwapTo and vice versa */
    public void swapAt(int indexOfItem, int indexToSwapTo);
    public void setFirst(int indexOfItem);
    public void setFirst(T item);
    public void setLast(int indexOfItem);
    public void setLast(T item);
    public void setTo(int index, T item);
  }

  public interface Filters<T> {
    public interface Filter<T> {
      public boolean matches(T item);
    }
    public ImmutableList<T> filterBy(Filter<T> filter);
  }

  public interface ImmutableList<T> 
    extends 
      Serializable, 
      Iterable<T>, 
      Indexes<T>, 
      Model {
    
    public boolean contains(T item);
    public boolean containsAll(Iterable<T> items);
    public boolean containsNone(Iterable<T> items);
    public T get(int index); // This really should be byIndex
    public int size();
    public boolean doesNotContain(T item);
    public boolean notEmpty();
    public boolean isEmpty();
    public int indexOf(T item);
    public boolean canContainNullItems();
    /** True when collection only contains one item */
    public boolean isSingeton();
    public ImmutableList<T> copy();

    public static abstract class AbstractImmutableList<T> implements ImmutableList<T> {
      protected List<T> items;

      /** Derivers should implement this to keep a reference to their wrapped list */
      protected abstract List<T> createList();
      public AbstractImmutableList() {
        items = createList();
      }
      public AbstractImmutableList(ImmutableList<T> wrapped) {
        this();
        for (T item : wrapped)
          items.add(item);
      }
      public AbstractImmutableList(T singletonItem) {
        this();
        items.add(singletonItem);
      }
      public AbstractImmutableList(List<T> byType) {
        this();
        items.addAll(byType);
      }
      public AbstractImmutableList(T[] items2) {
        this();
        for (T item : items2)
          items.add(item);
      }
      @Override public int size() {
        return items.size();
      }
      @Override public Iterator<T> iterator() {
        return items.iterator();
      }
      @Override public boolean doesNotContain(T item) {
        return !contains(item);
      }
      @Override public boolean contains(T item) {
        return items.contains(item);
      }
      @Override public boolean containsNone(Iterable<T> items) {
        for (T item : items)
          if (this.contains(item))
            return false;
        return true;
      }
      @Override public boolean containsAll(Iterable<T> itemsToCheck) {
        for (T item : itemsToCheck)
          if (doesNotContain(item))
            return false;
        return true;
      }
      @Override public T get(int index) {
        assert items.size() > (index + 1);
        return items.get(index);
      }
      @Override public boolean notEmpty() {
        return !isEmpty();
      }
      @Override public boolean isEmpty() {
        return items.isEmpty();
      }
      @Override public int indexOf(T item) {
        assert contains(item);
        return items.indexOf(item);
      }
      @Override public boolean canContainNullItems() {
        return false;
      }
      @Override public boolean isSingeton() {
        return size() == 1;
      }
      @Override public ImmutableList<T> copy() {
        return new ImmutableListImpl(this);
      }
      @Override public Object id() {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public HasId setId(Object id) {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public IdScope scope() {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public boolean isFirst(T item) {
        // TODO Auto-generated method stub
        return false;
      }
      @Override public boolean isLast(T item) {
        // TODO Auto-generated method stub
        return false;
      }
    }

    public class ImmutableListImpl<T> extends AbstractImmutableList<T> {
      public ImmutableListImpl(ImmutableList<T> wrapped) {
        super(wrapped);
      }
      public ImmutableListImpl(T... items) {
        super(items);
      }
//      public DefaultImmutableList(T singletonItem) {
//        super(singletonItem);
//      }
      @Override protected List<T> createList() {
        return new ArrayList<T>();
      }
      public ImmutableListImpl() {
        super();
      }
      public ImmutableListImpl(List<T> byType) {
        super(byType);
      }
      @Override public boolean isFirst(T item) {
        // TODO Auto-generated method stub
        return false;
      }
      @Override public boolean isLast(T item) {
        // TODO Auto-generated method stub
        return false;
      }
      
    }
  }

  public interface FullImmutableList
      <T extends Model<K>, K>
    extends
      ImmutableList<T>,
      HasIds<T, K>,
      Indexes<T>,
      ByType<T>  {
    
    public interface ImmutableListView
        <M extends Model, 
        L extends ImmutableList<M>>
      extends ModelView<L> {
      
    }

    public interface ImmutableListPresenter
      <M extends Model, 
       V extends ImmutableListView<M, L>, 
       L extends ImmutableList<M>>
            extends
            ModelPresenter {
      
      public class ImmutableListPresenterImpl<M, L, V> implements ImmutableListPresenter {
        private L listModel;
        private V view;

        public ImmutableListPresenterImpl(L listModel, V view) {
          this.listModel = listModel;
          this.view = view;
        }

        @Override public ModelView view() {
          return null; //view;
        }
      }
    }

    public abstract class AbstractFullImmutableList
        <T extends Model<K>, 
         Li extends FullImmutableList<T, K>, 
         K> 
      implements
        FullImmutableList<T, K> {
      
      protected final Map<K, T> itemsById = new HashMap<K, T>();
      protected final Map<Class, MutableList<T>> itemsByType = new HashMap<Class, MutableList<T>>();
      protected final List<T> items = new ArrayList<T>();

      protected AbstractFullImmutableList() {};
      public AbstractFullImmutableList(T[] withItems) {
        for (T item : withItems)
          add(item);
      }
      public AbstractFullImmutableList(FullImmutableList<T, K>... lists) {
        for (FullImmutableList<T, K> list : lists) 
          for (T item : list)
            add(item);
      }
      public AbstractFullImmutableList(ImmutableList<T> resources) {
        for (T item: resources)
          add(item);
      }
      private void add(T item) {
        assert !items.contains(item);
        items.add(item);
        itemsById.put(item.id(), item);
        MutableList<T> byType = itemsByType.get(item.getClass());
        if (byType == null)  {
          byType = new MutableListImpl<T>();
          itemsByType.put(item.getClass(), byType);
        }
        byType.add(item);
      }
      @Override public boolean contains(T item) {
        return itemsById.containsKey(item);
      }
      @Override public boolean containsAll(Iterable<T> itemsToHave) {
        for (T item : itemsToHave)
          if (!contains(item))
            return false;
        return true;
      }
      @Override public boolean containsNone(Iterable<T> itemsToNotHave) {
        for (T item : itemsToNotHave)
          if (contains(item))
            return false;
        return true;
      }
      @Override public T get(int index) {
        return items.get(index);
      }
      @Override public int size() {
        return items.size();
      }
      @Override public boolean doesNotContain(T item) {
        return !contains(item);
      }
      @Override public boolean notEmpty() {
        return !isEmpty();
      }
      @Override public boolean isEmpty() {
        return items.isEmpty();
      }
      @Override public int indexOf(T item) {
        return items.indexOf(item);
      }
      @Override public boolean canContainNullItems() {
        return false;
      }
      @Override public boolean isSingeton() {
        return items.size() == 1;
      }
      @Override public ImmutableList<T> copy() {
        return null;
      }
      @Override public Iterator<T> iterator() {
        return items.iterator();
      }
      @Override public ImmutableList<T> ofType(Object type) {
        if (hasType(type.getClass())) 
          return itemsByType.get(type.getClass());
        return new ImmutableListImpl<T>();
      }
      @Override public T byId(K id) {
        return itemsById.get(id);
      }
      @Override public void giveIdToAll(IdGenerator<K> keyGen) {
        for (T hasId : items)
          hasId.setId(keyGen.generate());
      }
      @Override public Li byIds(ImmutableList<K> ids) {
        MutableModelList<T, Li, K> list = new FullMutableListImpl<T, Li, K>();
        for (K id : ids)
          if (containsWith(id))
            list.add(itemsById.get(id));
        return list.toImmutableModels();
      }
      @Override public boolean containsWith(K id) {
        return itemsById.containsKey(id);
      }
      @Override public boolean containsWith(List<K> ids) {
        for (K id : ids)
          if (!containsWith(id))
            return false;
        return true;
      }
      @Override public Set<Class> types() {
        return itemsByType.keySet();
      }
      @Override public boolean hasType(Class clazz) {
        return itemsByType.containsKey(clazz);
      }
      @Override public boolean isFirst(T item) {
        return false;
      }
      @Override public boolean isLast(T item) {
        return false;
      }
      @Override public Object id() {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public HasId setId(Object id) {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public IdScope scope() {
        // TODO Auto-generated method stub
        return null;
      }
    }

    public class FullImmutableListImpl
      <T extends Model<K>, K>
       implements
         FullImmutableList<Model<K>, K> {
      
      private Map<K, T> itemsById = new HashMap<K, T>();
      private Map<Class, ImmutableList<Model<K>>> typesMap = new HashMap<Class, ImmutableList<Model<K>>>();
      protected final List<T> items = new ArrayList<T>();

      public FullImmutableListImpl(List<T> withItems) {
        for (T item : withItems)
          items.add(item);
      }
      public FullImmutableListImpl(T... withItems) {
        for (T item : withItems)
          items.add(item);
      }
      @Override public boolean contains(Model<K> item) {
        return itemsById.containsKey(item);
      }
      @Override public boolean containsAll(Iterable<Model<K>> items) {
        return false;
      }
      @Override public boolean containsNone(Iterable<Model<K>> items) {
        // TODO Auto-generated method stub
        return false;
      }
      @Override public Model<K> get(int index) {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public int size() {
        return items.size();
      }
      @Override public boolean doesNotContain(Model<K> item) {
        // TODO Auto-generated method stub
        return false;
      }
      @Override public boolean notEmpty() {
        return !isEmpty();
      }
      @Override public boolean isEmpty() {
        return items.isEmpty();
      }
      @Override public int indexOf(Model<K> item) {
        // TODO Auto-generated method stub
        return 0;
      }
      @Override public boolean canContainNullItems() {
        return false;
      }
      @Override public boolean isSingeton() {
        return items.size() == 1;
      }
      @Override public ImmutableList<Model<K>> copy() {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public Iterator<Model<K>> iterator() {
        return null;
      }
      @Override public Model<K> byId(K id) {
        return itemsById.get(id);
      }
      @Override public void giveIdToAll(IdGenerator<K> keyGen) {
        // TODO Auto-generated method stub
      }
      @Override public ImmutableList<Model<K>> byIds(ImmutableList<K> ids) {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public boolean containsWith(K id) {
        return itemsById.get(id) != null;
      }
      @Override public boolean containsWith(List<K> ids) {
        // TODO Auto-generated method stub
        return false;
      }
      @Override public ImmutableList<Model<K>> ofType(Object type) {
        if (hasType(type.getClass()))
          return typesMap.get(type.getClass());
        return new ImmutableListImpl();
      }
      @Override public Set<Class> types() {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public boolean hasType(Class clazz) {
        return typesMap.get(clazz) !=null;
      }
      @Override public boolean isFirst(Model<K> item) {
        // TODO Auto-generated method stub
        return false;
      }
      @Override public boolean isLast(Model<K> item) {
        // TODO Auto-generated method stub
        return false;
      }
      @Override public Object id() {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public HasId setId(Object id) {
        // TODO Auto-generated method stub
        return null;
      }
      @Override public IdScope scope() {
        // TODO Auto-generated method stub
        return null;
      }
    }
  }

  /** An immutable list where only addition of items is possible */
  public interface Log<T>
          extends
          ImmutableList<T>,
          Adds<T>, HasAddedHandlers<T> {
    public class DefaultLog<T> extends AbstractImmutableList<T> implements Log<T> {
      private MutableList<T> wrapped = new MutableListImpl<T>();

      @Override public void add(T item) {
        wrapped.add(item);
      }
      @Override public HandlerRegistration addAddedHandler(Added<T> handler) {
        return null;
      }
      @Override protected List<T> createList() {
        return null;
      }
      @Override public boolean isFirst(T item) {
        // TODO Auto-generated method stub
        return false;
      }
      @Override public boolean isLast(T item) {
        // TODO Auto-generated method stub
        return false;
      }
    }
  }

  /** Simple list supporting add/removal/moving of single items */
  public interface MutableList<T>
    extends
      //Collection<T>,
      ImmutableList<T>,
      Adds<T>,
      Removes<T>,
      Moves<T> {
      ImmutableList<T> toImmutable();

    /** Base implementation of a list subscribers can listen to. Derivers should explicitly implement
     * addHandler, because there is no generic implementation possible for that in Java. */
    public abstract class AbstractMutableList<T> extends AbstractImmutableList<T> implements
            MutableList<T> {
      protected ListEventBus<T> eventBus = new ListEventBus<T>();
      public AbstractMutableList() {
        super();
      }

      @Override public void add(T item) {
        items.add(item);
        eventBus.added(item);
      }
      @Override public void remove(T item) {
        assert (contains(item));
        items.remove(item);
        eventBus.removed(item);
      }
      @Override public void move(Removes<T> from, T itemToMove) {
        assert (from.contains(itemToMove));
        from.remove(itemToMove);
        add(itemToMove);
        eventBus.moved(itemToMove);
      }
      @Override public HandlerRegistration addAddedHandler(Added<T> handler) {
        return eventBus.addAddedHandler(handler);
      }
      @Override public HandlerRegistration addRemovedHandler(Removed<T> handler) {
        return eventBus.addRemovedHandler(handler);
      }
      @Override public HandlerRegistration addMovedHandler(Moved<T> handler) {
        return eventBus.addMovedHandler(handler);
      }
      @Override public void clear() {
        for (T item : this)
          remove(item);
      }
      @Override public ImmutableList<T> toImmutable() {
        return new ImmutableListImpl(this);
      }

      @Override public boolean isFirst(T item) {
        // TODO Auto-generated method stub
        return false;
      }

      @Override public boolean isLast(T item) {
        // TODO Auto-generated method stub
        return false;
      }

      @Override public Object id() {
        // TODO Auto-generated method stub
        return null;
      }

      @Override public HasId setId(Object id) {
        // TODO Auto-generated method stub
        return null;
      }

      @Override public IdScope scope() {
        // TODO Auto-generated method stub
        return null;
      }
      
    }

    public class MutableListImpl<T> extends AbstractMutableList<T> {
      public MutableListImpl() {
        super();
      }
      public MutableListImpl(MutableList<T> mutableList) {
        for (T item : mutableList)
          items.add(item);
      }
      @Override protected List<T> createList() {
        return new ArrayList<T>();
      }
    }

    /** full featured list */
    public interface MutableModelList
        <T extends Model<K>, 
         Li extends FullImmutableList<T, K>, 
         K>
      extends
        FullImmutableList<T, K>,
        MutableList<T>,
        Adds<T>,
        AddsList<T>,
        Removes<T>,
        RemovesList<T>,
        Moves<T>,
        MovesList<T>,
        ByType<T>,
        HasIds<T, K> {
      
      /** Allows delegation (wrapping) of core list logic to his immutable implementation */
      public Li toImmutableModels();
      
      /** Keeps instances in a list, a map of keys, and a map of types */
      public class FullMutableListImpl
        <T extends Model<K>, 
         ImmutableL extends FullImmutableList<T, K>, 
         K>
        extends
          MutableList.AbstractMutableList<T>
        implements
          MutableList.MutableModelList<T, ImmutableL, K> {
        
        private Map<K, T> itemsById = new HashMap<K, T>();
        private Map<Class, MutableList<T>> typesMap = new HashMap<Class, MutableList<T>>();

        public FullMutableListImpl() {
          super();
          items = createList();
        }
        public FullMutableListImpl(FullImmutableList<T, K> list) {
          this();
          for (T item : list)
            add(item);
        }
        public FullMutableListImpl(MutableList.MutableModelList<T, ImmutableL, K> list) {
          this();
          for (T item : list)
            add(item);
        }
        public FullMutableListImpl(ImmutableList<T> list) {
          this();
          for (T item : list)
            add(item);
        }
        @Override protected List<T> createList() {
          return new ArrayList<T>();
        }
        @Override public Iterator<T> iterator() {
          return items.iterator();
        }
        @Override public void add(T item) {
          items.add(item);
          MutableList<T> ofType = typesMap.get(item.getClass());
          if (ofType == null) {
            ofType = new MutableList.MutableListImpl<T>();
            typesMap.put(item.getClass(), ofType);
          }
          ofType.add(item);
          assert !itemsById.containsKey(item.id());
          itemsById.put(item.id(), item);
          eventBus.added(item);
        }
        @Override public void remove(T item) {
          assert contains(item);
          items.remove(item);
          itemsById.remove(item.id());
          typesMap.get(item.getClass()).remove(item);
          eventBus.removed(item);
        }
        @Override public void move(Removes<T> from, T itemToMove) {
          assert (from.contains(itemToMove));
          from.remove(itemToMove);
          add(itemToMove);
          eventBus.moved(itemToMove);
        }
        @Override public void addList(ImmutableList<T> itemsToAdd) {
          for (T item : itemsToAdd)
            add(item);
          eventBus.listAdded(itemsToAdd);
        }
        @Override public void removeList(ImmutableList<T> itemsToRemove) {
          assert containsAll(items);
          for (T item : itemsToRemove)
            remove(item);
          eventBus.listRemoved(itemsToRemove);
        }
        @Override public void moveListFrom(RemovesList<T> from,
                ImmutableList<T> itemsToMove) {
          assert from.containsAll(itemsToMove);
          from.removeList(itemsToMove);
          addList(itemsToMove);
          eventBus.listMoved(itemsToMove);
        }
        @Override public HandlerRegistration addListAddedHandler(AddsList.ListAdded<T> handler) {
          return eventBus.addListAddedHandler(handler);
        }
        @Override public HandlerRegistration addListRemovedHandler(RemovesList.ListRemoved<T> handler) {
          return eventBus.addListRemovedHandler(handler);
        }
        @Override public HandlerRegistration addListMovedHandler(MovesList.ListMoved<T> handler) {
          return eventBus.addListMovedHandler(handler);
        }
        @Override public T byId(K id) {
          assert (containsWith(id));
          return itemsById.get(id);
        }
        @Override public void giveIdToAll(IdGenerator<K> keyGen) {
          for (T item : items)
            item.setId(keyGen.generate());
        }
        @Override public ImmutableList<T> byIds(ImmutableList<K> ids) {
          Adds<T> list = new MutableList.MutableListImpl<T>();
          for (K id : ids) {
            assert (containsWith(id));
            list.add(byId(id));
          }
          return new ImmutableList.ImmutableListImpl<T>(list);
        }
        @Override public boolean containsWith(K id) {
          return itemsById.get(id) != null;
        }
        @Override public boolean containsWith(List<K> ids) {
          for (K id : ids)
            if (!containsWith(id))
              return false;
          return true;
        }
        @Override public ImmutableList<T> ofType(Object type) {
          if (hasType(type.getClass()))
            return typesMap.get(type.getClass());
          return new ImmutableListImpl();
        }
        @Override public Set<Class> types() {
          // TODO Auto-generated method stub
          return null;
        }
        @Override public boolean hasType(Class type) {
          return typesMap.get(type) != null;
        }
        @Override public ImmutableL toImmutableModels() {
          return null;
        }
      }
      
      public interface MutableListView
         <M extends Model<K>, 
         ImmutableL extends FullImmutableList<M, K>,
         L extends MutableModelList<M, ImmutableL, K>, 
         K> 
        extends 
          ModelView<L> {
        
        public void add(M model);
        public void remove(M model);
      }

      public interface MutableListPresenter
          <M extends Model<K>, 
           Li extends FullImmutableList<M, K>,
           Lm extends MutableModelList<M, Li, K>, 
           V extends MutableListView, 
           K> {
        
        public class MutableListPresenterImpl
           <M extends Model<K>,
            Li extends FullImmutableList<M, K>,
            Lm extends MutableModelList<M, Li, K>,
            V extends MutableListView,
            K>
          implements 
            MutableListPresenter {
          
          private Lm list;
          private V view;

          @Inject public MutableListPresenterImpl(Lm list, final V view) {
            this.list = list;
            this.view = view;
            
            list.addAddedHandler(new Added<M>() {
              @Override public void added(M item) {
                view.add(item);
              }
            });
            list.addListAddedHandler(new ListAdded<M>() {
              @Override public void listAdded(ImmutableList<M> items) {
                for (M item : items)
                  view.add(item);
              }
            });
            list.addListRemovedHandler(new ListRemoved<M>() {
              @Override public void listRemoved(ImmutableList<M> items) {
                for (M item: items)
                  view.remove(item);
              }
            });
            list.addRemovedHandler(new Removed<M>() {
              @Override public void removed(M item) {
                view.remove(item);
              }
            });
          }
        }
      }
    }
  }
}// @formatter:on
