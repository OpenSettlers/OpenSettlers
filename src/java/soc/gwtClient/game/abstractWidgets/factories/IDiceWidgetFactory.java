package soc.gwtClient.game.abstractWidgets.factories;

import soc.common.game.dices.IDice;
import soc.gwtClient.game.abstractWidgets.IDiceWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

/*
 * Creates an instanc of IDiceWidget based upon a dicetype
 */
public interface IDiceWidgetFactory
{
    public IDiceWidget createDiceWidget(IDice diceType, IGamePanel gamePanel);
}
