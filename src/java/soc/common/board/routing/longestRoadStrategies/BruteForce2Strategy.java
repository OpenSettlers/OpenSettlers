package soc.common.board.routing.longestRoadStrategies;

import java.util.ArrayList;
import java.util.List;

import soc.common.board.HexSide;
import soc.common.board.pieces.abstractPieces.SidePiece;
import soc.common.board.routing.BoardGraph;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.Route;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;

public class BruteForce2Strategy implements LongestRoadStrategy
{
    private BoardGraph boardGraph;

    @Override
    public Route calculateLongestRoad(Route currentLongest,
            BoardGraph boardGraph, Game game)
    {
        return null;
    }

    private GraphPoint getSource(HexSide side)
    {
        return boardGraph.getGraph().getEdgeSource(
                boardGraph.findGraphSide(side));
    }

    private GraphPoint getTarget(HexSide side)
    {
        return boardGraph.getGraph().getEdgeTarget(
                boardGraph.findGraphSide(side));
    }

    private Route getLongest(GamePlayer player)
    {
        List<List<GraphPoint>> routesWithoutEnd = new ArrayList<List<GraphPoint>>();
        for (SidePiece piece : player.getSidePieces())
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
