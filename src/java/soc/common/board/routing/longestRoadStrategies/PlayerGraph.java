package soc.common.board.routing.longestRoadStrategies;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;

import soc.common.board.routing.BoardGraph;
import soc.common.board.routing.GraphPoint;
import soc.common.board.routing.GraphSide;
import soc.common.board.routing.PossibleRoute;
import soc.common.board.routing.Route;
import soc.common.game.player.GamePlayer;

/*
 * A PlayerGraph is a graph containing all edges (roads, ships, bridges) and
 * vertices (towns, cities, knights) of a player.
 * The PlayerGraph does not contain edges of opponents, but it can contain
 * vertices (cities, towns, knights) from opponents.
 */
public class PlayerGraph
{
    // Set of GraphPoints connected to only one GraphSide
    private List<GraphPoint> ends = new ArrayList<GraphPoint>();

    // Set of GraphPoints connected to three GraphSides
    private List<GraphPoint> splits = new ArrayList<GraphPoint>();

    // Subgraph containing only sides/point with a path in between
    private UndirectedGraph<GraphPoint, GraphSide> graph = new SimpleGraph<GraphPoint, GraphSide>(
                    GraphSide.class);

    // Reference to the mother graph
    private BoardGraph boardGraph;

    // Longest path found in this player graph
    private Route longestPath;

    // Player owning this graph
    private GamePlayer player;

    public PlayerGraph(BoardGraph boardGraph, GamePlayer player)
    {
        this.boardGraph = boardGraph;
        this.player = player;

        constructGraph();
        constructEndsAndSplits();

        longestPath = calculatLongestPath();
    }

    /** @return the longestPath */
    public Route getLongestPath()
    {
        return longestPath;
    }

    /*
     * Constructs the graph given the set of GraphSides
     */
    private void constructGraph()
    {
        for (GraphSide side : boardGraph.getSides(player))
        {
            GraphPoint source = boardGraph.getGraph().getEdgeSource(side);
            GraphPoint target = boardGraph.getGraph().getEdgeTarget(side);
            graph.addVertex(source);
            graph.addVertex(target);
            graph.addEdge(source, target, side);
        }
    }

    /*
     * Returns the other point for a given 1-degree GraphPoint
     */
    private GraphPoint otherPoint(GraphPoint end)
    {
        List<GraphPoint> points = Graphs.neighborListOf(graph, end);

        for (GraphPoint point : points)
            if (!point.equals(end))
                return point;

        throw new AssertionError("Expected point with degree==1");
    }

    /*
     * Returns the other point for a given 2-degree middle GraphPoint (middle),
     * and given originating GraphPoint (from)
     */
    private GraphPoint otherPoint(GraphPoint middle, GraphPoint from)
    {
        List<GraphPoint> points = Graphs.neighborListOf(graph, middle);

        for (GraphPoint point : points)
            if (!point.equals(from))
                return point;

        throw new AssertionError("Expected point with degree==2");
    }

    /*
     * Returns a list of GraphPoints given a middle 3-degree GraphPoint and a
     * given originating GraphPoint (from)
     */
    private List<GraphPoint> otherPoints(GraphPoint middle, GraphPoint from)
    {
        // Result list
        List<GraphPoint> otherPoints = new ArrayList<GraphPoint>();

        // All the points neighbouring middle point
        List<GraphPoint> points = Graphs.neighborListOf(graph, middle);

        // Add all other neighbours
        for (GraphPoint point : points)
            if (!point.equals(from))
                otherPoints.add(point);

        if (otherPoints.size() != 2)
            throw new AssertionError("Expected point with degree==3");

        return otherPoints;
    }

