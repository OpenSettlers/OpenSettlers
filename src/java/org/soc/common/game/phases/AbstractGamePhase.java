package org.soc.common.game.phases;

import org.soc.common.actions.gameAction.GameAction;
import org.soc.common.actions.gameAction.turns.GamePhaseHasEnded;
import org.soc.common.game.Game;
import org.soc.common.game.Turn;
import org.soc.common.game.TurnImpl;
import org.soc.common.game.player.GamePlayer;
import org.soc.common.utils.ClassUtils;

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
     * @see org.soc.common.game.gamePhase.GamePhase#start(org.soc.common.game.Game)
     */
    public void start(Game game)
    {
    };

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.soc.common.game.gamePhase.GamePhase#isAllowed(org.soc.common.actions.gameAction
     * .GameAction)
     */
    public boolean isAllowed(GameAction action)
    {
        return action.isAllowed(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.gamePhase.GamePhase#getName()
     */
    public String getName()
    {
        return ClassUtils.getSimpleClassName(this.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.gamePhase.GamePhase#isDetermineFirstPlayer()
     */
    @Override
    public boolean isDetermineFirstPlayer()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.gamePhase.GamePhase#isEnded()
     */
    @Override
    public boolean isEnded()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.gamePhase.GamePhase#isInitialPlacement()
     */
    @Override
    public boolean isInitialPlacement()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.gamePhase.GamePhase#isLobby()
     */
    @Override
    public boolean isLobby()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.gamePhase.GamePhase#isPlacePorts()
     */
    @Override
    public boolean isPlacePorts()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.soc.common.game.gamePhase.GamePhase#isPlayTurns()
     */
    @Override
    public boolean isPlayTurns()
    {
        return false;
    }
}
