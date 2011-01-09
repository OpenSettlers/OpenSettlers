package soc.common.actions.lobby;

import soc.common.game.Game;
import soc.common.game.player.GamePlayer;
import soc.common.server.Lobby;

public class CreateGame extends AbstractLobbyAction
{
    private static final long serialVersionUID = 6745433721783796193L;
    private GamePlayer starter;
    private Game game;

    @Override
    public void perform(Lobby lobby)
    {
        lobby.getGames().add(game);
    }
}
