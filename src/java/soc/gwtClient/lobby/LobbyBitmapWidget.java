package soc.gwtClient.lobby;

import java.io.Serializable;
import java.util.List;

import soc.common.actions.lobby.LobbyAction;
import soc.common.lobby.Lobby;
import soc.common.lobby.LobbyImpl;
import soc.common.server.GreetingServiceAsync;
import soc.common.server.entities.Player;
import soc.common.views.widgetsInterface.lobby.ChatBoxWidget;
import soc.common.views.widgetsInterface.lobby.GameListWidget;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;
import soc.common.views.widgetsInterface.lobby.NewNetworkGameWidget;
import soc.common.views.widgetsInterface.lobby.UserListWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;

public class LobbyBitmapWidget extends Composite implements LobbyWidget
{
    private DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.PCT);
    private GreetingServiceAsync chatService;
    private UserListWidget userListWidget;
    private GameListWidget gameListWidget;
    private ChatBoxWidget chatBox;
    private LobbyStatusWidget statusWidget;
    private Lobby lobby = new LobbyImpl();
    private Player player;
    private NewNetworkGameWidget newNetworkGameWidget;

    public LobbyBitmapWidget(GreetingServiceAsync chatService)
    {
        this.chatService = chatService;
        statusWidget = new LobbyStatusBitmapWidget(lobby);
        gameListWidget = new GameListBitmapWidget(this);
        userListWidget = new UserListBitmapWidget(this);
        chatBox = new ChatBoxBitmapWidget(this);
        newNetworkGameWidget = new NewNetworkGameBitmapWidget(this);
        initWidget(rootPanel);

        rootPanel.addNorth(statusWidget.asWidget(), 10);
        rootPanel.addEast(gameListWidget.asWidget(), 33);
        rootPanel.addWest(userListWidget.asWidget(), 33);
        rootPanel.add(chatBox);

        this.setSize("100%", "100%");
    }

    @Override
    public void sendLobbyAction(LobbyAction action)
    {
        chatService.send(action, new AsyncCallback<Void>()
        {
            @Override
            public void onSuccess(Void result)
            {
            }

            @Override
            public void onFailure(Throwable caught)
            {
                statusWidget.setError(caught.getMessage());
            }
        });
    }

    @Override
    public Lobby getLobby()
    {
        return lobby;
    }

    @Override
    public ChatBoxWidget getChatBoxWidget()
    {
        return chatBox;
    }

    @Override
    public UserListWidget getUserListWidget()
    {
        return userListWidget;
    }

    @Override
    public GameListWidget getGameListWidget()
    {
        return gameListWidget;
    }

    @Override
    public Player getPlayer()
    {
        return player;
    }

    @Override
    public void doAction(LobbyAction lobbyAction)
    {
        lobbyAction.perform(lobby);
    }

    @Override
    public void onConnected(int heartbeat)
    {
        statusWidget.setConnected();
    }

    @Override
    public void onDisconnected()
    {
        statusWidget.setDisconnected();
    }

    @Override
    public void onError(Throwable exception, boolean connected)
    {
        statusWidget.setError(exception.getMessage());
        if (connected)
            statusWidget.setConnected();
        else
            statusWidget.setDisconnected();
    }

    @Override
    public void onHeartbeat()
    {
        statusWidget.onHeartBeat();
    }

    @Override
    public void onRefresh()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onMessage(List<? extends Serializable> messages)
    {
        for (Serializable serializable : messages)
            if (serializable instanceof LobbyAction)
            {
                // Cast to LobbyAction
                LobbyAction lobbyAction = (LobbyAction) messages.get(0);

                // Set the reference to the User
                lobbyAction.setUser(lobby.getUsers().getById(
                                lobbyAction.getUserId()));

                // Perform the LobbyAction
                doAction(lobbyAction);
            }
    }
    @Override
    public NewNetworkGameWidget getNewNetworkgameWidget()
    {
        return newNetworkGameWidget;
    }
}