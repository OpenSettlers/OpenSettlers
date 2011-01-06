package soc.gwtClient.game.abstractWidgets.factories;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.ActionWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;

/*
 * Widget interface to create UI based on an action which is allowed
 * in a game.
 */
public interface ActionWidgetFactory
{
    public ActionWidget createActionWidget(TurnAction action, GamePlayer player, GamePanel gamePanel);
}
