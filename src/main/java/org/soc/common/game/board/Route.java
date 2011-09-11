package org.soc.common.game.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.jgrapht.Graph;
import org.jgrapht.jgrapht.GraphPath;
import org.soc.common.game.GamePlayer;
import org.soc.common.game.board.HexSide.HexSideImpl;

public interface Route extends GraphPath<GraphPoint, GraphSide>, Serializable {
  public GamePlayer player();
  public Route setPlayer(GamePlayer player);
  public int length();
  public boolean validate();

  /** A route on a GameBoard */
  public class RouteImpl implements Route {
    private List<GraphSide> edges = new ArrayList<GraphSide>();
    private transient BoardGraph baseGraph;
    private transient GamePlayer player;
    private GraphPoint startVertex;
    private GraphPoint endVertex;
    private int playerID;

    public RouteImpl(BoardGraph baseGraph, Set<GraphSide> edges, GamePlayer player) {
      this.baseGraph = baseGraph;
      setPlayer(player);
      for (GraphSide side : edges)
        this.edges.add(side);
    }
    public RouteImpl(BoardGraph baseGraph, List<HexPoint> longest, GamePlayer player) {
      this.baseGraph = baseGraph;
      setPlayer(player);
      for (int i = 0; i < longest.size() - 1; i++) {
        HexSide side = new HexSideImpl(longest.get(i), longest.get(i + 1));
        GraphSide graphSide = baseGraph.findGraphSide(side);
        edges.add(graphSide);
      }
      startVertex = baseGraph.findGraphPoint(longest.get(0));
      endVertex = baseGraph.findGraphPoint(longest.get(longest.size() - 1));
    }
    @Override public int length() {
      return edges.size();
    }
    @Override public GamePlayer player() {
      return player;
    }
    @Override public List<GraphSide> getEdgeList() {
      return edges;
    }
    @Override public GraphPoint getEndVertex() {
      return endVertex;
    }
    @Override public Graph<GraphPoint, GraphSide> getGraph() {
      return baseGraph.graph();
    }
    @Override public GraphPoint getStartVertex() {
      return startVertex;
    }
    @Override public double getWeight() {
      return length();
    }
    @Override public Route setPlayer(GamePlayer player) {
      this.player = player;
      this.playerID = player.user().id();
      return this;
    }
    @Override public boolean validate() {
      return false;
    }
    public int getPlayerID() {
      return playerID;
    }
  }

  public class PossibleRoute implements Route {
    private List<GraphSide> sides = new ArrayList<GraphSide>();
    private List<GraphPoint> points = new ArrayList<GraphPoint>();
    private transient BoardGraph boardGraph;
    private GamePlayer player;

    public PossibleRoute(BoardGraph boardGraph, GraphPoint start, GraphPoint second) {
      this.boardGraph = boardGraph;
      points.add(start);
      points.add(second);
      sides.add(getSide(start, second));
    }
    public GamePlayer player() {
      return player;
    }
    public PossibleRoute setPlayer(GamePlayer player) {
      this.player = player;
      return this;
    }
    public PossibleRoute(BoardGraph boardGraph) {
      this.boardGraph = boardGraph;
    }
    @Override public List<GraphSide> getEdgeList() {
      return sides;
    }
    @Override public GraphPoint getEndVertex() {
      return points.get(points.size() - 1);
    }
    public GraphPoint getBeforeEndVertex() {
      return points.get(points.size() - 2);
    }
    @Override public Graph<GraphPoint, GraphSide> getGraph() {
      return boardGraph.graph();
    }
    @Override public GraphPoint getStartVertex() {
      return points.get(0);
    }
    @Override public double getWeight() {
      return sides.size();
    }
    @Override public int length() {
      return sides.size();
    }
    public void addPoint(GraphPoint point) {
      GraphPoint source = getEndVertex();
      points.add(point);
      sides.add(getSide(source, point));
    }
    public boolean contains(GraphPoint source, GraphPoint target) {
      return sides.contains(getSide(source, target));
    }
    public PossibleRoute copy() {
      PossibleRoute copy = new PossibleRoute(boardGraph);
      for (GraphPoint point : points)
        copy.points.add(point);
      for (GraphSide side : sides)
        copy.sides.add(side);
      return copy;
    }
    private GraphSide getSide(GraphPoint source, GraphPoint target) {
      return boardGraph.graph().getEdge(source, target);
    }
    @Override public boolean validate() {
      for (GraphPoint point : points)
        if (point.player() != null && !point.player().equals(player))
          return false;
      return true;
    }
  }
}
