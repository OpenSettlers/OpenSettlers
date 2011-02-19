package soc.gwtClient.game.widgetsBitmap.main;

import soc.gwtClient.game.widgetsInterface.main.GamePanelLayoutWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidget;
import soc.gwtClient.game.widgetsInterface.main.GameWidgetFactory;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;

public class DesktopGamePanelLayout implements GamePanelLayoutWidget
{
    private DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    private DockLayoutPanel playersBankChatPanel = new DockLayoutPanel(Unit.EM);
    private DockLayoutPanel boardActionResourcesPanel = new DockLayoutPanel(
            Unit.EM);

    protected GameWidgetFactory gameWidgetFactory;

    private GameWidget gamePanel;

    public DesktopGamePanelLayout(GameWidget gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
        gameWidgetFactory = new GameBitmapWidgetFactory(gamePanel);

    }

    @Override
    public GameWidgetFactory getGameWidgetFactory()
    {
        return gameWidgetFactory;
    }

    @Override
    public void initialize()
    {
        // boardActionResourcesPanel.addEast(playersActionsWidget, 15);
        boardActionResourcesPanel.addSouth(gamePanel.getStatusWidget()
                .asWidget(), 5);
        boardActionResourcesPanel.add(gamePanel.getBoardVisualWidget());

        playersBankChatPanel.addNorth(gamePanel.getPlayersInfoWidget()
                .asWidget(), 20);
        playersBankChatPanel.addNorth(gamePanel.getbankStockPanel().asWidget(),
                5);
        // playersBankChatPanel.add(chatHistoryDebugPanel);

        rootPanel.addWest(playersBankChatPanel, 20);
        rootPanel.add(boardActionResourcesPanel);
    }

    @Override
    public Panel getRootPanel()
    {
        return rootPanel;
    }

}
