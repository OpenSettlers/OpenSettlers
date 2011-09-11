package org.soc.common.board.pieces;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class OwnerChangedEvent extends GwtEvent<OwnerChangedEvent.OwnerChangedHandler> { 

  public interface HasOwnerChangedHandlers extends HasHandlers {
    HandlerRegistration addOwnerChangedHandler(OwnerChangedHandler handler);
  }

  public interface OwnerChangedHandler extends EventHandler {
    public void onOwnerChanged(OwnerChangedEvent event);
  }

  private static final Type<OwnerChangedHandler> TYPE = new Type<OwnerChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.GamePlayer oldOwner, org.soc.common.game.GamePlayer newOwner) {
    source.fireEvent(new OwnerChangedEvent(oldOwner, newOwner));
  }

  public static Type<OwnerChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.GamePlayer oldOwner;
  org.soc.common.game.GamePlayer newOwner;

  public OwnerChangedEvent(org.soc.common.game.GamePlayer oldOwner, org.soc.common.game.GamePlayer newOwner) {
    this.oldOwner = oldOwner;
    this.newOwner = newOwner;
  }

  protected OwnerChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<OwnerChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.GamePlayer getOldOwner() {
    return oldOwner;
  }

  public org.soc.common.game.GamePlayer getNewOwner() {
    return newOwner;
  }

  @Override
  protected void dispatch(OwnerChangedHandler handler) {
    handler.onOwnerChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    OwnerChangedEvent other = (OwnerChangedEvent) obj;
    if (oldOwner == null) {
      if (other.oldOwner != null)
        return false;
    } else if (!oldOwner.equals(other.oldOwner))
      return false;
    if (newOwner == null) {
      if (other.newOwner != null)
        return false;
    } else if (!newOwner.equals(other.newOwner))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (oldOwner == null ? 1 : oldOwner.hashCode());
    hashCode = (hashCode * 37) + (newOwner == null ? 1 : newOwner.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "OwnerChangedEvent["
                 + oldOwner
                 + ","
                 + newOwner
    + "]";
  }
}