    /*
     * Calculates longest path using SoC rules.
     * 
     * 1. Creates a list of routes assembled from ends and splits. 2. Loops until
     * all routes encountered an end. 3. Each iteration takes a route, and either
     * detects the route ends, or adds the lookahead GraphPoint. 4. Loop ends
     * when all routes have ended.
     * 
     * Takes ends and splits as starting points to calculate the longest path.
     * This will usually work, but not when there is a loop without an end or
     * split. In that case, the loopChecker will detect the loop and add another
     * possible route.
     * 
     * Loops without ends and splits may occur multiple times, and more then 2
     * times when a volcano is supported (extremely rare edge case, but still possible).
     * Sequences of roads may be split when a
     * volcano disconnects a road + town + ship combination, and the ship is
     * later moved away.
     */
    private Route calculatLongestPath()
    {
        // Bugger out when there are not enough edges
        if (graph.edgeSet().size() < 5)
            return null;

        // When the loopChecker list is not empty after the splits and ends
        // routes have been walked, it means there are sequences of edges with
        // no split or end (loops).
        List<GraphSide> loopChecker = new ArrayList<GraphSide>();
        for (GraphSide side : graph.edgeSet())
            loopChecker.add(side);

        // Paths which have not yet encountered an end
        List<PossibleRoute> routesToProcess = new ArrayList<PossibleRoute>();

        // Finished paths
        List<PossibleRoute> paths = new ArrayList<PossibleRoute>();

        createStartRoutes(routesToProcess);

        // Make sure the starting edges are removed from the loopChecker
        for (PossibleRoute route : routesToProcess)
        {
            GraphSide side = route.getEdgeList().iterator().next();
            if (loopChecker.contains(side))
                loopChecker.remove(side);
        }

        // Iterate until no routes exist without an end detected
        while (routesToProcess.size() > 0)
        {
            // Grab a route from the list
            PossibleRoute currentRoute = routesToProcess.get(0);

            // Point at the end of the route
            GraphPoint endPoint = currentRoute.getEndVertex();

            // Point where the current GraphSide originates from
            GraphPoint beforeEndPoint = currentRoute.getBeforeEndVertex();

            // Last side of the route
            GraphSide endSide = graph.getEdge(beforeEndPoint, endPoint);

            // There is no other edge
            // Reached an end. Put the route on the list of paths, remove it
            // from the routes to process
            if (graph.degreeOf(endPoint) == 1)
            {
                paths.add(currentRoute);
                routesToProcess.remove(currentRoute);
            }

            // There's only one edge
            if (graph.degreeOf(endPoint) == 2)
            {
                GraphPoint lookAheadPoint = otherPoint(endPoint, beforeEndPoint);
                GraphSide lookAheadSide = graph.getEdge(endPoint,
                                lookAheadPoint);

                // We can't add a lookahead side twice, and the lookahead must
                // connect to the end side
                if (currentRoute.contains(endPoint, lookAheadPoint)
                                || !canConnect(endSide, lookAheadSide))
                {
                    paths.add(currentRoute);
                    routesToProcess.remove(currentRoute);
                } else
                {
                    currentRoute.addPoint(lookAheadPoint);
                    if (loopChecker.contains(lookAheadSide))
                        loopChecker.remove(lookAheadSide);
                }
            }

            // Two edges
            if (graph.degreeOf(endPoint) == 3)
            {
                List<GraphPoint> otherPoints = otherPoints(endPoint,
                                beforeEndPoint);
                GraphPoint lookAheadPoint1 = otherPoints.get(0);
                GraphPoint lookAheadPoint2 = otherPoints.get(1);
                GraphSide lookAheadSide1 = graph.getEdge(endPoint,
                                lookAheadPoint1);
                GraphSide lookAheadSide2 = graph.getEdge(endPoint,
                                lookAheadPoint2);

                // The current route should be copied when both lookahead sides
                // are valid to include in a possible route
                boolean shouldCopy = false;

                // Check the first lookahead point
                if (currentRoute.contains(endPoint, lookAheadPoint1)
                                || !canConnect(endSide, lookAheadSide1))
                {
                    paths.add(currentRoute);
                    routesToProcess.remove(currentRoute);
                } else
                {
                    currentRoute.addPoint(lookAheadPoint1);
                    if (loopChecker.contains(lookAheadSide1))
                        loopChecker.remove(lookAheadSide1);
                    shouldCopy = true;
                }

                // Check the second lookahead point
                if (currentRoute.contains(endPoint, lookAheadPoint2)
                                || !canConnect(endSide, lookAheadSide2))
                {
                    paths.add(currentRoute);
                    routesToProcess.remove(currentRoute);
                } else
                {
                    if (shouldCopy)
                        currentRoute = currentRoute.copy();
                    if (loopChecker.contains(lookAheadSide2))
                        loopChecker.remove(lookAheadSide2);
                    currentRoute.addPoint(lookAheadPoint2);
                }
            }

            // Check if there are loops. If so, pick a random edge and create a
            // new route to process
            if (routesToProcess.size() == 0 && loopChecker.size() != 0)
            {
                GraphSide whateverSide = loopChecker.get(0);
                GraphPoint source = graph.getEdgeSource(whateverSide);
                GraphPoint target = graph.getEdgeTarget(whateverSide);
                PossibleRoute routeToProcess = new PossibleRoute(boardGraph,
                                source, target);
                routesToProcess.add(routeToProcess);
                PossibleRoute routeToProcess2 = new PossibleRoute(boardGraph,
                                target, source);
                routesToProcess.add(routeToProcess2);
                loopChecker.remove(whateverSide);
            }
        }

        Route longest = findFirstLongest(paths);

        longest.setPlayer(player);

        // Return first longest route when it's length exceeds 4
        return longest.getLength() > 4 ? longest : null;
    }

