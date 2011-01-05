package soc.gwtClient.game.abstractWidgets;

import soc.common.game.dices.StandardDice;

public interface IStandardDiceWidget extends IDiceWidget
{
    public void setStandardDice(StandardDice standardDice);

    public void clicked();
}
