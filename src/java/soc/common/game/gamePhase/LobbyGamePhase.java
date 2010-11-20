package soc.common.game.gamePhase;

import soc.common.actions.gameAction.*;
import soc.common.game.Game;

public class LobbyGamePhase extends GamePhase
{
    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.GamePhase#next(soc.common.game.Game)
     */
    @Override
    public GamePhase next(Game game)
    {
        return new DetermineFirstPlayerGamePhase(); 
    }

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.GamePhase#performAction(soc.common.actions.gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public void performAction(GameAction action, Game game)
    {
        action.perform(game);
    }
}
