package soc.gwtClient.game.widgets;

import soc.common.actions.gameAction.HostStartsGame;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.client.behaviour.game.BuildTownBehaviour;
import soc.common.client.visuals.game.GameBoardVisual;
import soc.common.game.Game;
import soc.common.game.GamePlayer;
import soc.common.server.HotSeatServer;
import soc.common.server.actions.ServerAction;
import soc.gwtClient.game.ICenterWidget;
import soc.gwtClient.game.abstractWidgets.AbstractBankStockWidget;
import soc.gwtClient.game.abstractWidgets.AbstractGamePanel;
import soc.gwtClient.game.abstractWidgets.BankStockPanel;
import soc.gwtClient.game.abstractWidgets.BankTradeUI;
import soc.gwtClient.game.abstractWidgets.GameHistoryWidget;
import soc.gwtClient.game.abstractWidgets.HandCardsWidget;
import soc.gwtClient.game.abstractWidgets.IActionsWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.abstractWidgets.IPlayersWidget;
import soc.gwtClient.game.abstractWidgets.StatusPanel;
import soc.gwtClient.game.dialogs.TradeBankDialog;
import soc.gwtClient.game.widgets.bitmap.BitmapHistoryWidget;
import soc.gwtClient.game.widgets.bitmap.BoardLayoutPanel;
import soc.gwtClient.game.widgets.standard.bitmap.HandCardsBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.PlayersBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.StatusBitmapPanel;
import soc.gwtClient.game.widgets.standard.bitmap.actions.ActionsBitmapWidget;
import soc.gwtClient.visuals.svg.GameBoardSvg;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class HotSeatGamePanel extends AbstractGamePanel implements
        ICenterWidget
{
    DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    DockLayoutPanel playersBankChatPanel = new DockLayoutPanel(Unit.EM);
    DockLayoutPanel boardActionResourcesPanel = new DockLayoutPanel(Unit.EM);
    TabLayoutPanel chatHistoryDebugPanel = new TabLayoutPanel(20.0, Unit.PX);
    BoardLayoutPanel boardVisualPanel = new BoardLayoutPanel(gameBoardVisual);
    ChatPanel chatPanel;
    GameQueuePanel gameQueuePanel;

    public HotSeatGamePanel(Game game)
    {
        super(game);

        server = new HotSeatServer(this);

        chatPanel = new ChatPanel(this);
        gameQueuePanel = new GameQueuePanel(this);

        createChatHistoryDebugPanel();

        boardActionResourcesPanel.addEast(handCards.asWidget(), 15);
        boardActionResourcesPanel.addEast(buildPallette.asWidget(), 10);
        boardActionResourcesPanel.addSouth(statusPanel.asWidget(), 3);
        boardActionResourcesPanel.add(boardVisualPanel);

        playersBankChatPanel.addNorth(playersWidget.asWidget(), 20);
        playersBankChatPanel.addNorth(bankStockPanel.asWidget(), 5);
        playersBankChatPanel.add(chatHistoryDebugPanel);

        rootPanel.addWest(playersBankChatPanel, 20);
        rootPanel.add(boardActionResourcesPanel);

        HostStartsGame start = new HostStartsGame();
        start.setPlayer(player);

        server.sendAction(start);

        gameBoardVisual.setBehaviour(new BuildTownBehaviour(new BuildTown()));
    }

    private void createChatHistoryDebugPanel()
    {
        chatHistoryDebugPanel.add(chatPanel, "chat");
        chatHistoryDebugPanel.add(historyWidget, "history");
        chatHistoryDebugPanel.add(new SimplePanel(), "debug");
        chatHistoryDebugPanel.add(gameQueuePanel, "queue");
    }

    @Override
    public Panel getRootWidget()
    {
        return rootPanel;
    }

    @Override
    public IActionsWidget createActionsWidget()
    {
        return new ActionsBitmapWidget(this, game.getPlayers().get(0));
    }

    @Override
    public IPlayersWidget createPlayersWidget()
    {
        return new PlayersBitmapWidget(game);
    }

    @Override
    public BankStockPanel createBankStockPanel()
    {
        return new AbstractBankStockWidget(game);
    }

    @Override
    public GameBoardVisual createGameBoard(int width, int height, Game game)
    {
        return new GameBoardSvg(game, width, height);
    }

    @Override
    public HandCardsWidget createHandCardsWidget(GamePlayer player)
    {
        return new HandCardsBitmapWidget(player);
    }

    @Override
    public StatusPanel createStatusDicePanel(IGamePanel gamePanel)
    {
        return new StatusBitmapPanel(gamePanel);
    }

    @Override
    public GameHistoryWidget createHistoryWidget(IGamePanel gamePanel)
    {
        return new BitmapHistoryWidget(gamePanel);
    }

    @Override
    public BankTradeUI createBankTradeUI(IGamePanel gamePanel)
    {
        return new TradeBankDialog(gamePanel);
    }

    @Override
    public void receive(ServerAction serverAction)
    {
        // TODO Auto-generated method stub

    }
}
