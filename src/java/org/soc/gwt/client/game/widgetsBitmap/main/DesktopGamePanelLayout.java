package org.soc.gwt.client.game.widgetsBitmap.main;

import org.soc.common.views.widgetsInterface.main.GamePanelLayoutWidget;
import org.soc.common.views.widgetsInterface.main.GameWidget;
import org.soc.common.views.widgetsInterface.main.GameWidgetFactory;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class DesktopGamePanelLayout implements GamePanelLayoutWidget
{
    private DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    private DockLayoutPanel playersBankChatPanel = new DockLayoutPanel(Unit.EM);
    private DockLayoutPanel boardActionResourcesPanel = new DockLayoutPanel(
            Unit.EM);

    protected GameWidgetFactory gameWidgetFactory;

    private GameWidget gameWidget;
    protected Widget gameDetailsWidget;

    public DesktopGamePanelLayout(GameWidget gameWidget)
    {
        super();
        this.gameWidget = gameWidget;
        gameWidgetFactory = new GameBitmapWidgetFactory(gameWidget);

    }

    @Override
    public GameWidgetFactory getGameWidgetFactory()
    {
        return gameWidgetFactory;
    }

    @Override
    public void initialize()
    {
        boardActionResourcesPanel.addEast(gameWidget.getActionsWidget()
                .asWidget(), 15);
        boardActionResourcesPanel.addSouth(gameWidget.getStatusWidget()
                .asWidget(), 5);
        boardActionResourcesPanel.add(gameWidget.getBoardVisualWidget());

        playersBankChatPanel.addNorth(gameWidget.getPlayersInfoWidget()
                .asWidget(), 20);
        playersBankChatPanel.addNorth(gameWidget.getbankStockPanel().asWidget(),
                5);
        playersBankChatPanel.add(gameWidget.getDetailsWidget());

        rootPanel.addWest(playersBankChatPanel, 20);
        rootPanel.add(boardActionResourcesPanel);
    }

    @Override
    public Panel getRootPanel()
    {
        return rootPanel;
    }

}
