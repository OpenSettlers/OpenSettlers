package org.soc.common.views.widgetsInterface.actions;

import org.soc.common.game.dices.DiceChangedEventHandler;
import org.soc.common.views.widgetsInterface.generic.Point2D;

public interface DiceWidget extends ActionWidget, DiceChangedEventHandler
{
    public Point2D getPosition();
}
