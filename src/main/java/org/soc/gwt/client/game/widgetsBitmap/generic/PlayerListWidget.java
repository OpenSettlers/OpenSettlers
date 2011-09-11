package org.soc.gwt.client.game.widgetsBitmap.generic;

import org.soc.common.game.GamePlayer;
import org.soc.gwt.client.game.widgetsBitmap.generic.PlayersChangedEvent.HasPlayersChangedHandlers;

import com.google.gwt.user.client.ui.IsWidget;
import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

public interface PlayerListWidget extends IsWidget, HasPlayersChangedHandlers
{
  public Iterable<GamePlayer> getPlayers();
  public void addPlayer(GamePlayer player);
  public void removePlayer(GamePlayer player);
  public int amountPlayers();

  @GenEvent public class PlayersChanged {
    @Order(0) GamePlayer playerAdded;
    @Order(1) GamePlayer playerRemoved;
  }
}
