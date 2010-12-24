package soc.common.board.routing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.Subgraph;
import org.jgrapht.graph.UndirectedSubgraph;

import soc.common.board.Board;
import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.hexes.AbstractHex;
import soc.common.board.pieces.City;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.game.Player;

/*
 * Implementation of a board graph to support longest route calculation and bots
 * with strategy calculation.
 * 
 * Designed such that it should easily support variants. A couple things to note:
 * - Knights may split a road like a town
 * - Volcano's may split a road, when a town blows up and the town connected a ship 
 *   and a road. 
 * 
 * In graph theory, a dot (HexPoint) is called a vertex. A line (HexSide) is called
 * an edge.  
 */
public class BoardGraph
{
    public ListenableUndirectedGraph<IGraphPoint, IGraphSide> graph = 
        new ListenableUndirectedGraph<IGraphPoint, IGraphSide>(IGraphSide.class);
    
    /*
     * Create the graph containing of all the empty vertices and edges
     */
    public void buildGraph(Board board)
    {
        //Iterate over all hexes in the board 
        for (AbstractHex hex : board.getHexes())
        {
            // Iterate over all 6 possible HexPoint of a hex
            for (HexPoint point : hex.getLocation().getNeighbourHexPoints())
            {
                IGraphPoint graphPoint = getPointFromSet(point);
                
                if (graphPoint == null && //no need for duplicates 
                    // point should be actually on the board, not on the edges
                    point.fallsWithinBoardBounds(board.getWidth(), board.getHeight()))
                {
                    graphPoint = new GraphPoint()
                        .setPoint(point);
                    
                    graph.addVertex(graphPoint);
                }
            }
        }
        
        //Now we have all the HexPoints in place. Now add the sides.
        
        // Iterate over all points in the graph
        for (IGraphPoint point : graph.vertexSet())
        {
            // Each point has three sides. Add them when they fall. within the bounds of the board
            for (HexSide side : point.getPoint().getNeighbourSides())
            {
                // Side should be on the inner map space
                if (side.fallsWithinBoardBounds(board.getWidth(), board.getHeight()) &&
                    // undirected graph, only need to add the edge once
                    !graph.edgeSet().contains(side))
                {
                    // Get the other point
                    HexPoint otherPoint = side.getOtherPoint(point.getPoint());

                    // instantiate the other IGraphPoint
                    IGraphPoint otherGraphPoint = new GraphPoint()
                        .setPoint(otherPoint);
                    
                    // Find existing side 
                    IGraphSide graphSide = getSideFromSet(side);
                    
                    if (graphSide == null)
                    {
                        graphSide = new GraphSide()
                            .setSide(side);
                    }
                    
                    graph.addEdge(otherGraphPoint, point, graphSide);
                }
            }
        }
    }
    
    private IGraphSide getSideFromSet(HexSide side)
    {
        for (IGraphSide graphSide : graph.edgeSet())
        {
            if (graphSide.getSide().equals(side))
            {
                return graphSide;
            }
        }
        
        return null;
    }
    
    private IGraphPoint getPointFromSet(HexPoint point)
    {
        for (IGraphPoint graphPoint : graph.vertexSet())
        {
            if (graphPoint.getPoint().equals(point))
            {
                return graphPoint;
            }
        }
        
        return null;
    }
    
    public void addTown(Town town)
    {
        IGraphPoint point = getPointFromSet(town.getPoint());
        
        point.setPlayerPiece(town);
        
        // Mark all neighbour points as non-buildable
        
        // Iterate over all possible neighbours
        for (IGraphSide side : graph.edgesOf(point))
        {
            IGraphPoint otherPoint = getOtherPoint(point, side);
            otherPoint.setTownBuildable(false);
        }
        
        // Set the originating point as non-buildable
        point.setTownBuildable(false);
    }
    
    /*
     * Replaces town of target GraphPoint to a city
     */
    public void addCity(City city)
    {
        IGraphPoint point = getPointFromSet(city.getPoint());
        
        point.setPlayerPiece(city);
    }

    public void addRoad(Road road)
    {
        IGraphSide side = getSideFromSet(road.getSide());
        
        side.setPlayerPiece(road);
        side.setBuildable(false);
    }
    
