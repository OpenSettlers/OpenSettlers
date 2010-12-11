package soc.common.actions.gameAction;

import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;

/*
 * Announces a gamephase which has been ended
 */
public class GamePhaseHasEnded extends GameAction
{
    private GamePhase endedGamePhase;
    private GamePhase newPhase;

    /**
     * @return the newPhase
     */
    public GamePhase getNewPhase()
    {
        return newPhase;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.GameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        
        endedGamePhase = game.getCurrentPhase();
        
        // Advance the phase to next phase
        game.setCurrentPhase(game.getCurrentPhase().next(game));
        
        // Start the next phase
        game.getCurrentPhase().start(game);
        
        newPhase = game.getCurrentPhase();

        super.perform(game);
    }

    /**
     * @return the endedGamePhase
     */
    public GamePhase getEndedGamePhase()
    {
        return endedGamePhase;
    }

    /**
     * @param endedGamePhase the endedGamePhase to set
     */
    public GamePhaseHasEnded setEndedGamePhase(GamePhase endedGamePhase)
    {
        this.endedGamePhase = endedGamePhase;
    
        return this;
    }
}
