package org.soc.common.game.board.longestRoad;

import org.soc.common.game.Game;
import org.soc.common.game.board.BoardGraph;
import org.soc.common.game.board.Route;

public interface LongestRoadStrategy
{
  public Route calculateLongestRoad(Route currentLongest,
          BoardGraph boardGraph, Game game);
}
