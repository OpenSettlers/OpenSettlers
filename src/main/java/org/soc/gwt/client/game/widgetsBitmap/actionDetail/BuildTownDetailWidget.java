package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.actions.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;

public class BuildTownDetailWidget extends AbstractActionDetailWidget
{
  private BuildTown buildTown;

  public BuildTownDetailWidget(GameWidget gameWidget, BuildTown buildTown)
  {
    super(gameWidget, buildTown.player());
    this.buildTown = buildTown;
    rootPanel.add(new Image(R.icons().build32()));
    rootPanel.add(new Image(Models.mediumIcon(buildTown)));
  }
  @Override public GameAction getGameAction()
  {
    return buildTown;
  }
}