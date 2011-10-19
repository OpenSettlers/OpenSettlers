package org.soc.common.game.actions;

import org.soc.common.game.*;
import org.soc.common.game.actions.Action.ActionPresenter.ActionWidgetFactory;
import org.soc.common.game.actions.GameAction.AbstractGameAction;
import org.soc.common.internationalization.*;

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
  public PlacePort setPort(Port port)
  {
    this.port = port;
    return this;
  }
  public int getTerritoryID()
  {
    return territoryID;
  }
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
}
