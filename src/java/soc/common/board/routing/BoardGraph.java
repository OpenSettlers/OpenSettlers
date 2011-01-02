package soc.common.board.routing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.UndirectedSubgraph;

import soc.common.board.Board;
import soc.common.board.HexPoint;
import soc.common.board.HexSide;
import soc.common.board.hexes.Hex;
import soc.common.board.pieces.City;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.game.GamePlayer;

/*
 * Implementation of a board graph to support longest route calculation and bots
 * with strategy calculation.
 * 
 * Designed such that it should easily support variants. A couple things to note:
 * - Knights may split a road like a town
 * - Volcano's may split a road, when a town blows up and the town connected a ship 
 *   and a road. 
 * 
 * A dot (HexPoint) is called a vertex. A line (HexSide) is called
 * an edge.  
 */
public class BoardGraph
{
    private ListenableUndirectedGraph<GraphPoint, GraphSide> graph = new ListenableUndirectedGraph<GraphPoint, GraphSide>(
            GraphSide.class);

    public Set<GraphSide> getSides()
    {
        return graph.edgeSet();
    }

    public Set<GraphPoint> getPoints()
    {
        return graph.vertexSet();
    }

    /**
     * @return the graph
     */
    public ListenableUndirectedGraph<GraphPoint, GraphSide> getGraph()
    {
        return graph;
    }

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
                GraphPoint graphPoint = findGraphPoint(point);

                if (board.includeInGame(point))
                {
                    GraphPoint newGraphPoint = new GraphPointImpl()
                            .setPoint(point);

                    graph.addVertex(newGraphPoint);
                }
            }
        }

        // Now we have all the HexPoints in place. Now add the sides.

        // Iterate over all points in the graph
        for (GraphPoint point : graph.vertexSet())
        {
            // Each point has three sides. Add them when they fall within the
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

                    // get the other IGraphPoint
                    GraphPoint otherGraphPoint = findGraphPoint(otherPoint);

                    if (otherGraphPoint != null)
                    {

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

    private GraphPoint findGraphPoint(HexPoint point)
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
        GraphPoint point = findGraphPoint(town.getPoint());

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
        GraphPoint point = findGraphPoint(city.getPoint());

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

    public UndirectedGraph<GraphPoint, GraphSide> getPlayerGraph(
            GamePlayer player)
    {
        UndirectedGraph<GraphPoint, GraphSide> result = new SimpleGraph<GraphPoint, GraphSide>(
                GraphSide.class);

        // Construct the players' graph with all his owned edges
        for (GraphSide side : graph.edgeSet())
        {
            // Each encountered side ought to be unique.
            if (side.getPlayer().equals(player))
            {
                GraphPoint source = graph.getEdgeSource(side);
                GraphPoint target = graph.getEdgeTarget(side);
                result.addVertex(source);
                result.addVertex(target);
                result.addEdge(source, target, side);
            }
        }

        // Keep track of the points need splitting
        List<GraphPoint> pointsToSplit = new ArrayList<GraphPoint>();

        // Split points when there is a piece of an opponent,
        // remove points when there are no roads/ships/bridges connecting
        for (GraphPoint point : result.vertexSet())
        {
            // Split the chain when HexPoint is in use by another player
            // TODO: add support for volcanos by checking for ship+road
            // disconnects
            if (point.getPlayer() != null && point.getPlayer() != player)
            {
                pointsToSplit.add(point);
            }
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

    /*
     * Calculate longest route 1. Create a PlayerGraph for every player 2. Split
     * the playergraph in seperate islands (nonconnected graphs) 3. Calculate
     * longest path for every playergraph of every player
     */
    public Route getLongestRoute(List<GamePlayer> players)
    {
        // Route longestRoute = new Route();

        Set<UndirectedGraph<GraphPoint, GraphSide>> playerGraphs = new HashSet<UndirectedGraph<GraphPoint, GraphSide>>();

        // Fill the set of graphs for each player
        for (GamePlayer player : players)
        {
            // Only consider players with at least 5 SidePieces
            if (player.getBuildPieces().getSidePieces().size() >= 5)
            {
                // Get the subgraph from the main graph
                UndirectedGraph<GraphPoint, GraphSide> subGraph = getPlayerGraph(player);

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

    public Set<GraphPoint> getTownCandidatesTurnPhase(GamePlayer forPlayer)
    {
        Set<GraphPoint> result = new HashSet<GraphPoint>();

        return result;
    }

    /*
     * Returns a set of possibilties for a player to build on, for his first
     * town
     */
    public Set<GraphPoint> getTownCandidatesFirstTown(GamePlayer forPlayer)
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
    public Set<GraphPoint> getTownCandidatesSecondTown(GamePlayer forPlayer)
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
    public Set<GraphSide> getRoadCandidatesFirstTown(GamePlayer player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }

    /*
     * 
     */
    public Set<GraphSide> getRoadCandidatesSecondTown(GamePlayer player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }

    public Set<GraphSide> getRoadCandidates(GamePlayer player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }

    public Set<GraphSide> getShipBuildCandidates(GamePlayer player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }

    public Set<GraphSide> getShipMoveCandidates(GamePlayer player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }

    public Set<GraphSide> getBridgeBuildCandidates(GamePlayer player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        // TODO: implement

        return result;
    }
}
