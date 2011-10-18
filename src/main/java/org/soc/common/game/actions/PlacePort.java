package org.soc.common.game.actions;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;

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
  @Override public ActionPresenter createPresenter(
          ActionWidgetFactory actionWidgetFactory)
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Name name()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Description description()
  {
    // TODO Auto-generated method stub
    return null;
  }
  @Override public Icon icon() {
    // TODO Auto-generated method stub
    return null;
  }
}
