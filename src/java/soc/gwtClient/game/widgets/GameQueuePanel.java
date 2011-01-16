package soc.gwtClient.game.widgets;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.logs.ActionQueueChangedEvent;
import soc.common.game.logs.ActionQueueChangedEventHandler;
import soc.gwtClient.game.abstractWidgets.GamePanel;
import soc.gwtClient.game.widgets.bitmap.StringQueuedActionCell;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class GameQueuePanel implements IsWidget, ActionQueueChangedEventHandler
{
    private ScrollPanel rootPanel = new ScrollPanel();
    private GamePanel gamePanel;
    private StringQueuedActionCell cell = new StringQueuedActionCell();
    private CellList<GameAction> queuedList = new CellList<GameAction>(cell);
    private ListDataProvider<GameAction> dataList = new ListDataProvider<GameAction>();

    public GameQueuePanel(GamePanel gamePanel)
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
