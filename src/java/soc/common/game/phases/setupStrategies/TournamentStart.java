package soc.common.game.phases.setupStrategies;

import soc.common.actions.gameAction.standard.BuildCity;
import soc.common.actions.gameAction.standard.BuildRoad;
import soc.common.actions.gameAction.standard.BuildTown;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;

/*
 * Places a town, road, city, road and a third road in the initial placement
 * phase
 */
public class TournamentStart implements InitialPlacementStrategy
{
    private static final long serialVersionUID = 783452797828320990L;

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
            // Second building should be a city
            if (back)
                // Tournament starting rules, add a city
                game.getActionsQueue().enqueue(
                                new BuildCity().setPlayer(player), true);
            else
                // First building to build is a town
                game.getActionsQueue().enqueue(
                                new BuildTown().setPlayer(player), true);

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
                // next loop is walked with same maximum value
                i--;

                // switch flag
                back = true;
            }

            addThirdRoads(game);
        }
    }

    /*
     * Adds a third road to place for every player, starting with the game
     * starter
     */
    private void addThirdRoads(Game game)
    {
        // Loop players by index
        for (GamePlayer player : game.getPlayers())
            // Enqueue a BuildRoad action for each player
            game.getActionsQueue().enqueue(new BuildRoad().setPlayer(player),
                            true);
    }
}
