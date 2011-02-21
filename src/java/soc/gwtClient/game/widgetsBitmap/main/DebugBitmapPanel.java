package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.actions.gameAction.MessageFromServer;
import soc.gwtClient.game.widgetsBitmap.generic.StringErrorCell;
import soc.gwtClient.game.widgetsInterface.main.DebugWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class DebugBitmapPanel implements DebugWidget
{
    StringErrorCell cell = new StringErrorCell();
    ScrollPanel rootPanel = new ScrollPanel();
    CellList<MessageFromServer> actionsList = new CellList<MessageFromServer>(
            cell);
    GameWidget gameWidget;
    ListDataProvider<MessageFromServer> dataProvider = new ListDataProvider<MessageFromServer>();

    public DebugBitmapPanel(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

        rootPanel.add(actionsList);
        dataProvider.addDataDisplay(actionsList);

    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void addError(MessageFromServer error)
    {
        dataProvider.getList().add(error);
    }
}
