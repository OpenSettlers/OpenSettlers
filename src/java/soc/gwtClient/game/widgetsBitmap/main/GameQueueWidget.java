package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.logs.ActionQueueChangedEvent;
import soc.common.game.logs.ActionQueueChangedEventHandler;
import soc.gwtClient.game.widgetsBitmap.generic.StringQueuedActionCell;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.QueueWidget;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class GameQueueWidget implements QueueWidget,
        ActionQueueChangedEventHandler
{
    private ScrollPanel rootPanel = new ScrollPanel();
    private GameWidget gamePanel;
    private StringQueuedActionCell cell = new StringQueuedActionCell();
    private CellList<GameAction> queuedList = new CellList<GameAction>(cell);
    private ListDataProvider<GameAction> dataList = new ListDataProvider<GameAction>();

    public GameQueueWidget(GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        rootPanel.add(queuedList);
        dataList.addDataDisplay(queuedList);

        gamePanel.getGame().getActionsQueue().addQueueChangedEventHandler(this);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onQueueChanged(ActionQueueChangedEvent event)
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
