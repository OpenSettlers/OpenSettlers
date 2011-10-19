package org.soc.common.game.pieces;

import org.soc.common.game.*;
import org.soc.common.game.board.*;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.views.widgetsInterface.visuals.*;

public class Wall extends AbstractPlayerPiece implements HasPoint {
  private HexPoint pointLocation;

  @Override public HexPoint point() {
    return pointLocation;
  }
  @Override public HasPoint setPoint(HexPoint point) {
    this.pointLocation = point;
    return this;
  }
  @Override public boolean isStockPiece() {
    return true;
  }
  @Override public void addTo(GamePlayer player) {}
  @Override public void removeFrom(GamePlayer player) {
    // TODO Auto-generated method stub
  }
  @Override public boolean affectsRoad() {
    return false;
  }
  @Override public void addToBoard(Board board) {}
  @Override public void removeFromBoard(Board board) {}
  @Override public PieceVisual createPiece(VisualFactory visualFactory) {
    return visualFactory.createWallVisual(this);
  }
}
