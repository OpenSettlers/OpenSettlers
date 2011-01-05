package soc.common.board.routing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import soc.common.game.player.GamePlayer;

public class PlayerGraph
{
    // Set of GraphPoints connected to only one GraphSide
    private List<GraphPoint> ends = new ArrayList<GraphPoint>();

    // Set of GraphPoints connected to three GraphSides
    private List<GraphPoint> splits = new ArrayList<GraphPoint>();

    private UndirectedGraph<GraphPoint, GraphSide> graph = new SimpleGraph<GraphPoint, GraphSide>(
            GraphSide.class);
    UndirectedGraph<GraphPoint, GraphSide> base;
    private Route longestPath;
    private GamePlayer player;

    public PlayerGraph(UndirectedGraph<GraphPoint, GraphSide> base,
            GamePlayer player)
    {
        this.base = base;

        constructEndsAndSplits();

        // If there are no splits, all the edges belong to the longest path
        if (splits.size() == 0)
        {
            longestPath = new RouteImpl(base, base.edgeSet(), player);
        }
        else
        // We have splits. Create weighted graph where every split is a new
        // node, with edge weight
        // representing amount of edges between a split
        {
            calculatLongestPath();
        }

    }

    private void calculatLongestPath()
    {
        // Create a set of paths (set of edges) for every split
        List<Set<GraphSide>> paths = new ArrayList<Set<GraphSide>>();
        for (GraphPoint split : splits)
        {
            Set<GraphSide> neighbourSides = base.edgesOf(split);
            for (GraphSide side : neighbourSides)
            {
                Set<GraphSide> path = new HashSet<GraphSide>();
                path.add(side);
            }

        }
    }

    private void constructRoad(GraphPoint start)
    {
    }

    private void constructEndsAndSplits()
    {
        // Construct the sets of splits and ends
        for (GraphPoint point : base.vertexSet())
        {
            int degree = base.degreeOf(point);
            if (degree == 1)
            {
                ends.add(point);
            }
            if (degree == 3)
            {
                splits.add(point);
            }
        }
    }
}
