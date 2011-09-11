package org.soc.common.game.hexes;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class TerritoryChangedEvent extends GwtEvent<TerritoryChangedEvent.TerritoryChangedHandler> { 

  public interface HasTerritoryChangedHandlers extends HasHandlers {
    HandlerRegistration addTerritoryChangedHandler(TerritoryChangedHandler handler);
  }

  public interface TerritoryChangedHandler extends EventHandler {
    public void onTerritoryChanged(TerritoryChangedEvent event);
  }

  private static final Type<TerritoryChangedHandler> TYPE = new Type<TerritoryChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Territory territory) {
    source.fireEvent(new TerritoryChangedEvent(territory));
  }

  public static Type<TerritoryChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Territory territory;

  public TerritoryChangedEvent(org.soc.common.game.Territory territory) {
    this.territory = territory;
  }

  protected TerritoryChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<TerritoryChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Territory getTerritory() {
    return territory;
  }

  @Override
  protected void dispatch(TerritoryChangedHandler handler) {
    handler.onTerritoryChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    TerritoryChangedEvent other = (TerritoryChangedEvent) obj;
    if (territory == null) {
      if (other.territory != null)
        return false;
    } else if (!territory.equals(other.territory))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (territory == null ? 1 : territory.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "TerritoryChangedEvent["
                 + territory
    + "]";
  }
}
