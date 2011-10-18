package org.soc.common.game.pieces;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.Resources.ResourceList;
import org.soc.common.game.board.*;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.visuals.*;

import static org.soc.common.game.Resource.*;

import static org.soc.common.game.Resources.*;

public class Ship extends AbstractPlayerPiece<Integer> implements HasSide {
  private HexSide sideLocation;

  @Override public Icon icon() {
    return new IconImpl(null, null, null, null);
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().ship());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().shipDescription());
  }
  @Override public boolean canBuild(Board board, GamePlayer player) {
    if (player.stock().ships().size() == 0)
      return false;
    // TODO: port to java
    // if (GetShipBuildPlaces(game, board).Count == 0) return false;
    return true;
  }
  @Override public ResourceList cost() {
    return newResources(timber, sheep);
  }
  // TODO: port to java
  public boolean canMove(Board board, GamePlayer player)
  {
    // If there is no ship to move, bugger out
    // if (player.getShips().size() == 0)
    // return false;
    // when can only move a ship once per turn
    // var movedShip = (from MoveShipAction ms in
    // game.GameLog.OfType<MoveShipAction>()
    // where ms.TurnID == game.CurrentTurn
    // select ms).SingleOrDefault();
    // if (movedShip != null)
    // return false;
    // We should be able to have a moveable ship, and a location to put the
    // moved ship
    // in order to be able to move te ship
    // if (GetMoveableShips(game, game.Board).Count == 0)
    // return false;
    return true;
  }
  @Override public boolean isStockPiece() {
    return true;
  }
  @Override public void addTo(GamePlayer player) {
    player.ships().move(player.stock().ships(), this);
    player.sidePieces().add(this);
  }
  @Override public void removeFrom(GamePlayer player) {
    // TODO Auto-generated method stub
  }
  @Override public HexSide getSide() {
    return sideLocation;
  }
  @Override public HasSide setSide(HexSide side) {
    this.sideLocation = side;
    return this;
  }
  @Override public boolean affectsRoad() {
    return true;
  }
  @Override public void addToBoard(Board board) {}
  @Override public void removeFromBoard(Board board) {}
  @Override public boolean canConnect(GraphPoint graphPoint, HasSide otherPiece) {
    return (player.equals(graphPoint.player()) || graphPoint.player() == null)
            && otherPiece.connectsWithShip();
  }
  @Override public boolean connectsWithBridge() {
    return false;
  }
  @Override public boolean connectsWithRoad() {
    return false;
  }
  @Override public boolean connectsWithShip() {
    return true;
  }
  @Override public PieceVisual createPiece(VisualFactory visualFactory) {
    return visualFactory.createShipVisual(this);
  }
}