    private Route findFirstLongest(List<PossibleRoute> routes)
    {
        // We don't have a route when the list is empty
        if (routes.size() == 0)
            return null;

        // Grab the first longest route
        PossibleRoute longest = routes.get(0);

        // Search for a longer route and set it when found
        for (PossibleRoute route : routes)
            if (route.getWeight() > longest.getWeight())
                longest = route;

        return longest;
    }

    /*
     * Returns true when the "to" GraphSide can connect to the "from" GraphSide.
     */
    private boolean canConnect(GraphSide from, GraphSide to)
    {
        GraphPoint point = getCommonPoint(from, to);
        return from.getSidePiece().canConnect(point, to.getSidePiece());
    }

    /*
     * Creates a list of routes to start a walk with on the player graph. These
     * will be ends and splits, loops without ends and splits are therefore not
     * checked when walking the graph using only the ends and splits.
     */
    private void createStartRoutes(List<PossibleRoute> routesToProcess)
    {
        // Create a set of routes for every end
        for (GraphPoint end : ends)
        {
            PossibleRoute routeToProcess = new PossibleRoute(boardGraph, end,
                            otherPoint(end));
            routesToProcess.add(routeToProcess);
        }

        // Create a set of routes for every split
        for (GraphPoint split : splits)
            for (GraphSide side : boardGraph.getGraph().edgesOf(split))
            {
                GraphPoint otherPoint = boardGraph.getOtherPoint(split, side);
                PossibleRoute routeToProcess = new PossibleRoute(boardGraph,
                                split, otherPoint);
                routesToProcess.add(routeToProcess);
            }
    }

    /*
     * Returns the vertex (GraphPoint) common to both given GraphSides
     */
    private GraphPoint getCommonPoint(GraphSide from, GraphSide to)
    {
        GraphPoint fromSource = graph.getEdgeSource(from);
        GraphPoint fromTarget = graph.getEdgeTarget(from);
        GraphPoint toSource = graph.getEdgeSource(to);
        GraphPoint toTarget = graph.getEdgeTarget(to);
        if (fromSource.equals(toSource))
            return fromSource;
        if (fromTarget.equals(toSource))
            return fromTarget;

        if (fromSource.equals(toTarget))
            return fromSource;
        if (fromTarget.equals(toTarget))
            return fromTarget;

        throw new AssertionError("Expected common GraphPoint");
    }

    /*
     * Assembles a list of single-connected points, and a list of tri-connected
     * points
     */
    private void constructEndsAndSplits()
    {
        // Construct the sets of splits and ends
        for (GraphPoint point : graph.vertexSet())
        {
            // Degree is amount of connected sides to a point
            int degree = graph.degreeOf(point);

            if (degree == 1)
                ends.add(point);

            if (degree == 3)
                splits.add(point);
        }
    }
}
