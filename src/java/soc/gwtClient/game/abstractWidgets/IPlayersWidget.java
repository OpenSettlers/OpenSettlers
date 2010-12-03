package soc.gwtClient.client.game;

import soc.common.game.Game;
import soc.common.game.Player;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface IPlayersWidget extends IsWidget
{
    public ComplexPanel createRootPanel();
    public IPlayerWidget createPlayerWidget(Game game, Player player);
}
