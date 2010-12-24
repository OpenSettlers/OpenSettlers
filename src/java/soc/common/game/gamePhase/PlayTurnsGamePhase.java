package soc.common.game.gamePhase;

import soc.common.actions.gameAction.AbstractGameAction;
import soc.common.actions.gameAction.GameAction;
import soc.common.game.Game;
import soc.common.game.gamePhase.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;

public class PlayTurnsGamePhase extends GamePhase
{
    // Current phase of the player on turn
    private TurnPhase turnPhase = new BeforeDiceRollTurnPhase();

    /**
     * @return the turnPhase
     */
    public TurnPhase getTurnPhase()
    {
        return turnPhase;
    }

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.GamePhase#next(soc.common.game.Game)
     */
    @Override
    public GamePhase next(Game game)
    {
        return new EndedGamePhase();
    }

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.GamePhase#performAction(soc.common.actions.gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public void performAction(AbstractGameAction action, Game game)
    {
        /* TODO: port to java, first implement GameAction classes
         * 
        ClaimVictoryAction claimVictory = inGameAction as ClaimVictoryAction;
        if (claimVictory != null)
        {
            _TurnPhase.ProcessAction(inGameAction, game);
            return;
        }

        PlacementDoneAction placementDone = inGameAction as PlacementDoneAction;
        if (placementDone != null)
        {
            _TurnPhase.ProcessAction(inGameAction, game);
            return;
        }
        EndTurnAction endTurn = inGameAction as EndTurnAction;
        if (endTurn != null)
        {
            _TurnPhase = new BeforeDiceRollTurnPhase();
            endTurn.PerformTurnAction(game);
            return;
        }
        // Process the actual in the current turnphase
        TurnPhase next = _TurnPhase.ProcessAction(inGameAction, game);

        // If return phase does not match current phase, we have to switch phases and process the action
        // again
        if (_TurnPhase != next)
        {
            _TurnPhase = next; 
            _TurnPhase = _TurnPhase.ProcessAction(inGameAction, game);
        }

        // When the incoming action is not valid, check if it's valid in the next phase.
        // If so, switch phases
        if (!_TurnPhase.AllowedAction(inGameAction, game))
        {
            if (_TurnPhase.Next().AllowedAction(inGameAction, game))
            {
                _TurnPhase = _TurnPhase.Next();
            }
        }
        */
    }

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.GamePhase#start(soc.common.game.Game)
     */
    @Override
    public void start(Game game)
    {
        super.start(game);
    }

    /* (non-Javadoc)
     * @see soc.common.game.gamePhase.GamePhase#isAllowed(soc.common.actions.gameAction.GameAction)
     */
    @Override
    public boolean isAllowed(GameAction action)
    {
        return turnPhase.isAllowed(action);
    }


}
