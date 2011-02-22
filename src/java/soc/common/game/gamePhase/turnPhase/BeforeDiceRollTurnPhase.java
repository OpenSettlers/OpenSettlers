package soc.common.game.gamePhase.turnPhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;

public class BeforeDiceRollTurnPhase extends AbstractTurnPhase
{
    private static final long serialVersionUID = -2334736656517691453L;

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#next()
     */
    @Override
    public TurnPhase next()
    {
        return new RollDiceTurnPhase();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.turnPhase.TurnPhase#processAction(soc.common
     * .actions.gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public TurnPhase processAction(GameAction action, Game game)
    {
        return this;
        // return super.processAction(action, game);
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return "Start turn";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.turnPhase.AbstractTurnPhase#isBeforeDiceRoll()
     */
    @Override
    public boolean isBeforeDiceRoll()
    {
        return true;
    }

}
