package org.soc.common.game.actions;

import org.soc.common.game.GamePhase;
import org.soc.common.game.Port;
import org.soc.common.game.TurnPhase;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.widgetsInterface.actions.ActionWidget;
import org.soc.common.views.widgetsInterface.actions.ActionWidget.ActionWidgetFactory;

public class PlacePort extends AbstractGameAction
{
  private static final long serialVersionUID = -9211239639325281661L;
  private int territoryID;
  private Port port;

  /** @return the port */
  public Port getPort()
  {
    return port;
  }
  /** @param port the port to set */
  public PlacePort setPort(Port port)
  {
    this.port = port;
    return this;
  }
  /** @return the territoryID */
  public int getTerritoryID()
  {
    return territoryID;
  }
  /** @param territoryID the territoryID to set */
  public PlacePort setTerritoryID(int territoryID)
  {
    this.territoryID = territoryID;
    return this;
  }
  @Override public boolean isAllowed(TurnPhase turnPhase)
  {
    return false;
  }
  @Override public boolean isAllowed(GamePhase gamePhase)
  {
    return gamePhase.isPlacePorts();
  }
  @Override public String toDoMessage()
  {
    return I.get().actions().placePortToDo(player.user().name());
  }
  @Override public ActionWidget createActionWidget(
          ActionWidgetFactory actionWidgetFactory)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getLocalizedName() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Icon icon() {
    // TODO Auto-generated method stub
    return null;
  }
}
