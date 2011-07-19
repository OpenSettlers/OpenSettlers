package soc.gwt.client.lobby;

import java.io.Serializable;
import java.util.List;

import soc.common.actions.ValidateResult;
import soc.common.actions.lobby.LobbyAction;
import soc.common.lobby.GameInfo;
import soc.common.lobby.GameListChangedEvent;
import soc.common.lobby.GameListChangedEventHandler;
import soc.common.lobby.Lobby;
import soc.common.lobby.LobbyImpl;
import soc.common.server.GreetingServiceAsync;
import soc.common.server.entities.User;
import soc.common.views.widgetsInterface.lobby.ChatBoxWidget;
import soc.common.views.widgetsInterface.lobby.GameListWidget;
import soc.common.views.widgetsInterface.lobby.LobbyWidget;
import soc.common.views.widgetsInterface.lobby.NetworkGameDetailsWidget;
import soc.common.views.widgetsInterface.lobby.NewNetworkGameWidget;
import soc.common.views.widgetsInterface.lobby.UserListWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class LobbyBitmapWidget extends Composite implements LobbyWidget,
                GameListChangedEventHandler
{
    private DockLayoutPanel rootPanel = new DockLayoutPanel(Unit.PCT);
    private GreetingServiceAsync chatService;
    private UserListWidget userListWidget;
    private GameListWidget gameListWidget;
    private ChatBoxWidget chatBox;
    private LobbyStatusWidget statusWidget;
    private Lobby lobby = new LobbyImpl();
    private User user;
    private NewNetworkGameWidget newNetworkGameWidget;
    private GameInfo currentGame;
    private NetworkGameDetailsWidget currentGameWidget;
    private SimplePanel currentgameWidgetRoot = new SimplePanel();

    public LobbyBitmapWidget(GreetingServiceAsync chatService)
    {
        this.chatService = chatService;
        statusWidget = new LobbyStatusBitmapWidget(lobby);
        gameListWidget = new GameListBitmapWidget(this);
        userListWidget = new UserListBitmapWidget(this);
        chatBox = new ChatBoxBitmapWidget(this);
        currentGameWidget = new NetworkGameDetailsBitmapWidget(this);
        newNetworkGameWidget = new NewNetworkGameBitmapWidget(this);
        initWidget(rootPanel);

        rootPanel.addNorth(statusWidget.asWidget(), 10);
        rootPanel.addWest(userListWidget.asWidget(), 33);
        rootPanel.addSouth(currentgameWidgetRoot.asWidget(), 40);
        rootPanel.addEast(gameListWidget.asWidget(), 33);
        rootPanel.add(chatBox);

        this.setSize("100%", "100%");
        lobby.getGames().addGameListChangedEventHandler(this);
    }
    @Override
    public void sendLobbyAction(LobbyAction action)
    {
        chatService.send(action, new AsyncCallback<ValidateResult>()
        {
            @Override
            public void onSuccess(ValidateResult result)
            {
                if (!result.isValid())
                    statusWidget.setError(result.getInvalidReason());
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
    public User getUser()
    {
        return user;
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

    @Override
    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public void onGameListChanged(GameListChangedEvent event)
    {
        if (event.getAddedGame() != null
                        && event.getAddedGame().getHost().equals(user))
        {
            currentGameWidget.setGame(event.getAddedGame());
            currentgameWidgetRoot.add(currentGameWidget);
        }

        if (event.getRemovedGame() != null
                        && event.getRemovedGame().equals(currentGame))
        {
            currentGameWidget.setGame(null);
            currentgameWidgetRoot.remove(currentGameWidget);
        }
    }
    @Override
    public Widget getRootWidget()
    {
        return this;
    }
}