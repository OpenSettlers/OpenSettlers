package soc.gwtClient.game.abstractWidgets.factories;

import soc.common.game.dices.Dice;
import soc.gwtClient.game.abstractWidgets.DiceWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;

/*
 * Creates an instance of IDiceWidget based upon a dice type
 */
public interface DiceWidgetFactory
{
    public DiceWidget createDiceWidget(Dice diceType, GamePanel gamePanel);
}
