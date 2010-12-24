package soc.common.game.gamePhase.turnPhase;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;

public class BeforeDiceRollTurnPhase extends TurnPhase
{

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#next()
     */
    @Override
    public TurnPhase next()
    {
        return super.next();
    }

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.turnPhase.TurnPhase#processAction(soc.common.actions.gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public TurnPhase processAction(GameAction action, Game game)
    {
        return super.processAction(action, game);
    }

}
