package soc.gwtClient.game.widgets;

import soc.common.actions.gameAction.MessageFromServer;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.widgets.abstractWidgets.DebugWidget;
import soc.gwtClient.game.widgets.bitmap.StringErrorCell;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class DebugPanel implements DebugWidget
{
    StringErrorCell cell = new StringErrorCell();
    ScrollPanel rootPanel = new ScrollPanel();
    CellList<MessageFromServer> actionsList = new CellList<MessageFromServer>(
            cell);
    IGamePanel gamePanel;
    ListDataProvider<MessageFromServer> dataProvider = new ListDataProvider<MessageFromServer>();

    public DebugPanel(IGamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;

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
