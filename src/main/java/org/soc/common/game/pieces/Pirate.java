package org.soc.common.game.pieces;

import org.soc.common.game.board.*;
import org.soc.common.game.pieces.Piece.AbstractPiece;
import org.soc.common.views.widgetsInterface.visuals.*;

public class Pirate extends AbstractPiece {
  private HexLocation location;

  public Pirate() {}
  public Pirate(HexLocation hexLocation) {
    this.location = hexLocation;
  }
  public HexLocation getLocation() {
    return location;
  }
  public Pirate setLocation(HexLocation location) {
    this.location = location;
    return this;
  }
  @Override public PieceVisual createPiece(VisualFactory visualFactory) {
    return visualFactory.createPirateVisual(this);
  }
}
