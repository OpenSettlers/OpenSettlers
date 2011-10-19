package org.soc.gwt.client.game.widgetsBitmap.actionDetail;

import org.soc.common.core.GenericList.Models;
import org.soc.common.game.actions.*;
import org.soc.common.views.widgetsInterface.main.*;
import org.soc.gwt.client.game.widgetsAbstract.actionDetail.*;
import org.soc.gwt.client.images.*;

import com.google.gwt.user.client.ui.*;

public class BuildCityDetailBitmapWidget extends AbstractActionDetailWidget
{
  private BuildCity buildCity;

  public BuildCityDetailBitmapWidget(GameWidget gameWidget,
          BuildCity buildCity)
  {
    super(gameWidget, buildCity.player());
    this.buildCity = buildCity;
    rootPanel.add(new Image(R.icons().build32()));
    rootPanel.add(new Image(Models.mediumIcon(buildCity)));
  }
  @Override public GameAction getGameAction()
  {
    return buildCity;
  }
}
