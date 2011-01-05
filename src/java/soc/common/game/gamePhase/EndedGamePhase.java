package soc.common.game.gamePhase;

import soc.common.game.Game;
import soc.common.game.Turn;

public class EndedGamePhase extends AbstractGamePhase
{

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Game has ended";
    }

    /*
     * Returns null, there is no next turn when game ended
     * 
     * @see
     * soc.common.game.gamePhase.AbstractGamePhase#nextTurn(soc.common.game.
     * Game)
     */
    @Override
    public Turn nextTurn(Game game)
    {
        return null;
    }

}
