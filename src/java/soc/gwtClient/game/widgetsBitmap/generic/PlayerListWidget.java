package soc.gwtClient.game.widgetsBitmap.generic;

import soc.common.game.player.GamePlayer;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;

public interface PlayerListWidget extends IsWidget
{
    public Iterable<GamePlayer> getPlayers();

    public void addPlayer(GamePlayer player);

    public void removePlayer(GamePlayer player);

    public HandlerRegistration addPlayersChangedEventHandler(
            PlayersChangedEventHandler handler);

    public int amountPlayers();
}
