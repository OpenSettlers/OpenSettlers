package soc.common.board.routing;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;

import soc.common.game.player.GamePlayer;

public class RouteImpl implements Route
{
    List<GraphSide> edges;
    transient UndirectedGraph<GraphPoint, GraphSide> baseGraph;
    transient GamePlayer player;

    public RouteImpl(UndirectedGraph<GraphPoint, GraphSide> baseGraph,
            Set<GraphSide> edges, GamePlayer player)
    {
        this.baseGraph = baseGraph;

        for (GraphSide side : edges)
            this.edges.add(side);

        this.player = player;
    }

    @Override
    public int getLength()
    {
        return edges.size();
    }

    @Override
    public GamePlayer getPlayer()
    {
        return player;
    }

    @Override
    public List<GraphSide> getEdgeList()
    {
        return edges;
    }

    @Override
    public GraphPoint getEndVertex()
    {
        int lastIndex = edges.size() - 1;
        GraphPoint source = baseGraph.getEdgeSource(edges.get(lastIndex));
        return baseGraph.degreeOf(source) == 1 ? source : baseGraph
                .getEdgeTarget(edges.get(lastIndex));
    }

    @Override
    public Graph<GraphPoint, GraphSide> getGraph()
    {
        return baseGraph;
    }

    @Override
    public GraphPoint getStartVertex()
    {
        GraphPoint source = baseGraph.getEdgeSource(edges.get(0));
        return baseGraph.degreeOf(source) == 1 ? source : baseGraph
                .getEdgeTarget(edges.get(0));
    }

    @Override
    public double getWeight()
    {
        return getLength();
    }

}
