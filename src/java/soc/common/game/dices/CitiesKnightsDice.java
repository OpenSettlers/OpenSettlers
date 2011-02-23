package soc.common.game.dices;

import soc.common.annotations.CitiesKnights;
import soc.common.server.randomization.Random;
import soc.gwtClient.game.widgetsInterface.actions.DiceWidget;
import soc.gwtClient.game.widgetsInterface.actions.DiceWidgetFactory;

@CitiesKnights
public class CitiesKnightsDice implements Dice
{
    private static final long serialVersionUID = 7369630454491902402L;

    @Override
    public void roll(Random random)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public DiceWidget createDiceWidget(DiceWidgetFactory factory)
    {
        return factory.createCitiesKnightDiceWidget();
    }
}
