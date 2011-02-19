package soc.gwtClient.game.widgetsInterface.actions;

import soc.common.game.dices.Dice;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

/*
 * Creates an instance of IDiceWidget based upon a dice type
 */
public interface DiceWidgetFactory
{
    public DiceWidget createDiceWidget(Dice diceType, GameWidget gamePanel);
}
