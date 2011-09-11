package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class LongestRoadChangedEvent extends GwtEvent<LongestRoadChangedEvent.LongestRoadChangedHandler> { 

  public interface HasLongestRoadChangedHandlers extends HasHandlers {
    HandlerRegistration addLongestRoadChangedHandler(LongestRoadChangedHandler handler);
  }

  public interface LongestRoadChangedHandler extends EventHandler {
    public void onLongestRoadChanged(LongestRoadChangedEvent event);
  }

  private static final Type<LongestRoadChangedHandler> TYPE = new Type<LongestRoadChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.board.Route oldRoute, org.soc.common.game.board.Route newRoute) {
    source.fireEvent(new LongestRoadChangedEvent(oldRoute, newRoute));
  }

  public static Type<LongestRoadChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.board.Route oldRoute;
  org.soc.common.game.board.Route newRoute;

  public LongestRoadChangedEvent(org.soc.common.game.board.Route oldRoute, org.soc.common.game.board.Route newRoute) {
    this.oldRoute = oldRoute;
    this.newRoute = newRoute;
  }

  protected LongestRoadChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<LongestRoadChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.board.Route getOldRoute() {
    return oldRoute;
  }

  public org.soc.common.game.board.Route getNewRoute() {
    return newRoute;
  }

  @Override
  protected void dispatch(LongestRoadChangedHandler handler) {
    handler.onLongestRoadChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    LongestRoadChangedEvent other = (LongestRoadChangedEvent) obj;
    if (oldRoute == null) {
      if (other.oldRoute != null)
        return false;
    } else if (!oldRoute.equals(other.oldRoute))
      return false;
    if (newRoute == null) {
      if (other.newRoute != null)
        return false;
    } else if (!newRoute.equals(other.newRoute))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (oldRoute == null ? 1 : oldRoute.hashCode());
    hashCode = (hashCode * 37) + (newRoute == null ? 1 : newRoute.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "LongestRoadChangedEvent["
                 + oldRoute
                 + ","
                 + newRoute
    + "]";
  }
}
