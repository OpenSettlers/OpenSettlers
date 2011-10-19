package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.actions.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;

public class BuildRoadDetailWidget extends AbstractActionDetailWidget
{
  private BuildRoad buildRoad;

  public BuildRoadDetailWidget(GameWidget gameWidget, BuildRoad buildRoad)
  {
    super(gameWidget, buildRoad.player());
    this.buildRoad = buildRoad;
    rootPanel.add(new Image(R.icons().build32()));
    rootPanel.add(new Image(Models.mediumIcon(buildRoad)));
  }
  @Override public GameAction getGameAction()
  {
    return buildRoad;
  }
}
