package soc.gwtClient.client.game.standard.bitmap;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import soc.common.game.Game;
import soc.gwtClient.client.ICenterWidget;
import soc.gwtClient.client.game.AbstractBankStockWidget;
import soc.gwtClient.client.game.AbstractGamePanel;
import soc.gwtClient.client.game.IActionsWidget;
import soc.gwtClient.client.game.IBankStockPanel;
import soc.gwtClient.client.game.IPlayersWidget;

public class BitmapGamePanel extends AbstractGamePanel implements ICenterWidget
{
    HorizontalPanel rootPanel = new HorizontalPanel();
    VerticalPanel playersBankChatPanel=new VerticalPanel();
    VerticalPanel boardActionResourcesPanel= new VerticalPanel();
    TabLayoutPanel chatHistoryDebugPanel = new TabLayoutPanel(20.0, Unit.PX);
    VerticalPanel chatPanel = new VerticalPanel();
    SimplePanel boardVisualPanel = new SimplePanel();
    
    public BitmapGamePanel(Game game)
    {
        super(game);
        
        rootPanel.setSize("100%", "100%");
        playersBankChatPanel.setSize("30%", "100");
        
        createChatHistoryDebugPanel();
        
        boardActionResourcesPanel.setStyleName("game-right-panel");
        boardActionResourcesPanel.setSize("70%", "100%");

        playersBankChatPanel.setStyleName("game-left-panel");
        playersBankChatPanel.add(playersWidget);
        playersBankChatPanel.add(bankStockPanel);
        playersBankChatPanel.add(chatHistoryDebugPanel);
        
        rootPanel.add(playersBankChatPanel);
        rootPanel.add(boardActionResourcesPanel);
    }

    private void createChatHistoryDebugPanel()
    {
        chatPanel.setSize("100%", "100%");
        chatPanel.setStyleName("chat-panel");
        TextArea chats = new TextArea();
        chats.setHeight("100%");
        TextBox saySomething = new TextBox();
        saySomething.setHeight("20px");
        chatPanel.add(chats);
        chatPanel.add(saySomething);
        chatHistoryDebugPanel.add(chatPanel, "chat");
        chatHistoryDebugPanel.add(new SimplePanel(), "history");
        chatHistoryDebugPanel.add(new SimplePanel(), "debug");
        chatHistoryDebugPanel.setSize("100%", "100%");
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
}
