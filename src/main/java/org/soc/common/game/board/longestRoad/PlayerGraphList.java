package org.soc.common.game.board.longestRoad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.jgrapht.UndirectedGraph;
import org.jgrapht.jgrapht.graph.SimpleGraph;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.board.BoardGraph;
import org.soc.common.game.board.GraphPoint;
import org.soc.common.game.board.GraphPoint.BlockedEndPoint;
import org.soc.common.game.board.GraphSide;
import org.soc.common.game.board.Route;

/*
 * Represents a list of PlayerGraphs. 
 */
public class PlayerGraphList implements Iterable<PlayerGraph>
{
  // Wrapped list of player graphs
  private List<PlayerGraph> playerGraphs = new ArrayList<PlayerGraph>();
  // Base graph where this subgraph is derived from
  private BoardGraph boardGraph;
  // Player to make the list of PlayerGraphs from
  private GamePlayer player;
  private UndirectedGraph<GraphPoint, GraphSide> completePlayerGraph;

  public PlayerGraphList(BoardGraph base, GamePlayer player)
  {
    this.boardGraph = base;
    this.player = player;
    // Create a graph from the base graph containing all players' pieces
    completePlayerGraph = getPlayerGraph(player);
    // Split the graph into subgraphs
    buildPlayerGraphList();
  }
  public Route getLongestRoute()
  {
    Route result = null;
    for (PlayerGraph playerGraph : playerGraphs)
      if (result == null
              || result.length() < playerGraph.getLongestPath()
                      .length())
        result = playerGraph.getLongestPath();
    return result;
  }
  /* Returns a graph containing all the owned edges (roads/ships/bridges) of given player. When a
   * town, city or knight blocks connection between two edges, the HexPoint is replaced by a
   * BlockedEndPoint. */
  public UndirectedGraph<GraphPoint, GraphSide> getPlayerGraph(
          GamePlayer player)
  {
    UndirectedGraph<GraphPoint, GraphSide> result = new SimpleGraph<GraphPoint, GraphSide>(
            GraphSide.class);
    // Construct the players' graph with all his owned edges and belonging
    // vertices
    for (GraphSide side : boardGraph.graph().edgeSet())
    {
      // Each encountered side ought to be unique.
      if (side.player() != null && side.player().equals(player))
      {
        GraphPoint source = boardGraph.graph().getEdgeSource(side);
        GraphPoint target = boardGraph.graph().getEdgeTarget(side);
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
      if (point.player() != null && point.player() != player)
        pointsToSplit.add(point);
    }
    // Split marked points in subgraph
    for (GraphPoint pointToSplit : pointsToSplit)
    {
      // Get rid of the old GraphPoint containing the city/town/knight of
      // opponent
      result.removeVertex(pointToSplit);
      for (GraphSide side : result.edgesOf(pointToSplit))
      {
        // Create the two new SplittedPoints
        if (side.player().equals(player))
        {
          // Create the splitted point
          BlockedEndPoint splittedPoint = new BlockedEndPoint(
                  pointToSplit.hexPoint());
          // Add the new splitted point to subgraph
          result.addVertex(splittedPoint);
          // Add removed edge to subgraph, along with it's new
          // splitted point
          result.addEdge(boardGraph
                  .getOtherPoint(splittedPoint, side), splittedPoint,
                  side);
        }
      }
    }
    return result;
  }
  /* Returns a set of PlayerGraphs derived from a graph containing all edges and vertices of a
   * player */
  private void buildPlayerGraphList()
  {
    // List of subsets where we create new subgraphs from
    Set<Set<GraphSide>> subSets = new HashSet<Set<GraphSide>>();
    Set<GraphSide> processedSides = new HashSet<GraphSide>();
    // Process each side in the edgeset until all sides are processed
    Iterator<GraphSide> it = completePlayerGraph.edgeSet().iterator();
    while (processedSides.size() != completePlayerGraph.edgeSet().size())
    {
      // Take a side from the edges to process
      GraphSide side = it.next();
      // When the edge is not yet encountered, create a new set of sides,
      // add it, and add its connected sides
      if (!processedSides.contains(side))
      {
        Set<GraphSide> connections = new HashSet<GraphSide>();
        getConnections(completePlayerGraph, connections,
                processedSides, side);
        subSets.add(connections);
      }
    }
    // // Construct a PlayerGraph using each subset of GraphSides
    // for (Set<GraphSide> playerGraphSet : subSets)
    // playerGraphs.add(new PlayerGraph(completePlayerGraph, boardGraph,
    // playerGraphSet, player));
  }
  private void getConnections(
          UndirectedGraph<GraphPoint, GraphSide> subGraph,
          Set<GraphSide> connections, Set<GraphSide> processedSides,
          GraphSide sideToGetConnectionsFrom)
  {
    // Add the side when not present in current connections
    if (!connections.contains(sideToGetConnectionsFrom))
    {
      connections.add(sideToGetConnectionsFrom);
      processedSides.add(sideToGetConnectionsFrom);
    }
    // Get neighbours of given side
    Set<GraphSide> neighbours = getNeighbours(sideToGetConnectionsFrom,
            subGraph);
    // Every unprocessed neighbour is recursively checked
    for (GraphSide neighbour : neighbours)
      if (!connections.contains(neighbour))
        getConnections(subGraph, connections, processedSides, neighbour);
  }
  /* Returns a set of neighbours from specified GraphSide in given graph */
  private Set<GraphSide> getNeighbours(GraphSide side,
          UndirectedGraph<GraphPoint, GraphSide> graph)
  {
    Set<GraphSide> neighbours = new HashSet<GraphSide>();
    // Get both GraphPoints the side is connected to
    GraphPoint source = graph.getEdgeSource(side);
    GraphPoint target = graph.getEdgeTarget(side);
    // Add each neighbour of the source
    for (GraphSide neighbour : graph.edgesOf(source))
      if (!neighbour.equals(side))
        neighbours.add(neighbour);
    // Add each neighbour of the target
    for (GraphSide neighbour : graph.edgesOf(target))
      if (!neighbour.equals(side))
        neighbours.add(neighbour);
    return neighbours;
  }
  @Override public Iterator<PlayerGraph> iterator()
  {
    return playerGraphs.iterator();
  }
}
