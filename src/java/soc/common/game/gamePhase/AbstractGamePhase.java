package soc.common.game.gamePhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.GamePhaseHasEnded;
import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.game.TurnImpl;
import soc.common.game.player.GamePlayer;
import soc.common.utils.ClassUtils;

/*
 * Represent a phase in the overall game phases
 * A GamePhase ends itself by adding an EndedGamePhase action onto the actionsQueue.
 */
public abstract class AbstractGamePhase implements GamePhase
{
    private static final long serialVersionUID = -1096857442642768802L;

    public void performAction(GameAction action, Game game)
    {
    };

    public Turn nextTurn(Game game)
    {
        GameAction next = game.getActionsQueue().peek();
        if (next != null && !(next instanceof GamePhaseHasEnded))
        {
            GamePlayer player = null;
            if (next.getPlayer().getUser().getId() == 0)
            {
                player = game.getStartPlayer();
            }
            else
            {
                player = next.getPlayer();
            }
            return new TurnImpl(player);
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
