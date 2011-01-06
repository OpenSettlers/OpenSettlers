package soc.common.actions.lobby;

import soc.common.game.player.GamePlayer;
import soc.common.server.Lobby;

public class CreateGame extends AbstractLobbyAction
{
    private static final long serialVersionUID = 6745433721783796193L;
    private GamePlayer starter;

    @Override
    public String getMessage()
    {
        // TODO fix message
        return null;
    }

    @Override
    public void perform(Lobby lobby)
    {
        // TODO Auto-generated method stub

    }
}
