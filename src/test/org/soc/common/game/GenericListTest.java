package org.soc.common.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.soc.common.utils.GenericList.Adds.Added;
import org.soc.common.utils.GenericList.AddsList.ListAdded;
import org.soc.common.utils.GenericList.HasId;
import org.soc.common.utils.GenericList.ImmutableList;
import org.soc.common.utils.GenericList.Moves.Moved;
import org.soc.common.utils.GenericList.MovesList;
import org.soc.common.utils.GenericList.MovesList.ListMoved;
import org.soc.common.utils.GenericList.MutableList;
import org.soc.common.utils.GenericList.MutableList.DefaultMutableList;
import org.soc.common.utils.GenericList.MutableList.FullMutableList;
import org.soc.common.utils.GenericList.MutableList.FullMutableList.DefaultFullMutableList;
import org.soc.common.utils.GenericList.Removes.Removed;
import org.soc.common.utils.GenericList.RemovesList;
import org.soc.common.utils.GenericList.RemovesList.ListRemoved;

public class GenericListTest {
  public interface Woei extends HasId<Integer> {}

  public static class Roei implements Woei {
    int id = 0;

    @Override public Integer id() {
      return id;
    }
    @Override public Roei setId(Integer id) {
      this.id = id;
      return this;
    }
    @Override public IdScope scope() {
      return IdScope.APPLICATION;
    }
  }

  public interface ListFactory<T extends HasId<K>, K> {
    public T newItem();
    public MutableList<T> newList();
    public FullMutableList<T, K> newFullMutableList();
  }

  public class WoeiFactory implements ListFactory<Woei, Integer> {
    @Override public Woei newItem() {
      return new Roei();
    }
    @Override public MutableList<Woei> newList() {
      return new DefaultMutableList<Woei>();
    }
    @Override public FullMutableList<Woei, Integer> newFullMutableList() {
      return new DefaultFullMutableList<Woei, Integer>();
    }
  }

  @Test public void testDefaultFullMutableList() {
    testMutableList(new WoeiFactory());
    testFullMutableList(new WoeiFactory());
  }
  public static <T extends HasId<K>, K> void testMutableList(ListFactory<T, K> factory) {
    MutableListTester tester = new MutableListTester();
    tester.testAll(factory.newList(), factory.newItem(), factory.newList());
  }
  public static <T extends HasId<K>, K> void testFullMutableList(ListFactory<T, K> f) {
    FullMutableListTester tester = new FullMutableListTester();
    tester.testListRemovedEventFires(f.newFullMutableList(), f.newList());
    tester.testFilterByType(f.newFullMutableList(), f.newItem());
    tester.testListAddedEventFires(f.newFullMutableList(), f.newList());
    tester.testListRemovedEventFires(f.newFullMutableList(), f.newList(), f.newFullMutableList());
    tester.testId(f.newFullMutableList(), f.newItem());
  }

  public static class FullMutableListTester {
    private boolean addedListFired = false;
    private boolean movedListFired = false;
    private boolean listRemovedFired = false;

    public <T extends HasId<K>, K> void testListAddedEventFires(FullMutableList<T, K> list,
            ImmutableList<T> listToAdd) {
      list.addListAddedHandler(new ListAdded<T>() {
        @Override public void listAdded(ImmutableList<T> items) {
          addedListFired = true;
        }
      });
      list.addList(listToAdd);
      assertTrue(addedListFired);
      assertTrue(list.containsAll(listToAdd));
    }
    public <T extends HasId<K>, K> void testListRemovedEventFires(FullMutableList<T, K> list,
            ImmutableList<T> itemsToRemove) {
      list.addList(itemsToRemove);
      list.addListRemovedHandler(new ListRemoved<T>() {
        @Override public void listRemoved(ImmutableList<T> items) {
          listRemovedFired = true;
        }
      });
      list.removeList(itemsToRemove);
      assertTrue(listRemovedFired);
    }
    public <T extends HasId<K>, K> void testListRemovedEventFires(MovesList<T> list,
            ImmutableList<T> itemsToMove, RemovesList<T> from) {
      list.addListMovedHandler(new ListMoved<T>() {
        @Override public void listMoved(ImmutableList<T> items) {
          movedListFired = true;
        }
      });
      list.moveList(from, itemsToMove);
      assertTrue(movedListFired);
      assertTrue(list.containsAll(itemsToMove));
      assertTrue(from.containsNone(itemsToMove));
    }
    public <T extends HasId<K>, K> void testFilterByType(FullMutableList<T, K> list, T item) {
      list.add(item);
      ImmutableList<T> ofType = list.ofType(item.getClass());
      assertTrue(ofType.contains(item));
    }
    public <T extends HasId<K>, K> void testId(FullMutableList<T, K> list, T item) {
      list.add(item);
      assertTrue(list.containsWith(item.id()));
    }
  }

  public static class AddsTester {
    private boolean addedFired = false;

    public <T> void assertAddEventFires(MutableList<T> list, T item) {
      list.addAddedHandler(new Added<T>() {
        @Override public void added(T item) {
          addedFired = true;
        }
      });
      list.add(item);
      assertTrue(addedFired);
    }
  }

  public static class RemovesTester {
    private boolean removedFired = false;

    public <T> void assertRemoveEventFires(MutableList<T> list, T item) {
      list.add(item);
      list.addRemovedHandler(new Removed<T>() {
        @Override public void removed(T item) {
          removedFired = true;
        }
      });
      list.remove(item);
      assertTrue(removedFired);
    }
  }

  public static class MovesTester {
    private boolean movedFired = false;

    public <T> void
            assertMoveEventFires(MutableList<T> list, final T itemToMove, MutableList<T> otherList) {
      otherList.add(itemToMove);
      list.addMovedHandler(new Moved<T>() {
        @Override public void moved(T movedItem) {
          movedFired = true;
          assertTrue(itemToMove.equals(movedItem));
        }
      });
      list.move(otherList, itemToMove);
      assertTrue("Expected item to be moved", movedFired);
    }
  }

  public static class MutableListTester {
    private MovesTester movesTester = new MovesTester();
    private AddsTester addsTester = new AddsTester();
    private RemovesTester removesTester = new RemovesTester();

    public <T> void testAll(MutableList<T> list, T item, MutableList<T> otherList) {
      movesTester.assertMoveEventFires(list, item, otherList);
      addsTester.assertAddEventFires(list, item);
      removesTester.assertRemoveEventFires(list, item);
    }
    public <T> boolean canGetByIndex(MutableList<T> list, T item) {
      list.add(item);
      return list.get(0) != null;
    }
  }
}
