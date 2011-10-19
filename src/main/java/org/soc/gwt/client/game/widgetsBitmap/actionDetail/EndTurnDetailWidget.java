package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.actions.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.*;

import com.google.gwt.user.client.ui.*;

public class EndTurnDetailWidget extends AbstractActionDetailWidget
{
  private EndTurn endTurn;

  public EndTurnDetailWidget(GameWidget gameWidget, EndTurn endTurn)
  {
    super(gameWidget, endTurn.player());
    this.endTurn = endTurn;
    rootPanel.add(new Image(Models.mediumIcon(endTurn)));
  }
  @Override public GameAction getGameAction()
  {
    return endTurn;
  }
}
