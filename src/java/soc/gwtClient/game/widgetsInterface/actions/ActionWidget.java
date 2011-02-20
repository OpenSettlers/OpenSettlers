package soc.gwtClient.game.widgetsInterface.actions;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import soc.common.actions.gameAction.turns.AbstractTurnAction;
import soc.common.game.player.GamePlayer;

/*
 * Interface for a widget that shows UI elements representing actions
 * in a turn, such as building a town or ending turn
 */
public interface ActionWidget extends IsWidget
{
    public GamePlayer getPlayer();
    public ActionWidget setEnabled(boolean enabled);
}
