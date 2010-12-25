package soc.common.board.routing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.UndirectedSubgraph;

import soc.common.board.Board;
import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.hexes.Hex;
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
    public ListenableUndirectedGraph<GraphPoint, GraphSide> graph = new ListenableUndirectedGraph<GraphPoint, GraphSide>(
            GraphSide.class);

    /*
     * Create the graph containing of all the empty vertices and edges
     */
    public void buildGraph(Board board)
    {
        // Iterate over all hexes in the board
        for (Hex hex : board.getHexes())
        {
            // Iterate over all 6 possible HexPoint of a hex
            for (HexPoint point : hex.getLocation().getNeighbourHexPoints())
            {
                GraphPoint graphPoint = getPointFromSet(point);

                if (graphPoint == null && // no need for duplicates
                        // point should be actually on the board, not on the
                        // edges
                        point.fallsWithinBoardBounds(board.getWidth(), board
                                .getHeight()))
                {
                    graphPoint = new GraphPointImpl().setPoint(point);

                    graph.addVertex(graphPoint);
                }
            }
        }

        // Now we have all the HexPoints in place. Now add the sides.

        // Iterate over all points in the graph
        for (GraphPoint point : graph.vertexSet())
        {
            // Each point has three sides. Add them when they fall. within the
            // bounds of the board
            for (HexSide side : point.getPoint().getNeighbourSides())
            {
                // Side should be on the inner map space
                if (side.fallsWithinBoardBounds(board.getWidth(), board
                        .getHeight())
                        &&
                        // undirected graph, only need to add the edge once
                        !graph.edgeSet().contains(side))
                {
                    // Get the other point
                    HexPoint otherPoint = side.getOtherPoint(point.getPoint());

                    // instantiate the other IGraphPoint
                    GraphPoint otherGraphPoint = new GraphPointImpl()
                            .setPoint(otherPoint);

                    // Find existing side
                    GraphSide graphSide = getSideFromSet(side);

                    if (graphSide == null)
                    {
                        graphSide = new GraphSideImpl().setSide(side);
                    }

                    graph.addEdge(otherGraphPoint, point, graphSide);
                }
            }
        }
    }

    private GraphSide getSideFromSet(HexSide side)
    {
        for (GraphSide graphSide : graph.edgeSet())
        {
            if (graphSide.getSide().equals(side))
            {
                return graphSide;
            }
        }

        return null;
    }

    private GraphPoint getPointFromSet(HexPoint point)
    {
        for (GraphPoint graphPoint : graph.vertexSet())
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
        GraphPoint point = getPointFromSet(town.getPoint());

        point.setPlayerPiece(town);

        // Mark all neighbour points as non-buildable

        // Iterate over all possible neighbours
        for (GraphSide side : graph.edgesOf(point))
        {
            GraphPoint otherPoint = getOtherPoint(point, side);
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
        GraphPoint point = getPointFromSet(city.getPoint());

        point.setPlayerPiece(city);
    }

    public void addRoad(Road road)
    {
        GraphSide side = getSideFromSet(road.getSide());

        side.setPlayerPiece(road);
        side.setBuildable(false);
    }

    /*
     * Returns the second point of the given side, exlcuding the given point
     */
    private GraphPoint getOtherPoint(GraphPoint ignore, GraphSide side)
    {
        GraphPoint otherPoint = null;
        otherPoint = graph.getEdgeSource(side);
        if (ignore.equals(otherPoint))
        {
            return graph.getEdgeTarget(side);
        }
        else
        {
            return otherPoint;
        }
    }

    public UndirectedSubgraph<GraphPoint, GraphSide> getPlayerGraph(
            Player player)
    {
        UndirectedSubgraph<GraphPoint, GraphSide> result;

        // Create a set of sides of the player
        Set<GraphSide> subSet = new HashSet<GraphSide>();
        for (GraphSide side : graph.edgeSet())
        {
            if (side.getPlayer() == player)
            {
                subSet.add(side);
            }
        }

        // Create subgraph, with players' sides and all the points of the main
        // graph
        result = new UndirectedSubgraph<GraphPoint, GraphSide>(graph, graph
                .vertexSet(), subSet);

        // Keep track of the points we need to remove
        List<GraphPoint> pointsToRemove = new ArrayList<GraphPoint>();

        // Keep track of the points need splitting
        List<GraphPoint> pointsToSplit = new ArrayList<GraphPoint>();

        // Split points when there is a piece of an opponent,
        // remove points when there are no roads/ships/bridges connecting
        for (GraphPoint point : result.vertexSet())
        {
            // Mark points without roads/ships/bridges connecting to them for
            // removal
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
        for (GraphPoint pointToRemove : pointsToRemove)
        {
            result.vertexSet().remove(pointToRemove);
        }

        // Split marked points in subgraph
        for (GraphPoint pointToSplit : pointsToSplit)
        {
            // Get rid of the old graphpoint containing the city/town/knight of
            // opponent
            result.removeVertex(pointToSplit);

            for (GraphSide side : result.edgesOf(pointToSplit))
            {
                // Create the two new SplittedPoints
                if (side.getPlayer().equals(player))
                {
                    // Create the splitted point
                    BlockedEndPoint splittedPoint = new BlockedEndPoint(
                            pointToSplit.getPoint());

                    // Add the new splitted point to subgraph
                    result.addVertex(splittedPoint);

                    // Add removed edge to subgraph, along with it's new
                    // splitted point
                    result.addEdge(getOtherPoint(splittedPoint, side),
                            splittedPoint, side);
                }
            }
        }

        return result;
    }

    public Route getLongestRoute(List<Player> players)
    {
        // Route longestRoute = new Route();

        Set<UndirectedSubgraph<GraphPoint, GraphSide>> playerGraphs = new HashSet<UndirectedSubgraph<GraphPoint, GraphSide>>();

        // Fill the set of graphs for each player
        for (Player player : players)
        {
            // Only consider players with at least 5 SidePieces
            if (player.getBuildPieces().getSidePieces().size() >= 5)
            {
                // Get the subgraph from the main graph
                UndirectedSubgraph<GraphPoint, GraphSide> subGraph = getPlayerGraph(player);

                // Add the graph when amount of vertices is equal or more then
                // 5. No need
                // to check longest road if there are not sufficient items to
                // meet
                // the requirement of at least 5 roads/ships/bridges per path
                if (subGraph.vertexSet().size() >= 5)
                {
                    playerGraphs.add(subGraph);
                }
            }
        }

        // Graphs may have two properties:
        // 1. Graphs with splits
        // Amount of edges represents the length of the road
        // 2. No points with degree = 1, any point in the graph has a degree of
        // 2 or 3.
        // When no points of degree = 3 (splits) exist, amount of edges
        // represents length of the road
        // When points of degree = 3 exist, compute longest path

        return null;
    }

    /*
     * Returns a set of PlayerGraphs derived from a graph containing all edges
     * and vertices of a player
     */
    private Set<PlayerGraph> getSeperateSubGraphs(
            UndirectedSubgraph<GraphPoint, GraphSide> subGraph)
    {
        // List of subsets where we create new subsubgraphs from
        Set<Set<GraphSide>> subSets = new HashSet<Set<GraphSide>>();

        // Check every point and put it into a subgraph
        for (GraphSide side : subGraph.edgeSet())
        {
            boolean isVisited = false;
            for (Set<GraphSide> set : subSets)
            {
                if (set.contains(side))
                    isVisited = true;
            }
            if (!isVisited)
            {

            }
            Set<GraphSide> subSet = new HashSet<GraphSide>();
            // subSet = getConnectedSides(subGraph, subSet, IGraphSide side);
        }

        return null;
    }

    private Set<GraphSide> getConnectedSides(
            UndirectedSubgraph<GraphPoint, GraphSide> subGraph,
            Set<GraphSide> connectedSides, Set<GraphPoint> processedPoints,
            GraphPoint point)
    {
        // Add each found edge to the list of connected edges, when not already
        // present
        for (GraphSide side : subGraph.edgeSet())
        {
            // Add the side when not present
            if (!connectedSides.contains(side))
                connectedSides.add(side);
        }

        // Mark this point as "processed": we have added it's edges
        processedPoints.add(point);

        // Repeat the process for each neighbour point
        List<GraphPoint> neighbours = Graphs.neighborListOf(subGraph, point);

        for (GraphPoint neighbour : neighbours)
        {
            if (!processedPoints.contains(neighbour))
                getConnectedSides(subGraph, connectedSides, processedPoints,
                        neighbour);
        }

        return connectedSides;
    }

    public Set<GraphPoint> getTownCandidatesTurnPhase(Player forPlayer)
    {
        Set<GraphPoint> result = new HashSet<GraphPoint>();

        return result;
    }

    /*
     * Returns a set of possibilties for a player to build on, for his first
     * town
     */
    public Set<GraphPoint> getTownCandidatesFirstTown(Player forPlayer)
    {
        Set<GraphPoint> result = new HashSet<GraphPoint>();

        for (GraphPoint possibleCandidate : graph.vertexSet())
        {
            // TODO: implement
        }

        return result;
    }

    /*
     * Returns a set of possible locations for a player's second town
     */
    public Set<GraphPoint> getTownCandidatesSecondTown(Player forPlayer)
    {
        Set<GraphPoint> result = new HashSet<GraphPoint>();

        for (GraphPoint possibleCandidate : graph.vertexSet())
        {
            // TODO: implement
        }

        return result;
    }

    /*
     * Returns a list of possible locations to build his first town on
     */
    public Set<GraphSide> getRoadCandidatesFirstTown(Player player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }

    /*
     * 
     */
    public Set<GraphSide> getRoadCandidatesSecondTown(Player player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }

    public Set<GraphSide> getRoadCandidates(Player player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }

    public Set<GraphSide> getShipBuildCandidates(Player player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }

    public Set<GraphSide> getShipMoveCandidates(Player player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }

    public Set<GraphSide> getBridgeBuildCandidates(Player player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }
}
