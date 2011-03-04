package soc.common.actions.lobby;

import java.io.Serializable;

import soc.common.lobby.Lobby;
import soc.common.server.entities.Player;
import soc.common.server.lobbyActions.ServerLobbyAction;
import soc.common.server.lobbyActions.ServerLobbyActionFactory;

public interface LobbyAction extends Serializable
{
    public void perform(Lobby lobby);

    public Player getPlayer();

    public String getMessage();

    public LobbyAction setPlayer(Player player);

    public ServerLobbyAction createServerLobbyAction(
            ServerLobbyActionFactory factory);
}
