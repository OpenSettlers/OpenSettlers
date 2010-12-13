package soc.gwtClient.game.abstractWidgets;

import soc.common.game.dices.StandardDice;

import com.google.gwt.user.client.ui.IsWidget;

public interface IStandardDiceWidget extends IDiceWidget
{
    public void setStandardDice(StandardDice standardDice);
}
