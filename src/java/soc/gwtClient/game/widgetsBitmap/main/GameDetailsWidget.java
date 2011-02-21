package soc.gwtClient.game.widgetsBitmap.main;

import soc.common.actions.gameAction.MessageFromServer;
import soc.gwtClient.game.widgetsInterface.main.ChatWidget;
import soc.gwtClient.game.widgetsInterface.main.DebugWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.HistoryWidget;
import soc.gwtClient.game.widgetsInterface.main.QueueWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class GameDetailsWidget implements HistoryWidget, ChatWidget,
        DebugWidget, QueueWidget
{
    private GameWidget gameWidget;
    private ChatWidget chatWidget = new ChatBitmapPanel();
    private HistoryWidget historyWidget;
    private DebugWidget debugWidget;
    private QueueWidget queueWidget;
    private TabLayoutPanel rootPanel = new TabLayoutPanel(20.0, Unit.PX);

    public GameDetailsWidget(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;

        historyWidget = new HistoryBitmapWidget(gameWidget);
        debugWidget = new DebugBitmapPanel(gameWidget);
        queueWidget = new GameQueueWidget(gameWidget);

        rootPanel.add(chatWidget, "chat");
        rootPanel.add(historyWidget, "history");
        rootPanel.add(debugWidget, "debug");
        rootPanel.add(queueWidget, "queue");
    }

    @Override
    public Widget asWidget()
    {
        return rootPanel;
    }

    @Override
    public void addError(MessageFromServer error)
    {
        debugWidget.addError(error);
    }

}
