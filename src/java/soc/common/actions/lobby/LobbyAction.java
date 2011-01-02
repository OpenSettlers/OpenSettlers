package soc.common.actions.lobby;

import java.io.Serializable;

import soc.common.server.Lobby;
import soc.common.server.data.Player;

public interface LobbyAction extends Serializable
{
    public void perform(Lobby lobby);

    public Player getPlayer();

    public String getMessage();
}
