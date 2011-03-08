package soc.common.views.widgetsInterface.actions;

import soc.common.game.dices.DiceChangedEventHandler;
import soc.common.views.widgetsInterface.generic.Point2D;

public interface DiceWidget extends ActionWidget, DiceChangedEventHandler
{
    public Point2D getPosition();
}
