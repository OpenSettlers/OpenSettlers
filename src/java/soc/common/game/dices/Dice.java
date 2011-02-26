package soc.common.game.dices;

import java.io.Serializable;

import soc.common.server.randomization.Random;
import soc.common.views.widgetsInterface.actions.DiceWidget;
import soc.common.views.widgetsInterface.actions.DiceWidgetFactory;

public interface Dice extends Serializable
{
    public void roll(Random random);

    public DiceWidget createDiceWidget(DiceWidgetFactory factory);
}
