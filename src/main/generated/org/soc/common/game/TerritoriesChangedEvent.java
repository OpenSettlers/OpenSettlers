package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class TerritoriesChangedEvent extends GwtEvent<TerritoriesChangedEvent.TerritoriesChangedHandler> { 

  public interface HasTerritoriesChangedHandlers extends HasHandlers {
    HandlerRegistration addTerritoriesChangedHandler(TerritoriesChangedHandler handler);
  }

  public interface TerritoriesChangedHandler extends EventHandler {
    public void onTerritoriesChanged(TerritoriesChangedEvent event);
  }

  private static final Type<TerritoriesChangedHandler> TYPE = new Type<TerritoriesChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Territory addedTerritory, org.soc.common.game.Territory removedTerritory) {
    source.fireEvent(new TerritoriesChangedEvent(addedTerritory, removedTerritory));
  }

  public static Type<TerritoriesChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Territory addedTerritory;
  org.soc.common.game.Territory removedTerritory;

  public TerritoriesChangedEvent(org.soc.common.game.Territory addedTerritory, org.soc.common.game.Territory removedTerritory) {
    this.addedTerritory = addedTerritory;
    this.removedTerritory = removedTerritory;
  }

  protected TerritoriesChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<TerritoriesChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Territory getAddedTerritory() {
    return addedTerritory;
  }

  public org.soc.common.game.Territory getRemovedTerritory() {
    return removedTerritory;
  }

  @Override
  protected void dispatch(TerritoriesChangedHandler handler) {
    handler.onTerritoriesChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    TerritoriesChangedEvent other = (TerritoriesChangedEvent) obj;
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
    return "TerritoriesChangedEvent["
                 + addedTerritory
                 + ","
                 + removedTerritory
    + "]";
  }
}
