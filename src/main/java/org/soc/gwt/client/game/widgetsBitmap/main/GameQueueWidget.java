package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.ActionQueueChangedEvent;
import org.soc.common.game.ActionQueueChangedEvent.ActionQueueChangedHandler;
import org.soc.common.game.actions.GameAction;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.QueueWidget;
import org.soc.gwt.client.game.widgetsBitmap.generic.StringQueuedActionCell;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class GameQueueWidget implements QueueWidget,
        ActionQueueChangedHandler
{
  private ScrollPanel rootPanel = new ScrollPanel();
  private GameWidget gameWidget;
  private StringQueuedActionCell cell = new StringQueuedActionCell();
  private CellList<GameAction> queuedList = new CellList<GameAction>(cell);
  private ListDataProvider<GameAction> dataList = new ListDataProvider<GameAction>();

  public GameQueueWidget(GameWidget gameWidget)
  {
    super();
    this.gameWidget = gameWidget;
    rootPanel.add(queuedList);
    dataList.addDataDisplay(queuedList);
    gameWidget.game().actionsQueue().addActionQueueChangedHandler(this);
  }
  @Override public Widget asWidget()
  {
    return rootPanel;
  }
  @Override public void onActionQueueChanged(ActionQueueChangedEvent event)
  {
    if (event.getDequeuedAction() != null)
    {
      boolean isremoved = dataList.getList().remove(
              event.getDequeuedAction());
      if (!isremoved)
      {
        // throw new RuntimeException("hm, itm not removed....");
      }
    }
    if (event.getEnqueuedAction() != null)
    {
      dataList.getList().add(event.getEnqueuedAction());
    }
  }
}
