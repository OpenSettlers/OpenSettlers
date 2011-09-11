package org.soc.common.game.board;

import java.io.Serializable;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.pieces.Piece.PlayerPiece;

import com.google.gwt.user.client.Random;

public interface GraphPoint extends GraphElement, Serializable {
  public HexPoint hexPoint();
  /* Returns true when a town is allowed to build on it */
  public boolean isTownBuildable();
  public GraphPoint setTownBuildable(boolean townBuildable);
  public HasPoint getPointPiece();

  /** Point usable on a GameBoard, such that it can hold a PlayerPiece */
  public class GraphPointImpl implements GraphPoint {
    private HexPoint pointLocation;
    private transient PlayerPiece playerPiece;
    private boolean townBuildable = true;

    @Override public HexPoint hexPoint() {
      return pointLocation;
    }
    public GraphPoint setPoint(HexPoint point) {
      this.pointLocation = point;
      return this;
    }
    @Override public PlayerPiece getPiece() {
      return playerPiece;
    }
    @Override public GamePlayer player() {
      return playerPiece == null ? null : playerPiece.player();
    }
    @Override public GraphElement setPlayerPiece(PlayerPiece piece) {
      this.playerPiece = piece;
      return this;
    }
    @Override public boolean isTownBuildable() {
      return townBuildable;
    }
    @Override public GraphPoint setTownBuildable(boolean townBuildable) {
      this.townBuildable = townBuildable;
      return this;
    }
    @Override public int hashCode() {
      return pointLocation.hashCode();
    }
    @Override public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (obj instanceof HexPoint) {
        HexPoint hexPoint = (HexPoint) obj;
        return hexPoint.equals(pointLocation);
      }
      if (getClass() != obj.getClass())
        return false;
      GraphPointImpl other = (GraphPointImpl) obj;
      return other.hexPoint().equals(pointLocation);
    }
    @Override public HasPoint getPointPiece() {
      return (HasPoint) playerPiece;
    }
  }

  /** Represents a splitted end point of a subgraph. When a town is built on an existing route of a
   * different player, this point would be added twice to the routes' list of vertices. This
   * violates the constraint of graphs that each point should be unique and never be added twice. A
   * SplittedEndPoint becomes the endpoint of the two roads connecting to the point now owned by the
   * player building a town on the existing road. */
  public class BlockedEndPoint implements GraphPoint {
    private HexPoint pointLocation;
    private int hashCode = Random.nextInt();

    @Override public HexPoint hexPoint() {
      return pointLocation;
    }
    public BlockedEndPoint(HexPoint pointLocation) {
      this.pointLocation = pointLocation;
    }
    /** Returns false, since a splittedEndPoint is created because the original point is causing a
     * split */
    @Override public boolean isTownBuildable() {
      return false;
    }
    @Override public GraphPoint setTownBuildable(boolean townBuildable) {
      return this;
    }
    @Override public PlayerPiece getPiece() {
      return null;
    }
    @Override public GamePlayer player() {
      return null;
    }
    @Override public GraphElement setPlayerPiece(PlayerPiece piece) {
      return null;
    }
    @Override public int hashCode() {
      return hashCode;
    }
    @Override public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      BlockedEndPoint other = (BlockedEndPoint) obj;
      if (hashCode != other.hashCode())
        return false;
      return true;
    }
    @Override public HasPoint getPointPiece() {
      return null;
    }
  }
}
