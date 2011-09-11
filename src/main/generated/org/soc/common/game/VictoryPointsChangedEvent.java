package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class VictoryPointsChangedEvent extends GwtEvent<VictoryPointsChangedEvent.VictoryPointsChangedHandler> { 

  public interface HasVictoryPointsChangedHandlers extends HasHandlers {
    HandlerRegistration addVictoryPointsChangedHandler(VictoryPointsChangedHandler handler);
  }

  public interface VictoryPointsChangedHandler extends EventHandler {
    public void onVictoryPointsChanged(VictoryPointsChangedEvent event);
  }

  private static final Type<VictoryPointsChangedHandler> TYPE = new Type<VictoryPointsChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.VictoryPointItem addedPoint, org.soc.common.game.VictoryPointItem removedPoint) {
    source.fireEvent(new VictoryPointsChangedEvent(addedPoint, removedPoint));
  }

  public static Type<VictoryPointsChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.VictoryPointItem addedPoint;
  org.soc.common.game.VictoryPointItem removedPoint;

  public VictoryPointsChangedEvent(org.soc.common.game.VictoryPointItem addedPoint, org.soc.common.game.VictoryPointItem removedPoint) {
    this.addedPoint = addedPoint;
    this.removedPoint = removedPoint;
  }

  protected VictoryPointsChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<VictoryPointsChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.VictoryPointItem getAddedPoint() {
    return addedPoint;
  }

  public org.soc.common.game.VictoryPointItem getRemovedPoint() {
    return removedPoint;
  }

  @Override
  protected void dispatch(VictoryPointsChangedHandler handler) {
    handler.onVictoryPointsChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    VictoryPointsChangedEvent other = (VictoryPointsChangedEvent) obj;
    if (addedPoint == null) {
      if (other.addedPoint != null)
        return false;
    } else if (!addedPoint.equals(other.addedPoint))
      return false;
    if (removedPoint == null) {
      if (other.removedPoint != null)
        return false;
    } else if (!removedPoint.equals(other.removedPoint))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (addedPoint == null ? 1 : addedPoint.hashCode());
    hashCode = (hashCode * 37) + (removedPoint == null ? 1 : removedPoint.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "VictoryPointsChangedEvent["
                 + addedPoint
                 + ","
                 + removedPoint
    + "]";
  }
}
