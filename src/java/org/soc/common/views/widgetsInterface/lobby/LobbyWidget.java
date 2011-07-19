package org.soc.common.views.widgetsInterface.lobby;

import org.soc.common.actions.lobby.LobbyAction;
import org.soc.common.lobby.Lobby;
import org.soc.common.server.entities.User;
import org.soc.common.views.widgetsInterface.main.CenterWidget;

import net.zschech.gwt.comet.client.CometListener;

import com.google.gwt.user.client.ui.IsWidget;

public interface LobbyWidget extends IsWidget, CometListener, CenterWidget
{
    public Lobby getLobby();

    public ChatBoxWidget getChatBoxWidget();

    public UserListWidget getUserListWidget();

    public GameListWidget getGameListWidget();

    public abstract void sendLobbyAction(LobbyAction action);

    public User getUser();

    public void doAction(LobbyAction lobbyAction);

    public NewNetworkGameWidget getNewNetworkgameWidget();

    public void setUser(User user);
}
