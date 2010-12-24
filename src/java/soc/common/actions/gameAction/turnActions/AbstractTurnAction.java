package soc.common.actions.gameAction.turnActions;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.game.Game;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public abstract class AbstractTurnAction extends AbstractGameAction implements TurnAction
{
    private static final long serialVersionUID = -7358139407510140679L;
    protected TurnPhase turnPhasePerformed = null;
    
    @Override
    public TurnPhase getTurnPhase()
    {
        return turnPhasePerformed;
    }

    /* (non-Javadoc)
     * @see soc.common.actions.gameAction.AbstractGameAction#perform(soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        // Record the turn phase
        turnPhasePerformed = ((PlayTurnsGamePhase)game.getCurrentPhase()).getTurnPhase();
        
        super.perform(game);
    }
    
}
