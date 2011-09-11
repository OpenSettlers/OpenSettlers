package org.soc.common.game;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class GamePhaseChangedEvent extends GwtEvent<GamePhaseChangedEvent.GamePhaseChangedHandler> { 

  public interface HasGamePhaseChangedHandlers extends HasHandlers {
    HandlerRegistration addGamePhaseChangedHandler(GamePhaseChangedHandler handler);
  }

  public interface GamePhaseChangedHandler extends EventHandler {
    public void onGamePhaseChanged(GamePhaseChangedEvent event);
  }

  private static final Type<GamePhaseChangedHandler> TYPE = new Type<GamePhaseChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.GamePhase previousPhase, org.soc.common.game.GamePhase newPhase) {
    source.fireEvent(new GamePhaseChangedEvent(previousPhase, newPhase));
  }

  public static Type<GamePhaseChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.GamePhase previousPhase;
  org.soc.common.game.GamePhase newPhase;

  public GamePhaseChangedEvent(org.soc.common.game.GamePhase previousPhase, org.soc.common.game.GamePhase newPhase) {
    this.previousPhase = previousPhase;
    this.newPhase = newPhase;
  }

  protected GamePhaseChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<GamePhaseChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.GamePhase getPreviousPhase() {
    return previousPhase;
  }

  public org.soc.common.game.GamePhase getNewPhase() {
    return newPhase;
  }

  @Override
  protected void dispatch(GamePhaseChangedHandler handler) {
    handler.onGamePhaseChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GamePhaseChangedEvent other = (GamePhaseChangedEvent) obj;
    if (previousPhase == null) {
      if (other.previousPhase != null)
        return false;
    } else if (!previousPhase.equals(other.previousPhase))
      return false;
    if (newPhase == null) {
      if (other.newPhase != null)
        return false;
    } else if (!newPhase.equals(other.newPhase))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (previousPhase == null ? 1 : previousPhase.hashCode());
    hashCode = (hashCode * 37) + (newPhase == null ? 1 : newPhase.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GamePhaseChangedEvent["
                 + previousPhase
                 + ","
                 + newPhase
    + "]";
  }
}
