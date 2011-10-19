package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.*;
import org.soc.common.game.actions.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.*;

import com.google.gwt.user.client.ui.*;

public class RobPlayerDetailWidget extends AbstractActionDetailWidget
{
  private RobPlayer robPlayer;

  public RobPlayerDetailWidget(GameWidget gameWidget, RobPlayer robPlayer)
  {
    super(gameWidget, robPlayer.player());
    this.robPlayer = robPlayer;
    rootPanel.add(new Image(Models.mediumIcon(robPlayer)));
    Resource stolenResource = robPlayer.getStolenResource();
    rootPanel.add(new Image(Models.mediumIcon(stolenResource)));
  }
  @Override public GameAction getGameAction()
  {
    return robPlayer;
  }
}
