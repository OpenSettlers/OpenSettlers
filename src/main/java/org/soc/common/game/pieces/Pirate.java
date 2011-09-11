package org.soc.common.game.pieces;

import org.soc.common.game.board.HexLocation;
import org.soc.common.game.pieces.Piece.AbstractPiece;
import org.soc.common.internationalization.I;
import org.soc.common.views.meta.Icon;
import org.soc.common.views.meta.IconImpl;
import org.soc.common.views.widgetsInterface.visuals.PieceVisual;
import org.soc.common.views.widgetsInterface.visuals.VisualFactory;

public class Pirate extends AbstractPiece {
  private HexLocation location;

  public Pirate() {}
  @Override public Icon icon() {
    return new IconImpl(null, null, null, null);
  }
  @Override public String getLocalizedName() {
    return I.get().constants().pirate();
  }
  @Override public String getDescription() {
    return I.get().constants().pirateDescription();
  }
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
