package org.soc.common.game.pieces;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.*;
import org.soc.common.game.board.*;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.visuals.*;

public class Wall extends AbstractPlayerPiece implements HasPoint {
  private HexPoint pointLocation;

  @Override public Icon icon() {
    return IconImpl.nullIcon();
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().wall());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().wallDescription());
  }
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
