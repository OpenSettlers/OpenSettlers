package org.soc.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.soc.common.utils.GenericList.Adds.Added;
import org.soc.common.utils.GenericList.AddsList.ListAdded;
import org.soc.common.utils.GenericList.Moves.Moved;
import org.soc.common.utils.GenericList.MovesList.ListMoved;
import org.soc.common.utils.GenericList.MutableList.DefaultMutableList;
import org.soc.common.utils.GenericList.Removes.Removed;
import org.soc.common.utils.GenericList.RemovesList.ListRemoved;

import com.google.web.bindery.event.shared.HandlerRegistration;

/** Base interface for lists of things used in model classes Fixes several shortcomings
 * and omissions of the java.util package: 
 * - Fully generic List<T>/Set<T> 
 * - Listenable lists
 * - Fine-grained support of lists using interfaces
 * - Immutable collections have mutator methods by default
 * - Unicity of items which have an identifier in global/local scope
 * - Literate methods like doesNotContain(), notEmpty() 
 * - Moving items from one to another collection
 */
public interface GenericList {
  public interface HasId<K> {
    /** Attempt to specify unicity scope of the identifier */
    // TODO: put more thought into this
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

  public interface ByType<T> {
    public ImmutableList<T> ofType(Class type);
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

  public interface Moves<T> extends HasMovedHandler<T> {
    public HandlerRegistration addMovedHandler(Moved<T> handler);
    public Moves<T> move(Removes<T> from, T itemToMove);

    public interface Moved<T> {
      public void moved(T item);
    }
  }

  public interface MovesList<T> extends HasListMovedHandlers<T>, ImmutableList<T> {
    public HandlerRegistration addListMovedHandler(ListMoved<T> handler);
    public MovesList<T> moveList(RemovesList<T> from, ImmutableList<T> itemsToMove);

    public interface ListMoved<T> {
      public void listMoved(ImmutableList<T> items);
    }
  }

  public interface Adds<T> extends HasAddedHandlers<T> {
    public Adds<T> add(T item);

    public interface Added<T> {
      public void added(T item);
    }
  }

  public interface AddsList<T> extends HasListAddedHandlers<T>, ImmutableList<T> {
    public HandlerRegistration addListAddedHandler(ListAdded<T> handler);
    public AddsList<T> addList(ImmutableList<T> items);

    public interface ListAdded<T> {
      public void listAdded(ImmutableList<T> items);
    }
  }

  public interface Removes<T> extends HasRemovedHandlers<T>, ImmutableList<T> {
    public HandlerRegistration addRemovedHandler(Removed<T> handler);
    public MutableList<T> remove(T item);

    public interface Removed<T> {
      public void removed(T item);
    }
  }

  public interface RemovesList<T> extends HasListRemovedHandlers<T>, ImmutableList<T> {
    public HandlerRegistration addListRemovedHandler(ListRemoved<T> handler);
    public MutableList<T> removeList(ImmutableList<T> items);

    public interface ListRemoved<T> {
      public void listRemoved(ImmutableList<T> items);
    }
  }

  public interface ChangesOrder<T> extends ImmutableList<T> {
    public HandlerRegistration addOrderChangedHandler(OrderChanged<T> handler);
    public MutableList<T> changeOrder(T item, int newIndex, int oldIndex);

    public interface OrderChanged<T> {
      public void orderChanged(T item, int newIndex, int oldIndex);
    }
  }

  public interface HasIds<T extends HasId<K>, K> {
    public T byId(K id);
    public HasIds giveIdToAll(IdGenerator<K> keyGen);
    public ImmutableList<T> byIds(ImmutableList<K> ids);
    public boolean containsWith(K id);
    public boolean containsWith(List<K> ids);
  }

  public interface ImmutableList<T> extends Serializable, Iterable<T> {
    public boolean contains(T item);
    public boolean containsAll(Iterable<T> items);
    public boolean containsNone(Iterable<T> items);
    public T get(int index);
    public int size();
    public boolean doesNotContain(T item);
    public boolean notEmpty();
    public boolean isEmpty();
    public int indexOf(T item);
    public boolean canContainNullItems();

    public abstract class AbstractImmutableList<T> implements ImmutableList<T> {
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
        assert (items.size() > index + 1);
        return items.get(index);
      }
      @Override public boolean notEmpty() {
        return !isEmpty();
      }
      @Override public boolean isEmpty() {
        return items.isEmpty();
      }
      @Override public int indexOf(T item) {
        assert (contains(item));
        return items.indexOf(item);
      }
      @Override public boolean canContainNullItems() {
        return false;
      }
    }

    public class DefaultImmutableList<T> extends AbstractImmutableList<T> {
      public DefaultImmutableList(ImmutableList<T> wrapped) {
        super(wrapped);
      }
      @Override protected List<T> createList() {
        return new ArrayList<T>();
      }
    }
  }

  /** A mutable list where only addition of items is possible */
  public interface Log<T>
          extends
          ImmutableList<T>,
          Adds<T>, HasAddedHandlers<T> {
    public class DefaultLog<T> extends AbstractImmutableList<T> implements Log<T> {
      private MutableList<T> wrapped = new DefaultMutableList<T>();

      @Override public Adds<T> add(T item) {
        wrapped.add(item);
        return this;
      }
      @Override public HandlerRegistration addAddedHandler(Added<T> handler) {
        return null;
      }
      @Override protected List<T> createList() {
        return null;
      }
    }
  }

