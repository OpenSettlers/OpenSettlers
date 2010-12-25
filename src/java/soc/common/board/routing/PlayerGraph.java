package soc.common.board.routing;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.UndirectedSubgraph;

public class PlayerGraph extends UndirectedSubgraph<GraphPoint, GraphSide>
{
    private static final long serialVersionUID = -7701781640158460743L;
    private Set<GraphPoint> ends = new HashSet<GraphPoint>();
    private Set<GraphPoint> splits = new HashSet<GraphPoint>();

    public PlayerGraph(UndirectedGraph<GraphPoint, GraphSide> base,
            Set<GraphPoint> vertexSubset, Set<GraphSide> edgeSubset)
    {
        super(base, vertexSubset, edgeSubset);

        // Initiate the sets of splits and ends
        for (GraphPoint point : vertexSet())
        {
            int degree = degreeOf(point);
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
