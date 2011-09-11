package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class TerritoryListChangedEvent extends GwtEvent<TerritoryListChangedEvent.TerritoryListChangedHandler> { 

  public interface HasTerritoryListChangedHandlers extends HasHandlers {
    HandlerRegistration addTerritoryListChangedHandler(TerritoryListChangedHandler handler);
  }

  public interface TerritoryListChangedHandler extends EventHandler {
    public void onTerritoryListChanged(TerritoryListChangedEvent event);
  }

  private static final Type<TerritoryListChangedHandler> TYPE = new Type<TerritoryListChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Territory addedTerritory, org.soc.common.game.Territory removedTerritory) {
    source.fireEvent(new TerritoryListChangedEvent(addedTerritory, removedTerritory));
  }

  public static Type<TerritoryListChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Territory addedTerritory;
  org.soc.common.game.Territory removedTerritory;

  public TerritoryListChangedEvent(org.soc.common.game.Territory addedTerritory, org.soc.common.game.Territory removedTerritory) {
    this.addedTerritory = addedTerritory;
    this.removedTerritory = removedTerritory;
  }

  protected TerritoryListChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<TerritoryListChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Territory getAddedTerritory() {
    return addedTerritory;
  }

  public org.soc.common.game.Territory getRemovedTerritory() {
    return removedTerritory;
  }

  @Override
  protected void dispatch(TerritoryListChangedHandler handler) {
    handler.onTerritoryListChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    TerritoryListChangedEvent other = (TerritoryListChangedEvent) obj;
    if (addedTerritory == null) {
      if (other.addedTerritory != null)
        return false;
    } else if (!addedTerritory.equals(other.addedTerritory))
      return false;
    if (removedTerritory == null) {
      if (other.removedTerritory != null)
        return false;
    } else if (!removedTerritory.equals(other.removedTerritory))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (addedTerritory == null ? 1 : addedTerritory.hashCode());
    hashCode = (hashCode * 37) + (removedTerritory == null ? 1 : removedTerritory.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "TerritoryListChangedEvent["
                 + addedTerritory
                 + ","
                 + removedTerritory
    + "]";
  }
}
