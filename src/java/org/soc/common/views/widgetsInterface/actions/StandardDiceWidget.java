package org.soc.common.views.widgetsInterface.actions;

import org.soc.common.game.dices.StandardDice;

public interface StandardDiceWidget extends DiceWidget
{
    public void setStandardDice(StandardDice standardDice);

    public void clicked();
}
