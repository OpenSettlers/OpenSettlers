package org.soc.common.game.pieces;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class MovedEvent extends GwtEvent<MovedEvent.MovedHandler> { 

  public interface HasMovedHandlers extends HasHandlers {
    HandlerRegistration addMovedHandler(MovedHandler handler);
  }

  public interface MovedHandler extends EventHandler {
    public void onMoved(MovedEvent event);
  }

  private static final Type<MovedHandler> TYPE = new Type<MovedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.board.HexLocation newLocation, org.soc.common.game.board.HexLocation oldLocation) {
    source.fireEvent(new MovedEvent(newLocation, oldLocation));
  }

  public static Type<MovedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.board.HexLocation newLocation;
  org.soc.common.game.board.HexLocation oldLocation;

  public MovedEvent(org.soc.common.game.board.HexLocation newLocation, org.soc.common.game.board.HexLocation oldLocation) {
    this.newLocation = newLocation;
    this.oldLocation = oldLocation;
  }

  protected MovedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<MovedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.board.HexLocation getNewLocation() {
    return newLocation;
  }

  public org.soc.common.game.board.HexLocation getOldLocation() {
    return oldLocation;
  }

  @Override
  protected void dispatch(MovedHandler handler) {
    handler.onMoved(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    MovedEvent other = (MovedEvent) obj;
    if (newLocation == null) {
      if (other.newLocation != null)
        return false;
    } else if (!newLocation.equals(other.newLocation))
      return false;
    if (oldLocation == null) {
      if (other.oldLocation != null)
        return false;
    } else if (!oldLocation.equals(other.oldLocation))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (newLocation == null ? 1 : newLocation.hashCode());
    hashCode = (hashCode * 37) + (oldLocation == null ? 1 : oldLocation.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "MovedEvent["
                 + newLocation
                 + ","
                 + oldLocation
    + "]";
  }
}
