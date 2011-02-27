package soc.common.game.gamePhase.turnPhase;

import soc.common.actions.gameAction.standard.BuildCity;
import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.game.Game;
import soc.common.game.gamePhase.InitialPlacementStrategy;

public class TournamentStartPlacementStrategy implements
        InitialPlacementStrategy
{

    @Override
    public void executeStrategy(Game game)
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
            if (back)
            {
                // Tournament starting rules, add a city
                game.getActionsQueue().enqueue(
                        new BuildCity().setPlayer(game.getPlayers().get(i)),
                        true);
            }
            else
            {
                // Normal starting rules, add two towns
                game.getActionsQueue().enqueue(
                        new BuildTown().setPlayer(game.getPlayers().get(i)),
                        true);
            }

            // This action actually might be a BuildShipAction too.
            // TODO: implement this somewhere
            game.getActionsQueue().enqueue(
                    new BuildRoad().setPlayer(game.getPlayers().get(i)), true);

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

            addThirdRoads(game);
        }
    }

    private void addThirdRoads(Game game)
    {
        for (int j = game.getPlayers().size(); j > 0; j++)
            game.getActionsQueue().enqueue(
                    new BuildRoad().setPlayer(game.getPlayers().get(j)), true);
    }
}
