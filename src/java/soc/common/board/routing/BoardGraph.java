package soc.common.board.routing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.ListenableUndirectedGraph;

import soc.common.board.Board;
import soc.common.board.hexes.Hex;
import soc.common.board.layout.HasPoint;
import soc.common.board.layout.HexLocation;
import soc.common.board.layout.HexPoint;
import soc.common.board.layout.HexSide;
import soc.common.board.pieces.City;
import soc.common.board.pieces.Road;
import soc.common.board.pieces.Town;
import soc.common.board.routing.longestRoadStrategies.LongestRoadStrategy;
import soc.common.board.routing.longestRoadStrategies.ReduceStrategy;
import soc.common.game.Game;
import soc.common.game.player.GamePlayer;

/*
 * Implementation of a board graph to support longest route calculation and bots
 * with strategy calculation.
 * 
 * Designed such that it should easily support variants. A couple things to note:
 * - Knights may split a road like a town
 * - Volcano's may split a road, when a town blows up and the town connected a ship
 * and a road.
 * 
 * A dot (HexPoint) is called a vertex. A line (HexSide) is called
 * an edge.
 * 
 * TODO: Refactor into GameBoard class
 */
public class BoardGraph
{
    private Board board;
    private ListenableUndirectedGraph<GraphPoint, GraphSide> graph = new ListenableUndirectedGraph<GraphPoint, GraphSide>(
                    GraphSide.class);

    private LongestRoadStrategy longestRoadStrategy;

    public BoardGraph(Board board)
    {
        this.board = board;

        // Create the edges and vertices when we have a board
        buildGraph();
    }

    public Set<GraphSide> getSides()
    {
        return graph.edgeSet();
    }

    public Set<GraphPoint> getPoints()
    {
        return graph.vertexSet();
    }

    /** @return the graph */
    public ListenableUndirectedGraph<GraphPoint, GraphSide> getGraph()
    {
        return graph;
    }

    /*
     * Create the graph containing of all the empty vertices and edges, which
     * player can build roads, ships, walls, towns, cities, bridges, knights etc
     * on
     */
    private void buildGraph()
    {
        // Iterate over all hexes in the board
        for (Hex hex : board.getHexes())
            // Iterate over all 6 possible HexPoint of a hex
            for (HexPoint point : hex.getLocation().getNeighbourHexPoints())

                // The HexPoint should be within the board bounds
                if (board.includeInGame(point))
                {
                    GraphPoint newGraphPoint = new GraphPointImpl()
                                    .setPoint(point);

                    if (!graph.vertexSet().contains(newGraphPoint))
                        graph.addVertex(newGraphPoint);
                }

        // All the HexPoints are in place. Add the sides.

        // Iterate over all points in the graph
        for (GraphPoint point : graph.vertexSet())
            // Each point has three sides. Add them when they fall within the
            // bounds of the board, and it's not yet added
            for (HexSide side : point.getPoint().getNeighbourSides())
            {
                // Find existing side
                GraphSide graphSide = new GraphSideImpl(side);

                // Side should be on the inner map space
                if (board.fallsWithinBounds(side) &&
                // undirected graph, only need to add the edge once
                                !graph.edgeSet().contains(graphSide))
                {
                    // Get the other point
                    HexPoint otherPoint = side.getOtherPoint(point.getPoint());

                    // get the other GraphPoint
                    GraphPoint otherGraphPoint = findGraphPoint(otherPoint);
                    if (otherGraphPoint != null)
                        graph.addEdge(otherGraphPoint, point, graphSide);
                }
            } // for side
    }
    public GraphSide findGraphSide(HexSide side)
    {
        for (GraphSide graphSide : graph.edgeSet())
            if (graphSide.getSide().equals(side))
                return graphSide;

        return null;
    }

    public GraphPoint findGraphPoint(HexPoint point)
    {
        for (GraphPoint graphPoint : graph.vertexSet())
            if (graphPoint.getPoint().equals(point))
                return graphPoint;

        return null;
    }

    public void addTown(Town town)
    {
        GraphPoint point = findGraphPoint(town.getPoint());

        // Add the town to the GraphPoint
        point.setPlayerPiece(town);

        // Mark all neighbour points as non-buildable
        List<GraphPoint> neighbours = Graphs.neighborListOf(graph, point);
        for (GraphPoint neighbour : neighbours)
            neighbour.setTownBuildable(false);

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
        GraphSide side = findGraphSide(road.getSide());

        side.setPlayerPiece(road);
    }

    /*
     * Returns the second point of the given side, excluding the given point
     */
    public GraphPoint getOtherPoint(GraphPoint ignore, GraphSide side)
    {
        GraphPoint otherPoint = graph.getEdgeSource(side);
        return ignore.equals(otherPoint) ? graph.getEdgeTarget(side)
                        : otherPoint;
    }

    /*
     * Calculate longest route 1. Create a PlayerGraph for every player 2. Split
     * the playergraph in seperate islands (nonconnected graphs) 3. Calculate
     * longest path for every playergraph of every player
     */
    public Route getLongestRoute(Game game, Route currentLongest)
    {
        longestRoadStrategy = new ReduceStrategy();
        return longestRoadStrategy.calculateLongestRoad(currentLongest, this,
                        game);
    }