    /*
     * Returns the second point of the given side, exlcuding the given point
     */
    private IGraphPoint getOtherPoint(IGraphPoint ignore, IGraphSide side)
    {
        IGraphPoint otherPoint = null;
        otherPoint=graph.getEdgeSource(side);
        if (ignore.equals(otherPoint))
        {
            return graph.getEdgeTarget(side);
        }
        else
        {
            return otherPoint;
        }
    }
    
    public UndirectedSubgraph<IGraphPoint, IGraphSide> getPlayerGraph(Player player)
    {
        UndirectedSubgraph<IGraphPoint, IGraphSide> result;
        
        // Create a set of sides of the player
        Set<IGraphSide> subSet = new HashSet<IGraphSide>();
        for (IGraphSide side : graph.edgeSet())
        {
            if (side.getPlayer() == player)
            {
                subSet.add(side);
            }
        }
        
        // Create subgraph, with players' sides and all the points of the main graph
        result = new UndirectedSubgraph<IGraphPoint, IGraphSide>
            (graph, graph.vertexSet(), subSet);
        
        // Keep track of the points we need to remove
        List<IGraphPoint> pointsToRemove = new ArrayList<IGraphPoint>();
        
        // Keep track of the points need splitting
        List<IGraphPoint> pointsToSplit = new ArrayList<IGraphPoint>();
        
        // Split points when there is a piece of an opponent,
        // remove points when there are no roads/ships/bridges connecting
        for (IGraphPoint point : result.vertexSet())
        {
            // Mark points without roads/ships/bridges connecting to them for removal
            if (result.degreeOf(point) == 0)
            {
                pointsToRemove.add(point);
                continue;
            }
            
            // Split the chain when HexPoint is in use by another player
            if (point.getPlayer() != null && point.getPlayer() != player)
            {
                pointsToSplit.add(point);
            }
        }
        
        // Remove marked points from subgraph
        for (IGraphPoint pointToRemove : pointsToRemove)
        {
            result.vertexSet().remove(pointToRemove);
        }
        
        // Split marked points in subgraph
        for (IGraphPoint pointToSplit : pointsToSplit)
        {
            // Get rid of the old graphpoint containing the city/town/knight of opponent
            result.removeVertex(pointToSplit);

            for (IGraphSide side : result.edgesOf(pointToSplit))
            {
                // Create the two new SplittedPoints
                if (side.getPlayer().equals(player))
                {
                    // Create the splitted point
                    BlockedEndPoint splittedPoint = new BlockedEndPoint(pointToSplit.getPoint());
                    
                    // Add the new splitted point to subgraph
                    result.addVertex(splittedPoint);
                    
                    // Add removed edge to subgraph, along with it's new splitted point
                    result.addEdge(getOtherPoint(splittedPoint, side), splittedPoint, side);
                }
            }
        }
        
        return result;
    }
    
    public Route getLongestRoute(List<Player> players)
    {
        //Route longestRoute = new Route();
        
        Set<UndirectedSubgraph<IGraphPoint, IGraphSide>> playerGraphs = 
            new HashSet<UndirectedSubgraph<IGraphPoint, IGraphSide>>();
        
        // Fill the set of graphs for each player
        for (Player player : players)
        {
            // Only consider players with at least 5 SidePieces
            if (player.getBuildPieces().getSidePieces().size() >= 5)
            {
                // Get the subgraph from the main graph
                UndirectedSubgraph<IGraphPoint, IGraphSide> subGraph = getPlayerGraph(player);
                
                // Add the graph when amount of vertices is equal or more then 5. No need
                // to check longest road if there are not sufficient items to meet
                // the requirement of at least 5 roads/ships/bridges per path
                if (subGraph.vertexSet().size() >= 5)
                {
                    playerGraphs.add(subGraph);
                }
            }
        }
        
        // Graphs may have two properties:
        // 1. Graphs with splits
        //      Amount of edges represents the length of the road
        // 2. No points with degree = 1, any point in the graph has a degree of 2 or 3. 
        //      When no points of degree = 3 (splits) exist, amount of edges represents length of the road
        //      When points of degree = 3 exist, compute longest path
        
        return null;
    }
    
