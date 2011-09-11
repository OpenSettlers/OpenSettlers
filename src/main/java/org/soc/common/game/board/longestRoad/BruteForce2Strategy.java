package org.soc.common.game.board.longestRoad;

import java.util.ArrayList;
import java.util.List;

import org.soc.common.game.Game;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.board.BoardGraph;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.HasSide;
import org.soc.common.game.board.HexSide;
import org.soc.common.game.board.Route;

public class BruteForce2Strategy implements LongestRoadStrategy
{
  private BoardGraph boardGraph;

  @Override public Route calculateLongestRoad(Route currentLongest,
          BoardGraph boardGraph, Game game)
  {
    return null;
  }
  private GraphPoint getSource(HexSide side)
  {
    return boardGraph.graph().getEdgeSource(
            boardGraph.findGraphSide(side));
  }
  private GraphPoint getTarget(HexSide side)
  {
    return boardGraph.graph().getEdgeTarget(
            boardGraph.findGraphSide(side));
  }
  private Route getLongest(GamePlayer player)
  {
    List<List<GraphPoint>> routesWithoutEnd = new ArrayList<List<GraphPoint>>();
    for (HasSide piece : player.sidePieces())
    {
      List<GraphPoint> newRoute = new ArrayList<GraphPoint>();
      List<GraphPoint> newRoute2 = new ArrayList<GraphPoint>();
      // each side creates 2 possibilities, with opposite directions to
      // go to.
      newRoute.add(getTarget(piece.getSide()));
      newRoute.add(getSource(piece.getSide()));
      newRoute2.add(getTarget(piece.getSide()));
      newRoute2.add(getSource(piece.getSide()));
      routesWithoutEnd.add(newRoute);
      routesWithoutEnd.add(newRoute2);
    }
    return null;
  }
}
