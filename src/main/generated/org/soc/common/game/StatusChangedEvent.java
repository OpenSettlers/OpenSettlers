package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class StatusChangedEvent extends GwtEvent<StatusChangedEvent.StatusChangedHandler> { 

  public interface HasStatusChangedHandlers extends HasHandlers {
    HandlerRegistration addStatusChangedHandler(StatusChangedHandler handler);
  }

  public interface StatusChangedHandler extends EventHandler {
    public void onStatusChanged(StatusChangedEvent event);
  }

  private static final Type<StatusChangedHandler> TYPE = new Type<StatusChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.GameStatus newStatus, org.soc.common.game.GameStatus oldStatus) {
    source.fireEvent(new StatusChangedEvent(newStatus, oldStatus));
  }

  public static Type<StatusChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.GameStatus newStatus;
  org.soc.common.game.GameStatus oldStatus;

  public StatusChangedEvent(org.soc.common.game.GameStatus newStatus, org.soc.common.game.GameStatus oldStatus) {
    this.newStatus = newStatus;
    this.oldStatus = oldStatus;
  }

  protected StatusChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<StatusChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.GameStatus getNewStatus() {
    return newStatus;
  }

  public org.soc.common.game.GameStatus getOldStatus() {
    return oldStatus;
  }

  @Override
  protected void dispatch(StatusChangedHandler handler) {
    handler.onStatusChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    StatusChangedEvent other = (StatusChangedEvent) obj;
    if (newStatus == null) {
      if (other.newStatus != null)
        return false;
    } else if (!newStatus.equals(other.newStatus))
      return false;
    if (oldStatus == null) {
      if (other.oldStatus != null)
        return false;
    } else if (!oldStatus.equals(other.oldStatus))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (newStatus == null ? 1 : newStatus.hashCode());
    hashCode = (hashCode * 37) + (oldStatus == null ? 1 : oldStatus.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "StatusChangedEvent["
                 + newStatus
                 + ","
                 + oldStatus
    + "]";
  }
}
