package org.soc.common.game.pieces;

import org.soc.common.core.property.Properties.Description;
import org.soc.common.core.property.Properties.Name;
import org.soc.common.game.board.*;
import org.soc.common.game.pieces.Piece.AbstractPiece;
import org.soc.common.internationalization.*;
import org.soc.common.views.meta.*;
import org.soc.common.views.widgetsInterface.visuals.*;

public class Pirate extends AbstractPiece {
  private HexLocation location;

  public Pirate() {}
  @Override public Icon icon() {
    return new IconImpl(null, null, null, null);
  }
  @Override public Name name() {
    return new Name.Impl(I.get().constants().pirate());
  }
  @Override public Description description() {
    return new Description.Impl(I.get().constants().pirateDescription());
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
