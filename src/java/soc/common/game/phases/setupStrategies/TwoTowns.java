package soc.common.game.phases.setupStrategies;

/*
 * Places a town, road, town and road in the initial placement phase.
 */
import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;

public class TwoTowns implements InitialPlacementStrategy
{
    private static final long serialVersionUID = 8769489119920430608L;

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
            GamePlayer player = game.getPlayers().get(i);

            // Normal starting rules, add two towns
            game.getActionsQueue().enqueue(new BuildTown().setPlayer(player),
                            true);

            // This action actually might be a BuildShipAction too.
            // TODO: implement this somewhere
            game.getActionsQueue().enqueue(new BuildRoad().setPlayer(player),
                            true);

            // if the "back" flag is set, we should decrease the counter
            if (back)
                i--;
            else
                i++;

            // flip the flag when counter reaches maximum value
            // (maximum value equals amount of players)
            if (i == game.getPlayers().size())
            {
                // next walk is performed with same maximum value
                i--;

                // switch flag
                back = true;
            }
        }
    }

}
