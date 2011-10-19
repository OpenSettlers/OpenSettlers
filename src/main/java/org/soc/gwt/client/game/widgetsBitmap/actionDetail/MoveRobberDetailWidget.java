package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.actions.*;
import org.soc.common.game.hexes.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.*;

import com.google.gwt.user.client.ui.*;

public class MoveRobberDetailWidget extends AbstractActionDetailWidget
{
  private PlaceRobber placeRobber;

  public MoveRobberDetailWidget(GameWidget gameWidget, PlaceRobber placeRobber)
  {
    super(gameWidget, placeRobber.player());
    this.placeRobber = placeRobber;
    rootPanel.add(new Image(Models.mediumIcon(placeRobber)));
    Hex affectedHex = gameWidget.game().board().hexes()
            .get(placeRobber.getNewLocation());
    rootPanel.add(new Image(Models.mediumIcon(affectedHex)));
  }
  @Override public GameAction getGameAction()
  {
    return placeRobber;
  }
}