    /*
     * Returns set of points to place a town on during PlayTurns GamePhase
     */
    public Set<GraphPoint> getTownCandidatesTurnPhase(GamePlayer forPlayer)
    {
        Set<GraphPoint> candidates = new HashSet<GraphPoint>();

        // Get list of sides from a player
        List<GraphSide> sides = new ArrayList<GraphSide>();
        for (GraphSide side : graph.edgeSet())
            if (forPlayer.equals(side.getPlayer()))
                sides.add(side);

        // Put every source/target in list, if not already contained in list
        List<GraphPoint> points = new ArrayList<GraphPoint>();
        for (GraphSide side : sides)
        {
            if (!points.contains(graph.getEdgeSource(side)))
                points.add(graph.getEdgeSource(side));

            if (!points.contains(graph.getEdgeTarget(side)))
                points.add(graph.getEdgeTarget(side));
        }

        // Filter list of adjacent points on ability to put town there
        for (GraphPoint point : points)
            if (point.isTownBuildable())
                candidates.add(point);

        return candidates;
    }

    /*
     * Returns a set of possibilties for a player to build on, for his first
     * town
     */
    public Set<GraphPoint> getTownCandidatesFirstTown(GamePlayer forPlayer)
    {
        Set<GraphPoint> result = new HashSet<GraphPoint>();

        for (GraphPoint possibleCandidate : graph.vertexSet())
            if (board.isTownBuildable(possibleCandidate)
                            && possibleCandidate.isTownBuildable())
                result.add(possibleCandidate);

        return result;
    }

    /*
     * Returns a list of possible locations to build his first town on
     */
    public Set<GraphSide> getRoadCandidatesFirstTown(GamePlayer player)
    {
        // Assuming the player has built only one piece, which is his first town
        HasPoint first = player.getPointPieces().get(0);

        // Grab the GraphPoint where the town resides
        GraphPoint townPoint = findGraphPoint(first.getPoint());

        // We assume it's safe to return all edges, because an opponent can't
        // build on a neighbour, since the opponent needs two roads for that.
        return graph.edgesOf(townPoint);
    }

    /*
     * 
     */
    public Set<GraphSide> getRoadCandidatesSecondTown(GamePlayer player)
    {
        Set<GraphSide> candidates = new HashSet<GraphSide>();
        // Assuming the player has built two pieces, and the second one in the
        // list of pieces is indeed the actual second built piece.
        // TODO: add support for Tournament Starting Rules
        HasPoint second = player.getPointPieces().get(1);

        // Grab the GraphPoint where the town resides
        GraphPoint townPoint = findGraphPoint(second.getPoint());
        for (GraphSide side : graph.edgesOf(townPoint))
            if (board.isBuildableLand(side.getSide()))
                candidates.add(side);

        // We assume it's safe to return all edges, because an opponent can't
        // build on a neighbour, since the opponent needs two roads for that.
        return candidates;
    }

    /*
     * Returns a set of GraphSides where given player can a road build on
     */
    public Set<GraphSide> getRoadCandidates(GamePlayer player)
    {
        Set<GraphSide> candidates = new HashSet<GraphSide>();
        List<GraphSide> currentSidePieces = new ArrayList<GraphSide>();

        // Compile a list of GraphSides owned by the player
        for (GraphSide side : graph.edgeSet())
            if (side.getPlayer() != null && side.getPlayer().equals(player))
                currentSidePieces.add(side);

        // Filter list by ability to build road on
        for (GraphSide side : currentSidePieces)
            for (GraphSide neighbour : getNeighbours(side, player))
                // Only add neighbour when not yet present, and another player
                // does not own it yet
                if ((neighbour.getPlayer() == null || neighbour.getPlayer()
                                .equals(player))
                                && !candidates.contains(neighbour)
                                && board.isRoadBuildable(neighbour))
                    candidates.add(neighbour);

        return candidates;
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

    /*
     * 
     */
    public Set<GraphSide> getNeighbours(GraphSide fromSide, GamePlayer player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        GraphPoint source = graph.getEdgeSource(fromSide);
        GraphPoint target = graph.getEdgeTarget(fromSide);

        // Add the zero or more edges from the _source_

        // GraphPoint must either be owned by no one, or owned by given player
        if (source.getPlayer() == null || source.getPlayer().equals(player))
            // Grab neihbours
            for (GraphPoint neighbourPoint : Graphs.neighborListOf(graph,
                            source))
                if (!neighbourPoint.equals(target))
                    result.add(graph.getAllEdges(source, neighbourPoint)
                                    .iterator().next());

        // Add the zero or more edges from the _target_

        // GraphPoint must either be owned by no one, or owned by given player
        if (source.getPlayer() == null || source.getPlayer().equals(player))
            for (GraphPoint neighbourPoint : Graphs.neighborListOf(graph,
                            target))
                if (!neighbourPoint.equals(source))
                    result.add(graph.getAllEdges(target, neighbourPoint)
                                    .iterator().next());
        return result;
    }

    public Set<GraphSide> getSides(GamePlayer player)
    {
        Set<GraphSide> result = new HashSet<GraphSide>();

        for (GraphSide side : graph.edgeSet())
            if (side.getPlayer() != null && side.getPlayer().equals(player))
                result.add(side);

        return result;
    }

    public List<HexLocation> getPossibleRobberLocations()
    {
        List<HexLocation> possibleRobberLocations = new ArrayList<HexLocation>();

        for (Hex hex : board.getHexes())
            if (hex.isRobberPlaceable())
                possibleRobberLocations.add(hex.getLocation());

        return possibleRobberLocations;
    }
}