  public interface KeyedImmutableList<T extends HasId<K>, K> extends ImmutableList<T> {}

  /** Simple list supporting add/removal/moving of single items */
  public interface MutableList<T>
          extends
          ImmutableList<T>,
          Adds<T>,
          Removes<T>,
          Moves<T> {
    public ImmutableList<T> toImmutable();

    /** Base implementation of a list subscribers can listen to. Derivers should explicitly implement
     * addHandler, because there is no generic implementation possible for that in Java. */
    public abstract class AbstractMutableList<T> extends AbstractImmutableList<T> implements
            MutableList<T> {
      protected ListEventBus<T> eventBus = new ListEventBus<T>();

      @Override public MutableList<T> add(T item) {
        items.add(item);
        eventBus.added(item);
        return this;
      }
      @Override public MutableList<T> remove(T item) {
        assert (contains(item));
        items.remove(item);
        eventBus.removed(item);
        return this;
      }
      @Override public Moves<T> move(Removes<T> from, T itemToMove) {
        assert (from.contains(itemToMove));
        from.remove(itemToMove);
        add(itemToMove);
        eventBus.moved(itemToMove);
        return this;
      }
      @Override public ImmutableList<T> toImmutable() {
        return new DefaultImmutableList<T>(this);
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
    }

    public class DefaultMutableList<T> extends AbstractMutableList<T> {
      public DefaultMutableList() {
        super();
      }
      public DefaultMutableList(MutableList<T> mutableList) {
        for (T item : mutableList)
          items.add(item);
      }
      @Override protected List<T> createList() {
        return new ArrayList<T>();
      }
    }

    public interface FullMutableList<T extends HasId<K>, K>
            extends
            MutableList<T>,
            Adds<T>,
            AddsList<T>,
            Removes<T>,
            RemovesList<T>,
            Moves<T>,
            MovesList<T>,
            ByType<T>,
            HasIds<T, K> {
      /** Keeps instances in a list, a map of keys, and a map of types */
      public class DefaultFullMutableList<T extends HasId<K>, K>
              extends AbstractMutableList<T>
              implements FullMutableList<T, K> {
        private Map<K, T> itemsById = new HashMap<K, T>();
        private Map<Class, MutableList<T>> typesMap = new HashMap<Class, MutableList<T>>();

        public DefaultFullMutableList() {
          super();
          items = createList();
        }
        @Override protected List<T> createList() {
          return new ArrayList<T>();
        }
        @Override public ImmutableList<T> ofType(Class type) {
          MutableList<T> ofType = typesMap.get(type);
          if (ofType == null) {
            ofType = new DefaultMutableList<T>();
            typesMap.put(type, ofType);
          }
          return ofType.toImmutable();
        }
        @Override public Iterator<T> iterator() {
          return items.iterator();
        }
        @Override public FullMutableList<T, K> add(T item) {
          items.add(item);
          MutableList<T> ofType = typesMap.get(item.getClass());
          if (ofType == null) {
            ofType = new DefaultMutableList<T>();
            typesMap.put(item.getClass(), ofType);
          }
          ofType.add(item);
          assert (!itemsById.containsKey(item.id()));
          itemsById.put(item.id(), item);
          eventBus.added(item);
          return this;
        }
        @Override public FullMutableList<T, K> remove(T item) {
          assert (contains(item));
          items.remove(item);
          itemsById.remove(item.id());
          typesMap.get(item.getClass()).remove(item);
          eventBus.removed(item);
          return this;
        }
        @Override public Moves<T> move(Removes<T> from, T itemToMove) {
          assert (from.contains(itemToMove));
          from.remove(itemToMove);
          add(itemToMove);
          eventBus.moved(itemToMove);
          return this;
        }
        @Override public FullMutableList<T, K> addList(ImmutableList<T> itemsToAdd) {
          for (T item : itemsToAdd)
            add(item);
          eventBus.listAdded(itemsToAdd);
          return this;
        }
        @Override public FullMutableList<T, K> removeList(ImmutableList<T> itemsToRemove) {
          assert (containsAll(items));
          for (T item : itemsToRemove)
            remove(item);
          eventBus.listRemoved(itemsToRemove);
          return this;
        }
        @Override public FullMutableList<T, K> moveList(RemovesList<T> from,
                ImmutableList<T> itemsToMove) {
          assert (from.containsAll(itemsToMove));
          from.removeList(itemsToMove);
          addList(itemsToMove);
          eventBus.listMoved(itemsToMove);
          return this;
        }
        @Override public HandlerRegistration addListAddedHandler(ListAdded<T> handler) {
          return eventBus.addListAddedHandler(handler);
        }
        @Override public HandlerRegistration addListRemovedHandler(ListRemoved<T> handler) {
          return eventBus.addListRemovedHandler(handler);
        }
        @Override public HandlerRegistration addListMovedHandler(ListMoved<T> handler) {
          return eventBus.addListMovedHandler(handler);
        }
        @Override public T byId(K id) {
          assert (containsWith(id));
          return itemsById.get(id);
        }
        @Override public HasIds giveIdToAll(IdGenerator<K> keyGen) {
          for (T item : items)
            item.setId(keyGen.generate());
          return this;
        }
        @Override public ImmutableList<T> byIds(ImmutableList<K> ids) {
          return null;
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
      }
    }
  }
}
