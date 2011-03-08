package soc.common.board.routing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

import soc.common.board.layout.HexPoint;
import soc.common.board.layout.HexSide;
import soc.common.game.player.GamePlayer;

public class RouteImpl implements Route
{
    private static final long serialVersionUID = -4485164015839740985L;
    private List<GraphSide> edges = new ArrayList<GraphSide>();
    private transient BoardGraph baseGraph;
    private transient GamePlayer player;
    private GraphPoint startVertex;
    private GraphPoint endVertex;
    private int playerID;

    public RouteImpl(BoardGraph baseGraph, Set<GraphSide> edges,
            GamePlayer player)
    {
        this.baseGraph = baseGraph;
        setPlayer(player);

        for (GraphSide side : edges)
            this.edges.add(side);
    }

    public RouteImpl(BoardGraph baseGraph, List<HexPoint> longest,
            GamePlayer player)
    {
        this.baseGraph = baseGraph;
        setPlayer(player);

        for (int i = 0; i < longest.size() - 1; i++)
        {
            HexSide side = new HexSide(longest.get(i), longest.get(i + 1));
            GraphSide graphSide = baseGraph.findGraphSide(side);
            edges.add(graphSide);
        }
        startVertex = baseGraph.findGraphPoint(longest.get(0));
        endVertex = baseGraph.findGraphPoint(longest.get(longest.size() - 1));
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
        return endVertex;
    }

    @Override
    public Graph<GraphPoint, GraphSide> getGraph()
    {
        return baseGraph.getGraph();
    }

    @Override
    public GraphPoint getStartVertex()
    {
        return startVertex;
    }

    @Override
    public double getWeight()
    {
        return getLength();
    }

    @Override
    public Route setPlayer(GamePlayer player)
    {
        this.player = player;
        this.playerID = player.getUser().getId();
        return this;
    }

    @Override
    public boolean validate()
    {
        return false;
    }

    public int getPlayerID()
    {
        return playerID;
    }
}
