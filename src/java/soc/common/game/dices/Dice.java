package soc.common.game.dices;

import java.io.Serializable;

import soc.common.server.randomization.Random;
import soc.gwtClient.game.widgetsInterface.actions.DiceWidget;
import soc.gwtClient.game.widgetsInterface.actions.DiceWidgetFactory;

public interface Dice extends Serializable
{
    public void roll(Random random);

    public DiceWidget createDiceWidget(DiceWidgetFactory factory);
}
