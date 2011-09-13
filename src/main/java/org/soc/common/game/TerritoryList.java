package org.soc.common.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.soc.common.game.Territory.TerritoryImpl;
import org.soc.common.game.TerritoryListChangedEvent.HasTerritoryListChangedHandlers;
import org.soc.common.game.TerritoryListChangedEvent.TerritoryListChangedHandler;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

/** List of territories. Defaults on having a Mainland, but a mainland is not required. */
public class TerritoryList implements Iterable<Territory>, Serializable,
        HasTerritoryListChangedHandlers
{
  @GenEvent public class TerritoryListChanged {
    @Order(0) Territory addedTerritory;
    @Order(1) Territory removedTerritory;
  }

  private List<Territory> territories = new ArrayList<Territory>();
  private transient SimpleEventBus eventBus = new SimpleEventBus();

  public static TerritoryList standardWithMainland() {
    return new TerritoryList()
            .add(new TerritoryImpl().setMainland(true));
  }
  public TerritoryList add(Territory territory) {
    if (territory.isMainland() && containsMainland())
      throw new RuntimeException("Can't have more then one mainland in a list of Territories");
    territories.add(territory);
    eventBus.fireEvent(new TerritoryListChangedEvent(territory, null));
    return this;
  }
  public Territory findByID(int id) {
    for (Territory t : this) {
      if (t.id() == id)
        return t;
    }
    throw new RuntimeException();
  }
  public TerritoryList addNew(Territory t) {
    this.add(t);
    return this;
  }
  private boolean containsMainland() {
    for (Territory territory : this)
      if (territory.isMainland())
        return true;
    return false;
  }
  public Territory createTerritory(boolean mainland) {
    Territory result = new TerritoryImpl();
    if (mainland) {
      result.setID(0);
    } else {
      int id = this.size();
      if (!containsMainland()) {
        id--;
      }
      result.setID(id);
    }
    return result;
  }
  @Override public Iterator<Territory> iterator() {
    return territories.iterator();
  }
  public int size() {
    return territories.size();
  }
  public Territory get(int index) {
    return territories.get(index);
  }
  @Override public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEvent(event);
  }
  @Override public HandlerRegistration addTerritoryListChangedHandler(
          TerritoryListChangedHandler handler) {
    return eventBus.addHandler(TerritoryListChangedEvent.getType(), handler);
  }
}
