package soc.gwtClient.client.game;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Player;

/*
 * Interface for a widget that shows UI elements representing actions
 * in a turn, such as building a town or ending turn
 */
public interface IActionWidget extends IsWidget
{
    public TurnAction getNewAction();
    public Player getPlayer();
    public void updateState();
}
