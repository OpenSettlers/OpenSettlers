package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class TurnChangedEvent extends GwtEvent<TurnChangedEvent.TurnChangedHandler> { 

  public interface HasTurnChangedHandlers extends HasHandlers {
    HandlerRegistration addTurnChangedHandler(TurnChangedHandler handler);
  }

  public interface TurnChangedHandler extends EventHandler {
    public void onTurnChanged(TurnChangedEvent event);
  }

  private static final Type<TurnChangedHandler> TYPE = new Type<TurnChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.Turn oldTurn, org.soc.common.game.Turn newTurn) {
    source.fireEvent(new TurnChangedEvent(oldTurn, newTurn));
  }

  public static Type<TurnChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.Turn oldTurn;
  org.soc.common.game.Turn newTurn;

  public TurnChangedEvent(org.soc.common.game.Turn oldTurn, org.soc.common.game.Turn newTurn) {
    this.oldTurn = oldTurn;
    this.newTurn = newTurn;
  }

  protected TurnChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<TurnChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.Turn getOldTurn() {
    return oldTurn;
  }

  public org.soc.common.game.Turn getNewTurn() {
    return newTurn;
  }

  @Override
  protected void dispatch(TurnChangedHandler handler) {
    handler.onTurnChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    TurnChangedEvent other = (TurnChangedEvent) obj;
    if (oldTurn == null) {
      if (other.oldTurn != null)
        return false;
    } else if (!oldTurn.equals(other.oldTurn))
      return false;
    if (newTurn == null) {
      if (other.newTurn != null)
        return false;
    } else if (!newTurn.equals(other.newTurn))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (oldTurn == null ? 1 : oldTurn.hashCode());
    hashCode = (hashCode * 37) + (newTurn == null ? 1 : newTurn.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "TurnChangedEvent["
                 + oldTurn
                 + ","
                 + newTurn
    + "]";
  }
}
