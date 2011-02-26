package soc.common.views.widgetsInterface.actions;

import soc.common.game.dices.StandardDice;

public interface StandardDiceWidget extends DiceWidget
{
    public void setStandardDice(StandardDice standardDice);

    public void clicked();
}
