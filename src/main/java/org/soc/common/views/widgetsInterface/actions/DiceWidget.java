package org.soc.common.views.widgetsInterface.actions;

import org.soc.common.game.Dice.StandardDice;
import org.soc.common.game.DiceChangedEvent.DiceChangedHandler;
import org.soc.common.views.widgetsInterface.generic.Point2D;

import com.google.gwt.user.client.ui.IsWidget;

public interface DiceWidget extends ActionWidget, DiceChangedHandler
{
  public Point2D getPosition();

  public interface SingleDiceWidget extends IsWidget
  {
  }

  public interface StandardDiceWidget extends DiceWidget
  {
    public void setStandardDice(StandardDice standardDice);
    public void clicked();
  }

  /* Creates an instance of IDiceWidget based upon a dice type */
  public interface DiceWidgetFactory
  {
    public DiceWidget createStandardDiceWidget();
    public DiceWidget createCitiesKnightDiceWidget();
    public DiceWidget createCardsDeckDiceWidget();
    public DiceWidget createVolcanoDiceWidget();
  }
}
