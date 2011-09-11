package org.soc.common.game.pieces;

import org.soc.common.game.GamePlayer;
import org.soc.common.game.board.Board;
import org.soc.common.game.board.HasPoint;
import org.soc.common.game.board.HexPoint;
import org.soc.common.game.pieces.Piece.AbstractPlayerPiece;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;

public class Wall extends AbstractPlayerPiece implements HasPoint {
  private HexPoint pointLocation;

  @Override public Icon icon() {
    return IconImpl.nullIcon();
  }
  @Override public String name() {
    return "Wall";
  }
  @Override public String getLocalizedName() {
    return I.get().constants().wall();
  }
  @Override public String getDescription() {
    return I.get().constants().wallDescription();
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
