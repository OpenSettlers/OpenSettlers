package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class TurnPhaseChangedEvent extends GwtEvent<TurnPhaseChangedEvent.TurnPhaseChangedHandler> { 

  public interface HasTurnPhaseChangedHandlers extends HasHandlers {
    HandlerRegistration addTurnPhaseChangedHandler(TurnPhaseChangedHandler handler);
  }

  public interface TurnPhaseChangedHandler extends EventHandler {
    public void onTurnPhaseChanged(TurnPhaseChangedEvent event);
  }

  private static final Type<TurnPhaseChangedHandler> TYPE = new Type<TurnPhaseChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.TurnPhase newPhase, org.soc.common.game.TurnPhase oldPhase) {
    source.fireEvent(new TurnPhaseChangedEvent(newPhase, oldPhase));
  }

  public static Type<TurnPhaseChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.TurnPhase newPhase;
  org.soc.common.game.TurnPhase oldPhase;

  public TurnPhaseChangedEvent(org.soc.common.game.TurnPhase newPhase, org.soc.common.game.TurnPhase oldPhase) {
    this.newPhase = newPhase;
    this.oldPhase = oldPhase;
  }

  protected TurnPhaseChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<TurnPhaseChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.TurnPhase getNewPhase() {
    return newPhase;
  }

  public org.soc.common.game.TurnPhase getOldPhase() {
    return oldPhase;
  }

  @Override
  protected void dispatch(TurnPhaseChangedHandler handler) {
    handler.onTurnPhaseChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    TurnPhaseChangedEvent other = (TurnPhaseChangedEvent) obj;
    if (newPhase == null) {
      if (other.newPhase != null)
        return false;
    } else if (!newPhase.equals(other.newPhase))
      return false;
    if (oldPhase == null) {
      if (other.oldPhase != null)
        return false;
    } else if (!oldPhase.equals(other.oldPhase))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (newPhase == null ? 1 : newPhase.hashCode());
    hashCode = (hashCode * 37) + (oldPhase == null ? 1 : oldPhase.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "TurnPhaseChangedEvent["
                 + newPhase
                 + ","
                 + oldPhase
    + "]";
  }
}
