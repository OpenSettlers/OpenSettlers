package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.game.actions.MessageFromServer;
import org.soc.common.views.widgetsInterface.main.ChatWidget;
import org.soc.common.views.widgetsInterface.main.DebugWidget;
import org.soc.common.views.widgetsInterface.main.GameDetailsWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.HistoryWidget;
import org.soc.common.views.widgetsInterface.main.QueueWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class GameDetailsBitmapWidget implements HistoryWidget, ChatWidget,
        DebugWidget, QueueWidget, GameDetailsWidget
{
    private GameWidget gameWidget;
    private ChatWidget chatWidget = new ChatBitmapPanel();
    private HistoryWidget historyWidget;
    private DebugWidget debugWidget;
    private QueueWidget queueWidget;
    private TabLayoutPanel rootPanel = new TabLayoutPanel(20.0, Unit.PX);

    public GameDetailsBitmapWidget(GameWidget gameWidget)
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
