package org.soc.common.views.widgetsInterface.dialogs;

import org.soc.common.actions.gameAction.standard.RollDice;
import org.soc.common.views.behaviour.gameWidget.received.RollDiceResult;

import com.google.gwt.user.client.ui.IsWidget;

public interface LooseCardsDialog extends IsWidget
{
    public void doneLoosingCards(LooseCardsWidget child);

    public void update(RollDice rollDice, RollDiceResult rollDiceResult);
}
