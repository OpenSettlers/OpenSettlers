package soc.gwtClient.game.widgetsInterface.dialogs;

import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.behaviour.gamePanel.RobPlayerGameBehaviour;

import com.google.gwt.user.client.ui.IsWidget;

public interface StealCardWidget extends IsWidget
{
    public void update(RobPlayerGameBehaviour robPlayerGameBehaviour);

    public void cardPicked(GamePlayer opponent);
}