    /*
     * Returns a set of PlayerGraphs derived from a graph containing all edges and vertices of a player
     */
    private Set<PlayerGraph> getSeperateSubGraphs(UndirectedSubgraph<IGraphPoint, IGraphSide> subGraph)
    {
        // List of subsets where we create new subsubgraphs from
        Set<Set<IGraphSide>> subSets = new HashSet<Set<IGraphSide>>();

        // Check every point and put it into a subgraph
        for (IGraphSide side : subGraph.edgeSet())
        {
            boolean isVisited = false;
            for (Set<IGraphSide> set : subSets)
            {
                if (set.contains(side))
                    isVisited=true;
            }
            if (!isVisited)
            {
                
            }
            Set<IGraphSide> subSet = new HashSet<IGraphSide>();
            //subSet = getConnectedSides(subGraph, subSet, IGraphSide side);
        }
        
        return null;
    }
    
    private Set<IGraphSide> getConnectedSides(
            UndirectedSubgraph<IGraphPoint, IGraphSide> subGraph,
            Set<IGraphSide> connectedSides,
            Set<IGraphPoint> processedPoints,
            IGraphPoint point)
    {
        // Add each found edge to the list of connected edges, when not already present
        for (IGraphSide side : subGraph.edgeSet())
        {
            // Add the side when not present
            if (!connectedSides.contains(side))
                connectedSides.add(side);
        }
        
        // Mark this point as "processed": we have added it's edges
        processedPoints.add(point);
        
        // Repeat the process for each neighbour point
        List<IGraphPoint> neighbours = Graphs.neighborListOf(subGraph, point);
        
        for (IGraphPoint neighbour : neighbours)
        {
            if (!processedPoints.contains(neighbour))
                getConnectedSides(subGraph, connectedSides, processedPoints, neighbour);
        }
        
        return connectedSides;
    }
    
    public Set<IGraphPoint> getTownCandidatesTurnPhase(Player forPlayer)
    {
        Set<IGraphPoint> result = new HashSet<IGraphPoint>();
        
        return result;
    }
    
    /*
     * Returns a set of possibilties for a player to build on,
     * for his first town
     */
    public Set<IGraphPoint> getTownCandidatesFirstTown(Player forPlayer)
    {
        Set<IGraphPoint> result = new HashSet<IGraphPoint>();
        
        for (IGraphPoint possibleCandidate : graph.vertexSet())
        {
            //TODO: implement
        }
        
        return result;
    }
    
    /*
     * Returns a set of possible locations for a player's second town
     */
    public Set<IGraphPoint> getTownCandidatesSecondTown(Player forPlayer)
    {
        Set<IGraphPoint> result = new HashSet<IGraphPoint>();
        
        for (IGraphPoint possibleCandidate : graph.vertexSet())
        {
            //TODO: implement
        }
        
        return result;
    }
    
    /*
     * Returns a list of possible locations to build his first town on
     */
    public Set<IGraphSide> getRoadCandidatesFirstTown(Player player)
    {
        Set<IGraphSide> result = new HashSet<IGraphSide>();
        
        //TODO: implement
        
        return result;
    }
    
    /*
     * 
     */
    public Set<IGraphSide> getRoadCandidatesSecondTown(Player player)
    {
        Set<IGraphSide> result = new HashSet<IGraphSide>();
        
        // TODO: implement
        
        return result;        
    }
    
    public Set<IGraphSide> getRoadCandidates(Player player)
    {
        Set<IGraphSide> result = new HashSet<IGraphSide>();
        
        // TODO: implement
        
        return result;     
    }
    
    public Set<IGraphSide> getShipBuildCandidates(Player player)
    {
        Set<IGraphSide> result = new HashSet<IGraphSide>();
        
        // TODO: implement
        
        return result;     
    }
    
    public Set<IGraphSide> getShipMoveCandidates(Player player)
    {
        Set<IGraphSide> result = new HashSet<IGraphSide>();
        
        // TODO: implement
        
        return result;     
    }
    
    public Set<IGraphSide> getBridgeBuildCandidates(Player player)
    {
        Set<IGraphSide> result = new HashSet<IGraphSide>();
        
        // TODO: implement
        
        return result;    
    }
}
