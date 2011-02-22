package soc.common.game.gamePhase;

import java.util.ArrayList;
import java.util.List;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.GamePhaseHasEnded;
import soc.common.actions.gameAction.standard.RollDice;
import soc.common.actions.gameAction.turns.RolledSame;
import soc.common.actions.gameAction.turns.StartingPlayerDetermined;
import soc.common.game.Game;
import soc.common.game.Turn;
import soc.common.game.TurnImpl;
import soc.common.game.player.GamePlayer;
import soc.gwtClient.game.widgetsBitmap.status.GamePhaseStatusWidget;
import soc.gwtClient.game.widgetsInterface.main.GamePhaseStatusWidgetFactory;

public class DetermineFirstPlayerGamePhase extends AbstractGamePhase
{
    private static final long serialVersionUID = 6144523951649998903L;

    @Override
    public void start(Game game)
    {
        // expect each player to roll at least once (first phase: everyone rolls
        // once)
        for (GamePlayer player : game.getPlayers())
        {
            game.getActionsQueue().enqueue(new RollDice().setPlayer(player),
                    true);
        }

        game.getActionsQueue().enqueue(
                (GameAction) new GamePhaseHasEnded().setSender(0), true);
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
                                    .setSender(0), true);
                }
                else
                {
                    // Starting player is not determined.

                    // Prepare list of same rolling players for RolledSame
                    // instance
                    List<Integer> sameRollingPlayers = new ArrayList<Integer>();

                    // Enqueue each high roller
                    for (RollDice sameRoll : rolledDices)
                    {
                        if (sameRoll.getDice().getDiceTotal() == highRoll)
                        {
                            game.getActionsQueue().enqueuePriority(
                                    new RollDice().setPlayer(sameRoll
                                            .getPlayer()), true);
                            sameRollingPlayers.add(sameRoll.getPlayer()
                                    .getUser().getId());
                        }
                    }

                    // First execute a RolledSame action
                    game.getActionsQueue().enqueuePriority(
                            (GameAction) new RolledSame()
                                    // Pass on the highest diceroll
                                    .setHighRoll(highRoll)
                                    .setSameRollingPlayers(sameRollingPlayers)
                                    // Server says dice rolled the same
                                    .setSender(0), true);
                }
            }

        }
        if (game.getActionsQueue().peek() instanceof RollDice)
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
            return new TurnImpl(game.getStartPlayer());
        }
        return super.nextTurn(game);
    }

    @Override
    public boolean isDetermineFirstPlayer()
    {
        return true;
    }

    @Override
    public GamePhaseStatusWidget createGamePhaseStatusWidget(
            GamePhaseStatusWidgetFactory factory)
    {
        return factory.createDetermineFirstPlayerStatusWidget(this);
    }
}
