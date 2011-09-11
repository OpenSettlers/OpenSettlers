package org.soc.common.views.widgetsInterface.dialogs;

import org.soc.common.game.actions.RollDice;
import org.soc.common.game.actions.RollDice.RollDiceInGame;

import com.google.gwt.user.client.ui.IsWidget;

public interface LooseCardsDialog extends IsWidget
{
  public void doneLoosingCards(LooseCardsWidget child);
  public void update(RollDice rollDice, RollDiceInGame rollDiceResult);
}
