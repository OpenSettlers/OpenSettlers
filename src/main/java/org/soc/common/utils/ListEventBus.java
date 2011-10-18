package org.soc.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.core.GenericList.*;
import org.soc.common.core.GenericList.Adds.*;
import org.soc.common.core.GenericList.AddsList.*;
import org.soc.common.core.GenericList.Moves.*;
import org.soc.common.core.GenericList.MovesList.*;
import org.soc.common.core.GenericList.Removes.*;
import org.soc.common.core.GenericList.RemovesList.*;

import com.google.web.bindery.event.shared.HandlerRegistration;

/** Notifies handlers of changes in a collection */
public class ListEventBus<T>                                    //@formatter:off
        implements
        Added<T>, HasAddedHandlers<T>,
        Removed<T>, HasRemovedHandlers<T>,
        Moved<T>, HasMovedHandler<T>,
        ListAdded<T>, HasListAddedHandlers<T>,
        ListRemoved<T>, HasListRemovedHandlers<T>,
        ListMoved<T>, HasListMovedHandlers<T>
{                                                               //@formatter:on
  private List<Added<T>> addedHandlers = new ArrayList<Added<T>>();
  private List<ListAdded<T>> listAddedHandlers = new ArrayList<ListAdded<T>>();
  private List<Removed<T>> removedHandlers = new ArrayList<Removed<T>>();
  private List<ListRemoved<T>> listRemovedHandlers = new ArrayList<ListRemoved<T>>();
  private List<Moved<T>> movedHandlers = new ArrayList<Moved<T>>();
  private List<ListMoved<T>> listMovedHandlers = new ArrayList<ListMoved<T>>();

  @Override public void added(T item) {
    if (addedHandlers != null)
      for (Added<T> handler : addedHandlers)
        handler.added(item);
  }
  @Override public HandlerRegistration addAddedHandler(final Added<T> handler) {
    if (addedHandlers == null)
      addedHandlers = new ArrayList<Added<T>>();
    addedHandlers.add(handler);
    return new HandlerRegistration() {
      @Override public void removeHandler() {
        addedHandlers.remove(handler);
      }
    };
  }
  @Override public HandlerRegistration addListMovedHandler(final ListMoved<T> handler) {
    if (listMovedHandlers == null)
      listMovedHandlers = new ArrayList<ListMoved<T>>();
    listMovedHandlers.add(handler);
    return new HandlerRegistration() {
      @Override public void removeHandler() {
        listMovedHandlers.remove(handler);
      }
    };
  }
  @Override public void listMoved(ImmutableList<T> items) {
    if (movedHandlers != null)
      for (ListMoved<T> handler : listMovedHandlers)
        handler.listMoved(items);
  }
  @Override public HandlerRegistration addListRemovedHandler(final ListRemoved<T> handler) {
    if (listRemovedHandlers == null)
      listRemovedHandlers = new ArrayList<ListRemoved<T>>();
    listRemovedHandlers.add(handler);
    return new HandlerRegistration() {
      @Override public void removeHandler() {
        listRemovedHandlers.remove(handler);
      }
    };
  }
  @Override public void listRemoved(ImmutableList<T> items) {
    if (listRemovedHandlers != null)
      for (ListRemoved<T> handler : listRemovedHandlers)
        handler.listRemoved(items);
  }
  @Override public HandlerRegistration addListAddedHandler(final ListAdded<T> handler) {
    if (listAddedHandlers == null)
      listAddedHandlers = new ArrayList<ListAdded<T>>();
    listAddedHandlers.add(handler);
    return new HandlerRegistration() {
      @Override public void removeHandler() {
        listAddedHandlers.remove(handler);
      }
    };
  }
  @Override public void listAdded(ImmutableList<T> item) {
    if (addedHandlers != null)
      for (ListAdded<T> handler : listAddedHandlers)
        handler.listAdded(item);
  }
  @Override public HandlerRegistration addMovedHandler(final Moved<T> handler) {
    if (movedHandlers == null)
      movedHandlers = new ArrayList<Moved<T>>();
    movedHandlers.add(handler);
    return new HandlerRegistration() {
      @Override public void removeHandler() {
        movedHandlers.remove(handler);
      }
    };
  }
  @Override public void moved(T item) {
    if (movedHandlers != null)
      for (Moved<T> handler : movedHandlers)
        handler.moved(item);
  }
  @Override public HandlerRegistration addRemovedHandler(final Removed<T> handler) {
    if (removedHandlers == null)
      removedHandlers = new ArrayList<Removed<T>>();
    removedHandlers.add(handler);
    return new HandlerRegistration() {
      @Override public void removeHandler() {
        removedHandlers.remove(handler);
      }
    };
  }
  @Override public void removed(T item) {
    if (removedHandlers != null)
      for (Removed<T> handler : removedHandlers)
        handler.removed(item);
  }
}
