package soc.gwtClient.game.widgets;

import soc.common.game.logs.ActionQueueChangedEvent;
import soc.common.game.logs.ActionQueueChangedEventHandler;
import soc.common.game.logs.QueuedAction;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.widgets.bitmap.StringQueuedActionCell;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class GameQueuePanel implements IsWidget, ActionQueueChangedEventHandler
{
    private LayoutPanel rootPanel = new LayoutPanel();
    private IGamePanel gamePanel;
    private StringQueuedActionCell cell = new StringQueuedActionCell();
    private CellList<QueuedAction> queuedList = new CellList<QueuedAction>(cell);
    private ListDataProvider<QueuedAction> dataList = new ListDataProvider<QueuedAction>();

    public GameQueuePanel(IGamePanel gamePanel)
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
            dataList.getList().remove(event.getDequeuedAction());
        }
        if (event.getEnqueuedAction() != null)
        {
            dataList.getList().add(event.getEnqueuedAction());
        }
    }

}
