package org.soc.common.actions.gameAction.turns;

import org.soc.common.actions.gameAction.AbstractGameAction;
import org.soc.common.game.Game;
import org.soc.common.game.phases.turnPhase.TurnPhase;

public abstract class AbstractTurnAction extends AbstractGameAction implements
        TurnAction
{
    private static final long serialVersionUID = -7358139407510140679L;
    protected TurnPhase turnPhasePerformed = null;

    @Override
    public TurnPhase getTurnPhase()
    {
        return turnPhasePerformed;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.AbstractGameAction#perform(org.soc.common.game
     * .Game)
     */
    @Override
    public void perform(Game game)
    {
        super.perform(game);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.actions.gameAction.turnActions.TurnAction#setTurnPhase(org.soc
     * .common.game.gamePhase.turnPhase.TurnPhase)
     */
    @Override
    public TurnAction setTurnPhase(TurnPhase turnPhase)
    {
        this.turnPhasePerformed = turnPhase;
        return null;
    }

}
