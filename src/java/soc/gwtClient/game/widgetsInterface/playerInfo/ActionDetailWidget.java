package soc.gwtClient.game.widgetsInterface.playerInfo;

import soc.common.actions.gameAction.GameAction;

import com.google.gwt.user.client.ui.IsWidget;

public interface ActionDetailWidget extends IsWidget
{
    public GameAction getGameAction();
}
