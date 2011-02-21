package soc.gwtClient.game.widgetsInterface.playerDetail;

import soc.common.actions.gameAction.GameAction;

import com.google.gwt.user.client.ui.IsWidget;

public interface ActionDetailWidget extends IsWidget
{
    public GameAction getGameAction();
}
