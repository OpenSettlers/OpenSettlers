package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.logs.ActionPerformedEvent;
import soc.common.game.logs.ActionPerformedEventHandler;
import soc.common.views.widgetsInterface.main.GameWidget;
import soc.common.views.widgetsInterface.main.HistoryWidget;
import soc.gwtClient.game.widgetsBitmap.generic.StringActionCell;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class HistoryBitmapWidget implements HistoryWidget,
        ActionPerformedEventHandler
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

        gameWidget.getGame().getGameLog().addActionPerformedEventHandler(this);

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
