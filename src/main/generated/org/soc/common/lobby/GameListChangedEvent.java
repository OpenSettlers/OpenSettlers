package org.soc.common.lobby;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class GameListChangedEvent extends GwtEvent<GameListChangedEvent.GameListChangedHandler> { 

  public interface HasGameListChangedHandlers extends HasHandlers {
    HandlerRegistration addGameListChangedHandler(GameListChangedHandler handler);
  }

  public interface GameListChangedHandler extends EventHandler {
    public void onGameListChanged(GameListChangedEvent event);
  }

  private static final Type<GameListChangedHandler> TYPE = new Type<GameListChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.server.GameDto addedGame, org.soc.common.server.GameDto removedGame) {
    source.fireEvent(new GameListChangedEvent(addedGame, removedGame));
  }

  public static Type<GameListChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.server.GameDto addedGame;
  org.soc.common.server.GameDto removedGame;

  public GameListChangedEvent(org.soc.common.server.GameDto addedGame, org.soc.common.server.GameDto removedGame) {
    this.addedGame = addedGame;
    this.removedGame = removedGame;
  }

  protected GameListChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<GameListChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.server.GameDto getAddedGame() {
    return addedGame;
  }

  public org.soc.common.server.GameDto getRemovedGame() {
    return removedGame;
  }

  @Override
  protected void dispatch(GameListChangedHandler handler) {
    handler.onGameListChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    GameListChangedEvent other = (GameListChangedEvent) obj;
    if (addedGame == null) {
      if (other.addedGame != null)
        return false;
    } else if (!addedGame.equals(other.addedGame))
      return false;
    if (removedGame == null) {
      if (other.removedGame != null)
        return false;
    } else if (!removedGame.equals(other.removedGame))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (addedGame == null ? 1 : addedGame.hashCode());
    hashCode = (hashCode * 37) + (removedGame == null ? 1 : removedGame.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "GameListChangedEvent["
                 + addedGame
                 + ","
                 + removedGame
    + "]";
  }
}
