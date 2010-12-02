package soc.gwtClient.client.game;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Player;

/*
 * Widget interface to create UI based on an action which is allowed
 * in a game.
 */
public interface IActionWidgetFactory
{
    public IActionWidget createActionPanel(TurnAction action, Player player, IGamePanel gamePanel);
}
