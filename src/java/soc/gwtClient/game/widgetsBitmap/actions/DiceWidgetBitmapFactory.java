package soc.gwtClient.game.widgetsBitmap.actions;

import soc.common.game.dices.CitiesKnightsDice;
import soc.common.game.dices.Dice;
import soc.common.game.dices.StandardDice;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsInterface.actions.DiceWidget;
import soc.gwtClient.game.widgetsInterface.actions.DiceWidgetFactory;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsSvg.actions.SvgStandardDiceWidget;

public class DiceWidgetBitmapFactory implements DiceWidgetFactory
{
    private GamePlayer player;

    public DiceWidgetBitmapFactory(GamePlayer player)
    {
        super();
        this.player = player;
    }

    @Override
    public DiceWidget createDiceWidget(Dice diceType, GameWidget gameWidget)
    {
        if (diceType instanceof StandardDice)
        {
            return new SvgStandardDiceWidget(gameWidget, player);
        }
        if (diceType instanceof CitiesKnightsDice)
        {
        }

        throw new RuntimeException(
                "We should either have a StandardDice or CitiesKnightsDice, or a newly implemented dicetype");
    }
}
