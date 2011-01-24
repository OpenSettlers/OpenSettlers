package soc.common.board.routing;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;

import soc.common.game.player.GamePlayer;

public class PossibleRoute implements Route
{
    private List<GraphSide> sides = new ArrayList<GraphSide>();
    private List<GraphPoint> points = new ArrayList<GraphPoint>();
    private BoardGraph boardGraph;
    private GamePlayer player;

    public PossibleRoute(BoardGraph boardGraph, GraphPoint start,
            GraphPoint second)
    {
        this.boardGraph = boardGraph;
        points.add(start);
        points.add(second);
        sides.add(getSide(start, second));
    }

    public GamePlayer getPlayer()
    {
        return player;
    }

    public PossibleRoute setPlayer(GamePlayer player)
    {
        this.player = player;
        return this;
    }

    public PossibleRoute(BoardGraph boardGraph)
    {
        this.boardGraph = boardGraph;
    }

    @Override
    public List<GraphSide> getEdgeList()
    {
        return sides;
    }

    @Override
    public GraphPoint getEndVertex()
    {
        return points.get(points.size() - 1);
    }

    public GraphPoint getBeforeEndVertex()
    {
        return points.get(points.size() - 2);
    }

    @Override
    public Graph<GraphPoint, GraphSide> getGraph()
    {
        return boardGraph.getGraph();
    }

    @Override
    public GraphPoint getStartVertex()
    {
        return points.get(0);
    }

    @Override
    public double getWeight()
    {
        return sides.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see soc.common.board.routing.Route#getLength()
     */
    @Override
    public int getLength()
    {
        return sides.size();
    }

    public void addPoint(GraphPoint point)
    {
        GraphPoint source = getEndVertex();
        points.add(point);
        sides.add(getSide(source, point));
    }

    public boolean contains(GraphPoint source, GraphPoint target)
    {
        return sides.contains(getSide(source, target));
    }

    public PossibleRoute copy()
    {
        PossibleRoute copy = new PossibleRoute(boardGraph);

        for (GraphPoint point : points)
            copy.points.add(point);
        for (GraphSide side : sides)
            copy.sides.add(side);

        return copy;
    }

    private GraphSide getSide(GraphPoint source, GraphPoint target)
    {
        return boardGraph.getGraph().getEdge(source, target);
    }

    @Override
    public boolean validate()
    {
        for (GraphPoint point : points)
            if (point.getPlayer() != null && !point.getPlayer().equals(player))
                return false;

        return true;
    }
}
