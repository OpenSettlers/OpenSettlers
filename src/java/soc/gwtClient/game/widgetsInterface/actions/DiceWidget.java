package soc.gwtClient.game.widgetsInterface.actions;

import soc.common.game.DiceChangedEventHandler;
import soc.gwtClient.game.Point2D;

public interface DiceWidget extends ActionWidget, DiceChangedEventHandler
{
    public Point2D getPosition();
}
