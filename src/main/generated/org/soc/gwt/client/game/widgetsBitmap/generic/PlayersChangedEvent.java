package org.soc.gwt.client.game.widgetsBitmap.generic;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import com.google.gwt.event.shared.HasHandlers;

public class PlayersChangedEvent extends GwtEvent<PlayersChangedEvent.PlayersChangedHandler> { 

  public interface HasPlayersChangedHandlers extends HasHandlers {
    HandlerRegistration addPlayersChangedHandler(PlayersChangedHandler handler);
  }

  public interface PlayersChangedHandler extends EventHandler {
    public void onPlayersChanged(PlayersChangedEvent event);
  }

  private static final Type<PlayersChangedHandler> TYPE = new Type<PlayersChangedHandler>();

  public static void fire(HasHandlers source, org.soc.common.game.GamePlayer playerAdded, org.soc.common.game.GamePlayer playerRemoved) {
    source.fireEvent(new PlayersChangedEvent(playerAdded, playerRemoved));
  }

  public static Type<PlayersChangedHandler> getType() {
    return TYPE;
  }

  org.soc.common.game.GamePlayer playerAdded;
  org.soc.common.game.GamePlayer playerRemoved;

  public PlayersChangedEvent(org.soc.common.game.GamePlayer playerAdded, org.soc.common.game.GamePlayer playerRemoved) {
    this.playerAdded = playerAdded;
    this.playerRemoved = playerRemoved;
  }

  protected PlayersChangedEvent() {
    // Possibly for serialization.
  }

  @Override
  public Type<PlayersChangedHandler> getAssociatedType() {
    return TYPE;
  }

  public org.soc.common.game.GamePlayer getPlayerAdded() {
    return playerAdded;
  }

  public org.soc.common.game.GamePlayer getPlayerRemoved() {
    return playerRemoved;
  }

  @Override
  protected void dispatch(PlayersChangedHandler handler) {
    handler.onPlayersChanged(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    PlayersChangedEvent other = (PlayersChangedEvent) obj;
    if (playerAdded == null) {
      if (other.playerAdded != null)
        return false;
    } else if (!playerAdded.equals(other.playerAdded))
      return false;
    if (playerRemoved == null) {
      if (other.playerRemoved != null)
        return false;
    } else if (!playerRemoved.equals(other.playerRemoved))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 23;
    hashCode = (hashCode * 37) + (playerAdded == null ? 1 : playerAdded.hashCode());
    hashCode = (hashCode * 37) + (playerRemoved == null ? 1 : playerRemoved.hashCode());
    return hashCode;
  }

  @Override
  public String toString() {
    return "PlayersChangedEvent["
                 + playerAdded
                 + ","
                 + playerRemoved
    + "]";
  }
}
