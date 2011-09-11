package org.soc.common.board.pieces;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class MovedEventEvent extends GwtEvent<MovedEventEvent.MovedEventHandler> { 

  public interface HasMovedEventHandlers extends HasHandlers {
    HandlerRegistration addMovedEventHandler(MovedEventHandler handler);
  }

  public interface MovedEventHandler extends EventHandler {
    public void onMovedEvent(MovedEventEvent event);
  }

  private static final Type<MovedEventHandler> TYPE = new Type<MovedEventHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.board.HexLocation newLocation, org.soc.common.game.board.HexLocation oldLocation) {
    source.fireEvent(new MovedEventEvent(newLocation, oldLocation));
  }

  public static Type<MovedEventHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.board.HexLocation newLocation;
  org.soc.common.game.board.HexLocation oldLocation;

  public MovedEventEvent(org.soc.common.game.board.HexLocation newLocation, org.soc.common.game.board.HexLocation oldLocation) {
    this.newLocation = newLocation;
    this.oldLocation = oldLocation;
  }

  protected MovedEventEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<MovedEventHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.board.HexLocation getNewLocation() {
    return newLocation;
  }

  public org.soc.common.game.board.HexLocation getOldLocation() {
    return oldLocation;
  }

  @Override
  protected void dispatch(MovedEventHandler handler) {
    handler.onMovedEvent(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    MovedEventEvent other = (MovedEventEvent) obj;
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
    return "MovedEventEvent["
                 + newLocation
                 + ","
                 + oldLocation
    + "]";
  }
}
