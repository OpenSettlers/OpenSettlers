package soc.common.game.gamePhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.turnActions.TurnAction;
import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.game.TurnImpl;
import soc.common.game.gamePhase.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;

public class PlayTurnsGamePhase extends AbstractGamePhase
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

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.GamePhase#next(soc.common.game.Game)
     */
    @Override
    public GamePhase next(Game game)
    {
        return new EndedGamePhase();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.GamePhase#performAction(soc.common.actions.
     * gameAction.GameAction, soc.common.game.Game)
     */
    @Override
    public void performAction(GameAction action, Game game)
    {
        action.perform(game);

        if (action instanceof TurnAction)
        {
            TurnAction turnAction = (TurnAction) action;
            turnAction.setTurnPhase(turnPhase);
        }
        /*
         * TODO: port to java, first implement GameAction classes
         * 
         * ClaimVictoryAction claimVictory = inGameAction as ClaimVictoryAction;
         * if (claimVictory != null) { _TurnPhase.ProcessAction(inGameAction,
         * game); return; }
         * 
         * PlacementDoneAction placementDone = inGameAction as
         * PlacementDoneAction; if (placementDone != null) {
         * _TurnPhase.ProcessAction(inGameAction, game); return; } EndTurnAction
         * endTurn = inGameAction as EndTurnAction; if (endTurn != null) {
         * _TurnPhase = new BeforeDiceRollTurnPhase();
         * endTurn.PerformTurnAction(game); return; } // Process the actual in
         * the current turnphase TurnPhase next =
         * _TurnPhase.ProcessAction(inGameAction, game);
         * 
         * // If return phase does not match current phase, we have to switch
         * phases and process the action // again if (_TurnPhase != next) {
         * _TurnPhase = next; _TurnPhase =
         * _TurnPhase.ProcessAction(inGameAction, game); }
         * 
         * // When the incoming action is not valid, check if it's valid in the
         * next phase. // If so, switch phases if
         * (!_TurnPhase.AllowedAction(inGameAction, game)) { if
         * (_TurnPhase.Next().AllowedAction(inGameAction, game)) { _TurnPhase =
         * _TurnPhase.Next(); } }
         */
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.GamePhase#start(soc.common.game.Game)
     */
    @Override
    public void start(Game game)
    {
        game.advanceTurn();
        super.start(game);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.GamePhase#isAllowed(soc.common.actions.gameAction
     * .GameAction)
     */
    @Override
    public boolean isAllowed(GameAction action)
    {
        return turnPhase.isAllowed(action);
    }

    @Override
    public String getMessage()
    {
        // TODO fix message
        return turnPhase.getMessage();
    }

    @Override
    public Turn nextTurn(Game game)
    {
        Turn newTurn = null;
        if (game.getCurrentTurn().getID() == 0)
        {
            newTurn = new TurnImpl().setPlayer(game.getPlayers().get(0)).setID(
                    1);
        }
        else
        {
            // Determine index of next player
            int nextPlayerIndex = game.getPlayers().indexOf(
                    game.getCurrentTurn().getPlayer()) + 1;
            if (nextPlayerIndex == game.getPlayers().size())
            {
                nextPlayerIndex = 0;
            }

            GamePlayer newPlayerOnTurn = game.getPlayers().get(nextPlayerIndex);

            // Create a new turn
            newTurn = new TurnImpl().setPlayer(newPlayerOnTurn).setID(
                    game.getCurrentTurn().getID() + 1);

        }
        return newTurn;
    }
}
