package soc.gwtClient.game.widgets.standard.bitmap;

import soc.common.game.dices.CitiesKnightsDice;
import soc.common.game.dices.Dice;
import soc.common.game.dices.StandardDice;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.abstractWidgets.DiceWidget;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.abstractWidgets.factories.DiceWidgetFactory;
import soc.gwtClient.game.widgets.standard.svg.SvgStandardDiceWidget;

public class DiceWidgetBitmapFactory implements DiceWidgetFactory
{
    private GamePlayer player;

    public DiceWidgetBitmapFactory(GamePlayer player)
    {
        super();
        this.player = player;
    }

    @Override
    public DiceWidget createDiceWidget(Dice diceType, GamePanel gamePanel)
    {
        if (diceType instanceof StandardDice)
        {
            return new SvgStandardDiceWidget(gamePanel, player);
        }
        if (diceType instanceof CitiesKnightsDice)
        {
        }

        throw new RuntimeException(
                "We should either have a StandardDice or CitiesKnightsDice, or a newly implemented dicetype");
    }
}
