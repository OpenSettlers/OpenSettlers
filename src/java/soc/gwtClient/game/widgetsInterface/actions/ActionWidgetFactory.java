package soc.gwtClient.game.widgetsInterface.actions;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

/*
 * Widget interface to create UI based on an action which is allowed
 * in a game.
 */
public interface ActionWidgetFactory
{
    public ActionWidget createActionWidget(TurnAction action, GamePlayer player, GameWidget gamePanel);
}
