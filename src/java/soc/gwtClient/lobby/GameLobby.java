package soc.gwtClient.lobby;

import java.io.Serializable;
import java.util.List;

import net.zschech.gwt.comet.client.CometListener;
import soc.common.game.Game;
import soc.common.lobby.GameList;
import soc.common.lobby.Lobby;
import soc.common.lobby.LobbyLog;
import soc.common.server.entities.Player;
import soc.common.server.entities.UserList;
import soc.gwtClient.main.CenterWidget;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class GameLobby extends Composite implements Lobby, CometListener,
        CenterWidget
{
    private PlayerCell playerCell = new PlayerCell();
    private CellList<Player> playersList = new CellList<Player>(playerCell);
    ListDataProvider<Player> players = new ListDataProvider<Player>();

    private GameCell gameCell = new GameCell();
    private CellList<Game> gamesList = new CellList<Game>(gameCell);
    ListDataProvider<Game> games = new ListDataProvider<Game>();

    private ChatCell chatCell = new ChatCell();
    private CellList<String> chatsList = new CellList<String>(chatCell);
    ListDataProvider<String> chats = new ListDataProvider<String>();

    private HeartBeatWidget heartBeat = new HeartBeatWidget();

    public GameLobby()
    {
        players.addDataDisplay(playersList);
        games.addDataDisplay(gamesList);
        chats.addDataDisplay(chatsList);

        DockPanel dockPanel = new DockPanel();
        dockPanel.setSpacing(10);
        initWidget(dockPanel);

        HorizontalPanel panelHeader = new HorizontalPanel();
        panelHeader.setSpacing(5);
        dockPanel.add(panelHeader, DockPanel.NORTH);

        Label lblLobby = new Label("Lobby");
        panelHeader.add(lblLobby);

        VerticalPanel panelPlayers = new VerticalPanel();
        dockPanel.add(panelPlayers, DockPanel.WEST);

        Label lblPlayers = new Label("Players");
        panelPlayers.add(lblPlayers);

        VerticalPanel verticalPanel_1 = new VerticalPanel();
        dockPanel.add(verticalPanel_1, DockPanel.EAST);

        Label lblGames = new Label("Games");
        verticalPanel_1.add(lblGames);

        DockPanel panelChat = new DockPanel();
        dockPanel.add(panelChat, DockPanel.WEST);

        Label lblChat = new Label("Chat");
        panelChat.add(lblChat, DockPanel.NORTH);

        TextBox textBox = new TextBox();
        textBox.setVisibleLength(40);
        panelChat.add(textBox, DockPanel.SOUTH);

        panelHeader.add(heartBeat);
    }

    @Override
    public void onConnected(int heartbeat)
    {

    }

    @Override
    public void onDisconnected()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onError(Throwable exception, boolean connected)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onHeartbeat()
    {
        heartBeat.beat();
    }

    @Override
    public void onMessage(List<? extends Serializable> messages)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onRefresh()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Widget getRootWidget()
    {
        return this;
    }

    @Override
    public UserList getUsers()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LobbyLog getLog()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameList getGames()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
