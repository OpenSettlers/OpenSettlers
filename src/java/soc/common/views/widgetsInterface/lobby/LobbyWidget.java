package soc.common.views.widgetsInterface.lobby;

import net.zschech.gwt.comet.client.CometListener;
import soc.common.actions.lobby.LobbyAction;
import soc.common.server.Lobby;
import soc.common.server.entities.Player;

import com.google.gwt.user.client.ui.IsWidget;

public interface LobbyWidget extends IsWidget, CometListener
{
    public Lobby getLobby();

    public ChatBoxWidget getChatBoxWidget();

    public UserListWidget getUserListWidget();

    public GameListWidget getGameListWidget();

    public abstract void sendLobbyAction(LobbyAction action);

    public Player getPlayer();

    public void doAction(LobbyAction lobbyAction);

    public NewNetworkGameWidget getNewNetworkgameWidget();
}
