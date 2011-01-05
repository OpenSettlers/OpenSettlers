package soc.common.game.gamePhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.GamePhaseHasEnded;
import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.game.TurnImpl;
import soc.common.utils.ClassUtils;

/*
 * Represent a phase in the overall game phases
 * A GamePhase ends itself by adding an EndedGamePhase action onto the actionsQueue.
 */
public abstract class AbstractGamePhase implements GamePhase
{
    public void performAction(GameAction action, Game game)
    {
    };

    public Turn nextTurn(Game game)
    {
        GameAction next = game.getActionsQueue().peekAction();
        if (next != null && !(next instanceof GamePhaseHasEnded))
        {
            return new TurnImpl().setPlayer(next.getPlayer());
        }
        throw new RuntimeException("No expected action, no turn to create");
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.GamePhase#start(soc.common.game.Game)
     */
    public void start(Game game)
    {
    };

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.GamePhase#next(soc.common.game.Game)
     */
    public GamePhase next(Game game)
    {
        throw new RuntimeException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.GamePhase#isAllowed(soc.common.actions.gameAction
     * .GameAction)
     */
    public boolean isAllowed(GameAction action)
    {
        return action.isAllowed(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.GamePhase#getName()
     */
    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }
}
