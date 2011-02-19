package soc.gwtClient.game.widgetsInterface.dialogs;

import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.gwtClient.game.behaviour.gameBoard.received.RollDiceResult;

import com.google.gwt.user.client.ui.IsWidget;

public interface LooseCardsDialog extends IsWidget
{
    public void doneLoosingCards(LooseCardsWidget child);

    public void update(RollDice rollDice, RollDiceResult rollDiceResult);
}
