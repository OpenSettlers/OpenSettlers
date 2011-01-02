package soc.common.game.gamePhase;

import soc.common.actions.gameAction.GameAction;
import soc.common.actions.gameAction.GamePhaseHasEnded;
import soc.common.actions.gameAction.turnActions.standard.BuildCity;
import soc.common.actions.gameAction.turnActions.standard.BuildRoad;
import soc.common.actions.gameAction.turnActions.standard.BuildTown;
import soc.common.game.Game;

public class InitialPlacementGamePhase extends AbstractGamePhase
{

    @Override
    public void start(Game game)
    {
        // Expect each player to place town/road - town/road
        int i = 0;
        boolean back = false;

        // A loop going backward. Each index should be hit twice.
        // Example with 4 players: p1 - p2 - p3 - p4 - p4 - p3 - p2 - p1
        while (i > -1)
        {
            // If tournament starting rules are set, second building should be a
            // city
            if (back && game.getGameSettings().isTournamentStart())
            {
                // Tournament starting rules, add a city
                game.getActionsQueue().enqueue(
                        new BuildCity().setPlayer(game.getPlayers().get(i)));

            }
            else
            {
                // Normal starting rules, add two towns
                game.getActionsQueue().enqueue(
                        new BuildTown().setPlayer(game.getPlayers().get(i)));
            }

            // This action actually might be a BuildShipAction too.
            // TODO: implement this somewhere
            game.getActionsQueue().enqueue(
                    new BuildRoad().setPlayer(game.getPlayers().get(i)));

            // if the "back" flag is set, we should decrease the counter
            if (back)
            {
                i--;
            }
            else
            {
                i++;
            }

            // flip the flag when counter reaches maximum value
            // (maximum value equals amount of players)
            if (i == game.getPlayers().size())
            {
                // next loop is walked with same maximum value
                i--;

                // switch flag
                back = true;
            }
        }

        // When in tournament phase, every player may build a third road
        if (game.getGameSettings().isTournamentStart())
        {
            for (int j = game.getPlayers().size(); j > 0; j++)
            {
                game.getActionsQueue().enqueue(
                        new BuildRoad().setPlayer(game.getPlayers().get(i)));
            }
        }

        // Set end of phase
        game.getActionsQueue().enqueue(new GamePhaseHasEnded().setSender(0));
    }

    @Override
    public void performAction(GameAction gameAction, Game game)
    {
        gameAction.perform(game);
    }

    @Override
    public GamePhase next(Game game)
    {
        return new PlayTurnsGamePhase();
    }

}
