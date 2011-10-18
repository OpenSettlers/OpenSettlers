package org.soc.common.game.board;

import java.util.*;

import org.jgrapht.jgrapht.*;
import org.jgrapht.jgrapht.graph.*;
import org.soc.common.game.*;
import org.soc.common.game.board.GraphPoint.GraphPointImpl;
import org.soc.common.game.board.GraphSide.GraphSideImpl;
import org.soc.common.game.board.longestRoad.*;
import org.soc.common.game.hexes.*;
import org.soc.common.game.pieces.*;

/** Keeps track of hexes, points and sides. Wraps a Board, and on initialization builds a graph of
 * points and sides based on the hexes. */
public class GameBoard {
  private Board board;
  private ListenableUndirectedGraph<GraphPoint, GraphSide> graph = new ListenableUndirectedGraph<GraphPoint, GraphSide>(
          GraphSide.class);
  private LongestRoadStrategy longestRoadStrategy;

  public GameBoard(Board board) {
    this.board = board;
    // Create the edges and vertices when we have a board
    buildGraph();
  }
  public Set<GraphSide> sides() {
    return graph.edgeSet();
  }
  public Set<GraphPoint> points() {
    return graph.vertexSet();
  }
  public ListenableUndirectedGraph<GraphPoint, GraphSide> graph() {
    return graph;
  }
  /** Create the graph containing of all the empty vertices and edges, which player can build roads,
   * ships, walls, towns, cities, bridges, knights etc on */
  private void buildGraph() {
    createVertices();
    createEdges();
  }
  private void createEdges() {
    for (GraphPoint point : graph.vertexSet())
      // Each point has three sides. Add them when they fall within the
      // bounds of the board, and it's not yet added
      for (HexSide side : point.hexPoint().neighbourSides()) {
        // Find existing side
        GraphSide graphSide = new GraphSideImpl(side);
        // Side should be on the inner map space
        if (board.fallsWithinBounds(side) &&
                // undirected graph, only need to add the edge once
                !graph.edgeSet().contains(graphSide)) {
          // Get the other point
          HexPoint otherPoint = side.getOtherPoint(point.hexPoint());
          // get the other GraphPoint
          GraphPoint otherGraphPoint = findGraphPoint(otherPoint);
          if (otherGraphPoint != null)
            graph.addEdge(otherGraphPoint, point, graphSide);
        }
      } // for side
  }
  /** Creates a list of vertices from given board */
  private void createVertices() {
    for (Hex hex : board.hexes())
      for (HexPoint point : hex.location().neighbourPoints())
        if (board.includeInGame(point)) {
          GraphPoint newGraphPoint = new GraphPointImpl().setPoint(point);
          if (!graph.vertexSet().contains(newGraphPoint))
            graph.addVertex(newGraphPoint);
        }
  }
  public GraphSide findGraphSide(HexSide side) {
    for (GraphSide graphSide : graph.edgeSet())
      if (graphSide.side().equals(side))
        return graphSide;
    return null;
  }
  public GraphPoint findGraphPoint(HexPoint point) {
    for (GraphPoint graphPoint : graph.vertexSet())
      if (graphPoint.hexPoint().equals(point))
        return graphPoint;
    return null;
  }
  public void addTown(Town town) {
    GraphPoint point = findGraphPoint(town.point());
    point.setPlayerPiece(town);
    // Mark all neighbour points as non-buildable
    List<GraphPoint> neighbours = Graphs.neighborListOf(graph, point);
    for (GraphPoint neighbour : neighbours)
      neighbour.setTownBuildable(false);
    // Set the originating point as non-buildable
    point.setTownBuildable(false);
  }
  /** Replaces town of target GraphPoint to a city */
  public void addCity(City city) {
    GraphPoint point = findGraphPoint(city.point());
    point.setPlayerPiece(city);
  }
  public void addRoad(Road road) {
    GraphSide side = findGraphSide(road.getSide());
    side.setPlayerPiece(road);
  }
  /** Returns the second point of the given side, excluding the given point */
  public GraphPoint getOtherPoint(GraphPoint ignore, GraphSide side) {
    GraphPoint otherPoint = graph.getEdgeSource(side);
    return ignore.equals(otherPoint) ? graph.getEdgeTarget(side)
            : otherPoint;
  }
  /** Calculate longest route 1. Create a PlayerGraph for every player 2. Split the playergraph in
   * seperate islands (nonconnected graphs) 3. Calculate longest path for every playergraph of every
   * player */
  public Route getLongestRoute(Game game, Route currentLongest) {
    longestRoadStrategy = new ReduceStrategy();
    return longestRoadStrategy.calculateLongestRoad(currentLongest, this, game);
  }
  /** Returns set of points to place a town on during PlayTurns GamePhase */
  public Set<GraphPoint> getTownCandidatesTurnPhase(GamePlayer player) {
    Set<GraphPoint> candidates = new HashSet<GraphPoint>();
    Set<GraphSide> sides = sidesOf(player);
    Set<GraphPoint> points = pointsOf(sides);
    // Filter list of adjacent points on ability to put town there
    for (GraphPoint point : points)
      if (point.isTownBuildable())
        candidates.add(point);
    return candidates;
  }
  /** Returns a set of related points from given set of sides */
  public Set<GraphPoint> pointsOf(Set<GraphSide> sides) {
    Set<GraphPoint> points = new HashSet<GraphPoint>();
    for (GraphSide side : sides) {
      if (!points.contains(graph.getEdgeSource(side)))
        points.add(graph.getEdgeSource(side));
      if (!points.contains(graph.getEdgeTarget(side)))
        points.add(graph.getEdgeTarget(side));
    }
    return points;
  }
  /** Returns a set of possibilties for a player to build on, for his first town */
  public Set<GraphPoint> getTownCandidatesFirstTown(GamePlayer forPlayer) {
    Set<GraphPoint> result = new HashSet<GraphPoint>();
    for (GraphPoint possibleCandidate : graph.vertexSet())
      if (board.isTownBuildable(possibleCandidate)
              && possibleCandidate.isTownBuildable())
        result.add(possibleCandidate);
    return result;
  }
  /** Returns a list of possible locations to build his first town on */
  public Set<GraphSide> getRoadCandidatesFirstTown(GamePlayer player) {
    // Assuming the player has built only one piece, which is his first town
    HasPoint first = player.pointPieces().get(0);
    // Grab the GraphPoint where the town resides
    GraphPoint townPoint = findGraphPoint(first.point());
    // We assume it's safe to return all edges, because an opponent can't
    // build on a neighbour, since the opponent needs two roads for that.
    return graph.edgesOf(townPoint);
  }
  public Set<GraphSide> getRoadCandidatesSecondTown(GamePlayer player) {
    Set<GraphSide> candidates = new HashSet<GraphSide>();
    // Assuming the player has built two pieces, and the second one in the
    // list of pieces is indeed the actual second built piece.
    // TODO: add support for Tournament Starting Rules
    HasPoint second = player.pointPieces().get(1);
    // Grab the GraphPoint where the town resides
    GraphPoint townPoint = findGraphPoint(second.point());
    for (GraphSide side : graph.edgesOf(townPoint))
      if (board.isBuildableLand(side.side()))
        candidates.add(side);
    // Assume it's safe to return all edges, because an opponent can't
    // build on a neighbour, since the opponent needs two roads for that.
    return candidates;
  }
  /** Returns a set of GraphSides where given player can a road build on */
  public Set<GraphSide> roadCandidates(GamePlayer player) {
    Set<GraphSide> candidates = new HashSet<GraphSide>();
    Set<GraphSide> currentSidePieces = sidesOf(player);
    // Filter list by ability to build road on
    for (GraphSide side : currentSidePieces)
      for (GraphSide neighbour : neighbours(side, player))
        // Only add neighbour when not yet present, and another player
        // does not own it yet
        if ((neighbour.player() == null || neighbour.player().equals(player))
                && !candidates.contains(neighbour)
                && board.isRoadBuildable(neighbour))
          candidates.add(neighbour);
    return candidates;
  }
  public Set<GraphSide> getShipBuildCandidates(GamePlayer player) {
    Set<GraphSide> result = new HashSet<GraphSide>();
    // TODO: implement
    return result;
  }
  public Set<GraphSide> getShipMoveCandidates(GamePlayer player) {
    Set<GraphSide> result = new HashSet<GraphSide>();
    // TODO: implement
    return result;
  }
  public Set<GraphSide> getBridgeBuildCandidates(GamePlayer player) {
    Set<GraphSide> result = new HashSet<GraphSide>();
    // TODO: implement
    return result;
  }
  public Set<GraphSide> neighbours(GraphSide fromSide, GamePlayer player) {
    Set<GraphSide> result = new HashSet<GraphSide>();
    GraphPoint source = graph.getEdgeSource(fromSide);
    GraphPoint target = graph.getEdgeTarget(fromSide);
    // Add the zero or more edges from the _source_
    // GraphPoint must either be owned by no one, or owned by given player
    if (notOwnedOrOwnedBy(player, source))
      // Grab neihbours
      for (GraphPoint neighbourPoint : Graphs.neighborListOf(graph, source))
        if (!neighbourPoint.equals(target))
          result.add(graph.getAllEdges(source, neighbourPoint)
                  .iterator().next());
    // Add the zero or more edges from the _target_
    // GraphPoint must either be owned by no one, or owned by given player
    if (source.player() == null || source.player().equals(player))
      for (GraphPoint neighbourPoint : Graphs.neighborListOf(graph, target))
        if (!neighbourPoint.equals(source))
          result.add(graph.getAllEdges(target, neighbourPoint)
                  .iterator().next());
    return result;
  }
  public boolean notOwnedOrOwnedBy(GamePlayer player, GraphPoint point) {
    return point.player() == null || point.player().equals(player);
  }
  /** Returns set of sides owned by given player */
  public Set<GraphSide> sidesOf(GamePlayer player) {
    Set<GraphSide> result = new HashSet<GraphSide>();
    for (GraphSide side : graph.edgeSet())
      if (player.equals(side.player()))
        result.add(side);
    return result;
  }
  /** Returns set of points owned by given player */
  public Set<GraphPoint> pointsOf(GamePlayer player) {
    Set<GraphPoint> result = new HashSet<GraphPoint>();
    for (GraphPoint point : graph.vertexSet())
      if (player.equals(point.player()))
        result.add(point);
    return result;
  }
  public List<HexLocation> possibleRobberLocations() {
    List<HexLocation> possibleRobberLocations = new ArrayList<HexLocation>();
    for (Hex hex : board.hexes())
      if (hex.isRobberPlaceable())
        possibleRobberLocations.add(hex.location());
    return possibleRobberLocations;
  }
}
