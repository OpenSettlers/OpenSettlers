package org.soc.common.game.dices;

import org.soc.common.annotations.CitiesKnights;
import org.soc.common.server.randomization.Random;
import org.soc.common.views.widgetsInterface.actions.DiceWidget;
import org.soc.common.views.widgetsInterface.actions.DiceWidgetFactory;

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
