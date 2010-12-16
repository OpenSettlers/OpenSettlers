package soc.common.actions.gameAction;

import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.LobbyGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public class HostStartsGame extends GameAction
{
    private Game game;

    /**
     * @return the game
     */
    public Game getGame()
    {
        return game;
    }

    /**
     * @param game the game to set
     */
    public HostStartsGame setGame(Game game)
    {
        this.game = game.copy();
    
        return this;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#isAllowed(soc.common.game.gamePhase.GamePhase)
     */
    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof LobbyGamePhase;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#isAllowed(soc.common.game.gamePhase.turnPhase.TurnPhase)
     */
    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return false;
    }

}
