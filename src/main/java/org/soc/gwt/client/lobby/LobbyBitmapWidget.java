package org.soc.gwt.client.lobby;

import java.io.Serializable;
import java.util.List;

import org.soc.common.game.actions.ValidateResult;
import org.soc.common.lobby.GameListChangedEvent;
import org.soc.common.lobby.GameListChangedEvent.GameListChangedHandler;
import org.soc.common.lobby.Lobby;
import org.soc.common.lobby.Lobby.LobbyImpl;
import org.soc.common.lobby.actions.LobbyAction;
import org.soc.common.server.GameDto;
import org.soc.common.server.GreetingServiceAsync;
import org.soc.common.server.entities.User;
import org.soc.common.views.widgetsInterface.lobby.ChatBoxWidget;
import org.soc.common.views.widgetsInterface.lobby.GameListWidget;
import org.soc.common.views.widgetsInterface.lobby.LobbyWidget;
import org.soc.common.views.widgetsInterface.lobby.NetworkGameDetailsWidget;
import org.soc.common.views.widgetsInterface.lobby.NewNetworkGameWidget;
import org.soc.common.views.widgetsInterface.lobby.UserListWidget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class LobbyBitmapWidget extends Composite implements LobbyWidget,
        GameListChangedHandler
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
  private GameDto currentGame;
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
    lobby.games().addGameListChangedHandler(this);
  }
  @Override public void sendLobbyAction(LobbyAction action)
  {
    chatService.send(action, new AsyncCallback<ValidateResult>()
    {
      @Override public void onSuccess(ValidateResult result)
      {
        if (!result.isValid())
          statusWidget.setError(result.getInvalidReason());
      }
      @Override public void onFailure(Throwable caught)
      {
        statusWidget.setError(caught.getMessage());
      }
    });
  }
  @Override public Lobby getLobby()
  {
    return lobby;
  }
  @Override public ChatBoxWidget getChatBoxWidget()
  {
    return chatBox;
  }
  @Override public UserListWidget getUserListWidget()
  {
    return userListWidget;
  }
  @Override public GameListWidget getGameListWidget()
  {
    return gameListWidget;
  }
  @Override public User getUser()
  {
    return user;
  }
  @Override public void doAction(LobbyAction lobbyAction)
  {
    lobbyAction.perform(lobby);
  }
  @Override public void onConnected(int heartbeat)
  {
    statusWidget.setConnected();
  }
  @Override public void onDisconnected()
  {
    statusWidget.setDisconnected();
  }
  @Override public void onError(Throwable exception, boolean connected)
  {
    statusWidget.setError(exception.getMessage());
    if (connected)
      statusWidget.setConnected();
    else
      statusWidget.setDisconnected();
  }
  @Override public void onHeartbeat()
  {
    statusWidget.onHeartBeat();
  }
  @Override public void onRefresh()
  {
    // TODO Auto-generated method stub
  }
  @Override public void onMessage(List<? extends Serializable> messages)
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
  @Override public NewNetworkGameWidget getNewNetworkgameWidget()
  {
    return newNetworkGameWidget;
  }
  @Override public void setUser(User user)
  {
    this.user = user;
  }
  @Override public void onGameListChanged(GameListChangedEvent event)
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
  @Override public Widget getRootWidget()
  {
    return this;
  }
}