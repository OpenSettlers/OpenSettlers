package soc.common.game.gamePhase;

import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.GamePhaseHasEnded;
import soc.common.actions.gameAction.StartingPlayerDetermined;
import soc.common.actions.gameAction.turnActions.RolledSame;
import soc.common.actions.gameAction.turnActions.standard.RollDice;
import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.game.TurnImpl;
import soc.common.game.player.GamePlayer;

public class DetermineFirstPlayerGamePhase extends AbstractGamePhase
{
    @Override
    public void start(Game game)
    {
        // expect each player to roll at least once (first phase: everyone rolls
        // once)
        for (GamePlayer player : game.getPlayers())
        {
            game.getActionsQueue().enqueue(new RollDice().setPlayer(player));
        }
        game.getActionsQueue().enqueue(
                (GameAction) new GamePhaseHasEnded().setSender(0));
    }

    private int getHighRoll(List<RollDice> rolledDices)
    {
        int result = 2;

        for (RollDice rollDice : rolledDices)
        {
            if (rollDice.getDice().getDiceTotal() > result)
                result = rollDice.getDice().getDiceTotal();
        }

        return result;
    }

    @Override
    public void performAction(GameAction action, Game game)
    {
        action.perform(game);

        if (action instanceof RollDice)
        {
            RollDice rollDice = (RollDice) action;
            // Check if a phase has ended. If the queue is empty, every player
            // has rolled the dice.
            if (game.getActionsQueue().size() == 1)
            {
                // Make a list of rolls in this round
                List<RollDice> rolledDices = game.getGameLog()
                        .getCurrentRoundRolls(game);

                // highroll dice number
                int highRoll = getHighRoll(rolledDices);

                // When starting player is not determined yet, repeat dice roll
                // between winners until
                // winner is determined
                GamePlayer gameStarter = game.getGameLog()
                        .firstPlayerIsDetermined(game, highRoll);
                if (gameStarter != null)
                {
                    // We have a starting player
                    game.getActionsQueue().enqueuePriority(
                            (GameAction) new StartingPlayerDetermined()
                            // winning dice
                                    .setDiceRoll(highRoll)
                                    // The starter of the
                                    // placement/portplacement/turnactionsgamephase
                                    .setGameStarter(gameStarter)
                                    // Server will send this message
                                    .setSender(0));
                }
                else
                {
                    // Enqueue each high roller
                    for (RollDice sameRoll : rolledDices)
                    {
                        if (sameRoll.getDice().getDiceTotal() == highRoll)
                        {
                            game.getActionsQueue().enqueuePriority(
                                    new RollDice().setPlayer(sameRoll
                                            .getPlayer()));
                        }
                    }
                    // Starting player is not determined. Notify players and
                    // update Game object
                    game.getActionsQueue().enqueuePriority(
                            (GameAction) new RolledSame()
                            // Pass on the highest diceroll
                                    .setHighRoll(highRoll)
                                    // Server says dice rolled the same
                                    .setSender(0));
                }
            }

        }
        if (game.getActionsQueue().peekAction() instanceof RollDice)
        {
            game.advanceTurn();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.game.gamePhase.GamePhase#next(soc.common.game.Game)
     */
    @Override
    public GamePhase next(Game game)
    {
        return new InitialPlacementGamePhase();
        //
        // // Determine if we should skip placing ports
        // // randomports are assigned at start using the port lists on each
        // // territory.
        // // The remaining ports are placed in the placement phase
        // List<Port> allPorts = new ArrayList<Port>();
        // for (Territory t : game.getBoard().getTerritories())
        // {
        // for (Port p : t.getPorts())
        // {
        // allPorts.add(p);
        // }
        // }
        // if (allPorts.size() == 0)
        // {
        // // We do not have any ports to set, skip to placement phase
        // return new InitialPlacementGamePhase();
        // }
        // else
        // {
        // // players should place ports
        // return new InitialPlacementGamePhase();
        // }
    }

    @Override
    public String getMessage()
    {
        // TODO: fix message
        return "Determine game starter";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * soc.common.game.gamePhase.AbstractGamePhase#nextTurn(soc.common.game.
     * Game)
     */
    @Override
    public Turn nextTurn(Game game)
    {
        if (game.getStartPlayer() != null)
        {
            return new TurnImpl().setPlayer(game.getStartPlayer());
        }
        return super.nextTurn(game);
    }

}
