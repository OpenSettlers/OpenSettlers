package soc.common.game.gamePhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.TradeAction;
import soc.common.actions.gameAction.TurnPhaseEnded;
import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.game.TurnImpl;
import soc.common.game.gamePhase.turnPhase.BeforeDiceRollTurnPhase;
import soc.common.game.gamePhase.turnPhase.TradingTurnPhase;
import soc.common.game.gamePhase.turnPhase.TurnPhase;
import soc.common.game.player.GamePlayer;

public class PlayTurnsGamePhase extends AbstractGamePhase
{
    private static final long serialVersionUID = 7286330376523360257L;
    // Current phase of the player on turn
    private TurnPhase turnPhase = new BeforeDiceRollTurnPhase();

    /**
     * @return the turnPhase
     */
    public TurnPhase getTurnPhase()
    {
        return turnPhase;
    }

    public void setTurnPhase(TurnPhase newPhase)
    {
        turnPhase = newPhase;
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
        if (turnPhase instanceof TradingTurnPhase
                && !(action instanceof TradeAction)
                && !(action instanceof TurnPhaseEnded))
        {
            // Non-trading action detected in trading turn phase, advance phase
            TurnPhaseEnded endedTradePhase = (TurnPhaseEnded) new TurnPhaseEnded()
                    .setSender(0);
            game.performAction(endedTradePhase);
        }

        action.perform(game);
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
            // First turn in PlayTurnsGamePhase has always ID=1
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

            // Grab the player next on turn
            GamePlayer newPlayerOnTurn = game.getPlayers().get(nextPlayerIndex);

            // Create a new turn with increased ID numer
            newTurn = new TurnImpl().setPlayer(newPlayerOnTurn).setID(
                    game.getCurrentTurn().getID() + 1);
        }

        return newTurn;
    }
}
