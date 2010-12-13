package soc.gwtClient.game.abstractWidgets.factories;

import soc.common.game.dices.IDice;
import soc.gwtClient.game.abstractWidgets.IDiceWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

/*
 * Creates an instance of IDiceWidget based upon a dice type
 */
public interface IDiceWidgetFactory
{
    public IDiceWidget createDiceWidget(IDice diceType, IGamePanel gamePanel);
}
