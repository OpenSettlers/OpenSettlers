package soc.gwtClient.game.widgets.standard.bitmap;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import soc.common.board.Board;
import soc.common.client.visuals.game.IGameBoardVisual;
import soc.common.game.Game;
import soc.gwtClient.game.ICenterWidget;
import soc.gwtClient.game.abstractWidgets.AbstractBankStockWidget;
import soc.gwtClient.game.abstractWidgets.AbstractGamePanel;
import soc.gwtClient.game.abstractWidgets.IActionsWidget;
import soc.gwtClient.game.abstractWidgets.IBankStockPanel;
import soc.gwtClient.game.abstractWidgets.IPlayersWidget;
import soc.gwtClient.game.widgets.SvgGameBoardVisual;

public class BitmapGamePanel extends AbstractGamePanel implements ICenterWidget
{
    DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.EM);
    DockLayoutPanel playersBankChatPanel=new DockLayoutPanel(Unit.EM);
    DockLayoutPanel boardActionResourcesPanel= new DockLayoutPanel(Unit.EM);
    TabLayoutPanel chatHistoryDebugPanel = new TabLayoutPanel(20.0, Unit.PX);
    DockLayoutPanel chatPanel = new DockLayoutPanel(Unit.EM);
    SimplePanel boardVisualPanel = new SimplePanel();
    
    public BitmapGamePanel(Game game)
    {
        super(game);
        
        createChatHistoryDebugPanel();
        
        boardActionResourcesPanel.addSouth(buildPallette.asWidget(), 5);
        boardActionResourcesPanel.add(gameBoard.getWidget());

        playersBankChatPanel.addNorth(playersWidget.asWidget(), 20);
        playersBankChatPanel.addNorth(bankStockPanel.asWidget(), 5);
        playersBankChatPanel.add(chatHistoryDebugPanel);
        
        rootPanel.addWest(playersBankChatPanel, 20);
        rootPanel.add(boardActionResourcesPanel);
    }

    private void createChatHistoryDebugPanel()
    {
        TextArea chats = new TextArea();
        TextBox saySomething = new TextBox();
        chatPanel.addSouth(saySomething, 2);
        chatPanel.add(chats);
        chatHistoryDebugPanel.add(chatPanel, "chat");
        chatHistoryDebugPanel.add(new SimplePanel(), "history");
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
        return new BitmapActionsWidget(this, game.getPlayers().get(0));
    }

    @Override
    public IPlayersWidget createPlayersWidget()
    {
        return new BitmapPlayersWidget(game);
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
}
