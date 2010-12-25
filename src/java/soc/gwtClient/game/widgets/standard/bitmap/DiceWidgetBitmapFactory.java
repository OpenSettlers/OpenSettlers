package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.dices.CitiesKnightsDice;
import soc.common.game.dices.Dice;
import soc.common.game.dices.StandardDice;
import soc.gwtClient.game.abstractWidgets.IDiceWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.abstractWidgets.factories.IDiceWidgetFactory;
import soc.gwtClient.game.widgets.standard.svg.SvgStandardDiceWidget;

public class DiceWidgetBitmapFactory implements IDiceWidgetFactory
{
    @Override
    public IDiceWidget createDiceWidget(Dice diceType, IGamePanel gamePanel)
    {
        if (diceType instanceof StandardDice)
        {
            return new SvgStandardDiceWidget(gamePanel);
        }
        if (diceType instanceof CitiesKnightsDice)
        {
            // TODO: C&K return CKDiceWidget
        }
        
        throw new RuntimeException("We should either have a StandardDice or CitiesKnightsDice, or a newly implemented dicetype");
    }
}
