package org.soc.common.game.board.longestRoad;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.Game;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.board.BoardGraph;
import org.soc.common.game.board.Route;

/*
 * Splits main graph into graphs per player using players' set of GraphSides
 * (roads, ships, bridges). Then it defers longst road calculation to the
 * PlayerGraph class, which represents a subgraph derived from the main
 * graph.
 * 
 * The idea is to support ListenableSubGraph eventually, such that LR checking
 * can be minimized as much as possible.
 */
public class ReduceStrategy implements LongestRoadStrategy {
  @Override public Route calculateLongestRoad(Route currentLongest,
          BoardGraph boardGraph, Game game) {
    List<Route> routes = new ArrayList<Route>();
    // Fill the set of graphs for each player
    for (GamePlayer player : game.players())
      // Only consider players with at least 5 SidePieces
      if (player.sidePieces().size() >= 5) {
        PlayerGraph playerGraph = new PlayerGraph(boardGraph, player);
        if (playerGraph.getLongestPath() != null)
          routes.add(playerGraph.getLongestPath());
      }
    // No routes found, return null
    if (routes.size() == 0)
      return null;
    Route result = null;
    for (Route route : routes)
      if (result == null || result.length() < route.length())
        result = route;
    return result;
  }
}
