package soc.common.board.routing.longestRoadStrategies;

import soc.common.board.routing.BoardGraph;
import soc.common.board.routing.Route;
import soc.common.game.Game;

public interface LongestRoadStrategy
{
    public Route calculateLongestRoad(Route currentLongest,
            BoardGraph boardGraph, Game game);
}
