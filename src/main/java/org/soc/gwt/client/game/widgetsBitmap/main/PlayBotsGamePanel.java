package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.views.widgetsInterface.actions.ActionsWidget;
import org.soc.common.views.widgetsInterface.generic.ToolTipManager;
import org.soc.gwt.client.game.widgetsAbstract.main.AbstractGameWidget;

public class PlayBotsGamePanel extends AbstractGameWidget
{
  @Override protected void initialize()
  {
    super.initialize();
    GameWidgetFactory gameWidgetFactory = gameWidgetLayoutWidget
            .getGameWidgetFactory();
  }
  @Override public GameWidgetFactory widgetFactory()
  {
    return new GameBitmapWidgetFactory(this);
  }
  @Override public ActionsWidget actionsWidget()
  {
    return null;
  }
  @Override public ToolTipManager toolTipManager()
  {
    // TODO Auto-generated method stub
    return null;
  }
}
