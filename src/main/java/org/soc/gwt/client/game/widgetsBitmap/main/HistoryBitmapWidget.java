package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.ActionPerformedEvent;
import org.soc.common.game.ActionPerformedEvent.ActionPerformedHandler;
import org.soc.common.game.actions.GameAction;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.HistoryWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.StringActionCell;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class HistoryBitmapWidget implements HistoryWidget,
        ActionPerformedHandler
{
  StringActionCell cell = new StringActionCell();
  ScrollPanel rootPanel = new ScrollPanel();
  CellList<GameAction> actionsList = new CellList<GameAction>(cell);
  GameWidget gameWidget;
  ListDataProvider<GameAction> dataProvider = new ListDataProvider<GameAction>();

  public HistoryBitmapWidget(GameWidget gameWidget)
  {
    super();
    this.gameWidget = gameWidget;
    dataProvider.addDataDisplay(actionsList);
    gameWidget.game().log().addActionPerformedHandler(this);
    rootPanel.add(actionsList);
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onActionPerformed(ActionPerformedEvent event)
  {
    dataProvider.getList().add(0, event.getPerformedAction());
  }
}
