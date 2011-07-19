package org.soc.common.views.widgetsInterface.actions;

import org.soc.common.actions.gameAction.turns.AbstractTurnAction;
import org.soc.common.game.player.GamePlayer;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;


/*
 * Interface for a widget that shows UI elements representing actions
 * in a turn, such as building a town or ending turn
 */
public interface ActionWidget extends IsWidget
{
    public GamePlayer getPlayer();
    public ActionWidget setEnabled(boolean enabled);
}
