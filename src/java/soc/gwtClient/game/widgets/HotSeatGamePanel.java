package soc.gwtClient.game.widgets;

import org.vaadin.gwtgraphics.client.DrawingArea;

import soc.common.actions.gameAction.HostStartsGame;
import soc.common.board.Board;
import soc.common.client.visuals.game.IGameBoardVisual;
import soc.common.game.Game;
import soc.common.game.Player;
import soc.common.server.HotSeatServer;
import soc.gwtClient.game.ICenterWidget;
import soc.gwtClient.game.abstractWidgets.AbstractBankStockWidget;
import soc.gwtClient.game.abstractWidgets.AbstractGamePanel;
import soc.gwtClient.game.abstractWidgets.IActionsWidget;
import soc.gwtClient.game.abstractWidgets.IBankStockPanel;
import soc.gwtClient.game.abstractWidgets.IGameHistoryWidget;
import soc.gwtClient.game.abstractWidgets.IGamePanel;
import soc.gwtClient.game.abstractWidgets.IHandCardsWidget;
import soc.gwtClient.game.abstractWidgets.IPlayersWidget;
import soc.gwtClient.game.abstractWidgets.IStatusPanel;
import soc.gwtClient.game.widgets.bitmap.BitmapHistoryWidget;
import soc.gwtClient.game.widgets.bitmap.BoardLayoutPanel;
import soc.gwtClient.game.widgets.standard.bitmap.HandCardsBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.PlayersBitmapWidget;
import soc.gwtClient.game.widgets.standard.bitmap.StatusDiceBitmapPanel;
import soc.gwtClient.game.widgets.standard.bitmap.actions.ActionsBitmapWidget;

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
    BoardLayoutPanel boardVisualPanel = new BoardLayoutPanel();
    ChatPanel chatPanel;

    public HotSeatGamePanel(Game game)
    {
        super(game);

        server = new HotSeatServer(this);

        chatPanel = new ChatPanel(this);

        createChatHistoryDebugPanel();
        boardVisualPanel.setCanvas((DrawingArea) gameBoard.asWidget());

        boardActionResourcesPanel.addSouth(handCards.asWidget(), 5);
        boardActionResourcesPanel.addSouth(buildPallette.asWidget(), 5);
        boardActionResourcesPanel.addSouth(statusDicePanel.asWidget(), 5);
        boardActionResourcesPanel.add(boardVisualPanel);

        playersBankChatPanel.addNorth(playersWidget.asWidget(), 20);
        playersBankChatPanel.addNorth(bankStockPanel.asWidget(), 5);
        playersBankChatPanel.add(chatHistoryDebugPanel);

        rootPanel.addWest(playersBankChatPanel, 20);
        rootPanel.add(boardActionResourcesPanel);

        // gameBoard.getWidget().

        HostStartsGame start = new HostStartsGame();
        start.setPlayer(player);

        server.sendAction(start);
    }

    private void createChatHistoryDebugPanel()
    {
        chatHistoryDebugPanel.add(chatPanel, "chat");
        chatHistoryDebugPanel.add(historyWidget, "history");
        chatHistoryDebugPanel.add(new SimplePanel(), "debug");
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
    public IBankStockPanel createBankStockPanel()
    {
        return new AbstractBankStockWidget(game);
    }

    @Override
    public IGameBoardVisual createGameBoard(int width, int height, Board board)
    {
        return new SvgGameBoardVisual(width, height, board);
    }

    @Override
    public IHandCardsWidget createHandCardsWidget(Player player)
    {
        return new HandCardsBitmapWidget(player);
    }

    @Override
    public IStatusPanel createStatusDicePanel(IGamePanel gamePanel)
    {
        return new StatusDiceBitmapPanel(gamePanel);
    }

    @Override
    public IGameHistoryWidget createHistoryWidget(IGamePanel gamePanel)
    {
        return new BitmapHistoryWidget(gamePanel);
    }
}
