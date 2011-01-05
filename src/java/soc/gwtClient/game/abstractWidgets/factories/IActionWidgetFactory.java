package soc.gwtClient.game.abstractWidgets.factories;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.IActionWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

/*
 * Widget interface to create UI based on an action which is allowed
 * in a game.
 */
public interface IActionWidgetFactory
{
    public IActionWidget createActionWidget(TurnAction action, GamePlayer player, IGamePanel gamePanel);
}
