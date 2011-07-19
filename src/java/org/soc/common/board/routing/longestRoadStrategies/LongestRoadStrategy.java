package org.soc.common.board.routing.longestRoadStrategies;

import org.soc.common.board.routing.BoardGraph;
import org.soc.common.board.routing.Route;
import org.soc.common.game.Game;

public interface LongestRoadStrategy
{
    public Route calculateLongestRoad(Route currentLongest,
            BoardGraph boardGraph, Game game);
}
