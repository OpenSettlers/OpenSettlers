package soc.common.actions.gameAction.turnActions.standard;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.GamePhaseHasEnded;
import soc.common.actions.gameAction.turnActions.AbstractTurnAction;
import soc.common.game.Game;
import soc.common.game.gamePhase.GamePhase;
import soc.common.game.gamePhase.PlayTurnsGamePhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.internationalization.I18n;

public class ClaimVictory extends AbstractTurnAction
{
    private static final long serialVersionUID = 7906062762366374296L;

    @Override
    public boolean isAllowed(TurnPhase turnPhase)
    {
        return true;
    }

    @Override
    public boolean isAllowed(GamePhase gamePhase)
    {
        return gamePhase instanceof PlayTurnsGamePhase;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.AbstractGameAction#isValid(soc.common.game
     * .Game)
     */
    @Override
    public boolean isValid(Game game)
    {
        if (!super.isValid(game))
        {
            return false;
        }

        if (player.getVictoryPoints().getTotalPoints() < game.getBoard()
                .getBoardSettings().getVpToWin())
        {
            invalidMessage = "Player does not have enough victory points to win";
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.actions.gameAction.turnActions.AbstractTurnAction#perform(
     * soc.common.game.Game)
     */
    @Override
    public void perform(Game game)
    {
        game.getActionsQueue().enqueue(
                (GameAction) new GamePhaseHasEnded().setSender(0));

        super.perform(game);
    }

    @Override
    public String getToDoMessage()
    {
        return I18n.get().actions()
                .claimVictoryToDo(player.getUser().getName());
    }

}
