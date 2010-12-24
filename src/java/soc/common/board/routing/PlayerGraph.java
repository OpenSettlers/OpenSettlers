package soc.common.board.routing;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.UndirectedSubgraph;

public class PlayerGraph extends UndirectedSubgraph<IGraphPoint, IGraphSide>
{
    private Set<IGraphPoint> ends = new HashSet<IGraphPoint>();
    private Set<IGraphPoint> splits = new HashSet<IGraphPoint>();
    
    public PlayerGraph(UndirectedGraph<IGraphPoint, IGraphSide> base,
            Set<IGraphPoint> vertexSubset, Set<IGraphSide> edgeSubset)
    {
        super(base, vertexSubset, edgeSubset);
        
        // Initiate the sets of splits and ends
        for (IGraphPoint point : vertexSet())
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
