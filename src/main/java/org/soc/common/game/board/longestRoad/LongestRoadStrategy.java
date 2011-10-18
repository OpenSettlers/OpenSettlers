package org.soc.common.game.board.longestRoad;

import org.soc.common.game.Game;
import org.soc.common.game.board.GameBoard;
import org.soc.common.game.board.Route;

public interface LongestRoadStrategy
{
  public Route calculateLongestRoad(Route currentLongest,
          GameBoard boardGraph, Game game);
}
