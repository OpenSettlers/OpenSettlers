package soc.gwtClient.game.widgets.bitmap;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.logs.ActionPerformedEvent;
import soc.common.game.logs.ActionPerformedEventHandler;
import soc.gwtClient.game.abstractWidgets.GameHistoryWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class BitmapHistoryWidget implements GameHistoryWidget,
        ActionPerformedEventHandler
{
    StringActionCell cell = new StringActionCell();
    ScrollPanel rootPanel = new ScrollPanel();
    CellList<GameAction> actionsList = new CellList<GameAction>(cell);
    IGamePanel gamePanel;
    ListDataProvider<GameAction> dataProvider = new ListDataProvider<GameAction>();

    public BitmapHistoryWidget(IGamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

        dataProvider.addDataDisplay(actionsList);

        gamePanel.getGame().getGameLog().addActionPerformedEventHandler(this);

        rootPanel.add(actionsList);
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void onActionPerformed(ActionPerformedEvent event)
    {
        dataProvider.getList().add(0, event.getPerformedAction());
    }
}
