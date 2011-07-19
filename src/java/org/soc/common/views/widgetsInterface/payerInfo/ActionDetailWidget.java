package org.soc.common.views.widgetsInterface.payerInfo;

import org.soc.common.actions.gameAction.GameAction;

import com.google.gwt.user.client.ui.IsWidget;

/*
 * Shows details for an action played
 */
public interface ActionDetailWidget extends IsWidget
{
    public GameAction getGameAction();
}
