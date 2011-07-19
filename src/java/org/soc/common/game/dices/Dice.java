package org.soc.common.game.dices;

import java.io.Serializable;

import org.soc.common.server.randomization.Random;
import org.soc.common.views.widgetsInterface.actions.DiceWidget;
import org.soc.common.views.widgetsInterface.actions.DiceWidgetFactory;


public interface Dice extends Serializable
{
    public void roll(Random random);

    public DiceWidget createDiceWidget(DiceWidgetFactory factory);
}
