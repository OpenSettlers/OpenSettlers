package soc.common.board.routing.longestRoadStrategies;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.routing.BoardGraph;
import soc.common.board.routing.Route;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;

/*
 * Splits main graph into graphs per player using players' set of GraphSides 
 * (roads, ships, bridges). Then it defers longst road calculation to the 
 * PlayerGraph class, which represents a subgraph derived from the main 
 * graph.
 */
public class ReduceStrategy implements LongestRoadStrategy
{
    @Override
    public Route calculateLongestRoad(Route currentLongest,
            BoardGraph boardGraph, Game game)
    {
        List<Route> routes = new ArrayList<Route>();

        // Fill the set of graphs for each player
        for (GamePlayer player : game.getPlayers())
        {
            // Only consider players with at least 5 SidePieces
            if (player.getSidePieces().size() >= 5)
            {
                PlayerGraph playerGraph = new PlayerGraph(boardGraph, player);

                if (playerGraph.getLongestPath() != null)
                    routes.add(playerGraph.getLongestPath());
            }
        }

        // No routes found, return null
        if (routes.size() == 0)
            return null;

        Route result = null;

        for (Route route : routes)
            if (result == null || result.getLength() < route.getLength())
                result = route;

        return result;
    }
}
